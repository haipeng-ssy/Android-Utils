package com.sunyiyan.java.file.io;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
/**
 * @author sunyiyan
 * 操作文件流
 * */
public class JavaOperateFile   {
	/**
	 * 把一个文件里的字符串全部读出来，并以字符串形式抛出
	 * */
	public static String readFile(File file){  
		 if(null == file ) return null;  
		 StringBuffer stringBuffer = new StringBuffer();    
		 try {
			 //BufferedReader fileReader = new BufferedReader(new FileReader(file));    
			 //默认字符编码:GBK
			 //   BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			 //默认字符编码:GBK   
			 
			 /**
			  * 文件到字符串
			  * */
			 BufferedReader fileReader = 
					 new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));  
			 String line = fileReader.readLine();  
			 
			/**
			 * 读完文件
			 * */
			 while(null != line){    
				 stringBuffer.append(line);    
				 line = fileReader.readLine(); 
				 }  
			 /**
			  * BufferReader关闭
			  * */
			 fileReader.close();//读完要及时关闭连接 
			
			 
		    } catch (Exception e) { 
				 // TODO Auto-generated catch block 
				 e.printStackTrace();   
				 System.out.println(file.getName()+"读取文件出错."+e.getMessage());  
				 } 
		 return stringBuffer.toString(); 
		 }
	public void writeFile(){
		 File file=new File("C:"+File.separator+"test.txt");
        FileOutputStream fos=null;
        try {
			fos=new FileOutputStream(file);
			for(int i=33;i<127;i++)
			{
				String str=i+": ";
				byte[] byteStr=str.getBytes("UTF-8");
				//
				fos.write(byteStr);
				//整数写入的是ASCII码
				fos.write(i);
				fos.write("\n".getBytes());
				
				//fos.write("\n".getBytes());
			}
			
			fos.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	 public static void copyStream(InputStream is, OutputStream os) {
	        final int BUFFER_SIZE = 1024 * 8;
	        byte[] buffer = new byte[BUFFER_SIZE];
	        try {
	            for (;;) {
	                int count = is.read(buffer, 0, BUFFER_SIZE);
	                if (count < 0)
	                    break;
	                os.write(buffer, 0, count);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            closeStream(is);
	            closeStream(os);
	        }
	    }
	 
	 public static void closeStream(Closeable stream) {
	        try {
	            if (stream != null) {
	                stream.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
		 
}
