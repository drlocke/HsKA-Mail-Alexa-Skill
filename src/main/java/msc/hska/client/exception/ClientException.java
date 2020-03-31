package msc.hska.client.exception;

public class ClientException extends Throwable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7533201561085773339L;

	public ClientException(String error) {
        super(error);
    }
}
