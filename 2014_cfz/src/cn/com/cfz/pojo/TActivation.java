package cn.com.cfz.pojo;

/**
 * TActivation entity. @author MyEclipse Persistence Tools
 */
public class TActivation extends AbstractTActivation implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TActivation() {
	}

	/** minimal constructor */
	public TActivation(String id, String CUserid, String CKeyword) {
		super(id, CUserid, CKeyword);
	}

	/** full constructor */
	public TActivation(String id, String CUserid, String CKeyword,
			Integer CState) {
		super(id, CUserid, CKeyword, CState);
	}

}
