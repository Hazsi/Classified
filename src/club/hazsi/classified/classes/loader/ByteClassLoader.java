package club.hazsi.classified.classes.loader;

// todo javadocs
public class ByteClassLoader extends ClassLoader {

    private static final ByteClassLoader INSTANCE;
    static {
        INSTANCE = new ByteClassLoader();
    }

    public static void load(byte[] clazz) throws ClassFormatError {
        INSTANCE.defineClass(null, clazz, 0, clazz.length);
    }
}