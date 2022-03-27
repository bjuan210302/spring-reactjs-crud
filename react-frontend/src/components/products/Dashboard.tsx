import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { APIRoute } from '../../App';

const Dashboard = (propUserId: number) => {

  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {

    const getUserProducts = async () => {
      setLoading(true);
      const res = await axios.get(APIRoute + 'products'); // .../api/v1/ + 
      setPosts(res.data);
      setLoading(false);
    };

    getUserProducts();
  }, []);

  return (
    <ul className='list-group mb-4'>

    </ul>
  );

};

export default Dashboard;