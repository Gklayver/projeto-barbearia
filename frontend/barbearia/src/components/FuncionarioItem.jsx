/* eslint-disable react/prop-types */
import { DeleteIcon, EditIcon } from "@chakra-ui/icons";
import { ButtonGroup, IconButton, Td, Tr } from "@chakra-ui/react";
import { Link } from "react-router-dom";

export default function FuncionarioItem({ funcionario, onUpdate, onDelete }) {
  function getOffice(office) {
    if (office === "A") {
      return "Atendente";
    }
    return "Barbeiro";
  }

  return (
    <Tr>
      <Td textColor="white">{funcionario.id}</Td>
      <Td textColor="white">{funcionario.nome}</Td>
      <Td textColor="white">{getOffice(funcionario.cargo)}</Td>
      <Td>
        <ButtonGroup>
          <IconButton
            size="sm"
            as={Link}
            to={`/update/funcionario/${funcionario.id}`}
            onClick={onUpdate}
            icon={<EditIcon />}
          />
          <IconButton size="sm" onClick={onDelete} icon={<DeleteIcon />} />
        </ButtonGroup>
      </Td>
    </Tr>
  );
}
