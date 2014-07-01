package cn.com.cfz.pojo;

/**
 * AbstractTPrivilege entity provides the base persistence definition of the
 * TPrivilege entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTPrivilege implements java.io.Serializable {

	// Fields

	private String id;
	private String CPackagename;
	private Integer CPrice;
	private String CPackagedesc;
	private String CPath;
	private Integer CCreatetime;
	private Integer CState;

	// Constructors

	/** default constructor */
	public AbstractTPrivilege() {
	}

	/** minimal constructor */
	public AbstractTPrivilege(String id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractTPrivilege(String id, String CPackagename, Integer CPrice,
			String CPackagedesc, String CPath, Integer CCreatetime,
			Integer CState) {
		this.id = id;
		this.CPackagename = CPackagename;
		this.CPrice = CPrice;
		this.CPackagedesc = CPackagedesc;
		this.CPath = CPath;
		this.CCreatetime = CCreatetime;
		this.CState = CState;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCPackagename() {
		return this.CPackagename;
	}

	public void setCPackagename(String CPackagename) {
		this.CPackagename = CPackagename;
	}

	public Integer getCPrice() {
		return this.CPrice;
	}

	public void setCPrice(Integer CPrice) {
		this.CPrice = CPrice;
	}

	public String getCPackagedesc() {
		return this.CPackagedesc;
	}

	public void setCPackagedesc(String CPackagedesc) {
		this.CPackagedesc = CPackagedesc;
	}

	public String getCPath() {
		return this.CPath;
	}

	public void setCPath(String CPath) {
		this.CPath = CPath;
	}

	public Integer getCCreatetime() {
		return this.CCreatetime;
	}

	public void setCCreatetime(Integer CCreatetime) {
		this.CCreatetime = CCreatetime;
	}

	public Integer getCState() {
		return this.CState;
	}

	public void setCState(Integer CState) {
		this.CState = CState;
	}

}