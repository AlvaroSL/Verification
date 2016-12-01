package EGC.Verification;

import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

//para exportar .jar ejecutable:
//-clic derecho en el proyecto en eclipse
//-Exportar
//-en el selector de export wizard, Java>JAR file
//-Select the export destination: <camino>/verification.jar
//-dando Next hasta la ultima pagina: Select the class of the application entry point:Browse y se marca esta clase.
//-Finish.
public class EntryPoint {

	public static void main(String[] args) {
		try{
			switch(args[0]){
			case "cipher":
				break;
			case "decipher":
				break;
			case "pubKey":
				break;
			case "privKey":
				break;
			default:
			case "help":
				showHelp();
				break;
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
			showHelp();
		}
	}
	
	//Genera un string parseable por stringToPrivKey y stringToPubKey
	private static String privKeyToString(Key key){
		byte[] bytes = key.getEncoded();
		String res = "";
		
		for(byte b : bytes){
			res += (char) (b & 0xFF);
			//esto toma cada byte del array, lo convierte a char y lo añade al final de la string
			//fuente: http://stackoverflow.com/questions/17912640/byte-and-char-conversion-in-java
		}
		
		return res;
	}
	
	private static PrivateKey stringToPrivKey(String in) throws NoSuchAlgorithmException, InvalidKeySpecException{
		KeyFactory kf = KeyFactory.getInstance("RSA");
		byte[] prvBytes = in.getBytes();
		
		PrivateKey res = kf.generatePrivate(new PKCS8EncodedKeySpec(prvBytes));
		//fuente: http://stackoverflow.com/questions/19818550/java-retrieve-the-actual-value-of-the-public-key-from-the-keypair-object
		
		return res;
	}
	
	private static PublicKey stringToPubKey(String in) throws InvalidKeySpecException, NoSuchAlgorithmException{
		
		KeyFactory kf = KeyFactory.getInstance("RSA");
		byte[] pubBytes = in.getBytes();
		
		PublicKey res = kf.generatePublic(new X509EncodedKeySpec(pubBytes));
		//fuente: http://stackoverflow.com/questions/19818550/java-retrieve-the-actual-value-of-the-public-key-from-the-keypair-object
		
		return res;
	}
	
	private static void showHelp(){
		System.out.println("Uso:");
		System.out.println("java -jar verification.jar <comando> [parametros]");
		System.out.println("Comandos:");
		System.out.println("help                         Muestra esta pagina de ayuda");
		System.out.println("cipher <dato> <clave>        Cifra <dato> usando la clave publica <clave> mediante RSA");
		System.out.println("decipher <cifrado> <clave>   Descifra <cifrado> usando la clave privada <clave> mediante RSA");
		System.out.println("privKey                      Genera una clave privada RSA");
		System.out.println("pubKey                       Genera una clave publica RSA");
	}
}
