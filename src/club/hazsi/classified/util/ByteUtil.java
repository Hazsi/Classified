package club.hazsi.classified.util;

// todo javadoc
public final class ByteUtil {
    public static int readByte(byte[] bytes, int index) {
        return ((int) bytes[index]) & 0xFF;
    }

    public static int readWORD(byte[] bytes, int startingIndex) {
        return (readByte(bytes, startingIndex) << 8) |
                readByte(bytes, startingIndex + 1);
    }

    public static int readDWORD(byte[] bytes, int startingIndex) {
        return (readWORD(bytes, startingIndex) << 16) |
                readWORD(bytes, startingIndex + 2);
    }

    public static long readQWORD(byte[] bytes, int startingIndex) {
        return ((long) readDWORD(bytes, startingIndex) << 32) |
                (long) readDWORD(bytes, startingIndex + 4);
    }
}
