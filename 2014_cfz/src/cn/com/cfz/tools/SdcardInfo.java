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
 * @mark 检测存储卡是否存在，以及存储卡容量是否快满了
 */
public class SdcardInfo {

	private static String sdcardpath = "";
	public static String File_Pic = Environment.getExternalStorageDirectory()
			.getPath() + "/cfz/pic";// 照片全部放在这个目录下
	public static String File_Download = Environment.getExternalStorageDirectory()
			.getPath() + "/cfz/download";// 下载全部放在这个目录下
	
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
	 *  根据路径删除指定的目录或文件，无论存在与否 
	 *@param sPath  要删除的目录或文件 
	 *@return 删除成功返回 true，否则返回 false。 
	 */  
	public boolean DeleteFolder(String sPath) {  
	    flag = true;  
	    File file = new File(sPath);  
	    // 判断目录或文件是否存在  
	    if (!file.exists()) {  // 不存在返回 false  
	        return flag;  
	    } else {  
	        // 判断是否为文件  
	        if (file.isFile()) {  // 为文件时调用删除文件方法  
	            return deleteFile(sPath);  
	        } else {  // 为目录时调用删除目录方法  
	            return deleteDirectory(sPath);  
	        }  
	    }  
	}  
	
	/** 
	 * 删除单个文件 
	 * @param   sPath    被删除文件的文件名 
	 * @return 单个文件删除成功返回true，否则返回false 
	 */  
	public boolean deleteFile(String sPath) {  
	    flag = false;  
	    file = new File(sPath);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag;  
	}  
	
	/** 
	 * 删除目录（文件夹）以及目录下的文件 
	 * @param   sPath 被删除目录的文件路径 
	 * @return  目录删除成功返回true，否则返回false 
	 */  
	public boolean deleteDirectory(String sPath) {  
	    //如果sPath不以文件分隔符结尾，自动添加文件分隔符  
	    if (!sPath.endsWith(File.separator)) {  
	        sPath = sPath + File.separator;  
	    }  
	    File dirFile = new File(sPath);  
	    //如果dir对应的文件不存在，或者不是一个目录，则退出  
	    if (!dirFile.exists() || !dirFile.isDirectory()) {  
	        return false;  
	    }  
	    flag = true;  
	    //删除文件夹下的所有文件(包括子目录)  
	    File[] files = dirFile.listFiles();  
	    for (int i = 0; i < files.length; i++) {  
	        //删除子文件  
	        if (files[i].isFile()) {  
	            flag = deleteFile(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        } //删除子目录  
	        else {  
	            flag = deleteDirectory(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        }  
	    }  
	    if (!flag) return false;  
	    //删除当前目录  
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

	// 是否存在存储卡
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
	 * @param 存储卡容量
	 * @param 可用空间
	 * @return 所占比率
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
	 * 创建文件夹
	 * 
	 * @author liujunlin
	 * @param appname
	 *            工程指定文件夹
	 * @param filename
	 *            保存的文件名称
	 * @param filetype
	 *            文件类型（pic/voice/video）
	 * @time 2012-8-29 返回已创建的路径
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

	// 以系统时间为文件夹名
	public String filename() {
		String systime;
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = c.getTime();
		systime = df.format(date);
		return systime;
	}
	
	/**
	 * 保存图片到本地
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
