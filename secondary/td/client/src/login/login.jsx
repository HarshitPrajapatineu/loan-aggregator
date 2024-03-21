import "./login.css";
import React, { useState } from "react";
import axios from "axios";

import { useNavigate } from "react-router-dom";

function Login() {
  const navigate = useNavigate();
  const [errMsg, setErrMsg] = useState("");

  const handleClick = (event) => {
    event.preventDefault();
    fetchData();
  }

  const fetchData = () => {
    let url = `http://localhost:8083/td/login`;
    let data = {
      "username": document.getElementById("username").value,
      "password": document.getElementById("password").value
    }
    axios
      .post(url, data)
      .then((res) => {
        console.log(res.data);
        if(res.data && res.data != "" && res.data.username){
          localStorage.setItem("main-auth", JSON.stringify(res.data));
          navigate("/home"); // Omit optional second argument
        }
        if(res.data && res.data.errMsg) {
          setErrMsg(res.data.errMsg);
        }
      })
      .catch((err) => {
        console.log(err.message);
        setErrMsg(err.message);
      });
  };
  return (
    <div className="login-form">
      <h2>Login Form</h2>
      {/* <%
      
      	if(request.getAttribute("errorMsg")!= null){
      		%> */}

      {errMsg && <p className="error">{errMsg}</p>}

      {/* <% 
      	}
      %> */}

      <form onSubmit={(e) => handleClick(e)}>
        <label htmlFor="username">Username</label>
        <input type="text" id="username" name="username" className="form-control" required></input>
        <label htmlFor="password">Password</label>
        <input type="password" id="password" name="password" className="form-control" required></input>
        <input className="submit" type="submit" value="Submit" ></input>
      </form>
    </div>
  );
};

export default Login;
