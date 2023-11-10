import { Table, TableContainer, Tbody, Th, Thead, Tr } from "@chakra-ui/react";
import { useEffect, useState } from "react";
import ClienteItem from "../components/ClienteItem";
import ModalConfirm from "../components/ModalConfirm";
import { api } from "../services/api";
import Style from "../components/Style";

export default function ListClient() {
  const [client, setClient] = useState([]);
  const [isOpen, setIsOpen] = useState(false);
  const [selecteClient, setselecteClient] = useState(null);

  async function fetchClient() {
    const response = await api.get("/clientes");
    setClient(response.data);
  }

  async function handleRemoveClient() {
    await api.delete(`/clientes/${selecteClient}`);
    await fetchClient();
  }

  function onCancelModel() {
    setIsOpen(false);
  }

  function onOpenModel(clientId) {
    setIsOpen(true);
    setselecteClient(clientId);
  }

  useEffect(() => {
    fetchClient();
  }, []);

  const ListClient = client.map((client) => {
    return (
      <ClienteItem
        client={client}
        key={client.id}
        onDelete={() => onOpenModel(client.id)}
      />
    );
  });

  return (
    <Style title="Lista de clientes" textColor="white">
      <TableContainer>
        <ModalConfirm
          title="Deseja remover o cliente?"
          onConfirm={handleRemoveClient}
          isOpen={isOpen}
          onClose={onCancelModel}
        />
        <Table variant="simple">
          <Thead>
            <Tr>
              <Th textColor="white">ID</Th>
              <Th textColor="white">Nome</Th>
              <Th textColor="white">Email</Th>
              <Th textColor="white">Opções</Th>
            </Tr>
          </Thead>
          <Tbody>{ListClient}</Tbody>
        </Table>
      </TableContainer>
    </Style>
  );
}
