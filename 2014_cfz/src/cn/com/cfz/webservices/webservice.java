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
	private String xmlString = "";// 接口返回来的xml字符串,还需要解析
	private String serverip = "";

	public webservice(Context activity) {
		this.m_aActivity = activity;

	}

	/**
	 * 
	 * @author liujunlin
	 * @return 接口返回来的字符串
	 * @time 2012-3-14
	 */
	public String getXmlString() {
		return xmlString;
	}

	/**
	 * 返回各类错误
	 */
	public String toString() {
		String result = "";
		switch (msg) {
		case XML_ERROR:
			result = "XML解析错误";
			break;
		case NET_ERROR:
			result = "网络错误";
			break;
		case METHOD_ERROR:
			result = "接口方法不存在";
			break;
		case URLISNULL_ERROR:
			result = "接口地址不存在";
			break;
		case PARA_ERROR:
			result = "接口参数不准确";
			break;
		case IO_ERROR:
			result = "请检查服务是否正常";
			break;
		case SUCCEED:
			result = "准确";
			break;
		}
		return result;
	}

	/**
	 * @author liujunlin
	 * @param method
	 *            接口方法名
	 * @param para
	 *            接口对应的参数名
	 * @param paravalues
	 *            接口对应的参数值
	 * @return TRUE调用接口成功 FALSE 调用失败
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
	 *            接口方法名
	 * @param para
	 *            接口对应的参数名
	 * @param paravalues
	 *            接口对应的参数值
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
