/**
 * 
 */
package org.mayank.shop.json.request;

import org.mayank.shop.model.ShopAddress;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Request object to map the JSON request from RetailManager
 * @author Mayank
 */
@JsonPropertyOrder({"shopname","shopaddress"})
public class ShopRequest {

	/**Name of the shop */
	@JsonProperty("shopname")
	private String shopName;

	
	/**Address of the shop*/
	@JsonProperty("shopaddress")
	private ShopAddress shopAddress;
	
	/**
	 * Default constructor
	 */
	public ShopRequest(){
		
	}
	
	/**
	 * @param shopName Name of Shop
	 * @param shopAddress Address of shop
	 */
	public ShopRequest(String shopName, ShopAddress shopAddress) {
		super();
		this.shopName = shopName;
		this.shopAddress = shopAddress;
	}

	/**
	 * Getter method to return ShopName
	 * @return Name of the Shop
	 */
	public String getShopName() {
		return shopName;
	}
	
	
	/**
	 * Setter to set ShopName
	 * @param shopName-Name of the Shop
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
	
	/**
	 * Getter Method to return ShopAddress
	 * @return shopAddress
	 */
	public ShopAddress getShopAddress() {
		return shopAddress;
	}
	
	
	/**
	 * Setter to set ShopAddress
	 * @param shopAddress-Address of the shop
	 */
	public void setShopAddress(ShopAddress shopAddress) {
		this.shopAddress = shopAddress;
	}
	
}
