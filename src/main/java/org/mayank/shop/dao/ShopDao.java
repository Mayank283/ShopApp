/**
 * 
 */
package org.mayank.shop.dao;

import java.util.List;

import org.mayank.shop.model.Shop;

/**
 * Dao Class to persist the shop object into Array
 * 
 * @author Mayank
 *
 */
public interface ShopDao {

	/**
	 * @param shop Shop to be added into repository
	 */
	public void addShop(Shop shop);

	/**
	 * @return List of shop present in repository
	 */
	public List<Shop> getShops();
}
