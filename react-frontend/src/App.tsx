import React from 'react';
import './App.css';
import Logger from './components/Logger/Logger';

export const APIRoute = "ec2-23-20-38-158.compute-1.amazonaws.com:8080/api/v1/";

const App = () => {
  
  return (
    <div className="container-fluid vh-100">
      <Logger />
    </div>
  );

};



export default App;
