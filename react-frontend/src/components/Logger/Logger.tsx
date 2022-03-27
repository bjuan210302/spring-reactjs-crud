import React from "react";
import Login from "./Login";
import LoginRegisterButonsButtons from "./LoginRegisterButtons";
import Register from "./Register";

const Home = () => {
  let showLogin: boolean = false // dotn know if allowed

  const handleLoginChange = (loginActive: boolean):void => {
    showLogin = loginActive;
    console.log(showLogin)
  }

  return (
    <div className="d-flex h-100 align-items-center justify-content-center">
      <div className="d-flex">
        
        <div className="row py-4 px-2 shadow">
          <LoginRegisterButonsButtons loginActive={true} onSelectedLoginChanged={handleLoginChange} />
          {showLogin && <Login />}
          {!showLogin && <Register />}
        </div>

      </div>
    </div>
  );

}

export default Home;