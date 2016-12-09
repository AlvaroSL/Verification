package EGC.Verification;

import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KeyManipulator {

	//Genera un string parseable por stringToPrivKey y stringToPubKey
	public static String keyToString(Key key){
		byte[] bytes = key.getEncoded();
		String res = Arrays.toString(bytes);
		
		return res;
	}
	
	public static PrivateKey stringToPrivKey(String in) throws NoSuchAlgorithmException, InvalidKeySpecException{
		KeyFactory kf = KeyFactory.getInstance("RSA");
		
		byte[] prvBytes = toByteArray(in);
				
		PrivateKey res = kf.generatePrivate(new PKCS8EncodedKeySpec(prvBytes));
		//fuente: http://stackoverflow.com/questions/19818550/java-retrieve-the-actual-value-of-the-public-key-from-the-keypair-object
		
		return res;
	}
	
	public static PublicKey stringToPubKey(String in) throws InvalidKeySpecException, NoSuchAlgorithmException{
		KeyFactory kf = KeyFactory.getInstance("RSA");
		
		byte[] pubBytes = toByteArray(in);
		
		PublicKey res = kf.generatePublic(new X509EncodedKeySpec(pubBytes));
		//fuente: http://stackoverflow.com/questions/19818550/java-retrieve-the-actual-value-of-the-public-key-from-the-keypair-object
		
		return res;
	}
	
	//fuente
	//http://stackoverflow.com/questions/6430841/java-byte-to-byte
	public static byte[] toByteArray(String in)
	{
		
		String[] piezas = in.split(",");
		
		List<Byte> depurado = new ArrayList<Byte>();
		for(String s : piezas){
			if(s.charAt(0) == '['){
				s = s.substring(1, s.length());
			}
			else if(s.charAt(s.length()-1) == ']') {
				s = s.substring(0, s.length()-1);
			}
			
			if(!s.isEmpty()){
				depurado.add(Byte.parseByte(s.trim()));
			}
		}
		
	    byte[] bytes = new byte[depurado.size()];

	    for(int i = 0; i < depurado.size(); i++){
	    	bytes[i] = depurado.get(i).byteValue();
	    }

	    return bytes;
	}

}
