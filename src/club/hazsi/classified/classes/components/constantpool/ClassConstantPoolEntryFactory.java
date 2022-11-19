package club.hazsi.classified.classes.components.constantpool;

import club.hazsi.classified.classes.components.constantpool.entry.*;

import java.util.Objects;

// TODO write this javadoc
public class ClassConstantPoolEntryFactory {

    // TODO write this javadoc
    public static ClassConstantPoolEntry<?> make(byte[] rawData) {
        final int tag = Byte.toUnsignedInt(rawData[0]);
        final ClassConstantPoolEntryType type = ClassConstantPoolEntryType.getByValue(tag);
        ClassConstantPoolEntry<?> returnObject;

        // TODO there are a lot of missing cases here!
        switch (Objects.requireNonNull(type)) {
            case UTF8 -> returnObject = new UTF8PoolEntry(rawData);
            case INTEGER -> returnObject = new IntegerPoolEntry(rawData);
            case LONG -> returnObject = new LongPoolEntry(rawData);
            case CLASS -> returnObject = new ClassPoolEntry(rawData);
            case STRING -> returnObject = new StringPoolEntry(rawData);
            case FIELD_REF -> returnObject = new FieldRefPoolEntry(rawData);
            case METHOD_REF -> returnObject = new MethodRefPoolEntry(rawData);
            case NAME_AND_TYPE -> returnObject = new NameAndTypePoolEntry(rawData);
            case METHOD_HANDLE -> returnObject = new MethodHandlePoolEntry(rawData);
            case METHOD_TYPE -> returnObject = new MethodTypePoolEntry(rawData);
            case INVOKE_DYNAMIC -> returnObject = new InvokeDynamicPoolEntry(rawData);
            default -> returnObject = new ClassConstantPoolEntry<>(rawData);
        }

        return returnObject;
    }
}
