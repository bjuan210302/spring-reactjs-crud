import React, { useEffect, useState } from 'react';
import './App.css';
import Logger, { User } from './components/Logger/Logger';
import Dashboard from './components/products/Dashboard';

import { Routes, Route, Navigate, BrowserRouter } from "react-router-dom";

// Tried useNavigate, can't redirect outside context
// https://github.com/remix-run/react-router/issues/8264
// Workaroud for redirections is createBrowserHistory and HistoryRouter
import { createBrowserHistory } from 'history';
export const history = createBrowserHistory();

export const APIRoute = "http://localhost:8080/api/v1/";

const App = () => {
  const [user, setUser] = useState<User>()

  const userLoggedIn = (newUserCreds: User) => {
    setUser(newUserCreds)
  }

  return (
    <BrowserRouter>
      <div className="container-fluid vh-100">

        <Routes>
          <Route path="/" element={
            <Logger onUserLoggedIn={userLoggedIn} />
          } />
          <Route path="/dashboard" element={<Dashboard propUser={user!} />} />
        </Routes>
      </div>
    </BrowserRouter>
  );

};

export default App;
