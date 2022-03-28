import React from 'react';
import ProductCard, { ProductCardProps } from './ProductCard';

export interface Product {
  id?: number,
  name: string,
  description: string,
  price: number,
  creationDate?: Date
}

export interface ProductsProps extends ProductCardProps {
  products: Product[],
  loading: boolean
}

const ProductsFrame = (props: ProductsProps) => {

  if (props.loading) {
    return <h2>Loading...</h2>;
  }

  return (
    <div className='row my-4 mx-auto h-75 border overflow-auto'>
      
      {props.products.map(post => (
          <div className='col-12 col-sm-6 col-md-4 col-lg-3' key={post.id}>
            <ProductCard product={post} askForCreds={props.askForCreds} notify={props.notify}/>
          </div>
        ))}

    </div>
  );

};

export default ProductsFrame;