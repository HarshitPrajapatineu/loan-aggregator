import "./addlisting.css";
import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";

// function handleSave() {
//   const [list, setList] = useState([]);

//   const saveData = () => {
//     let url = `http://localhost:8083/td/getUsers`;
//     axios
//       .get(url)
//       .then((res) => {
//         console.log("dataa");
//       })
//       .catch((err) => console.log(err.message));
//   };
//   return (
//     <h2>Add Listing</h2>
//   );
// };

function AddListing() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const navigate = useNavigate();
  const [errMsg, setErrMsg] = useState("");
  const { refid } = useParams();
  useEffect(() => {
    setIsLoggedIn(localStorage.getItem("main-auth") !== null);

    if (localStorage.getItem("main-auth") !== null) {
      // saveData();

      if (refid) {
        console.log(refid);
        getData();
      }
      console.log("dddddd");
    }
    else {
      navigate("/login");
    }
  }, [localStorage]);

  const handleClick = (event) => {
    event.preventDefault();
    saveData();
  }

  const saveData = () => {
    let url = "http://localhost:8083/td/" + (refid ? "updateListing" : "addListing");
    let data = {
      "id": 0,
      "referencenumber": document.getElementById("referencenumber").value,
      "name": document.getElementById("name").value,
      "description": document.getElementById("description").value,
      "interestrate": parseFloat(document.getElementById("interestrate").value),
      "status": document.getElementById("status").value,
      "requirements": document.getElementById("requirements").value,
      "ratehold": parseFloat(document.getElementById("ratehold").value),
      "prepayment": parseFloat(document.getElementById("prepayment").value),
      "paymentincrease": parseFloat(document.getElementById("paymentincrease").value),
      "loantovalue": parseFloat(document.getElementById("loantovalue").value),
      "preapproval": parseInt(document.getElementById("preapproval").value),
      "loantype": document.getElementById("loantype").value,
      "isFixed": parseInt(document.getElementById("isfixed").value),
      "tnc": document.getElementById("tnc").value,
      "datemodified": new Date()
    };

    axios
      .post(url, data)
      .then((res) => {
        console.log(res.data);
        if (res.data) {
          navigate("/home"); // Omit optional second argument
        }
        if (res.data && res.data.errMsg) {
          console.log(res.data.errMsg);
          setErrMsg(res.data.errMsg);
        }
      })
      .catch((err) => {
        console.log(err.message);
        setErrMsg(err.message);
      });
  };

  const getData = () => {
    let url = `http://localhost:8083/td/getListing/` + refid;
    axios
      .get(url)
      .then((res) => {
        console.log(res.data);
        if (res.data) {
          // populatedata
          document.getElementById("referencenumber").value = res.data.referencenumber;
          document.getElementById("name").value = res.data.name;
          document.getElementById("description").value = res.data.description;
          document.getElementById("interestrate").value = res.data.interestrate;
          document.getElementById("status").value = res.data.status;
          document.getElementById("requirements").value = res.data.requirements;
          document.getElementById("ratehold").value = res.data.ratehold;
          document.getElementById("prepayment").value = res.data.prepayment;
          document.getElementById("paymentincrease").value = res.data.paymentincrease;
          document.getElementById("loantovalue").value = res.data.loantovalue;
          document.getElementById("preapproval").value = res.data.preapproval;
          document.getElementById("loantype").value = res.data.loantype;
          console.log(document.getElementById("loantype").value);
          document.getElementById("isfixed").value = res.data.isFixed;
          document.getElementById("tnc").value = res.data.tnc;
        }
        if (res.data && res.data.errMsg) {
          console.log(res.data.errMsg);
          setErrMsg(res.data.errMsg);
        }
      })
      .catch((err) => {
        console.log(err.message);
        setErrMsg(err.message);
      });
  };


  return (
    <div className="registration-form">
      <h2>Add Listing</h2>

      <p id="error-msg" className="error hidden">Error: Password and confirm password does not
        match</p>


      <form onSubmit={(e) => handleClick(e)}>


        <div className="block">
          <label htmlFor="name">Loan name</label> <input type="text"
            id="name" name="name" className="form-control" required></input>
        </div>


        <div className="block">
          <label htmlFor="referencenumber">Loan Id</label> <input type="text"
            id="referencenumber" name="referencenumber" className="form-control" required></input>
        </div>


        <div className="full-block">
          <label htmlFor="description">Description</label> <textarea type="text-area" id="description"
            name="description" rows="4" className="form-control" required></textarea>
        </div>


        <div className="block">
          <label htmlFor="interestrate">Interest rate</label> <input type="text" id="interestrate"
            name="interestrate" className="form-control" required></input>
        </div>

        <div className="block">
          <label htmlFor="loantype">Loan type</label>

          <select name="loantype" id="loantype" className="form-control">
            <option value="home">Home</option>
            <option value="personal">Personal</option>
            <option value="car">Car</option>
          </select>
        </div>

        <div className="block">
          <label htmlFor="status">Status</label>

          <select name="status" id="status" className="form-control">
            <option value="active">Active</option>
            <option value="archived">Archived</option>
          </select>
        </div>


        <div className="full-block">
          <label htmlFor="requirements">Requirements</label> <textarea type="text-area" id="requirements"
            name="requirements" rows="4" className="form-control" required></textarea>
        </div>



        <div className="block">
          <label htmlFor="ratehold">Rate hold</label> <input type="text" id="ratehold"
            name="ratehold" className="form-control" required></input>
        </div>



        <div className="block">
          <label htmlFor="prepayment">Pre-payment</label> <input type="text" id="prepayment"
            name="prepayment" className="form-control" required></input>
        </div>



        <div className="block">
          <label htmlFor="paymentincrease">Payment increase</label> <input type="text" id="paymentincrease"
            name="paymentincrease" className="form-control" required></input>
        </div>



        <div className="block">
          <label htmlFor="loantovalue">Loan to value</label> <input type="text" id="loantovalue"
            name="loantovalue" className="form-control" required></input>
        </div>



        <div className="block">
          <label htmlFor="preapproval">Preapproval</label> <br />
          <div className="radio-item">
            <input type="checkbox" id="preapproval" className="form-control"
              name="preapproval"></input>
            <label className="radio-label" for="preapproval">Pre-approval</label>
          </div>
        </div>



        <div className="block">
          <label for="isfixed">Is Fixed</label> <br />
          <div className="radio-item">
            <input type="checkbox" id="isfixed" className="form-control"
              name="isfixed"></input>
            <label className="radio-label" for="isfixed">Is Fixed</label>
          </div>
        </div>


        <div className="full-block">
          <label for="tnc">Terms and Conditions</label> <textarea type="text-area" id="tnc"
            name="tnc" rows="4" className="form-control"></textarea>
        </div>

        <input className="submit" type="submit" value="Submit" ></input>
        {/* <input className="submit" type="button" value="Submit" onClick={handleClick} ></input> */}
      </form>
    </div>
  );
};

export default AddListing;
