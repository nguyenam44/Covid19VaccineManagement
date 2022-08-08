/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nampr
 */
public class Encryption {

    private static final String injecFileName = "injection.dat";
    private static final String checksumFileName = "checksum.dat";

    /**
     *
     * @param fileName
     * @return mảng byte mã MD5
     * @throws Exception
     */
    public static byte[] generateMD5(String fileName) throws Exception {
        return hashFile(fileName, "MD5");
    }

    /**
     *
     * @param fileName
     * @return mảng byte mã SH256
     * @throws Exception
     */
    public static byte[] generateSH256(String fileName) throws Exception {
        return hashFile(fileName, "SH256");
    }

    /**
     *
     * @param fileName
     * @param algorithm
     * @return mảng byte từ việc mã hoá file
     * @throws Exception
     */
    private static byte[] hashFile(String fileName, String algorithm)
            throws Exception {
        try (
                 FileInputStream inputStream = new FileInputStream(fileName)) {
            MessageDigest digest = MessageDigest.getInstance(algorithm);

            byte[] bytesBuffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(bytesBuffer)) != -1) {
                digest.update(bytesBuffer, 0, bytesRead);
            }
            inputStream.close();
            byte[] hashedBytes = digest.digest();

            return hashedBytes;
        } catch (NoSuchAlgorithmException | IOException ex) {
            throw new Exception(
                    "Could not generate hash from file", ex);
        }
    }

    /**
     * ghi mã checksum vào checksum.dat
     *
     * @param filepath
     * @param serObj
     */
    private static void writeEncryptionToFile(String filepath, byte[] serObj) {
        try {
            try ( FileOutputStream fileOut = new FileOutputStream(filepath)) {
                try ( ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
                    objectOut.write(serObj);
                    objectOut.close();
                }
//                System.out.println("The new encryption MD5 was succesfully written to a file");

            }
        } catch (IOException ex) {
        }
    }

    /**
     * ghi mã MD5 vào file checksum.dat
     *
     */
    public static void writeEncryptionToFile(){
        try {
            Encryption.writeEncryptionToFile(checksumFileName, Encryption.generateMD5(injecFileName));
        } catch (Exception ex) {
            Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param filepath
     * @return mảng byte từ việc đọc file checksum.dat
     * @throws ClassNotFoundException
     */
    private static byte[] readEncryptionFromFile(String filepath) throws ClassNotFoundException {
        try {
            byte[] arr;
            try ( FileInputStream fileIn = new FileInputStream(filepath);  ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
                arr = objectIn.readAllBytes();
            }
            return arr;
        } catch (IOException ex) {
        }
        return null;
    }

    /**
     *
     * @return true nếu mã checksum trùng với mã MD5 từ việc mã hoá file
     */
    public static boolean checkSum() {
        try {
            byte[] checkSum;
            StringBuffer cs = new StringBuffer();
            checkSum = Encryption.readEncryptionFromFile(checksumFileName);
            for (int i = 0; i < checkSum.length; ++i) {
                cs.append(Integer.toHexString(checkSum[i] & 0xFF | 0x100).substring(1, 3));
            }
            byte[] hashFile;
            StringBuffer hf = new StringBuffer();
            hashFile = Encryption.generateMD5(injecFileName);
            for (int i = 0; i < hashFile.length; ++i) {
                hf.append(Integer.toHexString(hashFile[i] & 0xFF | 0x100).substring(1, 3));
            }
            return cs.compareTo(hf) == 0;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return false;
    }
}
