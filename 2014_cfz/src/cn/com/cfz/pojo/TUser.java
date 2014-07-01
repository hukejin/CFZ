package cn.com.cfz.pojo;

/**
 * TUser entity. @author MyEclipse Persistence Tools
 */
public class TUser extends AbstractTUser implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public TUser() {
	}

	/** minimal constructor */
	public TUser(String id, String CPassword, String CMarkcode) {
		super(id, CPassword, CMarkcode);
	}

	/** full constructor */
	public TUser(String id, String CUsername, String CPassword,
			String CRegionid, String CRegiondesc, String CPhone, String CEmail,
			Integer CType, String CEnterprisecode, String CPersoncard,
			Float CTotalmoney, Float CMoney, String CDefaultcardcode,
			Long CCreatetime, Long CLogintime, String CMarkcode,
			Integer CAvailable) {
		super(id, CUsername, CPassword, CRegionid, CRegiondesc, CPhone, CEmail,
				CType, CEnterprisecode, CPersoncard, CTotalmoney, CMoney,
				CDefaultcardcode, CCreatetime, CLogintime, CMarkcode,
				CAvailable);
	}

}
