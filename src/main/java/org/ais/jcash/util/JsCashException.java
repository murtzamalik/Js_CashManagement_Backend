package org.ais.jcash.util;

/**
 * Class is used to Handle Bbs Exceptions.
 * 
 * @author Murtaza Malik
 *
 * @version 1.0
 * @see RuntimeException
 */
public class JsCashException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String errorMessage;

	/**
	 * Getter Method of errorMessage
	 *
	 * @see JsCashException
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Setter Method of errorMessage
	 *
	 * @see JsCashException
	 * @param errorMessage receives error message
	 */
	public JsCashException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	
	/**
	 * No Argument Constructor of Class
	 *
	 * @see JsCashException
	 */
	public JsCashException() {
		super();
	}
}
