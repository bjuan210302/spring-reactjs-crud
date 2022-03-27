import React, { useState } from 'react'

export interface LoginInfo {
    email: string;
    password: string;
}

const Login = () => {

  const [state, setState] = useState({
    email : "",
    password : ""
  })
  
  // Took me ages to understand this btw
  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setState(prevState => ({
        ...prevState,
        [event.target.id] : event.target.value
    }))
  }

  return (
    <div>
      <form>

        <div className="form-group py-2">
          <label>Email address</label>
          <input value={state.email} onChange={handleChange} id="email" type="email" className="form-control" placeholder="Enter email" />
          <small className="form-text">We'll never share your email with anyone else.</small>
        </div>

        <div className="form-group py-2">
          <label>Password</label>
          <input value={state.password} onChange={handleChange} id="password" type="password" className="form-control" placeholder="Password" />
        </div>

        <button type="submit" className="btn btn-primary mt-2">Submit</button>
        
      </form>

      <pre>
        {JSON.stringify(state, null, 2)}
      </pre>
    </div>
  )
}


export default Login;