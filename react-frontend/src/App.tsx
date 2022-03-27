import React, { useState } from 'react';
import './App.css';
import Logger, { User } from './components/Logger/Logger';
import Dashboard from './components/products/Dashboard';

import { BrowserRouter, Routes, Route, Navigate, } from "react-router-dom";


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
            <Logger onUserLoggedIn={userLoggedIn}/>
          } />
          <Route path="/dashboard" element={
            <Dashboard propUserId={user!}/> 
          } />
      </Routes>

      
    </div>
    </BrowserRouter>
  );

};

export default App;
