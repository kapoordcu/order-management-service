package com.skills.gk;

import com.skills.gk.dto.ProductRequest;
import com.skills.gk.model.Product;
import com.skills.gk.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductApplicationTests {

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.3");

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ProductRepository productRepository;

	private ObjectMapper objectMapper = new ObjectMapper() ;
	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Test
	void shouldCreateProduct() throws Exception {
		ProductRequest productRequest = getProductRequest();
		String productRequestAsString = objectMapper.writeValueAsString(productRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productRequestAsString))
				.andExpect(status().isCreated());
		Assertions.assertEquals(1, productRepository.findAll().size());
	}

	@Test
	void getAllProduct() throws Exception {


	}

	private ProductRequest getProductRequest() {
		return ProductRequest.builder()
				.name("iPhone 13 mini")
				.description("128 GB, Made in california")
				.price(BigDecimal.valueOf(1099)).build();
	}


}
