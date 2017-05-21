/**
 * 
 */
package org.mayank.shop;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mayank.shop.dao.ShopDao;
import org.mayank.shop.model.Shop;
import org.mayank.shop.model.ShopAddress;
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
public class ShopDaoTest {

	@Autowired 
	ShopDao shopDao;
	
	private Shop shop = new Shop();
	
	private ShopAddress shopAddress = new ShopAddress();
	
	/**
	 * Setting up the repo to test Shop Dao
	 */
	@Before
	public void setup(){
		shop.setShopName("Patanjali");
		shop.setShopLongitude(BigDecimal.valueOf(12.3544899));
		shop.setShopLatitude(BigDecimal.valueOf(78.235146));
		shopAddress.setNumber("Shop 21");
		shopAddress.setPostCode(281001L);
		shop.setShopAddress(shopAddress);
	}
	
	/**
	 * Testing add to shop is successful
	 * @throws Exception
	 */
	@Test
	public void givenShopAddToRepo() throws Exception {

		shopDao.addShop(shop);
	}
	
	
	
	/**
	 * Testing return from list of shop is successful
	 * @throws Exception
	 */
	@After
	public void returnShopFromRepository() throws Exception {

		List<Shop> shopRepo = shopDao.getShops();
		Shop shopPresent = shopRepo.get(0);
		assertEquals("Patanjali",shopPresent.getShopName());
		assertEquals("Shop 21",shopPresent.getShopAddress().getNumber());
	}
}
