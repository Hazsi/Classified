package club.hazsi.classified.classes.loader;

/**
 * A simple utility class to load a class -- stored as a byte array -- into the current JVM
 *
 * @since 1.0
 * @author Hazsi
 */
public class ByteClassLoader extends ClassLoader {

    private static final ByteClassLoader INSTANCE;
    static {
        INSTANCE = new ByteClassLoader();
    }

    /**
     * Loads a class -- as defined by an array of the class bytes -- into the current JVM using the
     * {@code ByteClassLoader} instance.
     *
     * @param clazz The class to load, as defined by its class bytes
     * @throws ClassFormatError If the bytes provided do not comprise a valid class
     * @throws SecurityException If the class was added to a package containing classes with different certificates,
     * or the class bytes make up a class with a {@code java.} package.
     */
    public static void load(byte[] clazz) throws ClassFormatError, SecurityException {
        INSTANCE.defineClass(null, clazz, 0, clazz.length);
    }
}