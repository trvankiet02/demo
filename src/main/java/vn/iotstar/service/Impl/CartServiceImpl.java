package vn.iotstar.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import vn.iotstar.entity.Cart;
import vn.iotstar.entity.Store;
import vn.iotstar.entity.User;
import vn.iotstar.Repository.CartRepository;
import vn.iotstar.service.ICartService;

@Service
public class CartServiceImpl implements ICartService {
	@Autowired
	CartRepository CartRepository;

	@Override
	public <S extends Cart> S save(S entity) {
		return CartRepository.save(entity);
	}

	@Override
	public <S extends Cart> Optional<S> findOne(Example<S> example) {
		return CartRepository.findOne(example);
	}

	@Override
	public List<Cart> findAll() {
		return CartRepository.findAll();
	}

	@Override
	public Page<Cart> findAll(Pageable pageable) {
		return CartRepository.findAll(pageable);
	}

	@Override
	public List<Cart> findAll(Sort sort) {
		return CartRepository.findAll(sort);
	}

	@Override
	public List<Cart> findAllById(Iterable<Integer> ids) {
		return CartRepository.findAllById(ids);
	}

	@Override
	public <S extends Cart> Page<S> findAll(Example<S> example, Pageable pageable) {
		return CartRepository.findAll(example, pageable);
	}

	@Override
	public Optional<Cart> findById(Integer id) {
		return CartRepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return CartRepository.existsById(id);
	}

	@Override
	public long count() {
		return CartRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		CartRepository.deleteById(id);
	}

	@Override
	public void delete(Cart entity) {
		CartRepository.delete(entity);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Cart getById(Integer id) {
		return CartRepository.getById(id);
	}

	@Override
	public void deleteAll() {
		CartRepository.deleteAll();
	}

	
	public Optional<Cart> findByStore(Store store) {
		// TODO Auto-generated method stub
		return CartRepository.findByStore(store);
	}

	
	public Optional<Cart> findByUser(User user) {
		// TODO Auto-generated method stub
		return CartRepository.findByUser(user);
	}

	@Override
	public List<Cart> findByStore(Integer Storeid) {
		// TODO Auto-generated method stub
		return CartRepository.findByStore(Storeid);
	}

	@Override
	public List<Cart> findByUser(Integer id) {
		// TODO Auto-generated method stub
		return CartRepository.findByUser(id);
	}



}
