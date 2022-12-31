/*    
 *  This class allows for the implementation of the AES encryption algorithm.
 *  Before coding this I attempted to implement the algorithm manually
 *  in C++ for time efficiency however was extremely complicated.
 *  I decided to to use Java since i was comfortable with it and knew
 *  of the encryption libraries.
 * 
 *  In order to use this class, simply create a new AES object
 *  then call the init constructor and then apply the encryption and decryption
 */

// Importing all required known libaries
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.Cipher;
import java.util.Base64;
import javax.crypto.KeyGenerator;

public class AES{
    // Defining some constants
    private SecretKey aeskey;
    private final int KEYSIZE = 128;
    private final int DATALENGTH = 128;
    private Cipher encryptionCipher;


    // initialization function 
    // Allows for key generation and key initialization. 
    // set our algorithm key to the generated key
    public void init() throws Exception {
        KeyGenerator generateKey = KeyGenerator.getInstance("AES");
        generateKey.init(KEYSIZE);
        aeskey = generateKey.generateKey();
    }

    /*
     * @brief convert byte array to string
     * @param a byte array to convert
     * @return string representation
     */
    private String encode(byte[] data) { return Base64.getEncoder().encodeToString(data);}

    /*
     * @brief convert  string to byte array 
     * @param a string to convert
     * @return byte representation
     */
    private byte[] decode(String data) {return Base64.getDecoder().decode(data);}

     /*
     * @brief encrypts given string using AES library 
     * @param a string to encrypt
     * @return encrypted string
     */
    public String encrypt(String encryptMe) throws Exception {
        byte[] dataInBytes = encryptMe.getBytes();
        encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        encryptionCipher.init(Cipher.ENCRYPT_MODE, aeskey);
        byte[] encryptedBytes = encryptionCipher.doFinal(dataInBytes);
        return encode(encryptedBytes);
    }
     /*
     * @brief decrypts the encrypted string using the inverse algorithm
     * @param an encrypted string
     * @return the decrypted plaintext
     */
    public String decrypt(String decryptMe) throws Exception {
        byte[] dataInBytes = decode(decryptMe);
        Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(DATALENGTH, encryptionCipher.getIV());
        decryptionCipher.init(Cipher.DECRYPT_MODE, aeskey, spec);
        byte[] decryptedBytes = decryptionCipher.doFinal(dataInBytes);
        return new String(decryptedBytes);
    }

  
    

}