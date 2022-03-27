import React from 'react';
import './App.css';
import Logger from './components/Logger/Logger';
import Dashboard from './components/products/Dashboard';

export const APIRoute = "http://localhost:8080/api/v1/";

const App = () => {
  
  return (
    <div className="container-fluid vh-100">
      <Dashboard propUserId={1}/>
    </div>
  );

};



export default App;
