import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import java.nio.charset.StandardCharsets;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import java.util.Base64;
import java.util.Scanner;

public class AES {
    /* Private variable declaration */
    private static final String SECRET_KEY = "123456789";
    private static final String SALT_VALUE = "abcdefg";

    /* Encryption Method */
    public static String encrypt(String strToEncrypt) {
        try {
            /* Declare a byte array. */
            final byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            final IvParameterSpec ivspec = new IvParameterSpec(iv);

            /* Create factory for secret keys. */
            final SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            /* PBEKeySpec class implements KeySpec interface. */
            final KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT_VALUE.getBytes(), 65536, 256);
            final SecretKey tmp = factory.generateSecret(spec);
            final SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);

            /* Returns encrypted value. */
            return Base64.getEncoder()
                    .encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            System.out.println("Error occurred during encryption: " + e);
        }
        return null;
    }

    /* Decryption Method */
    public static String decrypt(String strToDecrypt) {
        try {
            /* Declare a byte array. */
            final byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            final IvParameterSpec ivspec = new IvParameterSpec(iv);

            /* Create factory for secret keys. */
            final SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            /* PBEKeySpec class implements KeySpec interface. */
            final KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT_VALUE.getBytes(), 65536, 256);
            final SecretKey tmp = factory.generateSecret(spec);
            final SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
            final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);

            /* Returns decrypted value. */
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            System.out.println("Error occurred during decryption: " + e);
        }
        return null;
    }

    /* Driver Code */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        System.out.print("Enter message to encrypt: ");

        /* Message to be encrypted. */
        final String inputMessage = scanner.nextLine();

        /* Call to encrypt() method and store result of encryption. */
        final String cipherText = encrypt(inputMessage);

        /* Call the decrypt() method and store result of decryption. */
        final String outputMessage = decrypt(cipherText);

        /* Display the original message, encrypted message and decrypted message on the console. */
        System.out.println("Original value: " + inputMessage);
        System.out.println("Encrypted value: " + cipherText);
        System.out.println("Decrypted value: " + outputMessage);

        scanner.close();
    }
}