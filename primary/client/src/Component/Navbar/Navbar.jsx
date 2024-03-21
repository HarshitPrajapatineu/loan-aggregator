import "./Navbar.css";
import React, { useState } from "react";
import { Link } from "react-router-dom";
import { getUser } from "../../Util/helper";

const Navbar = ({ showBtns = true, showviewUser = false }) => {
  const [isNavbarOpen, setIsNavbarOpen] = useState(false);
  const onLogoutClick = () => {
    // it will clear token stored in local storage
    window.localStorage.clear();
  };

  return (
    <nav className="top-0 flex flex-wrap items-center justify-between px-2 py-3 lg:absolute w-full">
      <div className="w-full relative flex justify-between lg:w-auto lg:static lg:block lg:justify-start">
        <div className="flex ml-10 lg:ml-20">
          {/* <img src={dvLogo} alt="dv-logo" height={60} width={60} /> */}
          <div className="container px-4 mx-auto flex flex-wrap items-center justify-between">
            <span className="font-bold text-xl tracking-tight font-sourceCodePro text-3xl">
              QuikLoan
            </span>
          </div>
        </div>
        {showBtns && (
          <button
            className="cursor-pointer text-xl leading-none px-3 py-1 border border-solid border-transparent rounded bg-transparent block lg:hidden outline-none focus:outline-none"
            type="button"
            onClick={() => setIsNavbarOpen(!isNavbarOpen)}
          >
            <svg
              className="fill-current h-3 w-3"
              viewBox="0 0 20 20"
              xmlns="http://www.w3.org/2000/svg"
            >
              <title>Menu</title>
              <path d="M0 3h20v2H0V3zm0 6h20v2H0V9zm0 6h20v2H0v-2z" />
            </svg>
          </button>
        )}
      </div>
      {showBtns && (
        <div
          className={
            "lg:flex flex-grow items-center justify-end mr-3" + (isNavbarOpen ? " flex" : " hidden")
          }
          id="example-navbar-danger"
        >
        <Link
          to={"/editUser/" + getUser()}
          className="inline-block text-sm mr-4 px-4 py-2 leading-none border rounded border-black hover:bg-white mt-4 lg:mt-0"
        >
          Hi, {getUser()}
        </Link>
          {showviewUser &&
          <Link
            to="/viewUser"
            className="inline-block text-sm mr-4 px-4 py-2 leading-none border rounded border-black hover:bg-white mt-4 lg:mt-0"
          >
            ViewUsers
          </Link> }
          <Link
            to="/login"
            className="inline-block text-sm px-4 py-2 leading-none border rounded border-black hover:bg-white mt-4 lg:mt-0"
            onClick={onLogoutClick}
          >
            logout
          </Link>
        </div>
      )}
    </nav>
  );
};

export default Navbar;
