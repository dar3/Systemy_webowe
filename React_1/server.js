import express from "express";
import bodyParser from "body-parser";
import mysql from "mysql";
import fs from "fs";
import cors from "cors";
import path from "path";

const app = express();
const port = 5000;

// Pełna ścieżka do pliku users.json
const USERS_FILE = path.join(
  "D:/Documents/Programming_projects/spring_react_projects/Systemy_webowe/React_1",
  "users.json"
);

// Konfiguracja CORS
app.use(cors());

// Obsługa JSON w żądaniach
app.use(express.json());
app.use(bodyParser.json());

// Obsługa zapytań preflight (OPTIONS)
app.options("*", cors());

// Konfiguracja bazy danych
const db = mysql.createConnection({
  host: "localhost",
  user: "root",
  password: "",
  database: "test_db",
});

db.connect((err) => {
  if (err) {
    console.error("Database connection error:", err);
    process.exit(1);
  }
  console.log("Connection with database established.");
});

// Endpoint POST do dodawania użytkownika
app.post("/submit", (req, res) => {
  const { name, age } = req.body;

  if (!name || !age) {
    return res.status(400).json({ error: "Name and age are required." });
  }

  const query = "INSERT INTO users (name, age) VALUES (?, ?)";
  db.query(query, [name, age], (err, result) => {
    if (err) {
      console.error("Error saving to database:", err);
      return res.status(500).json({ error: "Failed to save to database." });
    }

    const newUser = { id: result.insertId, name, age };

    // Odczyt  pliku JSON
    fs.readFile(USERS_FILE, (err, data) => {
      let users = [];
      if (!err) {
        try {
          users = JSON.parse(data);
        } catch (parseErr) {
          console.warn("Failed to parse users.json, resetting file.");
        }
      }

      users.push(newUser);

      fs.writeFile(USERS_FILE, JSON.stringify(users, null, 2), (writeErr) => {
        if (writeErr) {
          console.error("Error writing to users.json:", writeErr);
          return res
            .status(500)
            .json({ error: "Failed to save user to JSON file." });
        }

        res.status(201).json(newUser); // Sukces - nowy użytkownik
      });
    });
  });
});

// Endpoint GET do pobierania użytkowników
app.get("/users", (req, res) => {
  const query = "SELECT * FROM users";

  db.query(query, (err, results) => {
    if (err) {
      console.error("Error fetching users from database:", err);
      return res.status(500).json({ error: "Failed to fetch users." });
    }

    // Zapisz dane do pliku JSON
    fs.writeFile(USERS_FILE, JSON.stringify(results, null, 2), (writeErr) => {
      if (writeErr) {
        console.error("Error writing to users.json:", writeErr);
        return res
          .status(500)
          .json({ error: "Failed to save users to JSON file." });
      }

      res.json(results); // Sukces - zwróć dane użytkowników
    });
  });
});

// Start serwera
app.listen(port, () => {
  console.log(`Server is running at http://localhost:${port}`);
});
