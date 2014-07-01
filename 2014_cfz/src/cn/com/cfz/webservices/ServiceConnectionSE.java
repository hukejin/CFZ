package cn.com.cfz.webservices;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.ksoap2.transport.ServiceConnection;

/** 
 * @author  liujunlin
 * @time  2013-3-7
 * @mark 
 */
public class ServiceConnectionSE implements ServiceConnection{
	private HttpURLConnection connection;  
	   
	  public ServiceConnectionSE(String url,int timeout)  
	    throws IOException  
	  {  
	    this.connection = ((HttpURLConnection)new URL(url).openConnection());  
	    this.connection.setUseCaches(false);  
	    this.connection.setDoOutput(true);  
	    this.connection.setDoInput(true);  
	    this.connection.setConnectTimeout(timeout);
	    this.connection.setReadTimeout(timeout);
	  }  
	   
	  public void connect() throws IOException {  
	    this.connection.connect();  
	  }  
	   
	  public void disconnect() {  
	    this.connection.disconnect();  
	  }  
	   
	  public void setRequestProperty(String string, String soapAction) {  
	    this.connection.setRequestProperty(string, soapAction);  
	  }  
	   
	  public void setRequestMethod(String requestMethod) throws IOException {  
	    this.connection.setRequestMethod(requestMethod);  
	  }  
	   
	  public OutputStream openOutputStream() throws IOException {  
	    return this.connection.getOutputStream();  
	  }  
	   
	  public InputStream openInputStream() throws IOException {  
	    return this.connection.getInputStream();  
	  }  
	   
	  public InputStream getErrorStream() {  
	    return this.connection.getErrorStream();  
	  }  
 

}
