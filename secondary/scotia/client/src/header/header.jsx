import "./header.css";
import React from "react";
import axios from "axios";

import { Link, useNavigate } from "react-router-dom";

function Header({ type }) {
  const navigate = useNavigate();

  const handleClick = () => {
    fetchData();
  }

  const fetchData = () => {
    let url = `http://localhost:8082/scotia/logout`;
    
    axios
      .get(url)
      .then((res) => {
        console.log(res.data);
        if(res.data){
          localStorage.setItem("main-auth", null);
          window.localStorage.clear();
          navigate("/login"); // Omit optional second argument
        }
      })
      .catch((err) => {
        console.log(err.message);
        navigate("/login");
      });
  };
  let buttonRoute = "login";
  let buttonText = "Log in";
  let t = type
  console.log(type);
  if (type == 1) {
    buttonRoute = "login";
    buttonText = "Log in";
  }
  if (type == 2) {
    buttonRoute = "registration";
    buttonText = "Sign up";
  }
  if (type == 3) {
    console.log(type);
    buttonRoute = "http://localhost:8082/scotia/logout";
    buttonText = "Log out";
  }
  return (

    <>
      <header className="header">
        <Link to="/home" className="logo">Home</Link>
        <input className="menu-btn" type="checkbox" id="menu-btn" />
        <label className="menu-icon" htmlFor="menu-btn"><span className="navicon"></span></label>
        {
          t > 0 && t < 3 &&
          < ul className="menu">
            <li><Link to={buttonRoute}>{buttonText}</Link></li>
          </ul>
        }
        {
          t = 3 &&
          < ul className="menu">
            <li><button onClick={handleClick}>{buttonText}</button></li>
          </ul>
        }
      </header>
    </>
  );
};

export default Header;
