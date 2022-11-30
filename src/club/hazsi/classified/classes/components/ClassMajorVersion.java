package club.hazsi.classified.classes.components;

/**
 * A representation of a classes major Java version, with the ability to generate a user-friendly String
 * representation of the Java version, correct to Oracle specifications.
 *
 * @author Hazsi
 * @since 1.0
 */
public class ClassMajorVersion {

    private final int version;

    /**
     * Using this constructor allows for the creation of a ClassMajorVersion instance directly from the raw bytes
     * of a class, rather than needing to determine the version elsewhere
     * @param classBytes A classes raw bytes
     */
    public ClassMajorVersion(byte[] classBytes) {
        this.version = classBytes[7];   // Per the JVM spec, the class major version always lays at the 7th byte
    }

    /**
     * Returns a user-friendly String representation of the Java version the class was compiled for. Java versions
     * prior to Java 5 are denoted as "JDK 1.X" and later versions are referred to as "Java SE X", as per Oracle's
     * standards
     *
     * @return A user-friendly representation of the classes Java major version number
     */
    public String getName() {
        final int versionOffset = 44;   // Class major versions are offset by a value of 44 from their Java versions
                                        // (see https://docs.oracle.com/javase/specs/jvms/se17/html/jvms-4.html#jvms-4.1-200-B.2)

        int javaVersion = this.version - versionOffset;
        return javaVersion < 5 ? ("JDK 1." + javaVersion) : ("Java SE " + javaVersion);
    }

    //todo javadoc
    public int getVersion() {
        return this.version;
    }
}