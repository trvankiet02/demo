package vn.iotstar.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import vn.iotstar.entity.OrderItem;

public interface IOrderItemService {

	void deleteAll();

	OrderItem getById(Integer id);

	OrderItem getOne(Integer id);

	void deleteById(Integer id);

	long count();

	<S extends OrderItem, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void deleteAllInBatch(Iterable<OrderItem> entities);

	void deleteInBatch(Iterable<OrderItem> entities);

	Optional<OrderItem> findById(Integer id);

	List<OrderItem> findAllById(Iterable<Integer> ids);

	List<OrderItem> findAll(Sort sort);

	Page<OrderItem> findAll(Pageable pageable);

	List<OrderItem> findAll();

	<S extends OrderItem> Optional<S> findOne(Example<S> example);

	<S extends OrderItem> S save(S entity);


}
