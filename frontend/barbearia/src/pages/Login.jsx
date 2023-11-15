import { Button, Flex, Heading, Image, Stack } from "@chakra-ui/react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Input } from "../components/input";
import { api } from "../services/api";

export default function Login() {
  const [login, setLogin] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  async function hendleLogin(e) {
    e.preventDefault();
    try {
      await api.get(`/administradores/login?login=${login}`);
      navigate("/agendamentos");
    } catch (error) {
      console.log(error);
    }
  }
  return (
    <Flex
      w="100vw"
      h="100vh"
      align="center"
      justify="center"
      direction={{
        sm: "column",
        md: "row",
      }}
    >
      <Flex
        flex
        align="center"
        justify="center"
        w={{
          xl: "50%",
        }}
        p={8}
      >
        <Stack
          spacing={"4"}
          w={["300px", "400px", "500px"]}
          as="form"
          onSubmit={hendleLogin}
        >
          <Heading fontSize="2xl" textAlign="Center">
            Bem-vindo!
          </Heading>
          <Input
            type="text"
            colorLabel="black"
            name="login"
            value={login}
            label="Login"
            onChange={(e) => setLogin(e.target.value)}
          />
          <Input
            type="password"
            colorLabel="black"
            name="password"
            value={password}
            label="Senha"
            onChange={(e) => setPassword(e.target.value)}
          />

          <Stack spacing={"6"}>
            <Button colorScheme="yellow" variant="solid" type="Submit">
              Entrar
            </Button>
          </Stack>
        </Stack>
      </Flex>
      <Flex
        minH={{
          base: 0,
          lg: "100vh",
        }}
      >
        <Image
          display={{
            base: "none",
            xl: "block",
          }}
          alt="Login Image"
          objectFit="cover"
          src={
            "https://media.istockphoto.com/id/506513408/pt/foto/fazendo-penteado-olha-perfeito.jpg?s=1024x1024&w=is&k=20&c=rQqp7Ig-voKVEhqF9UZ7Mrs3uN24cncMPKTibCP9XpM="
          }
        />
      </Flex>
    </Flex>
  );
}
