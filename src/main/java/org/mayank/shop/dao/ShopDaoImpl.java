/**
 * 
 */
package org.mayank.shop.dao;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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

	List<Shop> shopList = new CopyOnWriteArrayList<Shop>();
	
	@Override
	public void addShop(Shop shop) {
		shopList.add(shop);
	}
	
	@Override
	public List<Shop> getShops() {
		return shopList;
	}
}
