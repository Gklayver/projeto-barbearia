// eslint-disable-next-line no-unused-vars
import React, { useCallback, useState } from "react";

import { Calendar } from "@natscale/react-calendar";

import "@natscale/react-calendar/dist/main.css";
import Style from "../components/Style";
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
  VStack,
} from "@chakra-ui/react";
import { Link } from "react-router-dom";
import ModalConfirm from "../components/ModalConfirm";

export default function Basic() {
  const [value, setValue] = useState(new Date());

  const onChange = useCallback(
    (val) => {
      setValue(val);
      console.log(val);
    },
    [setValue]
  );

  return (
    <Style title="Agendamentos">
      <Flex></Flex>
      <br />
      <VStack>
        <TableContainer>
          <ModalConfirm
          //   title="Deseja remover o cliente?"
          //   onConfirm={handleRemoveClient}
          //   isOpen={isOpen}
          //   onClose={onCancelModel}
          />
          <Table variant="simple">
            <Thead>
              <Tr>
                <Th textColor="white">Hora</Th>
                <Th textColor="white">Nome</Th>
                <Th textColor="white">Barbeiro</Th>
                <Th textColor="white">Opções</Th>
              </Tr>
            </Thead>
            <Tbody>{}</Tbody>
          </Table>
        </TableContainer>
      </VStack>
      <VStack>
        <Calendar value={value} onChange={onChange} />;
        <HStack spacing="4">
          <Button as={Link} to={``} type="submit" colorScheme="green">
            Agendar
          </Button>
        </HStack>
      </VStack>
    </Style>
  );
}
