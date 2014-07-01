package cn.com.cfz.pojo;

/**
 * TPackageregion entity. @author MyEclipse Persistence Tools
 */
public class TPackageregion extends AbstractTPackageregion implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TPackageregion() {
	}

	/** minimal constructor */
	public TPackageregion(String id) {
		super(id);
	}

	/** full constructor */
	public TPackageregion(String id, String CPackageid, String CRegionid,
			String CPackagename, String CRegionname, String CGroupid, Float CIncome,
			Integer CValidity) {
		super(id, CPackageid, CRegionid, CPackagename, CRegionname, CGroupid, CIncome,
				CValidity);
	}

}
