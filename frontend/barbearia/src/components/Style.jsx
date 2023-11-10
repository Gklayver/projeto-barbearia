import { Divider, Flex, Heading } from "@chakra-ui/react";

// eslint-disable-next-line react/prop-types
export default function Style({ title, children }) {
  return (
    <Flex
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
      {children}
    </Flex>
  );
}
