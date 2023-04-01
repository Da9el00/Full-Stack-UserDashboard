import { useEffect, useState } from "react";
import "./App.css";
import UserRow from "./Userrow";

function App() {
  const [users, setUsers] = useState([]);

  useEffect((users) => {
    fetch("http://127.0.0.1:8080/users")
      .then((response) => response.json())
      .then((data) => {
        setUsers(data);
      });
  }, []);

  function deleteUser(value) {
    console.log("Deleting user");
    fetch("http://127.0.0.1:8080/delete-user?id=" + value.id, {
      method: "DELETE",
    }).then(() => {
      setUsers((oldValues) => {
        return oldValues.filter((user) => user !== value);
      });
    });
  }

  return (
    <div className="App">
      <header className="App-header">
        <p>User Dashboard</p>
      </header>
      <div>
        {users.map((user) => (
          <div key={user.id} className="User">
            <UserRow user={user} deleteUser={deleteUser} />
          </div>
        ))}
      </div>
    </div>
  );
}

export default App;
