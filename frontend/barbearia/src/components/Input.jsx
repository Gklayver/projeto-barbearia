/* eslint-disable react/prop-types */
import {
  Input as ChakraInput,
  FormControl,
  FormErrorMessage,
  FormLabel,
} from "@chakra-ui/react";
import { forwardRef } from "react";

const InputBase = (
  { error, name, label, colorLabel = "white", ...rest },
  ref
) => (
  <FormControl isInvalid={!!error}>
    <FormLabel color={colorLabel}>{label}</FormLabel>

    <ChakraInput
      size="lg"
      bgColor="white"
      focusBorderColor="yellow"
      ref={ref}
      name={name}
      _hover={{
        bgColor: "gray.100",
      }}
      {...rest}
    />

    {!!error && <FormErrorMessage>{error.message}</FormErrorMessage>}
  </FormControl>
);

export const Input = forwardRef(InputBase);
