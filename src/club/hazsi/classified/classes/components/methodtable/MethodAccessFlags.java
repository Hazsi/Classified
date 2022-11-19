package club.hazsi.classified.classes.components.methodtable;

import lombok.AllArgsConstructor;
import lombok.Getter;

// TODO javadocs (section 4.5-A)
@Getter
@AllArgsConstructor
public enum MethodAccessFlags {

    PUBLIC(0x0001),
    PRIVATE(0x0002),
    PROTECTED(0x0004),
    STATIC(0x0008),
    FINAL(0x0010),
    SYNCHRONIZED(0x0020),
    BRIDGE(0x0040),
    VARARGS(0x0080),
    NATIVE(0x0100),
    ABSTRACT(0x0400),
    STRICT(0x0800),
    SYNTHETIC(0x1000);

    private final int value;
}
