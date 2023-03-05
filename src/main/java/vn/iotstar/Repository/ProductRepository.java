package vn.iotstar.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.iotstar.entity.Product;
import vn.iotstar.entity.Store;

@Repository
public interface ProductRepository extends JpaRepository <Product,Integer>{

	List<Product> findBynameContaining(String name);
	List<Product> findBytrademarkContaining(String trademark);
	List<Product> findByratingContaining(int rating);
	List<Product> findByStore(Store store);
	
	List<Product> findTop13ByOrderBySoldDesc();
	List<Product> findTop10ByOrderByCreateatDesc();
	List<Product> findTop1ByOrderBySoldDesc();
	List<Product> findTop1ByOrderBySoldAsc();
	List<Product> findTop10ByOrderBySoldAsc();
	
}
