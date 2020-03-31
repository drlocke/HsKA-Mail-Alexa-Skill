package msc.hska.client.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mail {

	@SerializedName("subject")
	@Expose
	private String subject;
	@SerializedName("messageId")
	@Expose
	private String messageId;
	@SerializedName("sender")
	@Expose
	private Sender sender;
	@SerializedName("read")
	@Expose
	private Boolean read;
	@SerializedName("dateSent")
	@Expose
	private String dateSent;
	@SerializedName("bodyPlainText")
	@Expose
	private String bodyPlainText;
	@SerializedName("attachments")
	@Expose
	private Boolean attachments;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public Sender getSender() {
		return sender;
	}

	public void setSender(Sender sender) {
		this.sender = sender;
	}

	public Boolean getRead() {
		return read;
	}

	public void setRead(Boolean read) {
		this.read = read;
	}

	public String getDateSent() {
		return dateSent;
	}

	public void setDateSent(String dateSent) {
		this.dateSent = dateSent;
	}

	public String getBodyPlainText() {
		return bodyPlainText;
	}

	public void setBodyPlainText(String bodyPlainText) {
		this.bodyPlainText = bodyPlainText;
	}

	public Boolean getAttachments() {
		return attachments;
	}

	public void setAttachments(Boolean attachments) {
		this.attachments = attachments;
	}

}