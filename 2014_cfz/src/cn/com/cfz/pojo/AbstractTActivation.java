package cn.com.cfz.pojo;

/**
 * AbstractTActivation entity provides the base persistence definition of the
 * TActivation entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTActivation implements java.io.Serializable {

	// Fields

	private String id;
	private String CUserid;
	private String CKeyword;
	private Integer CState;

	// Constructors

	/** default constructor */
	public AbstractTActivation() {
	}

	/** minimal constructor */
	public AbstractTActivation(String id, String CUserid, String CKeyword) {
		this.id = id;
		this.CUserid = CUserid;
		this.CKeyword = CKeyword;
	}

	/** full constructor */
	public AbstractTActivation(String id, String CUserid, String CKeyword,
			Integer CState) {
		this.id = id;
		this.CUserid = CUserid;
		this.CKeyword = CKeyword;
		this.CState = CState;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCUserid() {
		return this.CUserid;
	}

	public void setCUserid(String CUserid) {
		this.CUserid = CUserid;
	}

	public String getCKeyword() {
		return this.CKeyword;
	}

	public void setCKeyword(String CKeyword) {
		this.CKeyword = CKeyword;
	}

	public Integer getCState() {
		return this.CState;
	}

	public void setCState(Integer CState) {
		this.CState = CState;
	}

}