package cn.com.cfz.pojo;

/**
 * TPackage entity. @author MyEclipse Persistence Tools
 */
public class TPackage extends AbstractTPackage implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public TPackage() {
	}

	/** minimal constructor */
	public TPackage(String id, String CPackagename, Integer CPrice) {
		super(id, CPackagename, CPrice);
	}

	/** full constructor */
	public TPackage(String id, String CPackagename, Integer CPrice,
			String CPackagedesc, String CPath, Long CCreatetime, Integer CType,
			Integer CState) {
		super(id, CPackagename, CPrice, CPackagedesc, CPath, CCreatetime,
				CType, CState);
	}

}
