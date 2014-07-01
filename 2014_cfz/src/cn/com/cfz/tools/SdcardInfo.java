package cn.com.cfz.tools;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

/**
 * @author liujunlin
 * @time 2011-8-23
 * @mark ���洢���Ƿ���ڣ��Լ��洢�������Ƿ������
 */
public class SdcardInfo {

	private static String sdcardpath = "";
	public static String File_Pic = Environment.getExternalStorageDirectory()
			.getPath() + "/cfz/pic";// ��Ƭȫ���������Ŀ¼��
	public static String File_Download = Environment.getExternalStorageDirectory()
			.getPath() + "/cfz/download";// ����ȫ���������Ŀ¼��
	
	private boolean flag = false;
	
	private File file;

	public SdcardInfo() {
		createDir();

	}

	@SuppressLint("NewApi")
	private void createDir() {
		if (Environment.getExternalStorageDirectory().getParentFile().exists()) {
			File fll = new File(Environment.getExternalStorageDirectory()
					.getParent());
			File[] fllist = fll.listFiles();
			if (fllist != null) {
				for (File file : fllist) {
					if (file.exists() && file.getFreeSpace() > 0) {
						File_Pic = file.getPath() + "/cfz/pic";
						File_Download = file.getPath() + "/cfz/download";
						boolean createFile = true;
						File fl = new File(File_Pic);
						if (!fl.exists())
							createFile = fl.mkdirs();
						File fl3 = new File(File_Download);
						if (!fl3.exists())
							createFile = fl3.mkdirs();
						if(!createFile)
							continue;
						sdcardpath = file.getPath();
						break;
					}
				}
			}
		}

	}

	/** 
	 *  ����·��ɾ��ָ����Ŀ¼���ļ������۴������ 
	 *@param sPath  Ҫɾ����Ŀ¼���ļ� 
	 *@return ɾ���ɹ����� true�����򷵻� false�� 
	 */  
	public boolean DeleteFolder(String sPath) {  
	    flag = true;  
	    File file = new File(sPath);  
	    // �ж�Ŀ¼���ļ��Ƿ����  
	    if (!file.exists()) {  // �����ڷ��� false  
	        return flag;  
	    } else {  
	        // �ж��Ƿ�Ϊ�ļ�  
	        if (file.isFile()) {  // Ϊ�ļ�ʱ����ɾ���ļ�����  
	            return deleteFile(sPath);  
	        } else {  // ΪĿ¼ʱ����ɾ��Ŀ¼����  
	            return deleteDirectory(sPath);  
	        }  
	    }  
	}  
	
	/** 
	 * ɾ�������ļ� 
	 * @param   sPath    ��ɾ���ļ����ļ��� 
	 * @return �����ļ�ɾ���ɹ�����true�����򷵻�false 
	 */  
	public boolean deleteFile(String sPath) {  
	    flag = false;  
	    file = new File(sPath);  
	    // ·��Ϊ�ļ��Ҳ�Ϊ�������ɾ��  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag;  
	}  
	
	/** 
	 * ɾ��Ŀ¼���ļ��У��Լ�Ŀ¼�µ��ļ� 
	 * @param   sPath ��ɾ��Ŀ¼���ļ�·�� 
	 * @return  Ŀ¼ɾ���ɹ�����true�����򷵻�false 
	 */  
	public boolean deleteDirectory(String sPath) {  
	    //���sPath�����ļ��ָ�����β���Զ�����ļ��ָ���  
	    if (!sPath.endsWith(File.separator)) {  
	        sPath = sPath + File.separator;  
	    }  
	    File dirFile = new File(sPath);  
	    //���dir��Ӧ���ļ������ڣ����߲���һ��Ŀ¼�����˳�  
	    if (!dirFile.exists() || !dirFile.isDirectory()) {  
	        return false;  
	    }  
	    flag = true;  
	    //ɾ���ļ����µ������ļ�(������Ŀ¼)  
	    File[] files = dirFile.listFiles();  
	    for (int i = 0; i < files.length; i++) {  
	        //ɾ�����ļ�  
	        if (files[i].isFile()) {  
	            flag = deleteFile(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        } //ɾ����Ŀ¼  
	        else {  
	            flag = deleteDirectory(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        }  
	    }  
	    if (!flag) return false;  
	    //ɾ����ǰĿ¼  
	    if (dirFile.delete()) {  
	        return true;  
	    } else {  
	        return false;  
	    }  
	}  
	
	
	public String getSdcardpath() {
		return sdcardpath;
	}

	public void setSdcardpath(String sdcardpath) {
		this.sdcardpath = sdcardpath;
	}

	public boolean Sdcardisfull() {
		double lb = spareCard();
		if (lb >= 0.8) {
			return true;
		} else {
			return false;
		}
	}

	// �Ƿ���ڴ洢��
	public boolean isExistSDcard() {
		boolean rt = false;
		if (!sdcardpath.equals("")) {
			// File path = Environment.getExternalStorageDirectory();
			// sdcardpath = path.getPath();
			Log.i("SDcardpath is@@@@@@@@", sdcardpath);
			rt = true;
		}

		return rt;
	}

	/**
	 * 
	 * @param �洢������
	 * @param ���ÿռ�
	 * @return ��ռ����
	 */
	private double spareCard() {
		double spare = 0.0;
		StatFs statfs = new StatFs(sdcardpath);
		long totalblocks = statfs.getBlockCount();
		long availableblocks = statfs.getAvailableBlocks();
		spare = (double) (totalblocks - availableblocks) / (double) totalblocks;
		return spare;
	}

	/**
	 * �����ļ���
	 * 
	 * @author liujunlin
	 * @param appname
	 *            ����ָ���ļ���
	 * @param filename
	 *            ������ļ�����
	 * @param filetype
	 *            �ļ����ͣ�pic/voice/video��
	 * @time 2012-8-29 �����Ѵ�����·��
	 */
	public String createFileDir(String appname, String filetype) {

		String filedir = filename();
		String picString = sdcardpath + java.io.File.separator + appname
				+ java.io.File.separator + filetype + java.io.File.separator
				+ filedir;
		try {
			File flFile = new File(picString);
			if (!flFile.exists())
				flFile.mkdirs();
			return picString;
		} catch (Exception e) {
			picString = "";
			return picString;
		}
	}

	// ��ϵͳʱ��Ϊ�ļ�����
	public String filename() {
		String systime;
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = c.getTime();
		systime = df.format(date);
		return systime;
	}
	
	/**
	 * ����ͼƬ������
	 * @param bm
	 * @param fileName
	 * @throws IOException
	 */
	public void saveFile(Bitmap bm, String fileName) throws IOException {  
        File dirFile = new File(File_Pic);  
        if(!dirFile.exists()){  
            dirFile.mkdirs();  
        }  
        File myCaptureFile = new File(File_Pic + fileName);  
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));  
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);  
        bos.flush();  
        bos.close();  
    } 

}
