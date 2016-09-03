package com.bugframework.common.utility;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class FileOperateUtil {

	public static List<String> preImgFileAbsolutePath = new ArrayList<String>();//存放上传文件的绝对路径,用于删除文件
	public final static String IMAGE_TYPE="image";
	public final static String WORD_TYPE="word";
	/** 
	 *  根据路径删除指定的目录或文件，无论存在与否 
	 *@param sPath  要删除的目录或文件 
	 *@return 删除成功返回 true，否则返回 false。 
	 */  
	public static boolean DeleteFolder(String sPath) {  
	    boolean flag = false;  
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
	public static boolean deleteFile(String sPath) {  
		boolean flag = false;  
	    File file = new File(sPath);
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag;  
	}
/*	public static void deleteMsgFile(int persist, String type){
		//如果前一种上传类型是image,现在的上传类型是word的话,要把第一个list去掉,因为第一个list是image类型的,不去除就会导致下面删除文件时出现逻辑错误
		if (FileOperateUtil.PRETYPE == FileOperateUtil.IMAGE_TYPE && type.equals(FileOperateUtil.WORD_TYPE)){
			FileOperateUtil.preImgFileAbsolutePath.remove(0);
		}
		
		int fileSize = FileOperateUtil.preImgFileAbsolutePath.size();
		for(int i=0;i<fileSize-persist;i++){
			//每删除一个文件,就去除相应的List
			FileOperateUtil.deleteFile(FileOperateUtil.preImgFileAbsolutePath.get(0));
			FileOperateUtil.preImgFileAbsolutePath.remove(0);
			if (FileOperateUtil.wordFileName.size()!=0 && FileOperateUtil.preWordFileRelativePath.size()!=0){
				FileOperateUtil.wordFileName.remove(i);
				FileOperateUtil.preWordFileRelativePath.remove(i);
			}
			
		}
		if (type.equals(FileOperateUtil.WORD_TYPE)){
			FileOperateUtil.preImgFileAbsolutePath.clear();
		}
		FileOperateUtil.PRETYPE = type;
	}*/
	public static void deleteImgFile(int persist){
		
		int fileSize = FileOperateUtil.preImgFileAbsolutePath.size();
		for(int i=0;i<fileSize-persist;i++){
			//每删除一个文件,就去除相应的List
			FileOperateUtil.deleteFile(FileOperateUtil.preImgFileAbsolutePath.get(0));
			FileOperateUtil.preImgFileAbsolutePath.remove(0);
		}
		FileOperateUtil.preImgFileAbsolutePath.clear();
	}
	
	
	public static String relativeToAbsolute (String relativePath , HttpServletRequest request) {
		String absolutePath = new String ();
		if (relativePath!=null && relativePath!="" && request!=null ){
			String splitBase[] = relativePath.split("[..]{2}");
			absolutePath = request.getServletContext().getRealPath("/")+splitBase[splitBase.length-1];
			
		}
		return absolutePath;
	}
	/** 
	 * 删除目录（文件夹）以及目录下的文件 
	 * @param   sPath 被删除目录的文件路径 
	 * @return  目录删除成功返回true，否则返回false 
	 */  
	public static boolean deleteDirectory(String sPath) {  
	    //如果sPath不以文件分隔符结尾，自动添加文件分隔符  
	    if (!sPath.endsWith(File.separator)) {  
	        sPath = sPath + File.separator;  
	    }  
	    File dirFile = new File(sPath);  
	    //如果dir对应的文件不存在，或者不是一个目录，则退出  
	    if (!dirFile.exists() || !dirFile.isDirectory()) {  
	        return false;  
	    }  
	    boolean flag = true;  
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
}
