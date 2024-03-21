import { useEffect, useState, MouseEvent } from "react";
import Input from "../../Component/Input/Input";
import Button from "../../Component/Button/Button";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { getToken, setClient, setRole, setToken, setUser } from "../../Util/helper";
import "./Login.css";

const Login = () => {
  const [formData, setFormData] = useState({ "username": "", "password": "" });
  const [isPassVisible, setIsPassVisible] = useState(false);
  const token = getToken();
  const navigate = useNavigate();

  useEffect(() => {
    if (token) {
      navigate("/");
    }
  });

  // Functions
  const handleSubmit = async (e) => {
    e.preventDefault();
    // api call to validate creds
    let url = "http://localhost:5000/quikloan/login"
    axios
      .post(
        url,
        formData,
        {
          headers: {
            "Access-Control-Allow-Origin": "*",
          },
        }
      )
      .then((res) => {
        console.log(res.data);
        const data = res.data;
        if (data.username && !data.errMsg && data.token) {
          setUser(data.username);
          setRole(data.role);
          navigate("/");
        }
      })
      .catch((err) => console.error("error is", err));
  };

  // Functions
  const handleSignup = async (e) => {
    e.preventDefault();
          navigate("/register");
  };

  function handleOnChange(e) {
    const { name, value } = e.target;
    if(name === 'showPass') {
      return setIsPassVisible(prev => !prev);
    }
    let data = {
      username: document.getElementById("username").value,
      password: document.getElementById("password").value
    }
    setFormData(data);
  }

  // Contants
  const inputFieldClasses =
    "block w-full px-4 py-2 mt-2 text-purple-700 bg-white border rounded-md focus:border-purple-400 focus:ring-purple-300 focus:outline-none focus:ring focus:ring-opacity-40";

  return (
    <div className="login-wrapper relative flex flex-col justify-center min-h-screen overflow-hidden">
      <div className="w-full p-6 m-auto bg-white rounded-md shadow-md lg:max-w-xl">
        <h1 className="text-3xl font-semibold text-center text-purple-700 underline">Sign in</h1>
        <form className="mt-6">
          <div className="mb-2">
            <label htmlFor="username" className="block text-sm font-semibold text-gray-800">
              Username
            </label>
            <Input
              id="username"
              name="userName"
              value={formData.userName}
              onChangeHandler={handleOnChange}
              classes={`${inputFieldClasses} login__form__input`}
              placeholder="Enter username"
            />
          </div>
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
            />
          </div>
          <div className="mb-1 flex gap-4 items-center">
          <input type='checkbox' id='show-pass' checked={isPassVisible} onChange={handleOnChange} name='showPass'/>
          <label htmlFor="show-pass">Show Password</label>
          </div>
          <div className="mt-6">
            <Button
              id="submit-btn btn"
              onClickHandler={handleSubmit}
              classes="w-full px-4 py-2 tracking-wide text-white transition-colors duration-200 transform bg-purple-700 rounded-md hover:bg-purple-600 focus:outline-none focus:bg-purple-600 login__form__btn"
              text="Log in"
            />
          </div>
          <div className="mt-6">
            <Button
              id="submit-btn btn"
              onClickHandler={handleSignup}
              classes="w-full px-4 py-2 tracking-wide text-white transition-colors duration-200 transform bg-purple-700 rounded-md hover:bg-purple-600 focus:outline-none focus:bg-purple-600 login__form__btn"
              text="Sign up"
            />
          </div>
        </form>
      </div>
    </div>
  );
};

export default Login;
