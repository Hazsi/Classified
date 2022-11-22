package club.hazsi.classified.classes.api;

import club.hazsi.classified.classes.components.ClassAccessFlags;
import club.hazsi.classified.classes.components.fieldtable.FieldAccessFlags;
import club.hazsi.classified.classes.components.methodtable.MethodAccessFlags;

/**
 * Found in enums used to represent class, method and field access/property flags. Together, these flags form
 * bitfields which are found at the top of class files, at the top of {@code method_info} structures, and at the
 * top of {@code field_info} structures, respectively.
 *
 * @see ClassAccessFlags
 * @see MethodAccessFlags
 * @see FieldAccessFlags
 *
 * @author Hazsi
 * @since 11/21/22
 */
public interface AccessPropertyFlags {
}