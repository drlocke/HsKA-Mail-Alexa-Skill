package msc.hska.client.util;

public class ApiNames {
	
	public static final String FOLDER_INCOMING = "Posteingang";

	public static Boolean compare(String one, String two)
	{
		if (one == null && two == null)
			return true;
		if (one == null || two == null)
			return false;
		if (one.equalsIgnoreCase(two.toLowerCase()))
			return true;
		return false;
	}
	
}
