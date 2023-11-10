/* eslint-disable react/prop-types */
import { DeleteIcon, EditIcon } from "@chakra-ui/icons";
import { ButtonGroup, IconButton, Td, Tr } from "@chakra-ui/react";
import { Link } from "react-router-dom";

export default function ServicosItem({ user, onUpdate, onDelete }) {
  return (
    <Tr>
      <Td textColor="white">{user.id}</Td>
      <Td textColor="white">{user.nome}</Td>
      <Td textColor="white">{user.valor}</Td>
      <Td>
        <ButtonGroup>
          <IconButton
            size="sm"
            onClick={onUpdate}
            icon={<EditIcon />}
            as={Link}
            to={`/update/servico/${user.id}`}
          />
          <IconButton size="sm" onClick={onDelete} icon={<DeleteIcon />} />
        </ButtonGroup>
      </Td>
    </Tr>
  );
}
