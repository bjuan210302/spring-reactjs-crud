import axios from 'axios';
import React, { useState } from 'react'
import { APIRoute } from '../../App';
import { UserSessionObeserver } from './Logger';

export interface LoginInfo {
    email: string;
    password: string;
}

const LoginForm = (props: UserSessionObeserver) => {

  const [state, setState] = useState<LoginInfo>({
    email: "",
    password: ""
  })
  
  // Took me ages to understand this btw
  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setState(prevState => ({
        ...prevState,
        [event.target.id] : event.target.value
    }))
  }

  const login = (event: React.MouseEvent<HTMLElement>) => {
    const loginAndSend = async () => {

      const res = await axios.post(APIRoute + 'users/login/', state);

      console.log(res.data)
      props.onUserLoggedIn(res.data)
    };

    loginAndSend();
  }

  return (
    <div>
      <form>

        <div className="form-group py-2">
          <label className="form-label">Email address</label>
          <input value={state.email} onChange={handleChange} id="email" type="email" className="form-control" />
          <small className="form-text">We'll never share your email with anyone else.</small>
        </div>

        <div className="form-group py-2">
          <label className="form-label">Password</label>
          <input value={state.password} onChange={handleChange} id="password" type="password" className="form-control"  />
        </div>

        <button onClick={login} className="btn btn-primary mt-2">Log in</button>
        
      </form>
    </div>
  )
}


export default LoginForm;