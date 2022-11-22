package club.hazsi.classified.classes.components.fieldtable;

import club.hazsi.classified.classes.api.AccessPropertyFlags;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * As defined in table 4.5-A of the Java
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html">class file format specifications</a>, this
 * enum represents a set of flags that can be found on a field. Together, these flags make up the {@code access_flags}
 * bitfield at the start of {@code field_info} structures found in class files. The following table is derived from the
 * above documentation.<br><br>
 *
 * <pre>
 | Flag Name      | Interpretation                                         |
 |----------------|--------------------------------------------------------|
 | ACC_PUBLIC     | Declared <i>public</i>; may be accessed from outside package. |
 | ACC_PRIVATE    | Declared <i>private</i>; accessible only within the class.    |
 | ACC_PROTECTED  | Declared <i>protected</i>; may be accessed within subclasses. |
 | ACC_STATIC     | Declared <i>static</i>.                                       |
 | ACC_FINAL      | Declared <i>final</i>; never assigned to post construction.   |
 | ACC_VOLATILE   | Declared <i>volatile</i>; cannot be cached.                   |
 | ACC_TRANSIENT  | Declared <i>transient</i>; ignored by persistent obj mgmt.    |
 | ACC_SYNTHETIC  | Declared <i>synthetic</i>; not present in the source code. *  |
 | ACC_ENUM       | Declared as an element of an enum class.               |
 * </pre><br>
 *
 * * While not mentioned in the official documentation, many modern obfuscators will mark classes, fields, and methods
 * as {@code synthetic}, as some decompilers will ignore them entirely or treat them differently.<br><br>
 *
 * Any flags not listed are reserved by future use and should never appear as set. JVM implementations should disregard
 * unused class access/property flags.
 *
 * @author Hazsi
 * @since 10/21/22
 */
@Getter
@AllArgsConstructor
public enum FieldAccessFlags implements AccessPropertyFlags {

    PUBLIC(0x0001),
    PRIVATE(0x0002),
    PROTECTED(0x0004),
    STATIC(0x0008),
    FINAL(0x0010),
    VOLATILE(0x0040),
    TRANSIENT(0x0080),
    SYNTHETIC(0x1000),
    ENUM(0x4000);

    private final int value;
}
