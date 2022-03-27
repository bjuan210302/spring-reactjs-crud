import React, { useState } from "react";

// ??????? 
// stack overflow said this is how you elevate info to pared
export interface Observable {
  loginActive: boolean;
  onSelectedLoginChanged(loginActive: boolean): any; 
}

const LoginRegisterButonsButtons = (props: Observable) => {

  const [loginActive, setLoginActive] = useState(props.loginActive)

  const handleClick = (event: React.MouseEvent<HTMLElement>) => {
    setLoginActive(event.currentTarget.id == "tab-login")
    props.onSelectedLoginChanged(event.currentTarget.id == "tab-login");
  }

  return (
    <div>
      <ul className="nav nav-pills nav-justified mb-3">

        <li className="nav-item">
          <a
            className={"nav-link " + (loginActive ? 'active' : '')}
            onClick={handleClick}
            id="tab-login"
            href="#login"
          >Login</a
          >
        </li>
        <li className="nav-item">
          <a
            className={"nav-link " + (!loginActive ? 'active' : '')}
            onClick={handleClick}
            id="tab-register"
            href="#register"
          >Register</a
          >
        </li>

      </ul>
    </div>
  );

}

export default LoginRegisterButonsButtons;
