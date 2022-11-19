package club.hazsi.classified.classes.components.fieldtable;

import lombok.AllArgsConstructor;
import lombok.Getter;

// TODO javadocs (section 4.5-A)
@Getter
@AllArgsConstructor
public enum FieldAccessFlags {

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
