import React, { useState } from "react";

export interface LoginRegisterButonsButtonsProps {
  loginActive: boolean;
  onSelectedLoginChanged(loginActive: boolean): any; 
}

const LoginRegisterButonsButtons = (props: LoginRegisterButonsButtonsProps) => {

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
