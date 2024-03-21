import logo from './logo.svg';
import './App.css';
import Login from './login/login';
import Registration from './registration/registration';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { useEffect, useState } from "react";
import AddListing from './addlisting/addlisting';
import Header from './header/header';
import Home from './home/home';
import RequireAuth from "./RequireAuth";
function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  useEffect(() => {
    setIsLoggedIn(localStorage.getItem("main-auth") !== null);
    console.log()
  }, [localStorage])
  // window.sessionStorage.setItem("isLoggedIn", true);
  return (
    <div>
      
      <div style={{ marginTop: 58, display: "flex" }}> </div>
      <BrowserRouter>
        <Routes>
          <Route exact path="/login" element={<><Header type={0}/><Login /></>} />
          <Route exact path="/registration" element={<><Header type={1}/><Registration /></>} />
          <Route exact path="/addListing/:refid" element={<><Header type={3}/><AddListing /></>} />
          <Route exact path="/addListing/" element={<><Header type={3}/><AddListing /></>} />
          <Route exact path="/" element={<><Header type={3}/><RequireAuth><Home /></RequireAuth></>} />
          <Route
            exact
            path="*"
            element={isLoggedIn ? <><Header type={3}/> <Home /> </>: <><Header type={0}/><Login /> </> }
            replace={true}
          />
        </Routes>
      </BrowserRouter>
    </div>

    // <div className="App">
    //   {/* <header className="App-header">
    //     <img src={logo} className="App-logo" alt="logo" />
    //     <p>
    //       Edit <code>src/App.js</code> and save to reload.
    //     </p>
    //     <a
    //       className="App-link"
    //       href="https://reactjs.org"
    //       target="_blank"
    //       rel="noopener noreferrer"
    //     >
    //       Learn React
    //     </a>
    //    */}
    // </div>
  );
}

export default App;
