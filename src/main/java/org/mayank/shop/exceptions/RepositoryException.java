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
public class RepositoryException extends Exception{

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param message Message description of Exception
	 */
	public RepositoryException(String message) {
		super(message);
	}
	
}
