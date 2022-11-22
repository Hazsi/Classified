package club.hazsi.classified.classes.components;

import club.hazsi.classified.classes.api.AccessPropertyFlags;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * As defined in table 4.1-B of the Java
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html">class file format specifications</a>, this
 * enum represents a set of flags that can be found on a class. Together, these flags make up the {@code access_flags}
 * bitfield at the start of class files. The following table is derived from the above documentation.<br><br>
 *
 * <pre>
 | Flag Name      | Interpretation                                         |
 |----------------|--------------------------------------------------------|
 | ACC_PUBLIC     | Declared <i>public</i>; may be accessed from outside package. |
 | ACC_FINAL      | Declared <i>final</i>; no subclasses allowed.                 |
 | ACC_SUPER      | Treat superclass methods specially when invoked.*      |
 | ACC_INTERFACE  | Is an interface, not a class.                          |
 | ACC_ABSTRACT   | Declared <i>abstract</i>; must not be instantiated.           |
 | ACC_SYNTHETIC  | Declared <i>synthetic</i>; not present in the source code.**  |
 | ACC_ANNOTATION | Declared as an annotation interface.                   |
 | ACC_ENUM       | Declared as an enum class.                             |
 | ACC_MODULE     | Is a module, not a class or interface.***              |
 * </pre><br>
 *
 * * When this flag is set, the {@code invokespecial} instruction uses an alternate semantic. This flag should always
 * be set by compilers, but may be missing in older classes. In Java versions above Java SE 7, the JVM always considers
 * this flag to be present. This flag still exists for backwards compatibility with extremely old classes.<br><br>
 *
 * ** While not mentioned in the official documentation, many modern obfuscators will mark classes, fields, and methods
 * as {@code synthetic}, as some decompilers will ignore them entirely or treat them differently.<br><br>
 *
 * *** Only present in classes compiled for Java SE 9 and above.<br><br>
 *
 * Any flags not listed are reserved by future use and should never appear as set. JVM implementations should disregard
 * unused class access/property flags.
 *
 * @author Hazsi
 * @since 10/21/22
 */
@Getter
@AllArgsConstructor
public enum ClassAccessFlags implements AccessPropertyFlags {

    PUBLIC(0x0001),
    FINAL(0x0010),
    SUPER(0x0020),
    INTERFACE(0x0200),
    ABSTRACT(0x0400),
    SYNTHETIC(0x1000),
    ANNOTATION(0x2000),
    ENUM(0x4000),
    MODULE(0x8000);

    private final int value;
}