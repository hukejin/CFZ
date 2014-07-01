package cn.com.cfz.pojo;

/**
 * AbstractTPackage entity provides the base persistence definition of the
 * TPackage entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTPackage implements java.io.Serializable {

	// Fields

	private String id;
	private String CPackagename;
	private Integer CPrice;
	private String CPackagedesc;
	private String CPath;
	private Long CCreatetime;
	private Integer CType;
	private Integer CState;

	// Constructors

	/** default constructor */
	public AbstractTPackage() {
	}

	/** minimal constructor */
	public AbstractTPackage(String id, String CPackagename, Integer CPrice) {
		this.id = id;
		this.CPackagename = CPackagename;
		this.CPrice = CPrice;
	}

	/** full constructor */
	public AbstractTPackage(String id, String CPackagename, Integer CPrice,
			String CPackagedesc, String CPath, Long CCreatetime, Integer CType,
			Integer CState) {
		this.id = id;
		this.CPackagename = CPackagename;
		this.CPrice = CPrice;
		this.CPackagedesc = CPackagedesc;
		this.CPath = CPath;
		this.CCreatetime = CCreatetime;
		this.CType = CType;
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

	public Long getCCreatetime() {
		return this.CCreatetime;
	}

	public void setCCreatetime(Long CCreatetime) {
		this.CCreatetime = CCreatetime;
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

}