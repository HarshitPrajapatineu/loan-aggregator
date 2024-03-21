import { useNavigate, useParams } from "react-router-dom";
import Button from "../../Component/Button/Button";
import Input from "../../Component/Input/Input";
import "./EditUser.css";
import React, { useEffect, useState } from "react";
import axios from "axios";
import { getRole, getUser, getToken } from "../../Util/helper";

const EditUser = () => {
  
  let { username } = useParams();
  const [showDelete, setShowDelete] = useState(false);
  const [formData, setFormData] = useState({ 
    "username": "",
    "password": "" ,
    "firstname": "",
    "lastname": "",
    "address": "",
    "email": "",
    "phone": "",
    "gender": "",
    "id": "",
    "role": "client"
  });
  const [isPassVisible, setIsPassVisible] = useState(false);
  const [errorMsg, setErrorMsg] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    if (getRole() === "client" && username === getUser()) {
      setShowDelete(false);
      fetchInitialData();
    } else if (getRole() === "Administrator") {
      setShowDelete(true);
      fetchInitialData();
    } else {
      navigate("/login");
    }
  }, []);

  // Functions
  const fetchInitialData = () => {
    // e.preventDefault();
    // api call to validate creds
    // if(pwd && cpwd && pwd === cpwd) {
      // setErrorMsg("");
      let url = "http://localhost:5000/quikloan/getUser/" + getUser(); 
      axios
        .get(
          url,
          formData,{
            headers: {
              "Access-Control-Allow-Origin": "*",
              "Accept": "application/json",
              "token": getToken()
            },
          }
        )
        .then((res) => {
          console.log(res.data);
          const data = res.data;
          if (data.username && !data.errMsg) {
            document.getElementById("username").value = data.username;
            document.getElementById("firstname").value = data.firstname;
            document.getElementById("lastname").value = data.lastname;
            document.getElementById("address").value = data.address;
            document.getElementById("email").value = data.email;
            document.getElementById("phone").value = data.phone;

          }
        })
        .catch((err) => console.error("error is", err));
    // } else {
    //   setErrorMsg("Password does not match");
    // }
  };

  const handleEditSubmit = async (e) => {
    e.preventDefault();
    // api call to validate creds
      let url = "http://localhost:5000/quikloan/updateUser/"; 
      axios
        .post(
          url,
          formData, {
            headers: {
              "Access-Control-Allow-Origin": "*",
              "Accept": "application/json",
              "token": getToken()
            },
          }
        )
        .then((res) => {
          console.log(res.data);
          const data = res.data;
          if (data.username && !data.errMsg) {
          }
        })
        .catch((err) => console.error("error is", err));
  };
  
  const handlePasswordSubmit = async (e) => {
    e.preventDefault();
    // api call to validate creds
    let pwd = document.getElementById("password").value; 
    let cpwd = document.getElementById("cpassword").value;
    if(pwd && cpwd && pwd === cpwd) {
      setErrorMsg("");
      let url = "http://localhost:5000/quikloan/updatePassword";
      axios
        .post(
          url,
          formData, {
            headers: {
              "Access-Control-Allow-Origin": "*",
              "Accept": "application/json",
              "token": getToken()
            },
          }
        )
        .then((res) => {
          console.log(res.data);
          const data = res.data;
          if (data.username && !data.errMsg) {
          }
        })
        .catch((err) => console.error("error is", err));
    } else {
      setErrorMsg("Password does not match");
    }
  };
  
  const handleDelete = async (e) => {
    e.preventDefault();
    // api call to validate creds
    let pwd = document.getElementById("password").value; 
    let cpwd = document.getElementById("cpassword").value;
    if(pwd && cpwd && pwd === cpwd) {
      setErrorMsg("");
      let url = "http://localhost:5000/quikloan/deleteUser";
      axios
        .post(
          url,
          formData, {
            headers: {
              "Access-Control-Allow-Origin": "*",
              "Accept": "application/json",
              "token": getToken()
            },
          }
        )
        .then((res) => {
          console.log(res.data);
          const data = res.data;
          if (data.username && !data.errMsg) {
            // setUser(data.username);
            navigate("/login");
          }
        })
        .catch((err) => console.error("error is", err));
    } else {
      setErrorMsg("Password does not match");
    }
  };

  function handleOnChange(e) {
    const { name, value } = e.target;
    if(name === 'showPass') {
      return setIsPassVisible(prev => !prev);
    }
    let data = {
      "username": document.getElementById("username").value,
      "password": document.getElementById("password").value,
      "firstname": document.getElementById("firstname").value,
      "lastname": document.getElementById("lastname").value,
      "address": document.getElementById("address").value,
      "email": document.getElementById("email").value,
      "phone": document.getElementById("phone").value,
      "id":"",
      "role": "client"
    }
    setFormData(data);
  }

  // Contants
  const inputFieldClasses =
    "block w-full px-4 py-2 mt-2 text-purple-700 bg-white border rounded-md focus:border-purple-400 focus:ring-purple-300 focus:outline-none focus:ring focus:ring-opacity-40";

  return (
    <div className="login-wrapper relative flex flex-col justify-center min-h-screen overflow-hidden">
      <div className="w-full p-6 m-auto bg-white rounded-md shadow-md lg:max-w-xl">
        <h1 className="text-3xl font-semibold text-center text-purple-700 underline">Edit User</h1>
        <form className="mt-6" 
              onSubmit={(e) => handleEditSubmit(e)}>
          <div className="mb-2">
            <label htmlFor="username" className="block text-sm font-semibold text-gray-800">
              Username
            </label>
            <Input
              id="username"
              name="username"
              value={formData.username}
              onChangeHandler={handleOnChange}
              classes={`${inputFieldClasses} login__form__input`}
              placeholder="Enter username"
              isrequired= {true}
            />
          </div>
          <div className="mb-2">
            <label htmlFor="firstname" className="block text-sm font-semibold text-gray-800">
              First Name
            </label>
            <Input
              id="firstname"
              name="firstname"
              value={formData.firstname}
              onChangeHandler={handleOnChange}
              classes={`${inputFieldClasses} login__form__input`}
              placeholder="Enter firstname"
              isrequired= {true}
            />
          </div>
          <div className="mb-2">
            <label htmlFor="lastname" className="block text-sm font-semibold text-gray-800">
              Last Name
            </label>
            <Input
              id="lastname"
              name="lastname"
              value={formData.lastname}
              onChangeHandler={handleOnChange}
              classes={`${inputFieldClasses} login__form__input`}
              placeholder="Enter lastname"
              isrequired= {true}
            />
          </div>
          <div className="mb-2">
            <label htmlFor="address" className="block text-sm font-semibold text-gray-800">
              Address
            </label>
            <Input
              id="address"
              name="address"
              value={formData.address}
              onChangeHandler={handleOnChange}
              classes={`${inputFieldClasses} login__form__input`}
              placeholder="Enter address"
            />
          </div>
          <div className="mb-2">
            <label htmlFor="email" className="block text-sm font-semibold text-gray-800">
              Email
            </label>
            <Input
              id="email"
              name="email"
              value={formData.email}
              onChangeHandler={handleOnChange}
              classes={`${inputFieldClasses} login__form__input`}
              placeholder="Enter email"
              isrequired= {true}
            />
          </div>
          <div className="mb-2">
            <label htmlFor="phone" className="block text-sm font-semibold text-gray-800">
              Phone
            </label>
            <Input
              id="phone"
              name="phone"
              value={formData.phone}
              onChangeHandler={handleOnChange}
              classes={`${inputFieldClasses} login__form__input`}
              placeholder="Enter phone"
              isrequired= {true}
            />
          </div>
          <div className="mt-6">
            <Input
              id="submit-btn btn"
              type="submit"
              name="Sign up" 
              classes="w-full px-4 py-2 tracking-wide text-white transition-colors duration-200 transform bg-purple-700 rounded-md hover:bg-purple-600 focus:outline-none focus:bg-purple-600 login__form__btn"
              text="Sign up"
            />
          </div>
        </form>
      </div>
      <div className="w-full p-6 m-auto bg-white rounded-md shadow-md lg:max-w-xl">
        <h1 className="text-3xl font-semibold text-center text-purple-700 underline">Change Password</h1>
        {errorMsg && <p className="text-red-600 w-full min-h-min text-center mt-2">{errorMsg}</p>}
        <form className="mt-6" 
              onSubmit={(e) => handlePasswordSubmit(e)}>
          <div className="mb-2">
            <label htmlFor="password" className="block text-sm font-semibold text-gray-800">
              Password
            </label>
            <Input
              id="password"
              name="password"
              type={isPassVisible ? 'text' : 'password'}
              value={formData.password}
              onChangeHandler={handleOnChange}
              classes={`${inputFieldClasses} login__form__input`}
              placeholder="Enter password"
              isrequired= {true}
            />
          </div>
          <div className="mb-2">
            <label htmlFor="cpassword" className="block text-sm font-semibold text-gray-800">
              Confirm Password
            </label>
            <Input
              id="cpassword"
              name="cpassword"
              type={isPassVisible ? 'text' : 'password'}
              value={formData.cpassword}
              onChangeHandler={handleOnChange}
              classes={`${inputFieldClasses} login__form__input`}
              placeholder="Enter password again"
              isrequired= {true}
            />
          </div>
          <div className="mb-1 flex gap-4 items-center">
          <input type='checkbox' id='show-pass' checked={isPassVisible} onChange={handleOnChange} name='showPass'/>
          <label htmlFor="show-pass">Show Password</label>
          </div>
          <div className="mt-6">
            <Input
              id="submit-btn btn"
              type="submit"
              name="Sign up" 
              classes="w-full px-4 py-2 tracking-wide text-white transition-colors duration-200 transform bg-purple-700 rounded-md hover:bg-purple-600 focus:outline-none focus:bg-purple-600 login__form__btn"
              text="Sign up"
            />
          </div>
        </form>
      </div>
      <div className="w-full p-6 m-auto bg-white rounded-md shadow-md lg:max-w-xl">
        <h1 className="text-3xl font-semibold text-center text-purple-700 underline">Delete User</h1>
        <form className="mt-6" 
              onSubmit={(e) => handleDelete(e )}>
          <div className="mt-6">
            <Input
              id="submit-btn btn"
              type="submit"
              name="Sign up" 
              classes="w-full px-4 py-2 tracking-wide text-white transition-colors duration-200 transform bg-purple-700 rounded-md hover:bg-purple-600 focus:outline-none focus:bg-purple-600 login__form__btn"
              text="Sign up"
            />
          </div>
        </form>
      </div>
    </div>
  );
};

export default EditUser;
