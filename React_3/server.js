import express from "express";
import bodyParser from "body-parser";
import fs from "fs";
import cors from "cors";

const app = express();
app.use(bodyParser.json());
app.use(cors());

let schedule = [
  {
    id: 1,
    name: "Aplikacje mobilne na platformę Android",
    duration: "2 godz.",
  },
  { id: 2, name: "Język angielski C1", duration: "1.5 godz." },
  { id: 3, name: "Programowanie", duration: "3 godz." },
  { id: 4, name: "Techniki prezentacji", duration: "2 godz." },
  { id: 5, name: "Programowanie systemów webowych", duration: "1 godz." },
];

app.get("/schedule", (req, res) => {
  res.json(schedule);
});

app.post("/schedule", (req, res) => {
  const { name, duration } = req.body;
  const id = schedule.length ? schedule[schedule.length - 1].id + 1 : 1;
  schedule.push({ id, name, duration });
  res.json(schedule);
});

app.delete("/schedule/:id", (req, res) => {
  const id = parseInt(req.params.id);
  schedule = schedule.filter((item) => item.id !== id);
  res.json(schedule);
});

app.post("/save", (req, res) => {
  fs.writeFileSync("schedule.json", JSON.stringify(schedule, null, 2));
  res.send("Plan zapisano do pliku.");
});

app.listen(5000, () => {
  console.log("Server running on http://localhost:5000");
});
