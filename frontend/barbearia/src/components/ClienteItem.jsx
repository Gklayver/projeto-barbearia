/* eslint-disable react/prop-types */
import { DeleteIcon, EditIcon } from "@chakra-ui/icons";
import { ButtonGroup, IconButton, Td, Tr } from "@chakra-ui/react";
import { Link } from "react-router-dom";

export default function ClienteItem({ client, onUpdate, onDelete }) {
  return (
    <Tr>
      <Td textColor="white">{client.id}</Td>
      <Td textColor="white">{client.nome}</Td>
      <Td textColor="white">{client.email}</Td>
      <Td>
        <ButtonGroup>
          <IconButton
            size="sm"
            onClick={onUpdate}
            icon={<EditIcon />}
            as={Link}
            to={`/update/cliente/${client.id}`}
          />
          <IconButton size="sm" onClick={onDelete} icon={<DeleteIcon />} />
        </ButtonGroup>
      </Td>
    </Tr>
  );
}
