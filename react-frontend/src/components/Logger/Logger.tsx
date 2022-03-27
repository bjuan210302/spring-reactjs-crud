import React, { useState } from "react";
import LoginForm from "./LoginForm";
import LoginRegisterButonsButtons from "./LoginRegisterButtons";
import RegisterForm from "./RegisterForm";

const Logger = () => {

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
          {state.showLogin ? <LoginForm /> : null }
          {!state.showLogin ? <RegisterForm /> : null}
        </div>

      </div>
    </div>
  );

}

export default Logger;