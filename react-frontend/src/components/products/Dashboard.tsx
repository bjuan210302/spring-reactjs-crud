import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { APIRoute } from '../../App';
import Products from './Products';
import Pagination from './Pagination';

export interface DashboardProps {
  propUserId: number
}

const Dashboard = (dashboardProps: DashboardProps) => {

  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(false);
  const [currentPage, setCurrentPage] = useState(1);
  const [productsPerPage] = useState(2);

  useEffect(() => {
    const getUserProducts = async () => {
      setLoading(true);

      const res = await axios.post(APIRoute + 'products/', {
        id: dashboardProps.propUserId
      });

      console.log(res)
      setProducts(res.data);
      setLoading(false);
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