import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

public class CombinedEncryption {
    private static final BigInteger ONE = BigInteger.ONE;
    private static final Random random = new Random();

    public static void main(String[] args) {
        byte[] desKey = "mySecret".getBytes();
        DESX desx = new DESX(desKey);

        BigInteger p = BigInteger.probablePrime(64, random);
        BigInteger g = new BigInteger(64, random).mod(p);
        BigInteger x = new BigInteger(64, random).mod(p.subtract(ONE));
        BigInteger y = g.modPow(x, p);

        String message = "abc";
        byte[] encryptedMessage = encrypt(message, p, g, y, desx);
        System.out.println("Encrypted message: " + Arrays.toString(encryptedMessage));

        String decryptedMessage = decrypt(encryptedMessage, p, x, desx);
        System.out.println("Decrypted message: " + decryptedMessage);
    }

    public static byte[] encrypt(String message, BigInteger p, BigInteger g, BigInteger y, DESX desx) {
        BigInteger[] elGamalEncrypted = ElGamal.encrypt(message, p, g, y);
        byte[] elGamalBytes = Arrays.stream(elGamalEncrypted)
                .map(BigInteger::toByteArray)
                .reduce(new byte[0], CombinedEncryption::concatenateArrays);

        return desx.encrypt(elGamalBytes);
    }

    public static String decrypt(byte[] cipher, BigInteger p, BigInteger x, DESX desx) {
        byte[] decryptedBytes = desx.decrypt(cipher);

        BigInteger[] elGamalDecrypted = new BigInteger[2];
        elGamalDecrypted[0] = new BigInteger(Arrays.copyOfRange(decryptedBytes, 0, decryptedBytes.length / 2));
        elGamalDecrypted[1] = new BigInteger(Arrays.copyOfRange(decryptedBytes, decryptedBytes.length / 2, decryptedBytes.length));

        return ElGamal.decrypt(elGamalDecrypted, p, x);
    }

    public static byte[] concatenateArrays(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }
}
