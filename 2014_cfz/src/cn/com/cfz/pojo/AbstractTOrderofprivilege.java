package cn.com.cfz.pojo;

/**
 * AbstractTOrderofprivilege entity provides the base persistence definition of
 * the TOrderofprivilege entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTOrderofprivilege implements java.io.Serializable {

	// Fields

	private String id;
	private String CPackageid;
	private String CUserid;
	private String CUserphone;
	private Integer CState;
	private Long CCreatetime;
	private Long CValidtime;

	// Constructors

	/** default constructor */
	public AbstractTOrderofprivilege() {
	}

	/** minimal constructor */
	public AbstractTOrderofprivilege(String id, String CPackageid,
			String CUserid, String CUserphone) {
		this.id = id;
		this.CPackageid = CPackageid;
		this.CUserid = CUserid;
		this.CUserphone = CUserphone;
	}

	/** full constructor */
	public AbstractTOrderofprivilege(String id, String CPackageid,
			String CUserid, String CUserphone, Integer CState,
			Long CCreatetime, Long CValidtime) {
		this.id = id;
		this.CPackageid = CPackageid;
		this.CUserid = CUserid;
		this.CUserphone = CUserphone;
		this.CState = CState;
		this.CCreatetime = CCreatetime;
		this.CValidtime = CValidtime;
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

	public String getCUserid() {
		return this.CUserid;
	}

	public void setCUserid(String CUserid) {
		this.CUserid = CUserid;
	}

	public String getCUserphone() {
		return this.CUserphone;
	}

	public void setCUserphone(String CUserphone) {
		this.CUserphone = CUserphone;
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

	public Long getCValidtime() {
		return this.CValidtime;
	}

	public void setCValidtime(Long CValidtime) {
		this.CValidtime = CValidtime;
	}

}