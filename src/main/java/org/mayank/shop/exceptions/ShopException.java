/**
 * 
 */
package org.mayank.shop.exceptions;

/**
 * Custom Exception for ShopApplication
 * 
 * @author Mayank
 *
 */
public class ShopException extends Exception{

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param message Message description of Exception
	 */
	public ShopException(String message) {
		super(message);
	}
	
	/**
	 * @param message Message description of Exception
	 * @param cause True cause of exception referring to other API's
	 */
	public ShopException(String message,Throwable cause) {
		super(message,cause);
	}

}
