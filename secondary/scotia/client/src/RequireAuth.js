import React, { useEffect } from "react";
import { useState } from "react";
import { Navigate } from "react-router-dom";

function RequireAuth({ children }) {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
useEffect(() => {
  setIsLoggedIn(localStorage.getItem("main-auth") !== null);
}, [])
  return isLoggedIn ? children : <Navigate to="/login" replace />;
}

export default RequireAuth;
