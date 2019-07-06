package com.library.LibraryService.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.library.LibraryService.model.Book;
import com.library.LibraryService.payload.book.GetAllBooksRequest;

public class BookRepositoryCustomImpl implements BookRepositoryCustom {

	 @PersistenceContext
	 private EntityManager entityManager;
	 
	@Override
	public List<Book> findAllBooksWithPagination(GetAllBooksRequest bookRequest, Pageable pageable) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Book> query = cb.createQuery(Book.class);
		
		Root<Book> book = query.from(Book.class);
			
		
		List<Predicate> predicates = new ArrayList<>();
		
		Predicate predicateForFilter= null;
		Predicate predicateForAuthor = null;
		Predicate predicateForTextFields = null;
		
		if(bookRequest.getAuthorFilter() != null && bookRequest.getAuthorFilter().strip().length()>0)
			predicateForAuthor =  cb.like(book.get("author"), bookRequest.getAuthorFilter());
		
		if(bookRequest.getFilter() != null && bookRequest.getFilter().strip().length()>0)
			predicateForFilter =  cb.like(book.get("author"), bookRequest.getFilter());		
		
		if(bookRequest.getMaxPriceFilter() != null && bookRequest.getMinPriceFilter() != null)
			predicates.add(cb.between(book.get("price"), bookRequest.getMinPriceFilter(), bookRequest.getMaxPriceFilter()));
		
		if(bookRequest.getMinPublishedDateFilter() != null && bookRequest.getMaxPublishedDateFilter() != null)
			predicates.add(cb.between(book.get("publishedDate"), bookRequest.getMinPublishedDateFilter(), bookRequest.getMaxPublishedDateFilter()));
		
		if(predicateForAuthor != null & predicateForFilter != null) {
			predicateForTextFields = cb.or(predicateForAuthor, predicateForFilter);
		} else {
			predicateForTextFields = predicateForAuthor!= null? predicateForAuthor: 
																	predicateForFilter != null? predicateForFilter: null; 
		}
		
		if(predicateForTextFields != null)
			predicates.add(predicateForTextFields);
		
		query.where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
		
		return entityManager.createQuery(query).setFirstResult(pageable.getPageNumber()*pageable.getPageSize()).setMaxResults(pageable.getPageSize()).getResultList();
	}

}
