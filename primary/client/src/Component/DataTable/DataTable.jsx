import Box from "@mui/material/Box";
import Modal from "@mui/material/Modal";
import Paper from "@mui/material/Paper";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TablePagination from "@mui/material/TablePagination";
import TableRow from "@mui/material/TableRow";
import Typography from "@mui/material/Typography";
import axios from "axios";
import { useCallback, useEffect, useMemo, useState } from "react";
import { Link } from "react-router-dom";
import { getClient, getToken } from "../../Util/helper";
import Input from "../Input/Input";
import Dropdown from "../Dropdown/Dropdown";
import Button from "../Button/Button";
import ChildModal from "../ChildModal/ChildModal";

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,
  p: 4,
  maxWidth: "100vw",
};

const DataTable = () => {
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(10);
  const [loans, setLoans] = useState();
  const [open, setOpen] = useState(false);
  const [modalInfo, setModalInfo] = useState({"isopen":false, "data": {}});
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
    setOpen(true);
  }, []);

  const handleOpenModal = (e, loan) => {
    console.log(loan);
    setModalInfo({"isopen":true, "data": loan});
  };
  const handleCloseModal = () => {
    setModalInfo({"isopen":false, "data": {}});
  };

  const handleClose = () => setOpen(false);



  function handleOnChange(e) {
    const { name, value } = e.target;
    let data = {
      "amount": document.getElementById("amount").value, 
      "duration": document.getElementById("duration").value,
      "loantype": document.getElementById("loantype").value
    }
    console.log(data);
    setFormData(data);
  }

  const columns = useMemo(
    () => [
      { id: "provider", label: "Provider", minWidth: 120 },
      { id: "referencenumber", label: "Ref #", minWidth: 120 },
      {
        id: "name",
        label: "Loan Name",
        minWidth: 120,
      },
      {
        id: "interestrate",
        label: "Interest Rate(%)",
        minWidth: 120,
      },
      {
        id: "loantype",
        label: "Loan Type",
        minWidth: 120,
      },
      {
        id: "monthlypayment",
        label: "Monthly Payment(CA$)",
        minWidth: 120,
      },
      {
        id: "action",
        label: "Action",
        minWidth: 100,
        format: (loan) => {
          return (
            <button className="underline text-blue-700" onClick={(e)=> handleOpenModal(e, loan)}>
              View Details
            </button>
          );
        },
      }
    ],
    [handleOpen]
  );

  const fetchInitialData = useCallback(() => {
    console.log(formData);
    axios.
    post("http://localhost:5000/quikloan/getLoans", formData, {
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Accept": "application/json"
      },
    }).then((data) => {
      setLoans(data.data);
    });
  },[formData]);

  useEffect(() => {
    fetchInitialData();
  }, [fetchInitialData]);

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(+event.target.value);
    setPage(0);
  };

  const copySharableLink = async () => {
    try {
      await navigator.clipboard.writeText(`${link}`);
    } catch (err) {
      console.error("Failed to copy: ", err);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    // api call to validate creds
    fetchInitialData();
    // let url = "http://localhost:5000/quikloan/login"
    // axios
    //   .post(
    //     url,
    //     formData,
    //     {
    //       headers: {
    //         "Access-Control-Allow-Origin": "*",
    //       },
    //     }
    //   )
    //   .then((res) => {
    //     console.log(res.data);
    //     const data = res.data;
    //     if (data.username && !data.errMsg) {
    //       setUser(data.username);
    //       navigate("/");
    //     }
    //   })
    //   .catch((err) => console.error("error is", err));
  };

  return (
    <>
      {open && (
        <Modal
          open={open}
          onClose={handleClose}
          aria-labelledby="modal-modal-title"
          aria-describedby="modal-modal-description"
        >
          <Box sx={style}>
            <Typography id="modal-modal-title" variant="h6" component="h2">
              This link is sharable
            </Typography>
            <Typography
              id="modal-modal-description"
              sx={{ mt: 2 }}
              className="flex items-baseline gap-2 flex-col"
            >
              <span className="break-words w-full">{link}</span>
              <Button variant="contained" onClick={copySharableLink}>
                copy
              </Button>
            </Typography>
          </Box>
        </Modal>
      )}
      <div className="flex flex-col items-center">
        <form className="mt-6 flex justify-between w-4/5">

          <div className="mb-2">
            <label htmlFor="loantype" className="block text-sm font-semibold text-gray-800">
              Loan Type
            </label>
            <Dropdown
              id="loantype"
              name="loantype"
              value={formData.loantype}
              onChangeHandler={handleOnChange}
              options={[
                { key: "Home", value: "home" },
                { key: "Car", value: "car" },
                { key: "Personal", value: "personal" }
              ]}
              classes={`${inputFieldClasses} login__form__input`}
            />
          </div>
          <div className="mb-2">
            <label htmlFor="amount" className="block text-sm font-semibold text-gray-800">
              Amount
            </label>
            <Input
              id="amount"
              name="amount"
              value={formData.amount}
              onChangeHandler={handleOnChange}
              classes={`${inputFieldClasses} login__form__input`}
              placeholder="Enter amount"
            />
          </div>
          <div className="mb-2">
            <label htmlFor="duration" className="block text-sm font-semibold text-gray-800">
              Duration
            </label>
            <Input
              id="duration"
              name="duration"
              value={formData.duration}
              onChangeHandler={handleOnChange}
              classes={`${inputFieldClasses} login__form__input`}
              placeholder="Enter duration in years"
            />
          </div>
          {/* <div className="mt-6">
            <Button
              id="submit-btn btn"
              onClickHandler={handleSubmit}
              classes="w-full px-4 py-2 tracking-wide text-white transition-colors duration-200 transform bg-purple-700 rounded-md hover:bg-purple-600 focus:outline-none focus:bg-purple-600 login__form__btn"
              text="Search"
            />
          </div> */}
        </form>
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
                {(loans?.length || 0) > 0 ? (
                  loans?.map((row, index) => {
                    return (
                      <TableRow hover role="checkbox" tabIndex={-1} key={index}>
                        {columns.map((column) => {
                          const value = row[column.id];
                          return (
                            <TableCell key={column.id}>
                              {column.format 
                                ? column.format(row)
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
          {/* <TablePagination
            rowsPerPageOptions={[10, 25, 50, 100]}
            component="div"
            count={currentStones?.totalCount || 0}
            rowsPerPage={rowsPerPage}
            page={page}
            onPageChange={handleChangePage}
            onRowsPerPageChange={handleChangeRowsPerPage}
          /> */}
        </Paper>

        <Modal
          open={modalInfo.isopen}
          onClose={handleCloseModal}
          aria-labelledby="parent-modal-title"
          aria-describedby="parent-modal-description"
        >
          <Box sx={{ ...style, width: 400 }}>
            <h2 id="font-bold">Name: {modalInfo.data.name} </h2>
            <p id="parent-modal-description">
              Ref #: {modalInfo.data.referencenumber}<br/>
              Interest Rate: {modalInfo.data.interestrate}%<br/>
              Monthly Payment: {modalInfo.data.monthlypayment} CA$<br/>
              Provider: {modalInfo.data.provider}<br/>
              Description: {modalInfo.data.description}<br/>
              Requirements: {modalInfo.data.requirements}<br/>
              Pre-payment: {modalInfo.data.prepayment}%<br/>
              Payment Increase: {modalInfo.data.paymentincrease}%<br/>
              Loan to Value: {modalInfo.data.loantovalue}%<br/>
              Loan Type: {modalInfo.data.loantype}<br/>
              Is Fixed: {modalInfo.data.isfixed ? <>Yes </> : <>No </>} <br/>
              Pre-approval: {modalInfo.data.preapproval ? <>Yes</> : <>No</>}<br/>
            </p>
            
            <ChildModal header="Terms and Conditions*" tnc={modalInfo.data.tnc} />
          </Box>
        </Modal>
      </div>
    </>
  );
};

export default DataTable;
