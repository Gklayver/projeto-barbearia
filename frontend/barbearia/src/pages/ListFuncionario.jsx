import {
  Button,
  Flex,
  HStack,
  Table,
  TableContainer,
  Tbody,
  Th,
  Thead,
  Tr,
} from "@chakra-ui/react";
import { useEffect, useState } from "react";
import FuncionarioItem from "../components/FuncionarioItem";
import ModalConfirm from "../components/ModalConfirm";
import { api } from "../services/api";
import Style from "../components/Style";
import { Link } from "react-router-dom";

export default function ListFuncionarios() {
  const [funcionario, setFuncionarios] = useState([]);
  const [isOpen, setIsOpen] = useState(false);
  const [selectedFuncionario, setSelectedFuncionario] = useState(null);

  async function fetchFuncionario() {
    const response = await api.get("/funcionarios");
    setFuncionarios(response.data);
  }

  async function handleRemoveFuncionario() {
    await api.delete(`/funcionarios/${selectedFuncionario}`);
    await fetchFuncionario();
  }

  function onCancelModel() {
    setIsOpen(false);
  }

  function onOpenModel(funcionarioId) {
    setIsOpen(true);
    setSelectedFuncionario(funcionarioId);
  }

  useEffect(() => {
    fetchFuncionario();
  }, []);

  const ListFuncionarios = funcionario.map((funcionario) => {
    return (
      <FuncionarioItem
        funcionario={funcionario}
        key={funcionario.id}
        onDelete={() => onOpenModel(funcionario.id)}
      />
    );
  });

  return (
    <Style title="Lista de Funcionario" textColor="white">
      <Flex display="flex" justifyContent="flex-end">
        <HStack spacing="4">
          <Button
            as={Link}
            to={`/funcionarios`}
            type="submit"
            colorScheme="green"
          >
            Cadastrar
          </Button>
        </HStack>
      </Flex>
      <TableContainer>
        <ModalConfirm
          title="Deseja remover o Funcionario?"
          onConfirm={handleRemoveFuncionario}
          isOpen={isOpen}
          onClose={onCancelModel}
        />
        <Table variant="simple">
          <Thead>
            <Tr>
              <Th textColor="white">ID</Th>
              <Th textColor="white">Nome</Th>
              <Th textColor="white">Cargo</Th>
              <Th textColor="white">Opções</Th>
            </Tr>
          </Thead>
          <Tbody>{ListFuncionarios}</Tbody>
        </Table>
      </TableContainer>
    </Style>
  );
}
