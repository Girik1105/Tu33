package Encryption;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * EncryptionUtils class provides utility methods for encryption operations.
 */
public class EncryptionUtils {

    /**
     * Converts a byte array to a char array.
     *
     * @param bytes The byte array.
     * @return The char array.
     */
    public static char[] toCharArray(byte[] bytes) {
        CharBuffer charBuffer = Charset.defaultCharset().decode(ByteBuffer.wrap(bytes));
        return Arrays.copyOf(charBuffer.array(), charBuffer.limit());
    }

    /**
     * Converts a char array to a byte array.
     *
     * @param chars The char array.
     * @return The byte array.
     */
    public static byte[] toByteArray(char[] chars) {
        ByteBuffer byteBuffer = Charset.defaultCharset().encode(CharBuffer.wrap(chars));
        return Arrays.copyOf(byteBuffer.array(), byteBuffer.limit());
    }

    /**
     * Generates an initialization vector from the provided text.
     *
     * @param text The text to derive the IV from.
     * @return The initialization vector.
     */
    public static byte[] getInitializationVector(char[] text) {
        final int IV_SIZE = 16;
        char iv[] = new char[IV_SIZE];

        int textPointer = 0;
        int ivPointer = 0;
        while (ivPointer < IV_SIZE) {
            iv[ivPointer++] = text[textPointer++ % text.length];
        }

        return toByteArray(iv);
    }

    /**
     * Generates a fixed initialization vector for article encryption.
     *
     * @return The initialization vector.
     */
    public static byte[] getArticleInitializationVector() {
        return new byte[] {
                (byte) 0x10, (byte) 0x20, (byte) 0x30, (byte) 0x40,
                (byte) 0x50, (byte) 0x60, (byte) 0x70, (byte) 0x80,
                (byte) 0x90, (byte) 0xA0, (byte) 0xB0, (byte) 0xC0,
                (byte) 0xD0, (byte) 0xE0, (byte) 0xF0, (byte) 0x00
        };
    }

    /**
     * Prints a char array to the console.
     *
     * @param chars The char array.
     */
    public static void printCharArray(char[] chars) {
        for (char c : chars) {
            System.out.print(c);
        }
    }
}
