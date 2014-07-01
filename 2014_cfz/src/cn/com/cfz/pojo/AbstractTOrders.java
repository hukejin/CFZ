package cn.com.cfz.pojo;

/**
 * AbstractTOrders entity provides the base persistence definition of the
 * TOrders entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTOrders implements java.io.Serializable {

	// Fields

	private String id;
	private String CPackageregionid;
	private String COrdername;
	private String COwnname;
	private String COwnphone;
	private String CEmail;
	private String CMarkcode;
	private Float CIncome;
	private Integer CState;
	private Long CValidtime;
	private Long CCreatetime;

	// Constructors

	/** default constructor */
	public AbstractTOrders() {
	}

	/** minimal constructor */
	public AbstractTOrders(String id, String CPackageregionid) {
		this.id = id;
		this.CPackageregionid = CPackageregionid;
	}

	/** full constructor */
	public AbstractTOrders(String id, String CPackageregionid,
			String COrdername, String COwnname, String COwnphone,
			String CEmail, String CMarkcode, Float CIncome, Integer CState,
			Long CValidtime, Long CCreatetime) {
		this.id = id;
		this.CPackageregionid = CPackageregionid;
		this.COrdername = COrdername;
		this.COwnname = COwnname;
		this.COwnphone = COwnphone;
		this.CEmail = CEmail;
		this.CMarkcode = CMarkcode;
		this.CIncome = CIncome;
		this.CState = CState;
		this.CValidtime = CValidtime;
		this.CCreatetime = CCreatetime;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCPackageregionid() {
		return this.CPackageregionid;
	}

	public void setCPackageregionid(String CPackageregionid) {
		this.CPackageregionid = CPackageregionid;
	}

	public String getCOrdername() {
		return this.COrdername;
	}

	public void setCOrdername(String COrdername) {
		this.COrdername = COrdername;
	}

	public String getCOwnname() {
		return this.COwnname;
	}

	public void setCOwnname(String COwnname) {
		this.COwnname = COwnname;
	}

	public String getCOwnphone() {
		return this.COwnphone;
	}

	public void setCOwnphone(String COwnphone) {
		this.COwnphone = COwnphone;
	}

	public String getCEmail() {
		return this.CEmail;
	}

	public void setCEmail(String CEmail) {
		this.CEmail = CEmail;
	}

	public String getCMarkcode() {
		return this.CMarkcode;
	}

	public void setCMarkcode(String CMarkcode) {
		this.CMarkcode = CMarkcode;
	}

	public Float getCIncome() {
		return this.CIncome;
	}

	public void setCIncome(Float CIncome) {
		this.CIncome = CIncome;
	}

	public Integer getCState() {
		return this.CState;
	}

	public void setCState(Integer CState) {
		this.CState = CState;
	}

	public Long getCValidtime() {
		return this.CValidtime;
	}

	public void setCValidtime(Long CValidtime) {
		this.CValidtime = CValidtime;
	}

	public Long getCCreatetime() {
		return this.CCreatetime;
	}

	public void setCCreatetime(Long CCreatetime) {
		this.CCreatetime = CCreatetime;
	}

}