package EGC.Verification;

import static org.junit.Assert.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VerificationTests {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	//actualizados en el trabajo de 2016/17
	
	@Test
	public void testGetKeysRsa() {
		System.out.println("==========================================================");
		System.out.println("=================TEST 1 (GENERAR KEYS RSA)================");
		System.out.println("==========================================================");
		System.out.println("");
		
		KeyPair keys = RSAUtils.returnKeysRSA();
		System.out.println("----------------------------KEYS--------------------------");
		System.out.println("-------------Son las keys que hemos generado--------------");
		System.out.println("Key privada: " + keys.getPrivate());
		System.out.println("Key publica: " + keys.getPublic());
		assertNotNull(keys.getPublic());
		System.out.println("");
	}
	
	@Test
	public void testGetKeyDes() {
		System.out.println("==========================================================");
		System.out.println("=================TEST 2 (KEY PRIVADA DES)=================");
		System.out.println("==========================================================");
		System.out.println("");
		
		System.out.println("----------------Es la key privada generada----------------");
		SecretKey key = RSAUtils.returnKeyDes();
		System.out.println("Key privada: " +key);
		assertNotNull(key);
		System.out.println("");
	}

	
	@Test
	public void testCheckVoteRSA() throws NoSuchAlgorithmException, IOException {
		System.out.println("==========================================================");
		System.out.println("===============TEST 3 (COMPROBAR VOTES RSA)===============");
		System.out.println("==========================================================");
		System.out.println("");
		
		KeyPair keys = RSAUtils.returnKeysRSA();
		KeyPair keysAuxiliar = RSAUtils.returnKeysRSA();
		PublicKey pubKey = keys.getPublic();
		byte[] votoCifrado = RSAUtils.encryptRSA(pubKey, "Esto es una prueba");
		boolean comprobacion = RSAUtils.checkVoteRSA(votoCifrado, keys);
		boolean comprobacionAuxiliar = RSAUtils.checkVoteRSA(votoCifrado, keysAuxiliar);
		System.out.println("---------------------------KEYS---------------------------");
		System.out.println("------Son las keys con las que hemos creado el voto-------");
		System.out.println("Key privada: " + keys.getPrivate());
		System.out.println("Key publica: " + keys.getPublic());
		System.out.println("");
		System.out.println("--Keys creadas para la comprobación errónea del checkKey--");
		System.out.println("Key privada: " + keysAuxiliar.getPrivate());
		System.out.println("Key publica: " + keysAuxiliar.getPublic());
		System.out.println("----------------------Resultados--------------------------");
		System.out.println("Comprobación con las keys correctas");
		if(comprobacion)
			System.out.println("Votación correcta");
		else 
			System.out.println("Votación amañada");
		System.out.println("Comprobación con las keys incorrectas");
		if(comprobacionAuxiliar)
			System.out.println("Votación correcta");
		else 
			System.out.println("Votación amañada");
		System.out.println("");
	}

	@Test
	public void testEncryptRSA() throws NoSuchAlgorithmException, IOException {

		System.out.println("==========================================================");
		System.out.println("==================TEST 4 (ENCRIPTAR RSA)==================");
		System.out.println("==========================================================");
		System.out.println("");
		
		KeyPair keys = RSAUtils.returnKeysRSA();
		PublicKey pubKey = keys.getPublic();
		byte[] res = RSAUtils.encryptRSA(pubKey, "Esto es una prueba");
		System.out.println("----------------------------KEYS--------------------------");
		System.out.println("--Son las keys con las que hemos encriptado el voto--");
		System.out.println("Key privada: " + keys.getPrivate());
		System.out.println("Key publica: " + keys.getPublic());
		System.out.println("----------------------Resultados--------------------------");
		System.out.println("Voto encriptado: " + new String(res));
		System.out.println("");
	}

	@Test
	public void testDecryptRSA() throws NoSuchAlgorithmException, IOException, BadPaddingException {
		System.out.println("==========================================================");
		System.out.println("=================TEST 5 (DESENCRIPTAR RSA)================");
		System.out.println("==========================================================");
		System.out.println("");
		
		KeyPair keys = RSAUtils.returnKeysRSA();
		PublicKey pubKey = keys.getPublic();
		PrivateKey privKey = keys.getPrivate();
		String entrada="Esto es una prueba";
		byte[] res = RSAUtils.encryptRSA(pubKey, entrada);
		System.out.println("----------------------------KEYS--------------------------");
		System.out.println("----Son las keys con las que hemos encriptado el voto-----");
		System.out.println("Key privada: " + keys.getPrivate());
		System.out.println("Key publica: " + keys.getPublic());
		System.out.println("----------------------Resultados--------------------------");
		System.out.println("Voto entrada: " + entrada);
		System.out.println("Encriptamos...");
		System.out.println("Voto encriptado :" + new String(res));
		System.out.println("Desencriptamos...");
		String finCorrecto = RSAUtils.decryptRSA(privKey, res);
		System.out.println("Voto desencriptado: " + finCorrecto);
		System.out.println("");
	
		
	}

	@Test
	public void testEncryptDES() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		System.out.println("==========================================================");
		System.out.println("==================TEST 6 (ENCRIPTAR DES)==================");
		System.out.println("==========================================================");
		System.out.println("");
		
		System.out.println("----------------Es la key privada generada----------------");
		SecretKey key = RSAUtils.returnKeyDes();
		String entrada="Esto es una prueba";
		System.out.println("Key privada: " +key);
		assertNotNull(key);
		System.out.println("----------------------Resultados--------------------------");
		System.out.println("Voto de entrada: " + entrada);
		byte[] enc = RSAUtils.encryptDES(key, entrada);
		assertNotNull(enc);
		System.out.println("Voto encriptado: " + new String(enc));
		System.out.println("");
	}

	@Test
	public void testDecryptDES() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		System.out.println("==========================================================");
		System.out.println("=================TEST 7 (DESENCRIPTAR DES)================");
		System.out.println("==========================================================");
		System.out.println("");

		System.out.println("----------------Es la key privada generada----------------");
		SecretKey key = RSAUtils.returnKeyDes();
		System.out.println("Key privada: " +key);
		System.out.println("----------------------Resultados--------------------------");
		String entrada= "Esto es una prueba";
		System.out.println("Voto entrada: " + entrada);
		System.out.println("Encriptamos...");
		byte[] enc = RSAUtils.encryptDES(key,entrada);
		System.out.println("Voto encriptado :" + new String(enc));
		System.out.println("Desencriptamos...");
		String fin = RSAUtils.decryptDES(key, enc);
		assertNotNull(fin);
		System.out.println("Voto desencriptado: " + new String(fin));
		System.out.println("");
	
	}

	@Test
	public void testGetMD5() {
		System.out.println("==========================================================");
		System.out.println("====================TEST 8 (HASHCODE MD5)=================");
		System.out.println("==========================================================");
		System.out.println("");
		
		String entrada="Esto es una prueba";
		byte[] res = RSAUtils.getHashCodeMD5(entrada);
		System.out.println("String entrada: " + entrada);
		System.out.println("Encriptamos...");
		System.out.println("String salida: " + new String(res));
		assertNotNull(new String(res));
		System.out.println("");
	}

	@Test()
	public void testGetSHA1() {
		System.out.println("==========================================================");
		System.out.println("====================TEST 9 (HASHCODE SHA)=================");
		System.out.println("==========================================================");
		System.out.println("");
		
		String entrada = "Esto es una prueba";
		byte[] res = RSAUtils.getHashCodeSHA(entrada);
		System.out.println("String entrada: " + entrada);
		System.out.println("Encriptamos...");
		System.out.println("String salida: " + new String(res));
		assertNotNull(new String(res));
		System.out.println("");
	}
	
	//añadidos en el trabajo de 2016/17
	
	@Test
	public void testPrivKeyStringConversion() throws NoSuchAlgorithmException, InvalidKeySpecException{
		KeyPair keys = RSAUtils.returnKeysRSA();
		PrivateKey k = keys.getPrivate();
		
		String s = EntryPoint.keyToString(k);
		PrivateKey k2 = EntryPoint.stringToPrivKey(s);
		
		assertEquals(k, k2);
	}
	
	@Test
	public void testPubKeyStringConversion() throws InvalidKeySpecException, NoSuchAlgorithmException{
		KeyPair keys = RSAUtils.returnKeysRSA();
		PublicKey k = keys.getPublic();
		
		String s = EntryPoint.keyToString(k);
		PublicKey k2 = EntryPoint.stringToPubKey(s);
		
		assertEquals(k, k2);
	}

}
