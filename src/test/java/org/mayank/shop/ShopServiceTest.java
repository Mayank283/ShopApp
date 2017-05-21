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

	private ShopRequest shopRequestInvalid;

	private ShopRequest shopRequestValid;

	private ShopAddress shopAddress = new ShopAddress();

	/**
	 * Setting up shopRequest without complete shopAddress
	 * 
	 * @throws Exception
	 */
	@Before
	public void setup() throws Exception {
		shopRequestInvalid = new ShopRequest("Bihari", new ShopAddress());
		shopAddress.setNumber("Shop 21");
		shopAddress.setPostCode(281001L);
		shopRequestValid = new ShopRequest("Bikaji", shopAddress);
		
	}

	/**
	 * Incorrect/Incomplete address should return Exception
	 * 
	 * @throws Exception
	 */
	@Test(expected = ShopException.class)
	public void givenIncorrectShopRequestThrowShopException() throws Exception {

		shopService.addShop(shopRequestInvalid);
	}

	/**
	 * Correct shopAddress
	 * 
	 * @throws Exception
	 */
	@Test
	public void givenCorrectShopRequest() throws Exception {
		
		shopService.addShop(shopRequestValid);
	}
}
