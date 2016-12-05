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
import java.util.Collections;
import java.util.List;

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
			case "keys":
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
	
	private static void showHelp(){
		System.out.println("Uso:");
		System.out.println("java -jar verification.jar <comando> [parametros]");
		System.out.println("Comandos:");
		System.out.println("help                         Muestra esta pagina de ayuda");
		System.out.println("cipher <dato> <clave>        Cifra <dato> usando la clave publica <clave> mediante RSA");
		System.out.println("decipher <cifrado> <clave>   Descifra <cifrado> usando la clave privada <clave> mediante RSA");
		System.out.println("keys                         Genera un par de claves RSA");
	}
	
}
