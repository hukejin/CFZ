package cn.com.cfz.pojo;

/**
 * TBankcard entity. @author MyEclipse Persistence Tools
 */
public class TBankcard extends AbstractTBankcard implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TBankcard() {
	}

	/** minimal constructor */
	public TBankcard(String id, String CUserid, String CCardnumber,
			String COpenperson) {
		super(id, CUserid, CCardnumber, COpenperson);
	}

	/** full constructor */
	public TBankcard(String id, String CUserid, String CCardnumber,
			String CBankname, String COpenperson, Long CCreatetime,
			Integer CDefaultcard, Integer CValidity) {
		super(id, CUserid, CCardnumber, CBankname, COpenperson, CCreatetime,
				CDefaultcard, CValidity);
	}

}
