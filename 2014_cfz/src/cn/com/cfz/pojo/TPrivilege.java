package cn.com.cfz.pojo;

/**
 * TPrivilege entity. @author MyEclipse Persistence Tools
 */
public class TPrivilege extends AbstractTPrivilege implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TPrivilege() {
	}

	/** minimal constructor */
	public TPrivilege(String id) {
		super(id);
	}

	/** full constructor */
	public TPrivilege(String id, String CPackagename, Integer CPrice,
			String CPackagedesc, String CPath, Integer CCreatetime,
			Integer CState) {
		super(id, CPackagename, CPrice, CPackagedesc, CPath, CCreatetime,
				CState);
	}

}
