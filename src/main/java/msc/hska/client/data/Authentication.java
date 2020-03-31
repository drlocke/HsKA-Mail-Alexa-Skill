package msc.hska.client.data;

public class Authentication {
	
	private String name;

	private String password;

	public Authentication() {

	}

	public Authentication(String name, String pw) {
		this.name = name;
		this.password = pw;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public void setName(String value) {
		name = value;
	}

	public void setPassword(String pw) {
		password = pw;
	}
}
