package club.hazsi.classified.util;

/**
 * A collection of simple bitwise logic utilities to read raw unsigned bytes into friendly Java formats.
 *
 * @author Hazsi
 * @since 1.0
 */
public final class ByteUtil {

    /**
     * Reads a single unsigned byte as a 4-bit integer
     * @param bytes The array of bytes to read from
     * @param index The index of the byte to read at
     * @return The 4-bit integer value of the unsigned byte
     * @since 1.0
     */
    public static int readByte(byte[] bytes, int index) {
        return ((int) bytes[index]) & 0xFF;
    }

    /**
     * Reads two unsigned bytes as an 8-bit integer
     * @param bytes The array of bytes to read from
     * @param startingIndex The index of the byte to start reading at
     * @return The 8-bit integer value of the unsigned bytes
     * @since 1.0
     */
    public static int readWORD(byte[] bytes, int startingIndex) {
        return (readByte(bytes, startingIndex) << 8) |
                readByte(bytes, startingIndex + 1);
    }

    /**
     * Reads four unsigned bytes as an 16-bit integer
     * @param bytes The array of bytes to read from
     * @param startingIndex The index of the byte to start reading at
     * @return The 16-bit integer value of the unsigned bytes
     * @since 1.0
     */
    public static int readDWORD(byte[] bytes, int startingIndex) {
        return (readWORD(bytes, startingIndex) << 16) |
                readWORD(bytes, startingIndex + 2);
    }

    /**
     * Reads eight unsigned bytes as an 32-bit integer
     * @param bytes The array of bytes to read from
     * @param startingIndex The index of the byte to start reading at
     * @return The 32-bit integer value of the unsigned bytes
     * @since 1.0
     */
    public static long readQWORD(byte[] bytes, int startingIndex) {
        return ((long) readDWORD(bytes, startingIndex) << 32) |
                (long) readDWORD(bytes, startingIndex + 4);
    }
}
