package vn.iotstar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import vn.iotstar.entity.Category;

public interface ICategoryService {

	void deleteAll();

	void deleteAllById(Iterable<? extends Integer> ids);

	Category getById(Integer id);

	void delete(Category entity);

	Category getOne(Integer id);

	void deleteById(Integer id);

	void deleteAllInBatch();

	long count();

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	<S extends Category> boolean exists(Example<S> example);

	<S extends Category> long count(Example<S> example);

	boolean existsById(Integer id);

	Optional<Category> findById(Integer id);

	List<Category> findAllById(Iterable<Integer> ids);

	List<Category> findAll(Sort sort);

	Page<Category> findAll(Pageable pageable);

	List<Category> findAll();

	<S extends Category> Optional<S> findOne(Example<S> example);

	<S extends Category> S save(S entity);

	Page<Category> findByNameContaining(String name, Pageable pageable);

	Category findOneByName(String name);

	List<Category> findByNameContaining(String name);
	
	List<Category> findTop3ByOrderByIdAsc();

}
