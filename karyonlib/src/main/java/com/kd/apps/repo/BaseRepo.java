package com.kd.apps.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Pradyumna Roy Extension of {@link PagingAndSortingRepository} and
 *         {@link JpaSpecificationExecutor} to provide additional methods to
 *         retrieve entities using the pagination and sorting abstraction along
 *         with specification abstraction.
 * 
 *         Each entity repository will implement this interface
 * 
 */
@NoRepositoryBean
public interface BaseRepo<E, ID extends Serializable>
		extends PagingAndSortingRepository<E, ID>, JpaSpecificationExecutor<E> {

}
