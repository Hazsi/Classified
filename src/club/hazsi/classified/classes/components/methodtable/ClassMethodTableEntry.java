package club.hazsi.classified.classes.components.methodtable;

import club.hazsi.classified.classes.components.AttributeInfo;
import club.hazsi.classified.classes.components.constantpool.ClassConstantPoolEntry;
import club.hazsi.classified.classes.components.fieldtable.ClassFieldTable;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a single entry in the classes method table. As per section 4.6 of the Java
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html">class file format specifications</a>, the
 * method table contains a table of entries. Each entry is expected to contain access and property information in an
 * 16 bit bitfield (see table 4.6-A of the aforementioned documentation), 16 bit constant pool indexes for the fields
 * name and descriptor, a 16 bit count of the additional attributes of the field, and a list of these attributes.<br><br>
 *
 * Taken from the aforementioned section of the Java class file format specifications, a C-like pseudocode
 * representation of a constant pool entry can be written as:
 *
 * <pre>{@code
 * method_info {
 *     u2               access_flags;
 *     u2               name_index;
 *     u2               descriptor_index;
 *     u2               attributes_count;
 *     attribute_info   attributes[attribute_count];
 * }
 * }</pre>
 *
 * Unlike {@link ClassConstantPoolEntry}, the size of the entry and the format of the data stored within it is always
 * constant and conforms to the same standard as above. Attribute info is defined in section 4.7 of the Java class file
 * format specifications, and is implemented by Classified in {@link AttributeInfo}. However, due to the volatile size
 * of {@link AttributeInfo}, this entry does not have a set length and must be calculated in construction. These
 * properties are shared with {@link ClassFieldTable}.
 *
 * @author Hazsi
 * @since 11/19/22
 */
@Getter
public class ClassMethodTableEntry {
    private final int length;
    private final int accessFlags; // TODO move this to an ArrayList of MethodAccessFlags instead of just the raw bitfield
    private final int nameIndex;
    private final int descriptorIndex;
    private final ArrayList<AttributeInfo> attributeInfos = new ArrayList<>();

    // TODO write this javadoc
    public ClassMethodTableEntry(byte[] classBytes) {

        final int attributeInfoCount = classBytes[7];
        int offset = 8;

        for (int currentAttributeIndex = 0; currentAttributeIndex < attributeInfoCount; currentAttributeIndex++) {
            AttributeInfo currentAttributeInfo = new AttributeInfo(Arrays.copyOfRange(classBytes, offset, classBytes.length));

            this.attributeInfos.add(currentAttributeInfo);
            offset += currentAttributeInfo.getLength();
        }

        // TODO all of these will be wrong! only checking largest byte! (flags is a bitfield!!!!)
        this.accessFlags = Byte.toUnsignedInt(classBytes[1]);
        this.nameIndex = Byte.toUnsignedInt(classBytes[3]);
        this.descriptorIndex = Byte.toUnsignedInt(classBytes[5]);
        this.length = offset;
    }
}