import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { APIRoute } from '../../App';
import ProductsFrame, { Product } from './ProductsFrame';
import Pagination from './Pagination';
import { User } from '../Logger/Logger';
import { Outlet, useNavigate } from 'react-router';
import { Link, Route, useOutletContext } from 'react-router-dom';
import ProductRegister from './ProductRegister';

export interface DashboardProps {
  propUser: User
}

const Dashboard = (props: DashboardProps) => {

  const navigate = useNavigate();
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(false);
  const [currentPage, setCurrentPage] = useState(1);
  const [productsPerPage] = useState(8);

  // This component will get rendered when the user press the Login or Register buttons.
  // 
  useEffect(() => {
    if (!props.propUser) {
      navigate("/")
      return
    }

    const getUserProducts = async () => {
      
      
      const res = await axios.post(APIRoute + 'products/', {
        id: props.propUser.id
      });
      setProducts(res.data);

    };

    getUserProducts();
    setLoading(false);
  }, [loading]);


  // (kind of) Custom hook to let ProductRegister scalate the productRegisterRequest to parent.
  // Using this because passing a function thru Outlet's context behaves weird.
  // Could also be done creating a hook for an object with a function I think, haven't tested.
  // See https://reactrouter.com/docs/en/v6/api#useoutletcontext
  // https://stackoverflow.com/questions/55621212/is-it-possible-to-react-usestate-in-react
  const onProductRegistrationRequest = (productCandidate: Product) => {
    console.log(productCandidate, "Candidato")

    axios.post(APIRoute + "products/save", {
      ...productCandidate,
      owner: {
        id: props.propUser.id
      }
    }).then((res) => {
      setLoading(true);
    });

  }
  const [registrateProduct, setRegistrateProduct] =
    React.useState<(x: Product) => {}>(() => onProductRegistrationRequest);
  // It looks really ugly tho

  let indexOfLastPost = currentPage * productsPerPage;
  let indexOfFirstPost = indexOfLastPost - productsPerPage;
  let productsSubset = products.slice(indexOfFirstPost, indexOfLastPost);

  const paginate = (newPage: number) => setCurrentPage(newPage)

  return (
    <div className='row h-100 mx-4'>
      <div className='d-flex mt-auto text-primary'>
        <h1 className='fs-1'>
          My Products
        </h1>

        <Link to={"register"} className="my-auto mx-2 text-bold">
        <span className='mx-2'>
          Add new product
        </span>
          <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" className="bi bi-plus-circle" viewBox="0 0 16 16">
            <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
            <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z" />
          </svg>
        </Link>

      </div>
      <ProductsFrame products={productsSubset} loading={loading} />
      <Pagination postsPerPage={productsPerPage} totalPosts={products.length} paginate={paginate} />
      <Outlet context={{ registrateProduct }} />

    </div>
  );

};

type ContextType = { registrateProduct: ((x: Product) => {}) };
export function useRegistrateProduct() {
  return useOutletContext<ContextType>();
}

export default Dashboard;