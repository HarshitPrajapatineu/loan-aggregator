import { Route, Routes } from "react-router-dom";
import './App.css';
import Login from './Pages/Login/Login'
import EditUser from './Pages/EditUser/EditUser'
import ViewDetails from './Pages/ViewDetails/ViewDetails'
import DataTable from './Component/DataTable/DataTable'
import PrivateRoutes from './Util/PrivateRoutes';
import Register from "./Pages/Register/Register";
import ViewUser from "./Pages/ViewUser/ViewUser";
import { getRole } from "./Util/helper";

function App() {
  return (
    <Routes>
    <Route path="/login" element={<Login />} />
    <Route path="/register" element={<Register />} />
    <Route path="/" element={<PrivateRoutes allowedRoles={["Administrator","client"]} showviewUser = {getRole() === "Administrator"} />}>
      <Route path="/" element={<DataTable />} />
      <Route path="/editUser/:username" element={<EditUser />} />
    </Route>
    <Route path="/" element={<PrivateRoutes allowedRoles={["Administrator"]}/>}>
      <Route path="/viewUser" element={<ViewUser />} />
    </Route>
    </Routes>
  );
}

export default App;
