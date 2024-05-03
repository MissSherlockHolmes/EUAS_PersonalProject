//package com.MissSherlockHolmes.Farmers.Market;
//
//import com.MissSherlockHolmes.Farmers.Market.repository.ProductRepository;
//import com.MissSherlockHolmes.Farmers.Market.repository.ShoppingCartRepository;
//import com.MissSherlockHolmes.Farmers.Market.repository.UserRepository;
//import com.MissSherlockHolmes.Farmers.Market.service.ShoppingCartService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class ShoppingCartServiceTest {
//
//	private final ShoppingCartService shoppingCartService;
//	private final ProductRepository productRepository;
//	private final UserRepository userRepository;
//	private final ShoppingCartRepository shoppingCartRepository;
//
//	@Autowired
//	public ShoppingCartServiceTest(ShoppingCartService shoppingCartService, ProductRepository productRepository, UserRepository userRepository, ShoppingCartRepository shoppingCartRepository) {
//		this.shoppingCartService = shoppingCartService;
//		this.productRepository = productRepository;
//		this.userRepository = userRepository;
//		this.shoppingCartRepository = shoppingCartRepository;
//	}
//
//	@Test
//	public void testAddToCart() {
//		// Create a new user
//		User user = new User();
//		user.setName("Test User");
//		user.setEmail("test@example.com");
//		user.setUsername("test_username"); // New field
//		user.setStreet("Test Street"); // New field
//		user.setHouseNumber("123"); // New field
//		user.setCity("Test City"); // New field
//		user.setRegion("Test Region"); // New field
//		user.setCountry("Test Country"); // New field
//		user.setPostalCode("12345"); // New field
//		userRepository.save(userId);
//
//		// Create a new product
//		Product product = new Product();
//		product.setName("Test Product");
//		product.setPrice(10.90);
//		product.setDescription("Test Description");
//		product.setMaxQuantity(100);
//		product.setRemainingQuantity(100);
//		productRepository.save(product);
//
//		// Add the product to the shopping cart
//		ShoppingCart cart = shoppingCartService.addToCart(user.getId(), product.getId(), 1);
//
//		// Verify that the cart is not null and contains the correct items
//		assertNotNull(cart);
//		assertEquals(1, cart.getQuantity());
//		assertEquals(user.getId(), cart.getUserId());
//		assertEquals(product.getId(), cart.getProductId());
//	}
//}
