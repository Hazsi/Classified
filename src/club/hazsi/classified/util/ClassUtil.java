package club.hazsi.classified.util;

public class ClassUtil {

    /**
     * Checks if a given file (in byte array form) is a Java class file by checking for the presence of the
     * {@code 0xCAFEBABE} magic bytes. This does <b>not</b> perform more in depth checks of the validity of other
     * parts of the class file, and should only be used to check if an unknown file is a class file.
     *
     * @param classBytes The file to check, in a byte array
     * @return Whether the provided byte array is a class file, without checking in depth for validity
     */
    public static boolean isClassFile(byte[] classBytes) {
        boolean CA = Byte.toUnsignedInt(classBytes[0]) == 0xCA;
        boolean FE = Byte.toUnsignedInt(classBytes[1]) == 0xFE;
        boolean BA = Byte.toUnsignedInt(classBytes[2]) == 0xBA;
        boolean BE = Byte.toUnsignedInt(classBytes[3]) == 0xBE;
        return CA && FE && BA && BE;
    }
}