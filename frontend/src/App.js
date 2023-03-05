import { useEffect, useState } from "react";
import "./App.css";

function App() {
  const [users, setUsers] = useState([]);

  useEffect((users) => {
    fetch("http://127.0.0.1:8080/users")
      .then((response) => response.json())
      .then((data) => {
        setUsers(data);
      });
  }, []);

  return (
    <div className="App">
      <header className="App-header">
        <p>User Dashboard</p>
      </header>
      <div>
        {users.map((user) => (
          <div key={user.id} className="User">
            <p style={{ margin: 20 }}>{user.id}</p>
            <p style={{ margin: 20 }}>{user.name}</p>
            <p style={{ margin: 20 }}>{user.email}</p>
            <p style={{ margin: 20 }}>{user.status}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default App;
