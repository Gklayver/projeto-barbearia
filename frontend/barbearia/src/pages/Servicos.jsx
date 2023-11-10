import { SimpleGrid, VStack } from "@chakra-ui/react";
import Form from "../components/Form";
import { Input } from "../components/input";
import { api } from "../services/api";
import toast from "react-hot-toast";
import { useState } from "react";

export default function Servicos() {
  const [name, setName] = useState();
  const [valor, setValor] = useState();

  const onSubmit = async (e) => {
    e.preventDefault();
    const data = {
      nome: name,
      valor: parseFloat(valor.replace(",", ".")),
    };

    toast.promise(api.post("/servicos", data), {
      loading: "Carregando...",
      success: <b>Cadastrado!</b>,
      error: <b>Nao foi possivel cadastrar.</b>,
    });
  };

  return (
    <Form title="Cadastrar servicos" onSubmit={onSubmit}>
      <VStack spacing="8">
        <SimpleGrid
          w={["300px", "400px", "500px", "600px"]}
          spacing={["6", "8"]}
        >
          <Input
            name="servicos"
            label="Servico"
            required
            onChange={(e) => setName(e.target.value)}
          />
          <Input
            name="valor"
            label="Valor"
            type="text"
            onChange={(e) => setValor(e.target.value)}
            required
          />
        </SimpleGrid>
      </VStack>
    </Form>
  );
}
