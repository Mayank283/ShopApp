/**
 * 
 */
package org.mayank.shop;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mayank.shop.exceptions.ShopException;
import org.mayank.shop.json.request.ShopRequest;
import org.mayank.shop.model.Shop;
import org.mayank.shop.model.ShopAddress;
import org.mayank.shop.services.ShopService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Test Class to test ShopController
 * 
 * @author Mayank
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ShopcontrollerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	ShopService shopService;

	private ShopRequest shopRequest;

	private ShopAddress shopAddress = new ShopAddress();

	private Shop shop;

	private String jsonStr;

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	/*private MediaType contentType2 = new MediaType(MediaType.TEXT_PLAIN.getType(), MediaType.TEXT_PLAIN.getSubtype(),
			Charset.forName("utf8"));*/

	/**
	 * Set up for unit testing of controller
	 * 
	 * @throws Exception
	 */
	@Before
	public void setup() throws Exception {
		shopAddress.setNumber("232");
		shopAddress.setPostCode(281001L);
		shopRequest = new ShopRequest("Bihari", shopAddress);
		ObjectMapper mapperObj = new ObjectMapper();
		jsonStr = mapperObj.writeValueAsString(shopRequest);
		shop = new Shop();
		shop.setShopAddress(shopAddress);
		shop.setShopName("Test3");
		shop.setShopLatitude(BigDecimal.valueOf(12.364899411));
		shop.setShopLongitude(BigDecimal.valueOf(72.364899411));
	}

	/**
	 * Correct Shop request should return status as ok. Mocking shopService to
	 * do nothing on sending request to URI/shop/add
	 * 
	 * @throws Exception
	 */
	@Test
	public void givenCorrectShopRequestResponseOK() throws Exception {

		// Mocking shopService to test ShopController method
		Mockito.doReturn(shop).when(shopService).addShop(shopRequest);

		this.mockMvc.perform(post("/shop/repository").content(jsonStr).contentType(contentType)).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void givenCorrectCustomerLocationResponseOK() throws Exception {

		// Mocking shopService to test ShopController method
		Mockito.doReturn(shop).when(shopService).getNearestShop(BigDecimal.valueOf(12.31456),
				BigDecimal.valueOf(77.2564178));

		this.mockMvc.perform(get("/shop/nearby")
				.param("customerLongitude", "12.31456")
				.param("customerLatitude","77.2564178"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(contentType));
	}
	
	
	@Test
	public void givenMissingLatitudeThrowBadRequest() throws Exception {

		// Mocking shopService to test ShopController method
		Mockito.doThrow(new ShopException("Latitude/Longitude is missing")).when(shopService).getNearestShop(BigDecimal.valueOf(12.31456),null);
		
		this.mockMvc.perform(get("/shop/nearby")
				.param("customerLongitude", "12.31456")
				.param("customerLatitude",""))
				.andDo(print()).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errorCode").value("SA-001"));
	}
}
