package cn.com.cfz.pojo;

/**
 * AbstractTUser entity provides the base persistence definition of the TUser
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTUser implements java.io.Serializable {

	// Fields

	private String id;
	private String CUsername;
	private String CPassword;
	private String CRegionid;
	private String CRegiondesc;
	private String CPhone;
	private String CEmail;
	private Integer CType;
	private String CEnterprisecode;
	private String CPersoncard;
	private Float CMoney;
	private String CDefaultcardcode;
	private Long CCreatetime;
	private Long CLogintime;
	private String CMarkcode;
	private Integer CAvailable;
	private Float CTotalmoney;

	// Constructors

	/** default constructor */
	public AbstractTUser() {
	}

	/** minimal constructor */
	public AbstractTUser(String id, String CPassword, String CMarkcode) {
		this.id = id;
		this.CPassword = CPassword;
		this.CMarkcode = CMarkcode;
	}

	/** full constructor */
	public AbstractTUser(String id, String CUsername, String CPassword,
			String CRegionid, String CRegiondesc, String CPhone,
			String CEmail, Integer CType, String CEnterprisecode,
			String CPersoncard, Float CTotalmoney,Float CMoney, String CDefaultcardcode,
			Long CCreatetime, Long CLogintime, String CMarkcode,
			Integer CAvailable) {
		this.id = id;
		this.CUsername = CUsername;
		this.CPassword = CPassword;
		this.CRegionid = CRegionid;
		this.CRegiondesc = CRegiondesc;
		this.CPhone = CPhone;
		this.CEmail = CEmail;
		this.CType = CType;
		this.CEnterprisecode = CEnterprisecode;
		this.CPersoncard = CPersoncard;
		this.CTotalmoney = CTotalmoney;
		this.CMoney = CMoney;
		this.CDefaultcardcode = CDefaultcardcode;
		this.CCreatetime = CCreatetime;
		this.CLogintime = CLogintime;
		this.CMarkcode = CMarkcode;
		this.CAvailable = CAvailable;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCUsername() {
		return this.CUsername;
	}

	public void setCUsername(String CUsername) {
		this.CUsername = CUsername;
	}

	public String getCPassword() {
		return this.CPassword;
	}

	public void setCPassword(String CPassword) {
		this.CPassword = CPassword;
	}

	public String getCRegionid() {
		return this.CRegionid;
	}

	public void setCRegionid(String CRegionid) {
		this.CRegionid = CRegionid;
	}

	public String getCRegiondesc() {
		return this.CRegiondesc;
	}

	public void setCRegiondesc(String CRegiondesc) {
		this.CRegiondesc = CRegiondesc;
	}

	public String getCPhone() {
		return this.CPhone;
	}

	public void setCPhone(String CPhone) {
		this.CPhone = CPhone;
	}

	public String getCEmail() {
		return this.CEmail;
	}

	public void setCEmail(String CEmail) {
		this.CEmail = CEmail;
	}

	public Integer getCType() {
		return this.CType;
	}

	public void setCType(Integer CType) {
		this.CType = CType;
	}

	public String getCEnterprisecode() {
		return this.CEnterprisecode;
	}

	public void setCEnterprisecode(String CEnterprisecode) {
		this.CEnterprisecode = CEnterprisecode;
	}

	public String getCPersoncard() {
		return this.CPersoncard;
	}

	public void setCPersoncard(String CPersoncard) {
		this.CPersoncard = CPersoncard;
	}

	public Float getCMoney() {
		return this.CMoney;
	}

	public void setCMoney(Float CMoney) {
		this.CMoney = CMoney;
	}

	public String getCDefaultcardcode() {
		return this.CDefaultcardcode;
	}

	public void setCDefaultcardcode(String CDefaultcardcode) {
		this.CDefaultcardcode = CDefaultcardcode;
	}

	public Long getCCreatetime() {
		return this.CCreatetime;
	}

	public void setCCreatetime(Long CCreatetime) {
		this.CCreatetime = CCreatetime;
	}

	public Long getCLogintime() {
		return this.CLogintime;
	}

	public void setCLogintime(Long CLogintime) {
		this.CLogintime = CLogintime;
	}

	public String getCMarkcode() {
		return this.CMarkcode;
	}

	public void setCMarkcode(String CMarkcode) {
		this.CMarkcode = CMarkcode;
	}

	public Integer getCAvailable() {
		return this.CAvailable;
	}

	public void setCAvailable(Integer CAvailable) {
		this.CAvailable = CAvailable;
	}

	public Float getCTotalmoney() {
		return CTotalmoney;
	}

	public void setCTotalmoney(Float cTotalmoney) {
		CTotalmoney = cTotalmoney;
	}

}