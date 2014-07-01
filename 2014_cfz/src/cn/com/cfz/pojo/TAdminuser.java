package cn.com.cfz.pojo;

/**
 * TAdminuser entity. @author MyEclipse Persistence Tools
 */
public class TAdminuser extends AbstractTAdminuser implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TAdminuser() {
	}

	/** minimal constructor */
	public TAdminuser(String id, String CPassword, String CRegionid) {
		super(id, CPassword, CRegionid);
	}

	/** full constructor */
	public TAdminuser(String id, String CAdmincode, String CPassword,
			String CRegionid, Integer CType, Integer CState,
			Long CCreatetime) {
		super(id, CAdmincode, CPassword, CRegionid, CType, CState,
				CCreatetime);
	}

}
