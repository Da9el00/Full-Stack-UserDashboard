import React from "react";
import "../App.css";

export default function UserRow(probs) {
  return (
    <div className="User">
      <p style={{ margin: 20 }}>{probs.user.id}</p>
      <p style={{ margin: 20 }}>{probs.user.name}</p>
      <p style={{ margin: 20 }}>{probs.user.email}</p>
      <p style={{ margin: 20 }}>{probs.user.status}</p>
      <button onClick={() => probs.deleteUser(probs.user)}>Delete</button>
    </div>
  );
}
