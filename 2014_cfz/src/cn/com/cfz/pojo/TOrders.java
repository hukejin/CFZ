package cn.com.cfz.pojo;

/**
 * TOrders entity. @author MyEclipse Persistence Tools
 */
public class TOrders extends AbstractTOrders implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public TOrders() {
	}

	/** minimal constructor */
	public TOrders(String id, String CPackageregionid) {
		super(id, CPackageregionid);
	}

	/** full constructor */
	public TOrders(String id, String CPackageregionid, String COrdername,
			String COwnname, String COwnphone, String CEmail, String CMarkcode,
			Float CIncome, Integer CState, Long CValidtime, Long CCreatetime) {
		super(id, CPackageregionid, COrdername, COwnname, COwnphone, CEmail,
				CMarkcode, CIncome, CState, CValidtime, CCreatetime);
	}

}
