import React from 'react';

export interface Product {
    id: string,
    name: string,
    desc: string,
    price: number,
    creationDate: Date
}

export interface ProductsProps {
    products: Product[],
    loading: boolean
}

const Products = (props: ProductsProps) => {

  if (props.loading) {
    return <h2>Loading...</h2>;
  }

  return (
    <ul className='list-group mb-4'>

      {props.products.map(post => (
        <li key={post.id} className='list-group-item'>
          {post.name}
        </li>
      ))}

    </ul>
  );

};

export default Products;