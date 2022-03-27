import React, { useState } from "react";

const RegisterForm = () => {

  const [state, setState] = useState({
    name: "",
    surname: "",
    email: "",
    password: "",
    repeatPassword: ""
  })
  
  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setState(prevState => ({
        ...prevState,
        [event.target.id] : event.target.value
    }))
  }

  return (
    <div>
      <form>

        <div className="form-outline mb-4">
          <label className="form-label">Name</label>
          <input value={state.name} onChange={handleChange} type="text" id="name" className="form-control" />
        </div>

        <div className="form-outline mb-4">
          <label className="form-label">Surname</label>
          <input value={state.surname} onChange={handleChange} type="text" id="surname" className="form-control" />
        </div>

        <div className="form-outline mb-4">
          <label className="form-label">Email</label>
          <input value={state.email} onChange={handleChange} type="email" id="email" className="form-control" />
        </div>

        <div className="form-outline mb-4">
          <label className="form-label">Password</label>
          <input value={state.password} onChange={handleChange} type="password" id="password" className="form-control" />
        </div>

        <div className="form-outline mb-4">
          <label className="form-label">Repeat password</label>
          <input value={state.repeatPassword} onChange={handleChange} type="password" id="repeatPassword" className="form-control" />
        </div>

        <div className="form-check d-flex justify-content-center mb-4">
          <input
            className="form-check-input me-2"
            type="checkbox"
            id="registerCheck"
          />
          <label className="form-check-label" htmlFor="registerCheck">
            I have read and agree to the terms
          </label>
        </div>

        <button type="submit" className="btn btn-primary btn-block mb-3">Sign in</button>
      </form>
    </div>
  );

}

export default RegisterForm;