package msc.hska.client.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MailFolder {

	@SerializedName("id")
	@Expose
	private String id;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("children")
	@Expose
	private List<Object> children = null;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Object> getChildren() {
		return children;
	}

	public void setChildren(List<Object> children) {
		this.children = children;
	}

}