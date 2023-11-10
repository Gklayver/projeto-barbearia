import { FormLabel, Select, SimpleGrid, VStack } from "@chakra-ui/react";

import { useState } from "react";
import Form from "../components/Form";
import { Input } from "../components/input";
import { api } from "../services/api";
import { phoneMask } from "../utils/masks";
import { removeMask } from "../utils/removeMask";
import toast from "react-hot-toast";

export default function Cliente() {
  const [phone, setPhone] = useState();
  const [email, setEmail] = useState();
  const [gender, setGender] = useState();
  const [name, setName] = useState();

  const handleInputMask = (e) => {
    setPhone(phoneMask(e));
  };
  const onSubmit = async (e) => {
    e.preventDefault();
    const data = {
      telefone: removeMask(phone.target.value),
      email,
      genero: gender,
      nome: name,
    };
    toast.promise(api.post("/clientes", data), {
      loading: "Carregando...",
      success: <b>Cadastrado!</b>,
      error: <b>Nao foi possivel cadastrar.</b>,
    });
  };

  return (
    <Form title="Cadastro cliente" onSubmit={onSubmit}>
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
            onChange={(e) => setGender(e.target.value)}
            name="gender"
            w={["300px", "400px", "500px", "600px"]}
            placeholder="Selecione o Genêro"
            backgroundColor="white"
            textColor="black"
            required
          >
            <option value="M">Masculino</option>
            <option value="F">Feminino</option>
            <option value="O">Outros</option>
          </Select>
          <br></br>
          <Input
            defaultValue={phone}
            onKeyUp={handleInputMask}
            name="phone"
            type="text"
            placeholder="(xx) xxxxx-xxxx"
            label="Celular"
            required
          />
        </FormLabel>
      </VStack>
    </Form>
  );
}
