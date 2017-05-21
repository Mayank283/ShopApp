/**
 * 
 */
package org.mayank.shop.exceptions;

/**
 * Custom ErrorResponse for ShopApplication
 * @author Mayank
 *
 */
public class ErrorResponse {

	/**
	 * Error Code's pertaining to exception
	 */
	private String errorCode;

	/**
	 * Message description of Exception
	 */
	private String message;

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
