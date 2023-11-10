import {
  Button,
  Divider,
  Flex,
  HStack,
  Heading,
  VStack,
} from "@chakra-ui/react";
import { Toaster } from "react-hot-toast";

// eslint-disable-next-line react/prop-types
export default function Form({ title, children, onSubmit }) {
  return (
    <>
      <Toaster />
      <Flex
        onSubmit={onSubmit}
        direction="column"
        as="form"
        h="100vh"
        bg="gray.800"
        p={["6", "8"]}
        borderRadius={8}
      >
        <Heading size="lg" fontWeight="normal" color="white" textAlign="center">
          {title}
        </Heading>

        <Divider my="6" borderColor="yellow" />
        <Flex direction="column" justifyContent="space-between" flex="1">
          <VStack spacing="8">{children}</VStack>

          <Flex mt="8" justify="center">
            <HStack spacing="4">
              <Button type="submit" colorScheme="yellow">
                Salvar
              </Button>
            </HStack>
          </Flex>
        </Flex>
      </Flex>
    </>
  );
}
