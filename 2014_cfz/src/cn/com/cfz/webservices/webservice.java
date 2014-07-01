package cn.com.cfz.webservices;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.xmlpull.v1.XmlPullParserException;

import com.cn.cfz.R;

import android.content.Context;
import android.content.res.Resources;

/**
 * @author liujunlin
 * @time 2012-3-13
 * @mark
 */
public class webservice {

	public enum Msg {
		XML_ERROR, NET_ERROR, METHOD_ERROR, URLISNULL_ERROR, PARA_ERROR, IO_ERROR, SUCCEED
	}

	private Context m_aActivity = null;
	private Msg msg;
	private String xmlString = "";// �ӿڷ�������xml�ַ���,����Ҫ����
	private String serverip = "";

	public webservice(Context activity) {
		this.m_aActivity = activity;

	}

	/**
	 * 
	 * @author liujunlin
	 * @return �ӿڷ��������ַ���
	 * @time 2012-3-14
	 */
	public String getXmlString() {
		return xmlString;
	}

	/**
	 * ���ظ������
	 */
	public String toString() {
		String result = "";
		switch (msg) {
		case XML_ERROR:
			result = "XML��������";
			break;
		case NET_ERROR:
			result = "�������";
			break;
		case METHOD_ERROR:
			result = "�ӿڷ���������";
			break;
		case URLISNULL_ERROR:
			result = "�ӿڵ�ַ������";
			break;
		case PARA_ERROR:
			result = "�ӿڲ�����׼ȷ";
			break;
		case IO_ERROR:
			result = "��������Ƿ�����";
			break;
		case SUCCEED:
			result = "׼ȷ";
			break;
		}
		return result;
	}

	/**
	 * @author liujunlin
	 * @param method
	 *            �ӿڷ�����
	 * @param para
	 *            �ӿڶ�Ӧ�Ĳ�����
	 * @param paravalues
	 *            �ӿڶ�Ӧ�Ĳ���ֵ
	 * @return TRUE���ýӿڳɹ� FALSE ����ʧ��
	 * @time 2012-3-13
	 */
	public boolean callFromweb(String method, String[] para, String[] paravalues) {
		boolean result = false;
		msg = getWebservice(method, para, paravalues);
		switch (msg) {
		case SUCCEED:
			result = true;
			break;
		}
		return result;
	}

	/**
	 * @author liujunlin
	 * @param method
	 *            �ӿڷ�����
	 * @param para
	 *            �ӿڶ�Ӧ�Ĳ�����
	 * @param paravalues
	 *            �ӿڶ�Ӧ�Ĳ���ֵ
	 * @return
	 * @time 2012-3-13
	 */
	private Msg getWebservice(String method, String[] para, String[] paravalues) {
		String webserviceUrl = m_aActivity.getString(R.string.webservice_url);
		if (para == null || paravalues == null)
			return Msg.PARA_ERROR;
		SoapObject request = new SoapObject(webserviceUrl, method);
		for (int i = 0; i < para.length; i++) {
			request.addProperty(para[i], paravalues[i]);
		}
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = request;
		int timeout = 60000; // set timeout 60s
		MyAndroidHttpTransport androidHttpTransport = new MyAndroidHttpTransport(
				webserviceUrl + "?wsdl", timeout);
		androidHttpTransport.debug = true;
		try {
			androidHttpTransport.call(null, envelope);
		} catch (IOException e) {
			e.printStackTrace();
			return Msg.IO_ERROR;

		} catch (XmlPullParserException e) {

			return Msg.XML_ERROR;
		} catch (Exception e) {
			e.printStackTrace();
		}
		SoapObject o = null;
		try {
			o = (SoapObject) envelope.bodyIn;
			xmlString = o.getProperty(0).toString();
			return Msg.SUCCEED;
		} catch (Exception e) {
			e.printStackTrace();
			return Msg.METHOD_ERROR;
		}
	}

}
