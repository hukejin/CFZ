package cn.com.cfz.pojo;

/**
 * TOrderofprivilege entity. @author MyEclipse Persistence Tools
 */
public class TOrderofprivilege extends AbstractTOrderofprivilege implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TOrderofprivilege() {
	}

	/** minimal constructor */
	public TOrderofprivilege(String id, String CPackageid, String CUserid,
			String CUserphone) {
		super(id, CPackageid, CUserid, CUserphone);
	}

	/** full constructor */
	public TOrderofprivilege(String id, String CPackageid, String CUserid,
			String CUserphone, Integer CState, Long CCreatetime, Long CValidtime) {
		super(id, CPackageid, CUserid, CUserphone, CState, CCreatetime,
				CValidtime);
	}

}
