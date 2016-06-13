package tk.fondomon.entities;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the smf_members database table.
 * 
 */
public class SmfMember implements Serializable {
	//private static final long serialVersionUID = 1L;

	private int idMember;

	private String additionalGroups;

	private String aim;

	private String avatar;

	private Date birthdate;

	private String buddyList;

	private int dateRegistered;

	private String emailAddress;

	private byte gender;

	private byte hideEmail;

	private String icq;

	private int idGroup;

	private int idMsgLastVisit;

	private int idPostGroup;

	private byte idTheme;

	private String ignoreBoards;

	private short instantMessages;

	private byte isActivated;

	private int karmaBad;

	private int karmaGood;

	private int lastLogin;

	private String lngfile;

	private String location;

	private String memberIp;

	private String memberIp2;

	private String memberName;

	private String messageLabels;

	private String modPrefs;

	private String msn;

	private byte newPm;

	private byte notifyAnnouncements;

	private byte notifyRegularity;

	private byte notifySendBody;

	private byte notifyTypes;

	private String openidUri;

	private String passwd;

	private String passwdFlood;

	private String passwordSalt;

	private String personalText;

	private byte pmEmailNotify;

	private String pmIgnoreList;

	private int pmPrefs;

	private byte pmReceiveFrom;

	private int posts;

	private String realName;

	private String secretAnswer;

	private String secretQuestion;

	private byte showOnline;

	private String signature;

	private String smileySet;

	private String timeFormat;

	private float timeOffset;

	private int totalTimeLoggedIn;

	private short unreadMessages;

	private String usertitle;

	private String validationCode;

	private byte warning;

	private String websiteTitle;

	private String websiteUrl;

	private String yim;

	private String quota;

	private String balance;

	private String contributions;

	private String amountCredits;

	private String valueCredits;

	public SmfMember() {
	}

	public int getIdMember() {
		return this.idMember;
	}

	public void setIdMember(int idMember) {
		this.idMember = idMember;
	}

	public String getAdditionalGroups() {
		return this.additionalGroups;
	}

	public void setAdditionalGroups(String additionalGroups) {
		this.additionalGroups = additionalGroups;
	}

	public String getAim() {
		return this.aim;
	}

	public void setAim(String aim) {
		this.aim = aim;
	}

	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getBuddyList() {
		return this.buddyList;
	}

	public void setBuddyList(String buddyList) {
		this.buddyList = buddyList;
	}

	public int getDateRegistered() {
		return this.dateRegistered;
	}

	public void setDateRegistered(int dateRegistered) {
		this.dateRegistered = dateRegistered;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public byte getGender() {
		return this.gender;
	}

	public void setGender(byte gender) {
		this.gender = gender;
	}

	public byte getHideEmail() {
		return this.hideEmail;
	}

	public void setHideEmail(byte hideEmail) {
		this.hideEmail = hideEmail;
	}

	public String getIcq() {
		return this.icq;
	}

	public void setIcq(String icq) {
		this.icq = icq;
	}

	public int getIdGroup() {
		return this.idGroup;
	}

	public void setIdGroup(int idGroup) {
		this.idGroup = idGroup;
	}

	public int getIdMsgLastVisit() {
		return this.idMsgLastVisit;
	}

	public void setIdMsgLastVisit(int idMsgLastVisit) {
		this.idMsgLastVisit = idMsgLastVisit;
	}

	public int getIdPostGroup() {
		return this.idPostGroup;
	}

	public void setIdPostGroup(int idPostGroup) {
		this.idPostGroup = idPostGroup;
	}

	public byte getIdTheme() {
		return this.idTheme;
	}

	public void setIdTheme(byte idTheme) {
		this.idTheme = idTheme;
	}

	public String getIgnoreBoards() {
		return this.ignoreBoards;
	}

	public void setIgnoreBoards(String ignoreBoards) {
		this.ignoreBoards = ignoreBoards;
	}

	public short getInstantMessages() {
		return this.instantMessages;
	}

	public void setInstantMessages(short instantMessages) {
		this.instantMessages = instantMessages;
	}

	public byte getIsActivated() {
		return this.isActivated;
	}

	public void setIsActivated(byte isActivated) {
		this.isActivated = isActivated;
	}

	public int getKarmaBad() {
		return this.karmaBad;
	}

	public void setKarmaBad(int karmaBad) {
		this.karmaBad = karmaBad;
	}

	public int getKarmaGood() {
		return this.karmaGood;
	}

	public void setKarmaGood(int karmaGood) {
		this.karmaGood = karmaGood;
	}

	public int getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(int lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getLngfile() {
		return this.lngfile;
	}

	public void setLngfile(String lngfile) {
		this.lngfile = lngfile;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMemberIp() {
		return this.memberIp;
	}

	public void setMemberIp(String memberIp) {
		this.memberIp = memberIp;
	}

	public String getMemberIp2() {
		return this.memberIp2;
	}

	public void setMemberIp2(String memberIp2) {
		this.memberIp2 = memberIp2;
	}

	public String getMemberName() {
		return this.memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMessageLabels() {
		return this.messageLabels;
	}

	public void setMessageLabels(String messageLabels) {
		this.messageLabels = messageLabels;
	}

	public String getModPrefs() {
		return this.modPrefs;
	}

	public void setModPrefs(String modPrefs) {
		this.modPrefs = modPrefs;
	}

	public String getMsn() {
		return this.msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public byte getNewPm() {
		return this.newPm;
	}

	public void setNewPm(byte newPm) {
		this.newPm = newPm;
	}

	public byte getNotifyAnnouncements() {
		return this.notifyAnnouncements;
	}

	public void setNotifyAnnouncements(byte notifyAnnouncements) {
		this.notifyAnnouncements = notifyAnnouncements;
	}

	public byte getNotifyRegularity() {
		return this.notifyRegularity;
	}

	public void setNotifyRegularity(byte notifyRegularity) {
		this.notifyRegularity = notifyRegularity;
	}

	public byte getNotifySendBody() {
		return this.notifySendBody;
	}

	public void setNotifySendBody(byte notifySendBody) {
		this.notifySendBody = notifySendBody;
	}

	public byte getNotifyTypes() {
		return this.notifyTypes;
	}

	public void setNotifyTypes(byte notifyTypes) {
		this.notifyTypes = notifyTypes;
	}

	public String getOpenidUri() {
		return this.openidUri;
	}

	public void setOpenidUri(String openidUri) {
		this.openidUri = openidUri;
	}

	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getPasswdFlood() {
		return this.passwdFlood;
	}

	public void setPasswdFlood(String passwdFlood) {
		this.passwdFlood = passwdFlood;
	}

	public String getPasswordSalt() {
		return this.passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public String getPersonalText() {
		return this.personalText;
	}

	public void setPersonalText(String personalText) {
		this.personalText = personalText;
	}

	public byte getPmEmailNotify() {
		return this.pmEmailNotify;
	}

	public void setPmEmailNotify(byte pmEmailNotify) {
		this.pmEmailNotify = pmEmailNotify;
	}

	public String getPmIgnoreList() {
		return this.pmIgnoreList;
	}

	public void setPmIgnoreList(String pmIgnoreList) {
		this.pmIgnoreList = pmIgnoreList;
	}

	public int getPmPrefs() {
		return this.pmPrefs;
	}

	public void setPmPrefs(int pmPrefs) {
		this.pmPrefs = pmPrefs;
	}

	public byte getPmReceiveFrom() {
		return this.pmReceiveFrom;
	}

	public void setPmReceiveFrom(byte pmReceiveFrom) {
		this.pmReceiveFrom = pmReceiveFrom;
	}

	public int getPosts() {
		return this.posts;
	}

	public void setPosts(int posts) {
		this.posts = posts;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSecretAnswer() {
		return this.secretAnswer;
	}

	public void setSecretAnswer(String secretAnswer) {
		this.secretAnswer = secretAnswer;
	}

	public String getSecretQuestion() {
		return this.secretQuestion;
	}

	public void setSecretQuestion(String secretQuestion) {
		this.secretQuestion = secretQuestion;
	}

	public byte getShowOnline() {
		return this.showOnline;
	}

	public void setShowOnline(byte showOnline) {
		this.showOnline = showOnline;
	}

	public String getSignature() {
		return this.signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getSmileySet() {
		return this.smileySet;
	}

	public void setSmileySet(String smileySet) {
		this.smileySet = smileySet;
	}

	public String getTimeFormat() {
		return this.timeFormat;
	}

	public void setTimeFormat(String timeFormat) {
		this.timeFormat = timeFormat;
	}

	public float getTimeOffset() {
		return this.timeOffset;
	}

	public void setTimeOffset(float timeOffset) {
		this.timeOffset = timeOffset;
	}

	public int getTotalTimeLoggedIn() {
		return this.totalTimeLoggedIn;
	}

	public void setTotalTimeLoggedIn(int totalTimeLoggedIn) {
		this.totalTimeLoggedIn = totalTimeLoggedIn;
	}

	public short getUnreadMessages() {
		return this.unreadMessages;
	}

	public void setUnreadMessages(short unreadMessages) {
		this.unreadMessages = unreadMessages;
	}

	public String getUsertitle() {
		return this.usertitle;
	}

	public void setUsertitle(String usertitle) {
		this.usertitle = usertitle;
	}

	public String getValidationCode() {
		return this.validationCode;
	}

	public void setValidationCode(String validationCode) {
		this.validationCode = validationCode;
	}

	public byte getWarning() {
		return this.warning;
	}

	public void setWarning(byte warning) {
		this.warning = warning;
	}

	public String getWebsiteTitle() {
		return this.websiteTitle;
	}

	public void setWebsiteTitle(String websiteTitle) {
		this.websiteTitle = websiteTitle;
	}

	public String getWebsiteUrl() {
		return this.websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public String getYim() {
		return this.yim;
	}

	public void setYim(String yim) {
		this.yim = yim;
	}

	public String getQuota(){
		return this.quota;
	}

	public void setQuota(String quota){
		this.quota = quota;
	}

	/**
	 * @return the balance
	 */
	public String getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(String balance) {
		this.balance = balance;
	}

	/**
	 * @return the contributions
	 */
	public String getContributions() {
		return contributions;
	}

	/**
	 * @param contributions the contributions to set
	 */
	public void setContributions(String contributions) {
		this.contributions = contributions;
	}

	/**
	 * @return the amountCredits
	 */
	public String getAmountCredits() {
		return amountCredits;
	}

	/**
	 * @param amountCredits the amountCredits to set
	 */
	public void setAmountCredits(String amountCredits) {
		this.amountCredits = amountCredits;
	}

	/**
	 * @return the valueCredits
	 */
	public String getValueCredits() {
		return valueCredits;
	}

	/**
	 * @param valueCredits the valueCredits to set
	 */
	public void setValueCredits(String valueCredits) {
		this.valueCredits = valueCredits;
	}
}