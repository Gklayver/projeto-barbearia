import { ChakraProvider } from "@chakra-ui/react";
import { Route, Routes } from "react-router-dom";
import Admin from "./pages/Admin";
import Login from "./pages/Login";
//import RequireAuth from "./pages/RequireAuth";
import SimpleSidebar from "./components/Sidebar";
import Cliente from "./pages/Cliente";
import Funcionario from "./pages/Funcionario";
import ListClient from "./pages/ListClientes";
import ListFuncionario from "./pages/ListFuncionario";
import ListServicos from "./pages/ListServico";
import Servicos from "./pages/Servicos";
import UpdateCliente from "./pages/UpdateCliente";
import UpdateFuncionario from "./pages/UpdateFuncionario";
import UpdateServico from "./pages/UpdateServico";
import Agendamento from "./pages/Agendamento";

function App() {
  return (
    <ChakraProvider>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route element={<SimpleSidebar />}>
          <Route
            path="/admin"
            element={
              // <RequireAuth>
              <Admin />
              // </RequireAuth>
            }
          />
          <Route path="/funcionarios" element={<Funcionario />} />
          <Route path="/clientes" element={<Cliente />} />
          <Route path="/servicos" element={<Servicos />} />
          <Route path="/agendamentos" element={<Agendamento />} />
          <Route path="/clientes/list" element={<ListClient />} />
          <Route path="/funcionarios/list" element={<ListFuncionario />} />
          <Route path="/servicos/list" element={<ListServicos />} />
          <Route path="/update/cliente/:id" element={<UpdateCliente />} />
          <Route
            path="/update/funcionario/:id"
            element={<UpdateFuncionario />}
          />
          <Route path="/update/servico/:id" element={<UpdateServico />} />
        </Route>
      </Routes>
    </ChakraProvider>
  );
}

export default App;
