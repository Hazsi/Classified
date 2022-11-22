package club.hazsi.classified.classes.components.constantpool;

import club.hazsi.classified.classes.components.ClassMajorVersion;
import lombok.AllArgsConstructor;
import lombok.Getter;

// TODO write javadoc
@Getter
@AllArgsConstructor
public enum ClassConstantPoolEntryType {
    UTF8(1, 0),
    INTEGER(3, 4),
    FLOAT(4, 4),
    LONG(5, 8),
    DOUBLE(6, 8),
    CLASS(7, 2),
    STRING(8, 2),
    FIELD_REF(9, 4),
    METHOD_REF(10, 4),
    INTERFACE_METHOD_REF(11, 4),
    NAME_AND_TYPE(12, 4),
    METHOD_HANDLE(15, 3, 51),   // Requires Java 7 or later
    METHOD_TYPE(16, 2, 51),     // Requires Java 7 or later
    DYNAMIC(17, 4, 55),         // Requires Java 11 or later
    INVOKE_DYNAMIC(18, 4, 51),  // Requires Java 7 or later
    MODULE(19, 2, 53),          // Requires Java 9 or later
    PACKAGE(20, 2, 53);         // Requires Java 9 or later

    private final int value;
    private final int dataSize;
    private final int minimumMajorClassVersion;

    /**
     * While the majority of constant pool types have existed since JDK 1.0, some have not, and we need to store the
     * minimum major class version that supports the specified type. This constructor can be used when the type
     * has always existed and uses a major class version of 45 (JDK 1.0, see {@link ClassMajorVersion} in place of
     * a custom version
     *
     * @param value The tag byte used to identify the tag/type
     * @param dataSize The size of the data/info associated with the tag/type. <b>A value of 0 indicates that the
     *                 size is not constant and can vary</b> (as of Java 19, only the UTF-8 tag is variable).
     *                 The same 2 byte minimum as specified in 4.4.0 of the Java class file format specifications
     *                 still applies.
     */
    ClassConstantPoolEntryType(int value, int dataSize) {
        this(value, dataSize, 45);
    }

    /**
     * Returns a ClassConstantPoolType value based on the tag byte (raw hexadecimal value used to represent the tag or
     * data type). This method is used when parsing constant pool entries, as the tag/type is stored in this format
     * @param value The tag byte used to identify the tag/type
     * @return The ClassConstantPoolType value associated with {@code value}, or null if none is found
     */
    public static ClassConstantPoolEntryType getByValue(int value) {
        for (ClassConstantPoolEntryType type : values()) {
            if (type.getValue() == value) return type;
        }
        return null;
    }
}
