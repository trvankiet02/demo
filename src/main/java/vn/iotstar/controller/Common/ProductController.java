package vn.iotstar.controller.Common;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.BeanUtils;

import vn.iotstar.entity.Cart;
import vn.iotstar.entity.CartItem;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Order;
import vn.iotstar.entity.Product;
import vn.iotstar.entity.Review;
import vn.iotstar.entity.Store;
import vn.iotstar.entity.User;
import vn.iotstar.model.CartItemModel;
import vn.iotstar.model.CartModel;
import vn.iotstar.model.ProductModel;
import vn.iotstar.model.ReviewModel;
import vn.iotstar.service.ICartItemService;
import vn.iotstar.service.ICartService;
import vn.iotstar.service.ICategoryService;
import vn.iotstar.service.IProductService;
import vn.iotstar.service.IReviewService;
import vn.iotstar.service.IStoreService;
import vn.iotstar.service.IUserService;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	IProductService productService;
	@Autowired
	ServletContext application;
	@Autowired
	ICategoryService categoryService;
	@Autowired
	IStoreService storeService;
	@Autowired
	IUserService userService;
	@Autowired
	IReviewService reviewService;
	@Autowired
	ICartService cartService;
	@Autowired
	ICartItemService iCartItemService;

	@Autowired
	HttpSession session;


	
	//User User= (User)session.getAttribute("user");
	
	  public Cart CreateCart1(Integer Storeid) { 
		  User User=(User)session.getAttribute("user"); 
		  List<Cart> cart1=cartService.findByStore(Storeid); 
		  List<Cart> cart2=cartService.findByUser(User.getId()); 
		  Optional<Store> store =storeService.findById(Storeid);
		  Store stores = store.get(); 
		  if(!cart1.isEmpty()&&!cart2.isEmpty()) {
		  for(Cart item :cart1) 
			  for(Cart item2 : cart2) { 
				  if (item.getId()==item2.getId()) 
					  return item; 
				  } }
		  Cart entity = new Cart(); 
		  entity.setUser(User);
		  entity.setStore(stores); 
		  Date getDate = new Date();
		  entity.setCreateat(getDate); 
		  entity.setUpdateat(getDate);
		  cartService.save(entity); 
		  return entity; 
		  }
	 
	
	public Cart CreateCart2(int Storeid) { 
		User User=(User)session.getAttribute("user");
	
		Optional<Store> store = storeService.findById(Storeid);
		List<Cart> cart1=cartService.findByStore(Storeid); 
		List<Cart> cart2=cartService.findByUser(User.getId()); 
		 Cart entity1 = new Cart(); 
		Store stores = store.get();

		if ( cartService.findByUser(User).isEmpty()) {
			//gọi hàm tạo item
			Cart entity = new Cart();
			entity.setUser(User);
			entity.setStore(stores);
			Date getDate = new Date();
			entity.setCreateat(getDate);
			entity.setUpdateat(getDate);
			cartService.save(entity);
			return entity;			
		}
		else {	 
			  if(!cart1.isEmpty()&&!cart2.isEmpty()) {
			  for(Cart item :cart1) 
				  for(Cart item2 : cart2) { 
					  if (item.getId()==item2.getId()) 
						  return item; 
					  } }			
			}
		return entity1;
		
		
		}
	public Cart CreateCart(int Storeid) { 
		User user=(User)session.getAttribute("user");
		Optional<Store> store = storeService.findById(Storeid);
		Store stores = store.get();
		if (cartService.findByStore(stores).isEmpty()&& cartService.findByUser(user).isEmpty()) {
			//gọi hàm tạo item
			Cart entity = new Cart();
			entity.setUser(user);
			entity.setStore(stores);
			Date getDate = new Date();
			entity.setCreateat(getDate);
			entity.setUpdateat(getDate);
			cartService.save(entity);
			return entity;
			
		}
		else {
			// tạo CreateCart
			Optional<Cart> cart = cartService.findByUser(user);
			Cart carts = cart.get();;
			return carts;
			
			}
		
		
		}
@PostMapping("AddCart")
public ModelAndView AddCart(ModelMap model, @Valid @ModelAttribute("cart") CartModel cart,
		@Valid @ModelAttribute("cartit") CartItemModel cartit, BindingResult result) {

		Cart cartid = CreateCart(cart.getStoreid());
		CartItem entity = new CartItem();
		BeanUtils.copyProperties(cartit, entity);
		entity.setProduct(productService.getById(cartit.getProductid()));
		entity.setCart(cartid);
		Date getDate = new Date();
		entity.setCreateat(getDate);
		entity.setUpdateat(getDate);
		iCartItemService.save(entity);
		String message = "";
		message = "Đã thêm vào giỏ hàng";
		model.addAttribute("message", message);
		String a = "redirect:/product/user/list/" + cartit.getProductid();
		return new ModelAndView(a, model);
	}

	public void TBDanhGia() {
		List<Product> pro = productService.findAll();
		for (Product item : pro) {
			if (!item.getReviews().isEmpty())
				item.setRating(productService.avgRating(item));
			productService.save(item);
		}
	}

	@GetMapping("")
	public String list(ModelMap model) {
		List<Product> page = productService.findAll();
		model.addAttribute("product", page);
		return "product/list";
	}

	@GetMapping("user")
	public String UserList(ModelMap model) {
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		List<Product> page = productService.findAll();
		List<Category> cate = categoryService.findAll();
		model.addAttribute("product", page);
		model.addAttribute("category", cate);

		long soSanPhamTrongGio = 0;
		if (user != null) {
			for (Cart cart : user.getCarts()) {
				Cart cartn = cart;
				soSanPhamTrongGio += iCartItemService.countByCart(cartn);
			}
		}

		model.addAttribute("count", soSanPhamTrongGio);

		model.addAttribute("count", soSanPhamTrongGio);
		return "user/product/list";
	}

	@GetMapping("add")
	public String add(ModelMap model) {
		ProductModel product = new ProductModel();
		product.setIsEdit(false);
		model.addAttribute("product", product);
		return "product/addOrEdit";
	}

	@GetMapping("edit/{id}")
	public ModelAndView edit(ModelMap model, @PathVariable("id") int id) throws IOException {
		Optional<Product> opt = productService.findById(id);

		ProductModel product = new ProductModel();
		if (opt.isPresent()) {
			Product entity = opt.get();
			BeanUtils.copyProperties(entity, product);
			product.setCategoryid(entity.getCategory().getId());
			product.setStoreid(entity.getStore().getId());
			product.setIsEdit(true);
			model.addAttribute("product", product);
			return new ModelAndView("product/addOrEdit", model);
		}
		model.addAttribute("error", "Product không tồn tại");
		return new ModelAndView("forward:/product", model);

	}

	@GetMapping("user/list/{id}")
	public ModelAndView ChiTiet(ModelMap model, @PathVariable("id") int id) throws IOException {

		Optional<Product> opt = productService.findById(id);
		List<Review> list = null;
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		ProductModel product = new ProductModel();
		if (opt.isPresent()) {

			Product entity = opt.get();
			BeanUtils.copyProperties(entity, product);
			list = entity.getReviews();
			List<ReviewModel> listkq = new ArrayList<ReviewModel>();
			if(!list.isEmpty()) {
			for (Review item : list) {
				ReviewModel review = new ReviewModel();
				BeanUtils.copyProperties(item, review);
				review.setLastname(item.getUser().getLastName());
				review.setFistname(item.getUser().getFirstName());
				review.setImgages(item.getUser().getAvatar());
				listkq.add(review);
			}
			model.addAttribute("review", listkq);
			}
			TBDanhGia();
			model.addAttribute("Storeid", entity.getStore().getId());
			model.addAttribute("product", product);
			
			model.addAttribute("slreview", list.size());

			long soSanPhamTrongGio = 0;
			if (user != null) {
				for (Cart cart : user.getCarts()) {
					Cart cartn = cart;
					soSanPhamTrongGio += iCartItemService.countByCart(cartn);
				}
			}
			model.addAttribute("count", soSanPhamTrongGio);

			return new ModelAndView("user/product/productDetails", model);
		}
		model.addAttribute("error", "Product không tồn tại");
		return new ModelAndView("forward:/product/user", model);

	}

	@PostMapping("saveofUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("product") ProductModel product,
			BindingResult result) {
		Product entity = new Product();

		/*
		 * if (result.hasErrors()) { return new ModelAndView("product/addOrEdit"); }
		 */

		if (!product.getListImageFile().isEmpty()) {
			String path = application.getRealPath("/");

			try {
				product.setListimage(product.getListImageFile().getOriginalFilename());
				String filePath = path + "/resources/images/" + product.getListimage();
				product.getListImageFile().transferTo(Path.of(filePath));
				product.setListImageFile(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (!product.getListImageFile1().isEmpty()) {
			String path1 = application.getRealPath("/");

			try {
				product.setListimage1(product.getListImageFile1().getOriginalFilename());
				String filePath1 = path1 + "/resources/images/" + product.getListimage1();
				product.getListImageFile1().transferTo(Path.of(filePath1));
				product.setListImageFile1(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (!product.getListImageFile2().isEmpty()) {
			String path2 = application.getRealPath("/");

			try {
				product.setListimage2(product.getListImageFile2().getOriginalFilename());
				String filePath2 = path2 + "/resources/images/" + product.getListimage2();
				product.getListImageFile2().transferTo(Path.of(filePath2));
				product.setListImageFile2(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Optional<Category> opt = categoryService.findById(product.getCategoryid());
		Optional<Store> store = storeService.findById(product.getStoreid());
		BeanUtils.copyProperties(product, entity);
		entity.setCategory(opt.get());
		entity.setStore(store.get());
		productService.save(entity);
		return new ModelAndView("redirect:/product", model);
	}

	@PostMapping("saveofUpdateRating")
	public ModelAndView saveOrUpdateRating(ModelMap model, @Valid @ModelAttribute("review") ReviewModel review,
			BindingResult result) {
		Review entity = new Review();
		User User = (User) session.getAttribute("user");

		/*
		 * if (result.hasErrors()) { return new ModelAndView("product/addOrEdit"); }
		 */
		BeanUtils.copyProperties(review, entity);
		Date getDate = new Date();
		entity.setCreateat(getDate);
		entity.setUpdateat(getDate);
		entity.setProduct(productService.getById(review.getProductid()));
		entity.setUser(userService.getById(User.getId()));
		reviewService.save(entity);
		String a = "redirect:/product/user/list/" + review.getProductid();
		return new ModelAndView(a, model);
	}

	@GetMapping("delete/{id}")
	public ModelAndView delete(ModelMap model, @PathVariable("id") int id) {
		productService.deleteById(id);
		model.addAttribute("message", "Delete Succesfull !!!");
		return new ModelAndView("forward:/product", model);

	}

	@GetMapping("Search")
	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name) {
		List<Product> list = null;
		if (StringUtils.hasText(name)) {
			list = productService.findBynameContaining(name);
		} else {
			list = productService.findAll();
		}
		model.addAttribute("product", list);

		return "product/list";
	}

	@GetMapping("SearchTrademark/{trademark}")
	public String trademark(ModelMap model, @PathVariable("trademark") String trademark) {
		List<Product> list = null;
		if (StringUtils.hasText(trademark)) {
			list = productService.findBytrademarkContaining(trademark);
			System.out.print(1);
		} else {
			list = productService.findAll();
			System.out.print(2);
		}

		model.addAttribute("product", list);
		List<Category> cate = categoryService.findAll();
		model.addAttribute("category", cate);
		return "user/product/list";
	}

	@GetMapping("search/{id}")
	public ModelAndView searchidcate(ModelMap model, @PathVariable("id") int id) throws IOException {
		Optional<Category> opt = categoryService.findById(id);
		List<Product> list = null;
		if (opt.isPresent()) {
			Category entity = opt.get();
			System.out.print(entity.getName());
			list = entity.getProducts();
			model.addAttribute("product", list);
			List<Category> cate = categoryService.findAll();
			model.addAttribute("category", cate);
			return new ModelAndView("user/product/list", model);
		}
		model.addAttribute("error", "Product không tồn tại");
		return new ModelAndView("forward:/product/user/list", model);

	}

}
