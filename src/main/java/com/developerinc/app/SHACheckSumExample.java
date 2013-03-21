package com.developerinc.app;

import java.io.FileInputStream;
import java.security.MessageDigest;
import org.apache.commons.codec.*;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

 
public class SHACheckSumExample 
{
    public static void main(String[] args)throws Exception
    {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        MessageDigest cd = MessageDigest.getInstance("SHA-1");
        
        FileInputStream fis = new FileInputStream("f:/resources/hey");
 
        byte[] dataBytes = new byte[1024];
        
        
        
 
        int nread = 0; 
        int cnt = 1;
        while ((nread = fis.read(dataBytes)) != -1) {
          md.update(dataBytes, 0, nread);
        };
        byte[] mdbytes = md.digest();
        cd.digest(mdbytes);
        
        for(byte bytes : mdbytes){
        	System.out.println(cnt++ + " - " + (int) bytes);
        }
        
        //System.out.println(Hex.encodeHexString(mdbytes));
        //String password = new String(Hex.encodeHex(cript.digest()),
        //        CharSet.forName("UTF-8"));
        
        if(Hex.encodeHexString(mdbytes).equals("b4798f1dedcb864e01f4e8ab276394d40a74b42e")){
        	System.out.println("This is equal");
        }
        if(MessageDigest.isEqual(md.digest(),cd.digest())) System.out.println("equal");
        //convert the byte to hex format method 1
//        StringBuffer sb = new StringBuffer();
//        for (int i = 0; i < mdbytes.length; i++) {
//          sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
//        }
// 
//        System.out.println("Hex format : " + sb.toString());
 
       //convert the byte to hex format method 2
//        StringBuffer hexString = new StringBuffer();
//    	for (int i=0;i<mdbytes.length;i++) {
//    	  hexString.append(Integer.toHexString(0xFF & mdbytes[i]));
//    	}
        //String decode = Hex.decodeHex("b4798f1dedcb864e01f4e8ab276394d40a74b42e".getBytes());
    	System.out.println("Hex format : " + Hex.encodeHexString(mdbytes));
    	
    }
}
