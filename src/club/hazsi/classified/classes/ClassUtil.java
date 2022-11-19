package club.hazsi.classified.classes;

public class ClassUtil {
    public static boolean isValidClassFile(byte[] classBytes) {
        boolean a = Byte.toUnsignedInt(classBytes[0]) == 0xCA;
        boolean b = Byte.toUnsignedInt(classBytes[1]) == 0xFE;
        boolean c = Byte.toUnsignedInt(classBytes[2]) == 0xBA;
        boolean d = Byte.toUnsignedInt(classBytes[3]) == 0xBE;
        return a && b && c && d;
    }
}