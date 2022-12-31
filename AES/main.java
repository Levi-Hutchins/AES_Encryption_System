/*  Author: Levi Hutchins
 *  This program takes use of the AES class encryption.
 *  It allows for the encryption of binary text using AES 128 bit encryption.
 *  Reads binary and cipher text and encrypts and decrypts respectively.
 *  
 */

// Import all required libraries
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
public class main {
    // Making a global AES object
    public static AES aes = new AES();

    /*
     * @brief write ciphertext to a text file 
     * @param a binary string
     * @return None
     */
    public static void writeEncryption(String messageToEncrypt){

        try{
            String encryptedMessage = aes.encrypt(messageToEncrypt); // Use aes object encryption method on the string
            try{
                // Write the encrypted string to a new text file
                FileWriter out = new FileWriter("../output/Encrypted.txt");
                out.write(encryptedMessage);
                out.close();
                System.out.println("Encrypted Successfully");

                // Exception handling
            }catch(IOException e){
                System.out.println("Sorry, an error occurred");
            }
        // Exception handling for the encryption method itself
        }catch(Exception ignoredException){}
    }
     /*
     * @brief write plaintext to a text file 
     * @param None
     * @return None
     */
    public static void writeDecryption(){
        // Open the encrypted file
        File file_to_decrypt = new File("../output/Encrypted.txt");   

        try{
            // Read the content of the encrypted file
            Scanner read = new Scanner(file_to_decrypt);
            while(read.hasNextLine()){
            try{
                // Create new file to write plain text to
                FileWriter out = new FileWriter("../output/Decrypted.txt");
                try{ 
                    // Perform decryptio of the ciphertext
                    String decryptedMessage = aes.decrypt(read.nextLine());
                    out.write(decryptedMessage);
                // Exception for decryption
                }catch(Exception ignoredException){}
                out.close();
            // Exception handling for file
            } catch(IOException e){System.out.println("Sorry, an error occurred");}

        } 
        read.close();
        System.out.println("Decrypted Successfully");
    // Exception handling incase file is not found
    }catch(FileNotFoundException e){
        System.out.println("File not found");
        e.printStackTrace();
    }
}
    /*
     * @brief Execute the program
     * @param None
     * @return None
     */
    public static void main(String[] args){
        // Initialize the AES algorithm, generate keys etc.
        try{aes.init();}catch(Exception ignored){}

        try{
            // Open the file to encrypt and read the line and call encryption method
            // NEED TO FIX - make it search for the lastest txt file
            File file_to_encrypt = new File("../output/new.txt");
            Scanner read = new Scanner(file_to_encrypt);
            while(read.hasNext()){
                writeEncryption(read.nextLine());
            }
            read.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
        }
        // After encryption is complete decrypt the message
        writeDecryption();




    }

    
}
