package EGC.Verification;

public class Interface {

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
		}catch(ArrayIndexOutOfBoundsException e){
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
		System.out.println("privKey                      Genera una clave privada RSA");
		System.out.println("pubKey                       Genera una clave publica RSA");
	}
}
