import Paper from "@mui/material/Paper";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import axios from "axios";
import { useCallback, useEffect, useMemo, useState } from "react";
import { useNavigate } from "react-router-dom";


const ViewUser = () => {
  console.log("sdsdfsdfsd")
  const navigate = useNavigate();
  const [users, setUsers] = useState();
  const [link, setLink] = useState("");
  const [formData, setFormData] = useState({ "amount": "100000", "duration": "5", "loantype": "home" });
  const inputFieldClasses =
    "block w-full px-4 py-2 mt-2 text-purple-700 bg-white border rounded-md focus:border-purple-400 focus:ring-purple-300 focus:outline-none focus:ring focus:ring-opacity-40";

  const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    bgcolor: 'background.paper',
    border: '2px solid #000',
    boxShadow: 24,
    pt: 2,
    px: 4,
    pb: 3,
  };
  
  const handleOpen = useCallback((e) => {
    const sharableLink = window.location.origin + "/stone/sharable/";
    setLink(sharableLink);
  }, []);

  const columns = useMemo(
    () => [
      {
        id: "username",
        label: "Username",
        minWidth: 120,
      },
      {
        id: "firstname",
        label: "First name",
        minWidth: 120,
      },
      {
        id: "lastname",
        label: "Last name",
        minWidth: 120,
      },
      {
        id: "email",
        label: "Email",
        minWidth: 120,
      },
      {
        id: "role",
        label: "Role",
        minWidth: 120,
      },
      {
        id: "action",
        label: "Action",
        minWidth: 100,
        format: (username) => {
          return (
            <button className="underline text-blue-700" onClick={(e)=> navigate("/editUser/" +username )}>
              Edit Details
            </button>
          );
        },
      }
    ],
    [handleOpen]
  );

  const fetchInitialData = () => {
    axios.
    get(`http://localhost:5000/quikloan/getUsers`, {
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Accept": "application/json"
      },
    }).then((res) => {
      setUsers(res.data);
    });
  };

  useEffect(() => {
    fetchInitialData();
  }, []);

  return (
    <>
      <div className="flex mt-8 flex-col items-center">
        <Paper sx={{ width: "80%", overflow: "hidden", margin: "auto" }}>
          <TableContainer sx={{ maxHeight: 600 }}>
            <Table stickyHeader aria-label="sticky table">
              <TableHead>
                <TableRow>
                  {columns.map((column) => (
                    <TableCell key={column.id} style={{ minWidth: column.minWidth }}>
                      <strong>{column.label}</strong>
                    </TableCell>
                  ))}
                </TableRow>
              </TableHead>
              <TableBody>
                {(users?.length || 0) > 0 ? (
                  users?.map((row, index) => {
                    return (
                      <TableRow hover role="checkbox" tabIndex={-1} key={index}>
                        {columns.map((column) => {
                          const value = row[column.id];
                          return (
                            <TableCell key={column.id}>
                              {column.format 
                                ? column.format(row.username)
                                : value}
                            </TableCell>
                          );
                        })}
                      </TableRow>
                    );
                  })
                ) : (
                  <TableRow>
                    <TableCell colSpan={columns.length}>There is no data</TableCell>
                  </TableRow>
                )}
              </TableBody>
            </Table>
          </TableContainer>
        </Paper>
      </div>
    </>
  );
};

export default ViewUser;
