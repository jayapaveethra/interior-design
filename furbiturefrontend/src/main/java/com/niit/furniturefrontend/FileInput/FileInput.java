package com.niit.furniturefrontend.FileInput;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.web.multipart.MultipartFile;

public class FileInput
{


public static void upload(String path,MultipartFile file,String fileName)
 {
	
if(!file.isEmpty())
 {
		
InputStream inputstream=null;
		
OutputStream outputstream=null;
		
if(file.getSize()>0) 
{
			
try 
{

inputstream=file.getInputStream();
	
outputstream=new FileOutputStream(path+fileName);
						
System.out.println("5");
						
int readBytes=0;
						
System.out.println("5");
						
byte[] buffer =new byte[1024];
						
System.out.println("5");
						
while((readBytes=inputstream.read(buffer,0,1024))!= -1)
{
							
outputstream.write(buffer,0,readBytes);
						
}
				
}
			
catch(IOException e) 
{
				
e.printStackTrace();
			
}
			
finally 
{
				
try 
{
					
outputstream.close();
					
inputstream.close();
				
}
				
catch(IOException e)
 {
				
  e.printStackTrace();
				
}
			
}
		
}
	
}

}

}
