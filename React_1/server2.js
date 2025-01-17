import express from "express";
import bodyParser from "body-parser";
import mysql from "mysql";
import fs from "fs";
import cors from "cors";

const app = express();

const port = 5000;

app.use(cors());

app.use(bodyParser.json());

const db = mysql.createConnection({
  host: "localhost",
  user: "root",
  password: "",
  database: "test_db",
});

db.connect((err) => {
  if (err) throw err;
  console.log("Connection with database established.");
});

app.post("/submit", (req, res) => {
  console.log("User data:", req.body);
  const { name, age } = req.body;
  const query = "INSERT INTO users (name, age) VALUES (?, ?)";
  db.query(query, [name, age], (err) => {
    if (err) {
      console.error("Error saving to db:", err);
      res.status(500).send("Error saving to db");
    } else {
      res.send("Added data to db");
    }
  });
});

app.get("/users", (req, res) => {
  const query = "SELECT * FROM users";
  db.query(query, (err, results) => {
    if (err) throw err;

    fs.writeFileSync("users.json", JSON.stringify(results, null, 2));
    res.json(results);
  });
});

app.listen(port, () => {
  console.log(`Server is active at http://localhost:${port}`);
});
