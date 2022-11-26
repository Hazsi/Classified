package club.hazsi.classified.classes;

import club.hazsi.classified.classes.components.ClassInterfaceTable;
import club.hazsi.classified.classes.components.ClassMajorVersion;
import club.hazsi.classified.classes.components.constantpool.ClassConstantPool;
import club.hazsi.classified.classes.components.fieldtable.ClassFieldTable;
import club.hazsi.classified.classes.components.methodtable.ClassMethodTable;

import java.util.Arrays;

// TODO javadoc
public final class ClassAttributes {
    private final byte[] rawBytes;
    private final int minorVersion;
    private final ClassMajorVersion majorVersion;
    private final ClassConstantPool constantPool;
    private final ClassInterfaceTable interfaceTable;
    private final ClassFieldTable fieldTable;
    private final ClassMethodTable methodTable;

    public ClassAttributes(byte[] classBytes) {
        int offset = 0;

        this.rawBytes = classBytes;
        this.minorVersion = Byte.toUnsignedInt(classBytes[5]);
        this.majorVersion = new ClassMajorVersion(classBytes);
        this.constantPool = new ClassConstantPool(classBytes);

        offset += this.constantPool.getLength();

        this.interfaceTable = new ClassInterfaceTable(
                Arrays.copyOfRange(classBytes, 16 + offset, classBytes.length));

        offset += this.interfaceTable.getLength();

        // This should be 18, not 16. I'm not entirely sure why it needs to be 16 just yet, but if issues
        // pop up later with strange field table data, come back to this. TODO investigate
        this.fieldTable = new ClassFieldTable(
                Arrays.copyOfRange(classBytes, 16 + offset, classBytes.length));

        offset += this.fieldTable.getLength();

        this.methodTable = new ClassMethodTable(
                Arrays.copyOfRange(classBytes, 18 + offset, classBytes.length));
    }
}
