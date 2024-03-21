import React from "react";
import { Outlet, Navigate } from "react-router-dom";
import Layout from "../Component/Layout/Layout";
import { getUser, getRole } from "../Util/helper";

const PrivateRoutes = ({ allowedRoles, showviewUser }) => {
  const user = getUser();

  if (!user) {
    return <Navigate to='/login'  />
  }

  return allowedRoles.find((role) => role === getRole()) ? (
    <Layout showviewUser = {showviewUser}>
      <Outlet />
    </Layout>
  ) : (
    <Navigate to="/login"/>
  );

  // return (
  //   <Layout>
  //     <Outlet />
  //   </Layout>
  // );
};

export default PrivateRoutes;
