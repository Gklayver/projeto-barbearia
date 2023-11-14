// eslint-disable-next-line no-unused-vars
import React, { useCallback, useEffect, useState } from "react";

import { Calendar } from "@natscale/react-calendar";

import { DeleteIcon, TimeIcon } from "@chakra-ui/icons";
import { Flex, HStack, IconButton, Text } from "@chakra-ui/react";
import "@natscale/react-calendar/dist/main.css";
import { AgendamentoModal } from "../components/AgendamentoModal";
import Style from "../components/Style";
import { api } from "../services/api";
import { formatPrice } from "../utils/formatPrice";

export default function Basic() {
  const [dataValue, setDataValue] = useState(new Date());
  const [isOpen, setIsOpen] = useState(false);
  const [horarios, setHorarios] = useState([]);
  const [agendamentos, setAgendamentos] = useState([]);

  const onChange = useCallback(
    (val) => {
      setDataValue(val);
      setIsOpen(true);
      console.log(new Date(val).get);
    },
    [setDataValue]
  );
  function formatarData(value) {
    const data = new Date(value);
    const ano = data.getFullYear();
    const mes = (data.getMonth() + 1).toString().padStart(2, "0"); // Mês começa do zero
    const dia = data.getDate().toString().padStart(2, "0");

    return `${ano}-${mes}-${dia}`;
  }
  function onClose() {
    setIsOpen(false);
  }
  async function fetchHorarios(date) {
    const formattedDate = formatarData(date);
    const response = await api.get(`/agendamentos/${formattedDate}`);
    return response.data;
  }

  async function gerarRangeDeHorarios() {
    const data = [];

    const horariosIndisponiveis = await fetchHorarios(dataValue);
    for (let hora = 8; hora <= 18; hora++) {
      const formato12Horas = hora > 12 ? hora - 12 : hora;
      const horarioFormatado = `${formato12Horas
        .toString()
        .padStart(2, "0")}:00`;
      data.push({ value: horarioFormatado, disponivel: true });
    }
    const result = data.map((item) => ({
      ...item,
      disponivel: horariosIndisponiveis.find(
        (horario) =>
          horario.hora === `${item.value}:00` &&
          horario.data === formatarData(dataValue)
      ),
    }));
    setHorarios(result);
  }

  async function removerAgendamento(id) {
    await api.delete(`/agendamentos/${id}`);
    setAgendamentos((state) => state.filter((item) => item.id !== id));
  }

  async function criarAgendamentos(data) {
    const payload = {
      data: formatarData(dataValue),
      hora: `${data.hora}:00`,
      idCliente: Number(data.clienteId),
      idFuncionario: Number(data.barbeiroId),
      servicos: [data.servicos],
    };
    await api.post("/agendamentos", payload);
    onClose();
  }
  function obterNomeDoDia(data) {
    const diasDaSemana = [
      "Domingo",
      "Segunda-feira",
      "Terça-feira",
      "Quarta-feira",
      "Quinta-feira",
      "Sexta-feira",
      "Sábado",
    ];

    const dataObjeto = new Date(data);
    const indiceDoDia = dataObjeto.getDay();

    return diasDaSemana[indiceDoDia];
  }
  useEffect(() => {
    fetchHorarios(dataValue).then((response) => setAgendamentos(response));
  }, [dataValue]);

  useEffect(() => {
    gerarRangeDeHorarios();
  }, [dataValue]);
  return (
    <>
      <AgendamentoModal
        isOpen={isOpen}
        onClose={onClose}
        horarios={horarios}
        onConfirm={criarAgendamentos}
      />
      <Style title="Agendamentos">
        <HStack color="white">
          <Flex h="full" flex="1" direction="column">
            <Text fontSize="2xl">Horarios Agendados</Text>
            <HStack>
              <Text pr="3" borderRight="1px">
                {dataValue.getDate().toString().padStart(2, "0")}
              </Text>

              <Text>{obterNomeDoDia(dataValue)}</Text>
            </HStack>
            <Flex gap="4" mt="8" direction="column">
              {agendamentos.map((horario) => (
                <Flex
                  p="4"
                  borderRadius="md"
                  flex="1"
                  bg="gray.600"
                  justify="space-around"
                  align="center"
                  w="full"
                  key={horario.id}
                >
                  <Flex
                    flex="1"
                    justify="center"
                    align="center"
                    direction="column"
                  >
                    <Text>{horario.servicos[0].nome}</Text>
                    <Text>{formatPrice(horario.servicos[0].valor)}</Text>
                  </Flex>
                  <Flex flex="1" justify="space-evenly" align="center">
                    <TimeIcon fontSize="2xl" /> <Text>{horario.hora}</Text>
                  </Flex>
                  <IconButton
                    aria-label="Excluir agendamento"
                    icon={<DeleteIcon />}
                    onClick={() => removerAgendamento(horario.id)}
                  />
                </Flex>
              ))}
            </Flex>
          </Flex>
          <Flex justify="flex-end" flex="1">
            <Calendar value={dataValue} onChange={onChange} />
          </Flex>
        </HStack>
      </Style>
    </>
  );
}
