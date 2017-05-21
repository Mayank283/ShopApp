/**
 * 
 */
package org.mayank.shop;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mayank.shop.dao.ShopDao;
import org.mayank.shop.exceptions.RepositoryException;
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
	
	@MockBean
	ShopDao shopDao;

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
	
	/**
	 * Missing Customer Latitude/Longitude
	 * 
	 * @throws Exception
	 */
	@Test(expected = ShopException.class)
	public void givenCustomerLocationWithoutLatitudeThrowsShopException() throws Exception {
		
		shopService.getNearestShop(BigDecimal.valueOf(72.15463486), null);
		
	}
	
	/**
	 * No shops present in repository
	 * 
	 * @throws Exception
	 */
	@Test(expected = RepositoryException.class)
	public void givenValidCustomerLocationNoShopsThrowsRepositoryException() throws Exception {
		
		Mockito.doReturn(new ArrayList<Shop>()).when(shopDao).getShops();
		
		shopService.getNearestShop(BigDecimal.valueOf(72.15463486),BigDecimal.valueOf(12.15463486));	
	}
}
