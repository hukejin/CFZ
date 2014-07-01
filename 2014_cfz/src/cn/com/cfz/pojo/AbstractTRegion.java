package cn.com.cfz.pojo;

/**
 * AbstractTRegion entity provides the base persistence definition of the
 * TRegion entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTRegion implements java.io.Serializable {

	// Fields

	private String id;
	private String CRegionname;
	private String CRegioncode;
	private String CParent;
	private Integer CType;
	private Integer CState;
	private Long CCreatetime;

	// Constructors

	/** default constructor */
	public AbstractTRegion() {
	}

	/** minimal constructor */
	public AbstractTRegion(String id, String CRegionname, String CRegioncode) {
		this.id = id;
		this.CRegionname = CRegionname;
		this.CRegioncode = CRegioncode;
	}

	/** full constructor */
	public AbstractTRegion(String id, String CRegionname, String CRegioncode,
			String CParent, Integer CType, Integer CState, Long CCreatetime) {
		this.id = id;
		this.CRegionname = CRegionname;
		this.CRegioncode = CRegioncode;
		this.CParent = CParent;
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

	public String getCRegionname() {
		return this.CRegionname;
	}

	public void setCRegionname(String CRegionname) {
		this.CRegionname = CRegionname;
	}

	public String getCRegioncode() {
		return this.CRegioncode;
	}

	public void setCRegioncode(String CRegioncode) {
		this.CRegioncode = CRegioncode;
	}

	public String getCParent() {
		return this.CParent;
	}

	public void setCParent(String CParent) {
		this.CParent = CParent;
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

	public void setCCreatetime(long CCreatetime) {
		this.CCreatetime = CCreatetime;
	}

}