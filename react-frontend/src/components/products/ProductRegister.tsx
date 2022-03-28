import axios from 'axios';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { APIRoute } from '../../App';
import { useRegistrateProduct } from './Dashboard';
import { Product } from './ProductsFrame';

const ProductRegister = () => {

  const navigate = useNavigate();
  const [state, setState] = useState<Product>({
    name: "",
    description: "",
    price: 0,
  })

  const handleChange = (event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement >) => {
    setState(prevState => ({
      ...prevState,
      [event.target.id]: event.target.value
    }))
  }

  const parentRegistrator = useRegistrateProduct();

  const addProduct = (event: React.MouseEvent<HTMLElement>) => {
    const tryToRegister = () => {
      // Elevate this because parent is the component with the user credentials
      // and the component in charge of pagination
      parentRegistrator.registrateProduct(state)
    };
    
    //Do data comprobations
    if(true){
      tryToRegister();
      navigate(-1)
    }

  }

  const modalBootstrapClasses = 
    "d-flex w-100 vh-100 align-items-center justify-content-center " +
    "position-absolute top-0 start-0 " +
    "";

  // Because bootstrap's bg-opacity-x won't work
  const modalAditionalStyle = {
    backgroundColor: 'rgba(0, 0, 0, 0.5)'
  }

  return (
    <div style={modalAditionalStyle} className={modalBootstrapClasses} onClick={() => { navigate(-1) }}>

      <div className="bg-white card p-4 w-50"
      onClick={e => e.stopPropagation()}>

        <form>

          <div className="form-group py-2">
            <label className="form-label">Product name</label>
            <input value={state.name} onChange={handleChange} id="name" type="text" className="form-control" />
          </div>

          <div className="form-group py-2">
            <div className="form-label">Description</div>-
            <small className="form-text">What's your idea?</small>
            <textarea value={state.description} onChange={handleChange} id="description" className="form-control" />
          </div>

          <div className="form-group py-2">
            <label className="form-label">Price</label>
            <input value={state.price} onChange={handleChange} id="price" type="number" className="form-control" />
            <small className="form-text">Must be greater than zero.</small>
          </div>

        </form>
        <button onClick={addProduct} className="btn btn-primary mt-2">Add Product</button>

      </div>
    </div>
  )
}


export default ProductRegister;