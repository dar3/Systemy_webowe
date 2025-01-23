import React, { useState, useEffect } from "react";
import axios from "axios";

const App = () => {
  const [schedule, setSchedule] = useState([]);
  const [newItem, setNewItem] = useState({ name: "", duration: "" });
  const [error, setError] = useState("");

  useEffect(() => {
    axios
      .get("http://localhost:5000/schedule")
      .then((res) => setSchedule(res.data));
  }, []);

  const addItem = () => {
    if (!newItem.name.trim() || !newItem.duration.trim()) {
      setError("Wszystkie pola są wymagane!");
      return;
    }
    const formattedItem = {
      ...newItem,
      duration: newItem.duration.endsWith("godz.")
        ? newItem.duration
        : `${newItem.duration} godz.`,
    };
    axios
      .post("http://localhost:5000/schedule", formattedItem)
      .then((res) => setSchedule(res.data));
    setNewItem({ name: "", duration: "" });
    setError("");
  };

  const deleteItem = (id) => {
    axios
      .delete(`http://localhost:5000/schedule/${id}`)
      .then((res) => setSchedule(res.data));
  };

  const saveToFile = () => {
    axios
      .post("http://localhost:5000/save")
      .then(() => alert("Plan zapisano do pliku JSON."));
  };

  return (
    <div
      style={{
        padding: "20px",
        maxWidth: "600px",
        margin: "0 auto",
        fontFamily: "Arial",
      }}
    >
      <h1>Lista przedmiotów</h1>
      <ul>
        {schedule.map((item) => (
          <li
            key={item.id}
            style={{
              border: "1px solid #ccc",
              margin: "10px 0",
              padding: "10px",
              backgroundColor: "green",
            }}
          >
            <strong>{item.name}</strong> - {item.duration}
            <button
              onClick={() => deleteItem(item.id)}
              style={{ marginLeft: "10px", backgroundColor: "red" }}
            >
              Usuń
            </button>
          </li>
        ))}
      </ul>
      <div>
        <input
          type="text"
          placeholder="Nazwa przedmiotu"
          value={newItem.name}
          onChange={(e) => setNewItem({ ...newItem, name: e.target.value })}
          style={{ marginRight: "10px" }}
        />
        <input
          type="number"
          min={1}
          required
          placeholder="Czas trwania"
          value={newItem.duration}
          onChange={(e) => setNewItem({ ...newItem, duration: e.target.value })}
          onKeyDown={(e) => {
            if (!/[0-9]/.test(e.key)) {
              e.preventDefault();
            }
          }}
        />

        <button
          onClick={addItem}
          style={{
            backgroundColor: "blue",
            color: "white",
            border: "none",
            padding: "10px 10px",
            borderRadius: "5px",
            cursor: "pointer",
            marginLeft: "10px",
          }}
        >
          Dodaj
        </button>
      </div>
      {error && <p style={{ color: "red" }}>{error}</p>}{" "}
      <button
        onClick={saveToFile}
        style={{ marginTop: "20px", backgroundColor: "purple" }}
      >
        Zapisz plan
      </button>
    </div>
  );
};

export default App;
