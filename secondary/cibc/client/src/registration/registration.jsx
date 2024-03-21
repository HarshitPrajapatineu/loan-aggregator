import "./registration.css";
import React from "react";
import axios from "axios";

function registration () {
  
}
function Registration() {
  return (
    <div class="registration-form">
      <h2>Add Listing</h2>

      <p id="error-msg" class="error hidden">Error: Password and confirm password does not
        match</p>


      <form action="registration" method="post">


        <div class="block">
          <label for="username">Username</label> <input type="text"
            id="username" name="username" class="form-control" required></input>
        </div>


        <div class="block"></div>


        <div class="block">
          <label for="firstname">First name</label> <input type="text"
            id="firstname" name="firstname" class="form-control" required></input>
        </div>


        <div class="block">
          <label for="lastname">Last name</label> <input type="text"
            id="lastname" name="lastname" class="form-control" required></input>
        </div>


        <div class="full-block">
          <label for="email">Address</label> <input type="email" id="email"
            name="email" class="form-control" required></input>
        </div>


        <div class="block">
          <label for="email">Gender</label><br />
          <div class="radio-item">
            <input type="radio" id="html" name="gender" value="male"></input>
            <label class="radio-label" for="male">Male</label>
          </div>
          <div class="radio-item">
            <input type="radio" id="css" name="gender" value="female"></input>
            <label class="radio-label" for="female">Female</label><br />
          </div>
        </div>


        <div class="block"></div>


        <div class="block">
          <label for="email">Email</label> <input type="email" id="email"
            name="email" class="form-control" required></input>
        </div>


        <div class="block">
          <label for="phone">Phone</label> <input type="tel" id="phone"
            name="phone" class="form-control" required></input>
        </div>


        <div class="block">
          <label for="password">Password</label> <input type="password"
            id="password" name="password" class="form-control" required></input>
        </div>


        <div class="block">
          <label for="cpassword">Confirm Password</label> <input
            type="cpassword" id="cpassword" name="cpassword"
            onchange="validatePassword()" class="form-control" required></input>
        </div>

        <input class="submit" type="submit" value="Submit"></input>
      </form>
    </div>
  );
};

export default Registration;
