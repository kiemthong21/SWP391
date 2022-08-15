package dal;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Java program to calculate MD5 hash value
public class MD5 {

    public String getMd5(String input) {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // Driver code
//    public static void main(String args[]) throws NoSuchAlgorithmException {
//        String inputPass = "1";
//        String passToMD5 = getMd5(inputPass);
//        System.out.println(getMd5("1"));
//        String getDatabase = "038412f80565f9ba448809bca3fd62ce";
//
////        if (passToMD5.equals(getDatabase)) {
////            System.out.println("login success"); c4ca4238a0b923820dcc509a6f75849b
////        }
//    }


}
