import "./Layout.css";
import React from "react";
import Navbar from "../Navbar/Navbar"


const Layout = ({ children, showviewUser }) => {
  return (
    <>
    <div className="site-wrapepr h-full flex  flex-col">
      <Navbar showviewUser = {showviewUser} />
      <div className="mt-14">
      {children}
      </div>
    </div>
    </>
  );
};

export default Layout;
