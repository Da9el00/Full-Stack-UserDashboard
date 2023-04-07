import { useEffect, useState } from "react";
import "./App.css";
import UserRow from "./components/UserRow";
import NewUser from "./components/NewUser";

function App() {
  const [users, setUsers] = useState([]);
  const [newUsers, setNewUsers] = useState([]);
  const [newUserMaxID, setnewUserMaxID] = useState(0);

  useEffect(() => {
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

  function addNewUserInput() {
    setNewUsers((newUsers) => [...newUsers, newUserMaxID]);
    setnewUserMaxID((maxID) => newUserMaxID + 1);
  }

  function deleteNewUser(id) {
    setNewUsers((oldValues) => {
      return oldValues.filter((user) => user !== id);
    });
  }

  function updateNewUser(newUser) {
    setUsers([...users, newUser]);
  }

  return (
    <div className="App">
      <header className="App-header">
        <p>User Dashboard</p>
      </header>
      <div>
        {users.map((user) => (
          <div key={user.id}>
            <UserRow user={user} deleteUser={deleteUser} />
          </div>
        ))}
      </div>
      {newUsers.map((newUserID) => (
        <div key={newUserID}>
          <NewUser
            id={newUserID}
            deleteUser={deleteNewUser}
            updateNewUser={updateNewUser}
          ></NewUser>
        </div>
      ))}
      <div>
        <button onClick={() => addNewUserInput()}>New User</button>
      </div>
    </div>
  );
}

export default App;
