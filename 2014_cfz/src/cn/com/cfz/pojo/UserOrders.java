package cn.com.cfz.pojo;

import java.io.Serializable;

/**
 * 
*   
* ���ƣ�UserOrders    
* �����������ṹ     
* �����ˣ�Administrator     
* ����ʱ�䣺2014-6-15 ����8:07:20        
* ��ע��
 */
public class UserOrders implements Serializable{

	private TOrders tOrders;
	private TPackageregion tPackageregion;
	private TPackage tPackage;
	private TRegion tRegion;
	public TOrders gettOrders() {
		return tOrders;
	}
	public void settOrders(TOrders tOrders) {
		this.tOrders = tOrders;
	}
	public TPackageregion gettPackageregion() {
		return tPackageregion;
	}
	public void settPackageregion(TPackageregion tPackageregion) {
		this.tPackageregion = tPackageregion;
	}
	public TPackage gettPackage() {
		return tPackage;
	}
	public void settPackage(TPackage tPackage) {
		this.tPackage = tPackage;
	}
	public TRegion gettRegion() {
		return tRegion;
	}
	public void settRegion(TRegion tRegion) {
		this.tRegion = tRegion;
	}
	
	
	
}

