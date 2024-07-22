package com.bvk.test.ecommerce;

import com.bvk.test.ecommerce.controller.InventoryController;
import com.bvk.test.ecommerce.controller.OrderController;
import com.bvk.test.ecommerce.model.Inventory;
import com.bvk.test.ecommerce.repository.InventoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class EcommerceApplicationTests {

	@Autowired
	private InventoryController inventoryController;

	@Autowired
	private InventoryRepository inventoryRepository;

	@Autowired
	private OrderController orderController;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		Inventory inventory = new Inventory();
		inventory.setId(1L);
		inventory.setName("Test");
		inventory.setPrice(100L);
		inventory.setDescription("Test");
		inventory.setQuantity(100L);
		inventoryRepository.save(inventory);
	}
	@Test
	void testAddInventory() throws Exception {

		Inventory inventory = new Inventory();
		inventory.setId(1L);
		inventory.setName("Test");
		inventory.setPrice(100L);
		inventory.setDescription("Test");
		inventory.setQuantity(100L);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/inventory/add")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(inventory)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(print());
	}

	@Test
	void testGetInventory() throws Exception {
		Inventory inventory = inventoryRepository.findById(1L).get();
		inventory.setId(1L);
		inventory.setName("Test");
		inventory.setPrice(100L);
		inventory.setDescription("Test");
		inventory.setQuantity(100L);
		inventoryRepository.save(inventory);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/inventory/get/1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test"))
				.andDo(print());
	}

	@Test
	void testUpdateInventory() throws Exception {
		Inventory inventory = inventoryRepository.findById(1L).get();
		inventory.setId(1L);
		inventory.setName("Test");
		inventory.setPrice(100L);
		inventory.setDescription("Test");
		inventory.setQuantity(100L);
		inventoryRepository.save(inventory);

		mockMvc.perform(MockMvcRequestBuilders.put("/api/inventory/updateQuantity/1")
						.content(String.valueOf(10L)).contentType("application/json"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.quantity").value(10))
				.andDo(print());
	}

	@Test
	void testOrder() throws Exception {
		Inventory inventory = inventoryRepository.findById(1L).get();
		inventory.setId(1L);
		inventory.setName("Test");
		inventory.setPrice(100L);
		inventory.setDescription("Test");
		inventory.setQuantity(100L);
		inventoryRepository.save(inventory);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/order/order/1")
						.content(String.valueOf(10L)).contentType("application/json"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.quantity").value(10))
				.andDo(print());
	}

}
