import { FormLabel, Select, SimpleGrid, VStack } from "@chakra-ui/react";

import { useEffect, useState } from "react";
import toast from "react-hot-toast";
import { useParams } from "react-router-dom";
import Form from "../components/Form";
import { Input } from "../components/input";
import { api } from "../services/api";

export default function UpdateCliente() {
  const [name, setName] = useState();
  const [email, setEmail] = useState();
  const [cargo, setCargo] = useState();
  const [gender, setGender] = useState();
  const [login, setLogin] = useState();
  const [password, setPassword] = useState();
  const params = useParams();

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

    toast.promise(api.put(`/funcionarios/${params.id}`, data), {
      loading: "Carregando...",
      success: <b>Atualizado!</b>,
      error: <b>Nao foi possivel Atualizar.</b>,
    });
  };

  const handleCargoChange = (e) => {
    setCargo(e.target.value);
  };

  async function fetchUserById() {
    const { data } = await api.get(`/funcionarios/${params.id}`);
    setName(data.nome);
    setEmail(data.email);
    setCargo(data.cargo);
    setGender(data.genero);
    setLogin(data.login);
    setPassword(data.senha);
  }

  useEffect(() => {
    if (params.id) fetchUserById();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [params]);

  return (
    <Form title="Atualizar funcionario" onSubmit={onSubmit}>
      <VStack spacing="8">
        <SimpleGrid
          w={["300px", "400px", "500px", "600px"]}
          spacing={["6", "8"]}
        >
          <Input
            name="name"
            label="Nome completo"
            value={name}
            onChange={(e) => setName(e.target.value)}
            required
          />
          <Input
            type="email"
            name="email"
            label="E-mail"
            value={email}
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
            value={cargo}
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
              value={login}
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
