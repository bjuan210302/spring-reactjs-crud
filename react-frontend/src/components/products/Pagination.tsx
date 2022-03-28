import React from 'react';

export interface PaginationProps {
  postsPerPage: number,
  totalPosts: number,
  paginate(pageNumber: number): any
}

const Pagination = (props: PaginationProps) => {

  //Array with numbers of pages from 1 to ceiling(props.totalPosts / props.postsPerPage)
  const pageNumbers = Array.from({length: Math.ceil(props.totalPosts / props.postsPerPage)}, (_, i) => i + 1)

  return (
      <div className='d-flex align-items-center justify-content-center h-auto'>
        {pageNumbers.map(number => (
          <div key={number} className='page-item'>
            <a onClick={() => props.paginate(number)} className='page-link'>
              {number}
            </a>
          </div>
        ))}
      </div>
  );
};

export default Pagination;