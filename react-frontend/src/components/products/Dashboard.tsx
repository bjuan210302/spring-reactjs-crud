import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { APIRoute } from '../../App';
import Products, { Product } from './Products';
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
  const [productsPerPage] = useState(10);

  // This component will get rendered when the user press the Login or Register buttons.
  // 
  useEffect(() => {
    if (!props.propUser) {
      navigate("/")
      return
    }

    const getUserProducts = async () => {
      setLoading(true);

      console.log("trying to load from user " + props.propUser)

      axios.post(APIRoute + 'products/', {
        id: props.propUser.id
      }).then((res) => {
        console.log(res)
        setProducts(res.data);
        setLoading(false);

      });
    };

    getUserProducts();
  }, []);


  const indexOfLastPost = currentPage * productsPerPage;
  const indexOfFirstPost = indexOfLastPost - productsPerPage;
  const productsSubset = products.slice(indexOfFirstPost, indexOfLastPost);


  // (kind of) Custom hook to let ProductRegister scalate the productRegisterRequest to parent.
  // Using this because passing a function thru Outlet's context behaves weird.
  // Could also be done creating a hook for an object with a function I think, haven't tested.
  // See https://reactrouter.com/docs/en/v6/api#useoutletcontext
  // https://stackoverflow.com/questions/55621212/is-it-possible-to-react-usestate-in-react
  const onProductRegistrationRequest = (productCandidate: Product) => {
    console.log(productCandidate)

    axios.post(APIRoute + "products/save", {
      ...productCandidate,
      owner: {
        id: props.propUser.id
      }
    }).then((res) =>{
      //Paginate new object
      console.log(res.data)
    });

  }
  const [registrateProduct, setRegistrateProduct] =
    React.useState<(x: Product) => {}>(() => onProductRegistrationRequest);
  // It looks really ugly tho

  const paginate = (newPage: number) => setCurrentPage(newPage)

  return (
    <ul className='list-group mb-4'>
      <Products products={productsSubset} loading={loading} />
      <Pagination postsPerPage={productsPerPage} totalPosts={products.length} paginate={paginate} />
      <Link to={"register"} className="btn btn-primary mt-2"> Add Product </Link>
      <Outlet context={{ registrateProduct }} />

    </ul>
  );

};

type ContextType = { registrateProduct: ((x: Product) => {}) };
export function useRegistrateProduct() {
  return useOutletContext<ContextType>();
}

export default Dashboard;