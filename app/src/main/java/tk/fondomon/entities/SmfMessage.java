package tk.fondomon.entities;

import java.io.Serializable;


/**
 * The persistent class for the smf_messages database table.
 * 
 */
public class SmfMessage implements Serializable {
	//private static final long serialVersionUID = 1L;

	private int idMsg;

	private byte approved;

	private String body;

	private String icon;

	private int idBoard;

	private int idMember;

	private int idMsgModified;

	private int idTopic;

	private String modifiedName;

	private int modifiedTime;

	private String posterEmail;

	private String posterIp;

	private String posterName;

	private int posterTime;

	private byte smileysEnabled;

	private String subject;

	public SmfMessage() {
	}

	public int getIdMsg() {
		return this.idMsg;
	}

	public void setIdMsg(int idMsg) {
		this.idMsg = idMsg;
	}

	public byte getApproved() {
		return this.approved;
	}

	public void setApproved(byte approved) {
		this.approved = approved;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getIdBoard() {
		return this.idBoard;
	}

	public void setIdBoard(int idBoard) {
		this.idBoard = idBoard;
	}

	public int getIdMember() {
		return this.idMember;
	}

	public void setIdMember(int idMember) {
		this.idMember = idMember;
	}

	public int getIdMsgModified() {
		return this.idMsgModified;
	}

	public void setIdMsgModified(int idMsgModified) {
		this.idMsgModified = idMsgModified;
	}

	public int getIdTopic() {
		return this.idTopic;
	}

	public void setIdTopic(int idTopic) {
		this.idTopic = idTopic;
	}

	public String getModifiedName() {
		return this.modifiedName;
	}

	public void setModifiedName(String modifiedName) {
		this.modifiedName = modifiedName;
	}

	public int getModifiedTime() {
		return this.modifiedTime;
	}

	public void setModifiedTime(int modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getPosterEmail() {
		return this.posterEmail;
	}

	public void setPosterEmail(String posterEmail) {
		this.posterEmail = posterEmail;
	}

	public String getPosterIp() {
		return this.posterIp;
	}

	public void setPosterIp(String posterIp) {
		this.posterIp = posterIp;
	}

	public String getPosterName() {
		return this.posterName;
	}

	public void setPosterName(String posterName) {
		this.posterName = posterName;
	}

	public int getPosterTime() {
		return this.posterTime;
	}

	public void setPosterTime(int posterTime) {
		this.posterTime = posterTime;
	}

	public byte getSmileysEnabled() {
		return this.smileysEnabled;
	}

	public void setSmileysEnabled(byte smileysEnabled) {
		this.smileysEnabled = smileysEnabled;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}