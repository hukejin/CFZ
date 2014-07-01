package cn.com.cfz.pojo;

/**
 * TRegion entity. @author MyEclipse Persistence Tools
 */
public class TRegion extends AbstractTRegion implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public TRegion() {
	}

	/** minimal constructor */
	public TRegion(String id, String CRegionname, String CRegioncode) {
		super(id, CRegionname, CRegioncode);
	}

	/** full constructor */
	public TRegion(String id, String CRegionname, String CRegioncode,
			String CParent, Integer CType, Integer CState, Long CCreatetime) {
		super(id, CRegionname, CRegioncode, CParent, CType, CState, CCreatetime);
	}

}
