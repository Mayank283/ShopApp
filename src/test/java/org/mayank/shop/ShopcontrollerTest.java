/**
 * 
 */
package org.mayank.shop;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mayank.shop.json.request.ShopRequest;
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

	private String jsonStr;

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	private MediaType contentType2 = new MediaType(MediaType.TEXT_PLAIN.getType(), MediaType.TEXT_PLAIN.getSubtype(),
			Charset.forName("utf8"));

	/**
	 * Set up for unit testing of controller
	 * @throws Exception
	 */
	@Before
	public void setup() throws Exception {
		shopAddress.setNumber("232");
		shopAddress.setPostCode(281001L);
		shopRequest = new ShopRequest("Bihari", shopAddress);
		ObjectMapper mapperObj = new ObjectMapper();
		jsonStr = mapperObj.writeValueAsString(shopRequest);
		System.out.println(jsonStr);
	}

	/**
	 * Correct Shop request should return status as ok.
	 * Mocking shopService to do nothing on sending request to URI/shop/add
	 * @throws Exception
	 */
	@Test
	public void givenCorrectShopRequestResponseOK() throws Exception {

		//given(this.shopService.addShop(shopRequest))
		Mockito.doNothing().when(shopService).addShop(shopRequest);
		
		this.mockMvc.perform(post("/shop/add").content(jsonStr).contentType(contentType)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().contentType(contentType2));
	}

}
