package cn.com.cfz.pojo;

/**
 * AbstractTTakemoney entity provides the base persistence definition of the
 * TTakemoney entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTTakemoney implements java.io.Serializable {

	// Fields

	private String id;
	private String CUserid;
	private String CBankcardid;
	private String CCardcode;
	private Float CMoney;
	private Long CCreatetime;
	private Integer CExcution;

	// Constructors

	/** default constructor */
	public AbstractTTakemoney() {
	}

	/** minimal constructor */
	public AbstractTTakemoney(String id, String CUserid, String CBankcardid) {
		this.id = id;
		this.CUserid = CUserid;
		this.CBankcardid = CBankcardid;
	}

	/** full constructor */
	public AbstractTTakemoney(String id, String CUserid, String CBankcardid,
			String CCardcode, Float CMoney, Long CCreatetime, Integer CExcution) {
		this.id = id;
		this.CUserid = CUserid;
		this.CBankcardid = CBankcardid;
		this.CCardcode = CCardcode;
		this.CMoney = CMoney;
		this.CCreatetime = CCreatetime;
		this.CExcution = CExcution;
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

	public String getCBankcardid() {
		return this.CBankcardid;
	}

	public void setCBankcardid(String CBankcardid) {
		this.CBankcardid = CBankcardid;
	}

	public String getCCardcode() {
		return this.CCardcode;
	}

	public void setCCardcode(String CCardcode) {
		this.CCardcode = CCardcode;
	}

	public Float getCMoney() {
		return this.CMoney;
	}

	public void setCMoney(Float CMoney) {
		this.CMoney = CMoney;
	}

	public Long getCCreatetime() {
		return this.CCreatetime;
	}

	public void setCCreatetime(Long CCreatetime) {
		this.CCreatetime = CCreatetime;
	}

	public Integer getCExcution() {
		return this.CExcution;
	}

	public void setCExcution(Integer CExcution) {
		this.CExcution = CExcution;
	}

}