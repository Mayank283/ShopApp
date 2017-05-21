/**
 * 
 */
package org.mayank.shop;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mayank.shop.exceptions.ShopException;
import org.mayank.shop.json.request.ShopRequest;
import org.mayank.shop.model.ShopAddress;
import org.mayank.shop.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Mayank
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ShopServiceTest {

	@Autowired
	private ShopService shopService;
	
	private ShopRequest shopRequest;

	private ShopAddress shopAddress = new ShopAddress();

	private String jsonStr;
	
	/**
	 * Setting up shopRequest without complete shopAddress 
	 * @throws Exception
	 */
	@Before
	public void setup() throws Exception {
		shopRequest = new ShopRequest("Bihari",shopAddress);
		ObjectMapper mapperObj = new ObjectMapper();
		jsonStr = mapperObj.writeValueAsString(shopRequest);
		System.out.println(jsonStr);
	}

	/**
	 * Incorrect/Incomplete address should return Exception
	 * @throws Exception
	 */
	@Test(expected = ShopException.class)
	public void givenIncorrectShopRequestThrowShopException() throws Exception {
	
		shopService.addShop(shopRequest);
	}
}
