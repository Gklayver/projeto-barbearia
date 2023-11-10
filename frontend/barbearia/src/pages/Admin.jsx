import { SimpleGrid, VStack } from "@chakra-ui/react";

import Form from "../components/Form";
import { Input } from "../components/input";
import toast from "react-hot-toast";
import { api } from "../services/api";
import { useState } from "react";

export default function Admin() {
  const [login, setLogin] = useState();
  const [email, setEmail] = useState();
  const [password, setPassword] = useState();
  const [name, setName] = useState();

  const onSubmit = async (e) => {
    e.preventDefault();
    const data = {
      nome: name,
      email,
      login,
      senha: password,
    };
    toast.promise(api.post("/administradores", data), {
      loading: "Carregando...",
      success: <b>Cadastrado!</b>,
      error: <b>Nao foi possivel cadastrar.</b>,
    });
  };

  return (
    <Form title="Cadastro Administrador" onSubmit={onSubmit}>
      <VStack spacing="8">
        <SimpleGrid
          w={["300px", "400px", "500px", "600px"]}
          spacing={["6", "8"]}
        >
          <Input
            name="name"
            label="Nome completo"
            onChange={(e) => setName(e.target.value)}
            required
          />
          <Input
            type="email"
            name="email"
            label="E-mail"
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </SimpleGrid>

        <SimpleGrid
          w={["300px", "400px", "500px", "600px"]}
          spacing={["6", "8"]}
        >
          <Input
            name="login"
            label="Login"
            onChange={(e) => setLogin(e.target.value)}
            required
          />
          <Input
            type="password"
            name="password"
            label="Senha"
            onChange={(e) => setPassword(e.target.value)}
            required
          />
          <Input
            type="password"
            name="password_confirmation"
            label="Confirmação da senha"
            required
          />
        </SimpleGrid>
      </VStack>
    </Form>
  );
}
