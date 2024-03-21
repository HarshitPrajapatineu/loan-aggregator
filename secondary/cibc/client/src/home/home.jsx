import "./home.css";
import React, { useEffect, useState } from "react";
import axios from "axios";
import loan from "./../models/loan"
import { useNavigate, Link } from "react-router-dom";

function Home(props) {
  const [list, setList] = useState([]);
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    setIsLoggedIn(localStorage.getItem("main-auth") !== null);
    if (localStorage.getItem("main-auth") !== null) {
      fetchData();
      console.log("dddddd");
    }
    else {
      navigate("/login");
    }
  }, [localStorage]);

  const fetchData = () => {
    let url = `http://localhost:8084/cibc/getLoans`;
    axios
      .get(url)
      .then((res) => {
        console.log("dataa");
        console.log(res);
        setList(res.data);
      })
      .catch((err) => console.log(err.message));
  };

  const handleDelete = (event, refid) => {
    event.preventDefault();
    let url = "http://localhost:8084/cibc/deleteLoan/" + refid;
    axios
      .get(url)
      .then((res) => {
        console.log(res.data);
        if(res.data) {
          fetchData();
        } 
      })
      .catch((err) => console.log(err.message));
  }

  // delete call fetch data


  return (
    <>
      <div style={{ marginRight: 72, marginLeft: 72 }}>


        <div class="menu-container">
          <div class="menu-button"><a class="create-post-button" href="addListing">Create Listing</a></div>
        </div>
        <table>
          <tr>
            <th>Ref#</th>
            <th>Name</th>
            <th>Interest rate</th>
            <th>Status</th>
            <th>Date Modified</th>
            <th>Action</th>
          </tr>
          {list &&
            list.map((el) => {
              return <tr>
                {console.log(el)}
                <td>{el.referencenumber}</td>
                <td>{el.name}</td>
                <td>{el.interestrate}</td>
                <td>{el.status}</td>
                <td>{el.datemodified}</td>
                <td>
                  <Link className="action-button accept"
                    to={"/addListing/" + el.referencenumber}>Edit </Link>
                  <button className="action-button reject"
                    onClick={(e) => handleDelete(e, el.referencenumber)}>Delete </button></td>
              </tr>;
            })
          }
        </table>
      </div>
    </>
  );
}

export default Home;

