package TinyTestJ;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
 public class Image {
 	int width;
	int height;
	public byte[] data;//class byte with each data 8 bits--one byte
	
	public Image(int width, int height) {
		this.width=width;
		this.height=height;
		data=new byte[width*height*3];//data is the byte type data, one data one byte.
	}
 	public void set(int x, int y, int value) {
		
		//change the input RGB pixel value to string
		String str=Integer.toHexString(value);
		//get every hexadecimal value with one byte in the string 
		String str0=str.substring(0, 2);
		String str1=str.substring(2, 4);
		String str2=str.substring(4, 6);
		//every time the axial x change, there is a increase index of 300
		data[300*y+x*3+0]=(byte)Integer.parseInt(str0, 16);
		data[300*y+x*3+1]=(byte)Integer.parseInt(str1, 16);
		data[300*y+x*3+2]=(byte)Integer.parseInt(str2, 16);
 	}
		
	public void write(String filename) throws IOException { 	
		
		int maxval=255;				    
		java.io.ByteArrayOutputStream output=new java.io.ByteArrayOutputStream();
		    byte[] fileHeader = ("P6\t" + width + " " + height + "\t" + maxval + "\t").getBytes();	
 			output.write(fileHeader);
			for (int i=0; i<height;i++) {	
				for (int j=0;j<width;j++) {			
					output.write(data);
				}			
			}	
			byte[] test_file= output.toByteArray( );
			//To write data with the byte array format into the ppm file
			java.nio.file.Files.write(java.nio.file.Paths.get(filename),test_file);
			output.close();
		}
 	public void writeAddTest(String addTest) throws IOException {
		
		int maxval=255;	
		//use the byteArrayOutputStream for writing the data in byte array format 
		//becuase normal writer can only write in string and int 
		java.io.ByteArrayOutputStream output=new java.io.ByteArrayOutputStream();
		//header format through the link:"http://netpbm.sourceforge.net/doc/ppm.html"
		    byte[] fileHeader = ("P6\t" + width + " " + height + "\t" + maxval + "\t").getBytes();	
			output.write(fileHeader);
			//code for pixels that form a exclamation mark
			for (int i=0; i<height;i++) {	
				for (int j=0;j<width;j++) {			
					if(i<20) {
						byte a=(byte)0x55;
						byte b=(byte)0x55;
						byte c=(byte)0x00;
						output.write(a);
						output.write(b);
						output.write(c);
					}
					else if(i>80){
						byte a=(byte)0x55;
						byte b=(byte)0x55;
						byte c=(byte)0x00;
						output.write(a);
						output.write(b);
						output.write(c);
					}					
					else if((i>=30&&i<=60)&&(j>=48&&j<=51)){						
							byte a=(byte)0x00;
							byte b=(byte)0x00;
							byte c=(byte)0x00;
							output.write(a);
							output.write(b);
							output.write(c);						
							}
						else if((i>=70&&i<=73)&&(j>=48&&j<=51)){
								byte a=(byte)0x00;
								byte b=(byte)0x00;
								byte c=(byte)0x00;
								output.write(a);
								output.write(b);
								output.write(c);					
						}	
						else {
							byte a=(byte)0xff;
							byte b=(byte)0xff;
							byte c=(byte)0xff;
							output.write(a);
							output.write(b);
							output.write(c);
						}
				}			
			}	
			byte[] test_file= output.toByteArray( );
			//To write data with the byte array format into the ppm file
			java.nio.file.Files.write(java.nio.file.Paths.get(addTest),test_file);
			output.close();
		}	
	}
