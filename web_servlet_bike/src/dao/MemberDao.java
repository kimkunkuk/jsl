package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MemberDao {
	//암호화
		public String encryptSHA256(String value) throws NoSuchAlgorithmException{
	        String encryptData ="";
	         
	        MessageDigest sha = MessageDigest.getInstance("SHA-256");
	        sha.update(value.getBytes());
	 
	        byte[] digest = sha.digest();
	        for (int i=0; i<digest.length; i++) {
	            encryptData += Integer.toHexString(digest[i] &0xFF).toUpperCase();
	        }
	         
	        return encryptData;
	    }
	
}
