package vn.iotstar.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String trademark;
	
	@Column(columnDefinition = "nvarchar(MAX)")
	private String name;
	@Column(columnDefinition = "nvarchar(MAX)")
	private String desciption;
	@Column(columnDefinition = "nvarchar(MAX)")
	private String desciption1;
	@Column(columnDefinition = "nvarchar(MAX)")
	private String desciption2;
	private Double price;
	private Double promotionaprice;
	private Integer quantity;
	private Integer sold;
	private Boolean isselling;
	private String listimage;
	private String listimage1;
	private String listimage2;
	
	// private Integer styleids;//kiểu của sản phẩm

	// private Integer category đổi khóa ngoại bằng 1 thực thể kết hợp
	@ManyToOne
	@JoinColumn(name = "categoryid")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "storeid")
	private Store store;

	private Integer rating;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date createat;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date updateat;
///chú ý
	@OneToMany(mappedBy = "product")
	List<OrderItem> orderItems;

	@OneToMany(mappedBy = "product")
	List<Review> reviews;

	@OneToMany(mappedBy = "product")
	List<CartItem> cartItems;

}
