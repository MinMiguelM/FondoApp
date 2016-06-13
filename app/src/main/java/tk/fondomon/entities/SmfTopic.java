package tk.fondomon.entities;

import java.io.Serializable;


/**
 * The persistent class for the smf_topics database table.
 * 
 */
public class SmfTopic implements Serializable {
	//private static final long serialVersionUID = 1L;

	private int idTopic;

	private byte approved;

	private int idBoard;

	private int idFirstMsg;

	private int idLastMsg;

	private int idMemberStarted;

	private int idMemberUpdated;

	private int idPoll;

	private short idPreviousBoard;

	private int idPreviousTopic;

	private byte isSticky;

	private byte locked;

	private int numReplies;

	private int numViews;

	private short unapprovedPosts;

	public SmfTopic() {
	}

	public int getIdTopic() {
		return this.idTopic;
	}

	public void setIdTopic(int idTopic) {
		this.idTopic = idTopic;
	}

	public byte getApproved() {
		return this.approved;
	}

	public void setApproved(byte approved) {
		this.approved = approved;
	}

	public int getIdBoard() {
		return this.idBoard;
	}

	public void setIdBoard(int idBoard) {
		this.idBoard = idBoard;
	}

	public int getIdFirstMsg() {
		return this.idFirstMsg;
	}

	public void setIdFirstMsg(int idFirstMsg) {
		this.idFirstMsg = idFirstMsg;
	}

	public int getIdLastMsg() {
		return this.idLastMsg;
	}

	public void setIdLastMsg(int idLastMsg) {
		this.idLastMsg = idLastMsg;
	}

	public int getIdMemberStarted() {
		return this.idMemberStarted;
	}

	public void setIdMemberStarted(int idMemberStarted) {
		this.idMemberStarted = idMemberStarted;
	}

	public int getIdMemberUpdated() {
		return this.idMemberUpdated;
	}

	public void setIdMemberUpdated(int idMemberUpdated) {
		this.idMemberUpdated = idMemberUpdated;
	}

	public int getIdPoll() {
		return this.idPoll;
	}

	public void setIdPoll(int idPoll) {
		this.idPoll = idPoll;
	}

	public short getIdPreviousBoard() {
		return this.idPreviousBoard;
	}

	public void setIdPreviousBoard(short idPreviousBoard) {
		this.idPreviousBoard = idPreviousBoard;
	}

	public int getIdPreviousTopic() {
		return this.idPreviousTopic;
	}

	public void setIdPreviousTopic(int idPreviousTopic) {
		this.idPreviousTopic = idPreviousTopic;
	}

	public byte getIsSticky() {
		return this.isSticky;
	}

	public void setIsSticky(byte isSticky) {
		this.isSticky = isSticky;
	}

	public byte getLocked() {
		return this.locked;
	}

	public void setLocked(byte locked) {
		this.locked = locked;
	}

	public int getNumReplies() {
		return this.numReplies;
	}

	public void setNumReplies(int numReplies) {
		this.numReplies = numReplies;
	}

	public int getNumViews() {
		return this.numViews;
	}

	public void setNumViews(int numViews) {
		this.numViews = numViews;
	}

	public short getUnapprovedPosts() {
		return this.unapprovedPosts;
	}

	public void setUnapprovedPosts(short unapprovedPosts) {
		this.unapprovedPosts = unapprovedPosts;
	}

}