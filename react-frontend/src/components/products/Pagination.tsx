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
    <nav>
      <ul className='pagination'>
        {pageNumbers.map(number => (
          <li key={number} className='page-item'>
            <a onClick={() => props.paginate(number)} className='page-link'>
              {number}
            </a>
          </li>
        ))}
      </ul>
    </nav>
  );
};

export default Pagination;