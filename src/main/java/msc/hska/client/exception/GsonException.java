package msc.hska.client.exception;

public class GsonException extends Throwable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3221570515311734592L;

	public GsonException(Throwable tr) {
        super("GSON: " + tr.getMessage(), tr);
    }
}

