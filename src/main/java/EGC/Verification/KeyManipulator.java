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
	private static byte[] toByteArray(String in)
	{
		
		String[] piezas = in.split("[, ]");
		
		List<Byte> depurado = new ArrayList<Byte>();
		for(String s : piezas){
			if(!s.isEmpty()){
				depurado.add(Byte.parseByte(s.trim()));
			}
		}
		Byte[] b = (Byte[]) depurado.toArray();
		
	    byte[] bytes = new byte[b.length];

	    for(int i = 0; i < b.length; i++) {
	        bytes[i] = b[i];
	    }

	    return bytes;
	}

}
