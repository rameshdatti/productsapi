package com.springweb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import com.springweb.entities.Product;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProductestapiApplicationTests {
	@Value("${productrestapi.services.url}")
	private String baseURL;

	@Test
	void getProducts() {
		RestTemplate template=new RestTemplate();
		Product product=template.getForObject(baseURL + "2", Product.class);
		System.out.println("========= ======="+product);
		assertNotNull(product);
		assertEquals(2, product.getId());
	}
	
	@Test
	void createProduct() {
		RestTemplate template=new RestTemplate();
		Product newProduct=new Product();
		newProduct.setName("Spring");
		newProduct.setDescription("€Baby");
		newProduct.setPrice(50000);
		Product product=template.postForObject("baseURL",newProduct, Product.class);
		System.out.println("================="+product);
		assertNotNull(product);
		assertEquals("Spring", product.getName());
	}

}
