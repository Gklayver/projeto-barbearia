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
import { Link } from "react-router-dom";
import ModalConfirm from "../components/ModalConfirm";
import ServicosItem from "../components/ServicoItem";
import Style from "../components/Style";
import { api } from "../services/api";

export default function ListServicos() {
  const [users, setUsers] = useState([]);
  const [isOpen, setIsOpen] = useState(false);
  const [selectedUser, setSelectedUser] = useState(null);

  async function fetchUsers() {
    const response = await api.get("/servicos");
    setUsers(response.data);
  }

  async function handleRemoveUser() {
    await api.delete(`/servicos/${selectedUser}`);
    await fetchUsers();
  }

  function onCancelModel() {
    setIsOpen(false);
  }

  function onOpenModel(userId) {
    setIsOpen(true);
    setSelectedUser(userId);
  }

  useEffect(() => {
    fetchUsers();
  }, []);

  const ListUsers = users.map((user) => {
    return (
      <ServicosItem
        user={user}
        key={user.id}
        onDelete={() => onOpenModel(user.id)}
      />
    );
  });

  return (
    <Style title="Lista de Serviços" textColor="white">
      <Flex bgColor="red">
        <HStack spacing="4">
          <Button as={Link} to={`/servicos`} type="submit" colorScheme="green">
            Cadastrar
          </Button>
        </HStack>
      </Flex>
      <TableContainer>
        <ModalConfirm
          title="Deseja remover o serviço ?"
          onConfirm={handleRemoveUser}
          isOpen={isOpen}
          onClose={onCancelModel}
        />
        <Table variant="simple">
          <Thead>
            <Tr>
              <Th textColor="white">ID</Th>
              <Th textColor="white">Serviço</Th>
              <Th textColor="white">Valor</Th>
              <Th textColor="white">Opções</Th>
            </Tr>
          </Thead>
          <Tbody>{ListUsers}</Tbody>
        </Table>
      </TableContainer>
    </Style>
  );
}
