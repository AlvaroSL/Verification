package EGC.Verification;

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
		System.out.println("help                         Muestra esta pagina de ayuda.");
		System.out.println("cipher <dato> <clave>        Cifra <dato> usando la clave publica RSA <clave>.");
		System.out.println("decipher <cifrado> <clave>   Descifra <cifrado> usando la clave privada RSA <clave>.");
		System.out.println("keys                         Genera un par de claves RSA.\n");
		System.out.println("Sobre las claves:");
		System.out.println("->Se entregan como representación textual de array de números.");
		System.out.println("->Ejemplo simplificado: \"[12, -56, 34, 0, 1, 1, -1]\".");
		System.out.println("->El comando keys devuelve primero la clave publica y despues la privada, ambas con el mismo formato.");
		System.out.println("->Es importante extraer e introducir las claves copiando todo el texto como en el ejemplo anterior, sin las comillas.");
	}
	
}
