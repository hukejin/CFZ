package cn.com.cfz.pojo;

/**
 * TTakemoney entity. @author MyEclipse Persistence Tools
 */
public class TTakemoney extends AbstractTTakemoney implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TTakemoney() {
	}

	/** minimal constructor */
	public TTakemoney(String id, String CUserid, String CBankcardid) {
		super(id, CUserid, CBankcardid);
	}

	/** full constructor */
	public TTakemoney(String id, String CUserid, String CBankcardid,
			String CCardcode, Float CMoney, Long CCreatetime, Integer CExcution) {
		super(id, CUserid, CBankcardid, CCardcode, CMoney, CCreatetime,
				CExcution);
	}

}
