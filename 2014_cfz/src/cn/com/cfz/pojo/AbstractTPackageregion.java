package cn.com.cfz.pojo;

/**
 * AbstractTPackageregion entity provides the base persistence definition of the
 * TPackageregion entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTPackageregion implements java.io.Serializable {

	// Fields

	private String id;
	private String CPackageid;
	private String CRegionid;
	private String CPackagename;
	private String CRegionname;
	private String CGroupid;
	private Float CIncome;
	private Integer CValidity;

	// Constructors

	/** default constructor */
	public AbstractTPackageregion() {
	}

	/** minimal constructor */
	public AbstractTPackageregion(String id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractTPackageregion(String id, String CPackageid,
			String CRegionid, String CPackagename,
			String CRegionname, String CGroupid, Float CIncome, Integer CValidity) {
		this.id = id;
		this.CPackageid = CPackageid;
		this.CRegionid = CRegionid;
		this.CIncome = CIncome;
		this.CValidity = CValidity;
		this.CPackagename = CPackagename;
		this.CRegionname = CRegionname;
		this.CGroupid = CGroupid;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCPackageid() {
		return this.CPackageid;
	}

	public void setCPackageid(String CPackageid) {
		this.CPackageid = CPackageid;
	}

	public String getCRegionid() {
		return this.CRegionid;
	}

	public void setCRegionid(String CRegionid) {
		this.CRegionid = CRegionid;
	}

	public Float getCIncome() {
		return this.CIncome;
	}

	public void setCIncome(Float CIncome) {
		this.CIncome = CIncome;
	}

	public Integer getCValidity() {
		return this.CValidity;
	}

	public void setCValidity(Integer CValidity) {
		this.CValidity = CValidity;
	}

	public String getCPackagename() {
		return CPackagename;
	}

	public void setCPackagename(String cPackagename) {
		CPackagename = cPackagename;
	}

	public String getCRegionname() {
		return CRegionname;
	}

	public void setCRegionname(String cRegionname) {
		CRegionname = cRegionname;
	}

	public String getCGroupid() {
		return CGroupid;
	}

	public void setCGroupid(String cGroupid) {
		CGroupid = cGroupid;
	}

}