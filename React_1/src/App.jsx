// import { useState } from "react";
// import reactLogo from "./assets/react.svg";
// import viteLogo from "/vite.svg";
import "./App.css";
import Header from "./Header.jsx";
import Footer from "./Footer.jsx";
import React, { useState } from "react";
import Form from "./Form.jsx";
import UsersList from "./UsersList.jsx";
import { useEffect } from "react";

function App() {
  const [users, setUsers] = useState([]);
  const fetchUsers = async () => {
    try {
      const response = await fetch("http://localhost:5000/users");
      if (!response.ok) {
        throw new Error("Failed to fetch users.");
      }
      const data = await response.json();
      setUsers(data);
    } catch (error) {
      console.error("Error fetching users:", error);
    }
  };
  useEffect(() => {
    fetchUsers();
  }, []);
  // Funkcja dodająca użytkownika do bazy danych i listy
  const addUser = async (user) => {
    try {
      const response = await fetch("http://localhost:5000/submit", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(user),
      });

      const responseText = await response.text(); // Odczytaj odpowiedź jako tekst
      console.log("Server Response (Raw):", responseText);

      if (!response.ok) {
        throw new Error("Failed to add user to the database.");
      }

      //   const newUser = await response.json();
      //   console.log("Added User:", newUser);

      //   fetchUsers();
      // } catch (error) {
      //   console.error("Error adding user:", error);
      //   alert("Failed to add user. Please try again.");
      // }

      const newUser = JSON.parse(responseText); // Parsuj odpowiedź jako JSON
      setUsers((prevUsers) => [...prevUsers, newUser]); // Aktualizuj stan
    } catch (error) {
      console.error("Error adding user:", error);
      alert("Failed to add user. Please try again.");
    }
  };
  return (
    <>
      <Header></Header>
      <p>
        These options don't work for now, but they just might work in the
        future!
      </p>
      <>
        <h1>Form and users list</h1>
        <Form addUser={addUser} />
        <UsersList users={users} />
      </>
      <Footer></Footer>
    </>
  );
}

export default App;
