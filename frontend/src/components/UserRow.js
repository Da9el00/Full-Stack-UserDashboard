import React from "react";
import { useState } from "react";
import "../App.css";

export default function UserRow(probs) {
  const [id, setId] = useState(probs.user.id);
  const [name, setName] = useState(probs.user.name);
  const [email, setEmail] = useState(probs.user.email);
  const [status, setStatus] = useState(probs.user.status);

  if (probs.user.viewMode) {
    return (
      <div className="User">
        <p style={{ margin: 20 }}>{id}</p>
        <p style={{ margin: 20 }}>{name}</p>
        <p style={{ margin: 20 }}>{email}</p>
        <p style={{ margin: 20 }}>{status}</p>
        <button onClick={() => probs.deleteUser(probs.user)}>Delete</button>
        <button onClick={() => probs.editModeChange(probs.user.id)}>
          Edit
        </button>
      </div>
    );
  } else {
    return (
      <div className="User">
        <div className="NewUser">
          <p style={{ margin: 20 }}>{id}</p>
          <div className="InputBox">
            <label>Name:</label>
            <input
              type="text"
              name="name"
              value={name}
              onChange={(event) => setName(event.target.value)}
            />
          </div>
          <div className="InputBox">
            <label>Email:</label>
            <input
              type="email"
              name="email"
              value={email}
              onChange={(event) => setEmail(event.target.value)}
            />
          </div>
          <div className="InputBox">
            <label>Status:</label>
            <input
              type="text"
              name="status"
              value={status}
              onChange={(event) => setStatus(event.target.value)}
            />
          </div>
        </div>
        <button onClick={() => probs.updateUser(id, name, email, status)}>
          Update
        </button>
        <button onClick={() => probs.editModeChange(probs.user.id)}>
          Restore
        </button>
      </div>
    );
  }
}
