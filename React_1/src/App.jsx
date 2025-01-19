// import { useState } from "react";
// import reactLogo from "./assets/react.svg";
// import viteLogo from "/vite.svg";
import "./App.css";
import Header from "./Header.jsx";
import Footer from "./Footer.jsx";
import React from "react";
import Form from "./Form.jsx";
import UsersList from "./UsersList.jsx";

function App() {
  return (
    <>
      <Header></Header>
      <p>
        These options don&apos;t work for now, but they just might work in the
        future!
      </p>
      <>
        <h1>Form and users list</h1>
        <Form />
        <UsersList />
      </>
      <Footer></Footer>
    </>
  );
}

export default App;
