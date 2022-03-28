import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { APIRoute } from '../../App';
import Products from './Products';
import Pagination from './Pagination';
import { User } from '../Logger/Logger';
import { useNavigate } from 'react-router';

export interface DashboardProps {
  propUser: User
}

const Dashboard = (props: DashboardProps) => {

  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(false);
  const [currentPage, setCurrentPage] = useState(1);
  const [productsPerPage] = useState(10);

  // This component will get rendered when the user press the Login or Register buttons.
  // 
  useEffect(() => {
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

  const paginate = (newPage: number) => setCurrentPage(newPage)

  return (
    <ul className='list-group mb-4'>
      <Products products={productsSubset} loading={loading} />
      <Pagination postsPerPage={productsPerPage} totalPosts={products.length} paginate={paginate}/>
    </ul>
  );

};

export default Dashboard;