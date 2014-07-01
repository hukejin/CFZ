package cn.com.cfz.pojo;

/**
 * AbstractTBankcard entity provides the base persistence definition of the
 * TBankcard entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTBankcard implements java.io.Serializable {

	// Fields

	private String id;
	private String CUserid;
	private String CCardnumber;
	private String CBankname;
	private String COpenperson;
	private Long CCreatetime;
	private Integer CDefaultcard;
	private Integer CValidity;

	// Constructors

	/** default constructor */
	public AbstractTBankcard() {
	}

	/** minimal constructor */
	public AbstractTBankcard(String id, String CUserid, String CCardnumber,
			String COpenperson) {
		this.id = id;
		this.CUserid = CUserid;
		this.CCardnumber = CCardnumber;
		this.COpenperson = COpenperson;
	}

	/** full constructor */
	public AbstractTBankcard(String id, String CUserid, String CCardnumber,
			String CBankname, String COpenperson, Long CCreatetime,
			Integer CDefaultcard, Integer CValidity) {
		this.id = id;
		this.CUserid = CUserid;
		this.CCardnumber = CCardnumber;
		this.CBankname = CBankname;
		this.COpenperson = COpenperson;
		this.CCreatetime = CCreatetime;
		this.CDefaultcard = CDefaultcard;
		this.CValidity = CValidity;
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

	public String getCCardnumber() {
		return this.CCardnumber;
	}

	public void setCCardnumber(String CCardnumber) {
		this.CCardnumber = CCardnumber;
	}

	public String getCBankname() {
		return this.CBankname;
	}

	public void setCBankname(String CBankname) {
		this.CBankname = CBankname;
	}

	public String getCOpenperson() {
		return this.COpenperson;
	}

	public void setCOpenperson(String COpenperson) {
		this.COpenperson = COpenperson;
	}

	public Long getCCreatetime() {
		return this.CCreatetime;
	}

	public void setCCreatetime(Long CCreatetime) {
		this.CCreatetime = CCreatetime;
	}

	public Integer getCDefaultcard() {
		return this.CDefaultcard;
	}

	public void setCDefaultcard(Integer CDefaultcard) {
		this.CDefaultcard = CDefaultcard;
	}

	public Integer getCValidity() {
		return this.CValidity;
	}

	public void setCValidity(Integer CValidity) {
		this.CValidity = CValidity;
	}

}