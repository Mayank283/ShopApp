/**
 * 
 */
package org.mayank.shop.model;

/**
 * Object to Map the Shop Address provided in RetailManager Request
 * @author Mayank
 */
public class ShopAddress {

	/** ShopAdress.number*/
	private String number;
	
	
	/** ShopAddress.postCode*/
	private Long postCode;


	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}


	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}


	/**
	 * @return the postCode
	 */
	public Long getPostCode() {
		return postCode;
	}


	/**
	 * @param postCode the postCode to set
	 */
	public void setPostCode(Long postCode) {
		this.postCode = postCode;
	}
	
}
