import React from "react";

function UsersList({ users }) {
  return (
    <div>
      <h2>Users List</h2>
      {users.length === 0 ? (
        <p>No users added yet.</p>
      ) : (
        <ul>
          {users.map((user, index) => (
            <li key={index}>
              {user.name} - {user.age}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default UsersList;
