import React, { useState } from "react";
import Login from "./Login";
import LoginRegisterButonsButtons from "./LoginRegisterButtons";
import Register from "./Register";

const Home = () => {

  const [state, setState] = useState({
    showLogin: true
  })

  const handleLoginChange = (loginActive: boolean):void => {
    setState({showLogin: loginActive})
    console.log(state.showLogin)
  }

  return (
    <div className="d-flex h-100 align-items-center justify-content-center">
      <div className="d-flex">
        
        <div className="row py-4 px-2 shadow">
          <LoginRegisterButonsButtons loginActive={true} onSelectedLoginChanged={handleLoginChange} />
          {state.showLogin ? <Login /> : null }
          {!state.showLogin ? <Register /> : null}
        </div>

      </div>
    </div>
  );

}

export default Home;