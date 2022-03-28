import React, { useState } from "react";
import { useNavigate } from "react-router";
import LoginForm from "./LoginForm";
import LoginRegisterButonsButtons from "./LoginRegisterButtons";
import RegisterForm from "./RegisterForm";

// Dont know where is the right place to put this model defs.
export interface User {
  id: number,
  name: string,
  surname: string,
  password: string,
}

export interface UserSessionObeserver {
  onUserLoggedIn(newUserCreds: User): any
}

const Logger = (props: UserSessionObeserver) => {

  let navigate = useNavigate();
  
  const [showLogin, setShowLogin] = useState(true)

  const handleLoginOption = (loginActive: boolean) => {
    setShowLogin(loginActive)
  }

  const onUserLoggedIn = (newUserCreds: User) => {
    props.onUserLoggedIn(newUserCreds)
    navigate("/dashboard", {replace: true})
  }
  // The Forms tell the Logger when the user logs in, and the Logger
  // then tells the main App. See line 34: onUserLoggedIn={props.onUserLoggedIn}
  return (
    <div className="d-flex h-100 align-items-center justify-content-center">
      <div className="d-flex">
        
        <div className="row py-4 px-2 shadow">
          <LoginRegisterButonsButtons loginActive={true} onSelectedLoginChanged={handleLoginOption} />
          {showLogin ? <LoginForm onUserLoggedIn={onUserLoggedIn}/> : null }
          {!showLogin ? <RegisterForm onUserLoggedIn={onUserLoggedIn}/> : null}
        </div>

      </div>
    </div>
  );

}

export default Logger;