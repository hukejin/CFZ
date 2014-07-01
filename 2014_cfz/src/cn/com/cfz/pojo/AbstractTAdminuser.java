package cn.com.cfz.pojo;

/**
 * AbstractTAdminuser entity provides the base persistence definition of the
 * TAdminuser entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTAdminuser implements java.io.Serializable {

	// Fields

	private String id;
	private String CAdmincode;
	private String CPassword;
	private String CRegionid;
	private Integer CType;
	private Integer CState;
	private Long CCreatetime;

	// Constructors

	/** default constructor */
	public AbstractTAdminuser() {
	}

	/** minimal constructor */
	public AbstractTAdminuser(String id, String CPassword, String CRegionid) {
		this.id = id;
		this.CPassword = CPassword;
		this.CRegionid = CRegionid;
	}

	/** full constructor */
	public AbstractTAdminuser(String id, String CAdmincode, String CPassword,
			String CRegionid, Integer CType, Integer CState,
			Long CCreatetime) {
		this.id = id;
		this.CAdmincode = CAdmincode;
		this.CPassword = CPassword;
		this.CRegionid = CRegionid;
		this.CType = CType;
		this.CState = CState;
		this.CCreatetime = CCreatetime;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCAdmincode() {
		return this.CAdmincode;
	}

	public void setCAdmincode(String CAdmincode) {
		this.CAdmincode = CAdmincode;
	}

	public String getCPassword() {
		return this.CPassword;
	}

	public void setCPassword(String CPassword) {
		this.CPassword = CPassword;
	}

	public String getCRegionid() {
		return CRegionid;
	}

	public void setCRegionid(String cRegionid) {
		CRegionid = cRegionid;
	}

	public Integer getCType() {
		return this.CType;
	}

	public void setCType(Integer CType) {
		this.CType = CType;
	}

	public Integer getCState() {
		return this.CState;
	}

	public void setCState(Integer CState) {
		this.CState = CState;
	}

	public Long getCCreatetime() {
		return this.CCreatetime;
	}

	public void setCCreatetime(Long CCreatetime) {
		this.CCreatetime = CCreatetime;
	}

}