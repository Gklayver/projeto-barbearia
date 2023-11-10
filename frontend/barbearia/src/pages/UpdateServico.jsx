import { SimpleGrid, VStack } from "@chakra-ui/react";

import { useEffect, useState } from "react";
import toast from "react-hot-toast";
import { useParams } from "react-router-dom";
import Form from "../components/Form";
import { Input } from "../components/input";
import { api } from "../services/api";

export default function UpdateCliente() {
  const [name, setName] = useState();
  const [valor, setValor] = useState();

  const params = useParams();

  const onSubmit = async (e) => {
    e.preventDefault();
    const data = {
      nome: name,
      valor: parseFloat(valor.replace(",", ".")),
    };

    toast.promise(api.put(`/servicos/${params.id}`, data), {
      loading: "Carregando...",
      success: <b>Atualizado!</b>,
      error: <b>Nao foi possivel Atualizar.</b>,
    });
  };

  async function fetchUserById() {
    const { data } = await api.get(`/servicos/${params.id}`);
    setName(data.nome);
    setValor(data.valor);
  }

  useEffect(() => {
    if (params.id) fetchUserById();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [params]);

  return (
    <Form title="Atualizar servicos" onSubmit={onSubmit}>
      <VStack spacing="8">
        <SimpleGrid
          w={["300px", "400px", "500px", "600px"]}
          spacing={["6", "8"]}
        >
          <Input
            name="service"
            label="Servico"
            required
            onChange={(e) => setName(e.target.value)}
            value={name}
          />
          <Input
            name="valor"
            label="Valor"
            type="text"
            onChange={(e) => setValor(e.target.value)}
            value={valor}
            required
          />
        </SimpleGrid>
      </VStack>
    </Form>
  );
}
