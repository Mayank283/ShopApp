/**
 * 
 */
package org.mayank.shop.dao;

import java.util.ArrayList;
import java.util.List;

import org.mayank.shop.model.Shop;
import org.springframework.stereotype.Repository;

/**
 * Implementation of ShopDao
 * 
 * @author Mayank
 *
 */

@Repository
public class ShopDaoImpl implements ShopDao{

	List<Shop> shopList = new ArrayList<Shop>();
	
	@Override
	public void addShop(Shop shop) {
		shopList.add(shop);
	}
	
	@Override
	public List<Shop> getShops() {
		return shopList;
	}
}
