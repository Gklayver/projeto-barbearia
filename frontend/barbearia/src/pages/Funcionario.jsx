import { FormLabel, Select, SimpleGrid, VStack } from "@chakra-ui/react";

import Form from "../components/Form";
import { Input } from "../components/input";
import toast from "react-hot-toast";
import { useState } from "react";
import { api } from "../services/api";

export default function Funcionario() {
  const [name, setName] = useState();
  const [email, setEmail] = useState();
  const [cargo, setCargo] = useState();
  const [gender, setGender] = useState();
  const [login, setLogin] = useState();
  const [password, setPassword] = useState();

  const onSubmit = async (e) => {
    e.preventDefault();
    const data = {
      nome: name,
      email,
      cargo,
      genero: gender,
      login,
      senha: password,
    };

    toast.promise(api.post("/funcionarios", data), {
      loading: "Carregando...",
      success: <b>Cadastrado!</b>,
      error: <b>Nao foi possivel cadastrar.</b>,
    });
  };
  const handleCargoChange = (e) => {
    setCargo(e.target.value);
  };

  return (
    <Form title="Cadastro funcionario" onSubmit={onSubmit}>
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

        <FormLabel>
          <FormLabel color="white">Genero</FormLabel>
          <Select
            value={gender}
            name="gender"
            w={["300px", "400px", "500px", "600px"]}
            placeholder="Selecione o Genêro"
            backgroundColor="white"
            textColor="black"
            onChange={(e) => setGender(e.target.value)}
            required
          >
            <option value="M">Masculino</option>
            <option value="F">Feminino</option>
            <option value="O">Outros</option>
          </Select>
        </FormLabel>

        <FormLabel>
          <FormLabel color="white">Cargo</FormLabel>
          <Select
            name="cargo"
            w={["300px", "400px", "500px", "600px"]}
            placeholder="Selecione o cargo"
            backgroundColor="white"
            textColor="black"
            onChange={handleCargoChange}
            required
          >
            <option value="A">Atendente</option>
            <option value="B">Barbeiro</option>
          </Select>
        </FormLabel>

        {cargo === "A" && (
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
        )}
      </VStack>
    </Form>
  );
}
