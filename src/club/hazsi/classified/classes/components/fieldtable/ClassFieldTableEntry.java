package club.hazsi.classified.classes.components.fieldtable;

import club.hazsi.classified.classes.components.AttributeInfo;
import club.hazsi.classified.classes.components.constantpool.ClassConstantPoolEntry;
import club.hazsi.classified.util.ByteUtil;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a single entry in the classes field table. As per section 4.5 of the Java
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html">class file format specifications</a>, the
 * field table contains a table of entries. Each entry is expected to contain access and property information in an
 * 16 bit bitfield (see table 4.5-A of the aforementioned documentation), 16 bit constant pool indexes for the fields
 * name and descriptor, a 16 bit count of the additional attributes of the field, and a list of these attributes.<br><br>
 *
 * Taken from the aforementioned section of the Java class file format specifications, a C-like pseudocode
 * representation of a constant pool entry can be written as:
 *
 * <pre>{@code
 * field_info {
 *     u2               access_flags;       // Bitfield of field access flags
 *     u2               name_index;         // The CP index of the field name
 *     u2               descriptor_index;   // The CP index of the descriptor name
 *     u2               attributes_count;   // Attributes array size
 *     attribute_info   attributes[attribute_count];    // Array of attributes
 * }
 * }</pre>
 *
 * Unlike {@link ClassConstantPoolEntry}, the size of the entry and the format of the data stored within it is always
 * constant and conforms to the same standard as above. Attribute info is defined in section 4.7 of the Java class file
 * format specifications, and is implemented by Classified in {@link AttributeInfo}. However, due to the volatile size
 * of {@link AttributeInfo}, this entry does not have a set length and must be calculated in construction.<br><br>
 *
 * @author Hazsi
 * @since 1.0
 */
public class ClassFieldTableEntry {
    private final int length;
    private final int accessFlags; // TODO move this to an ArrayList of FieldAccessFlags instead of just the raw bitfield
    private final int nameIndex;
    private final int descriptorIndex;
    private final ArrayList<AttributeInfo> attributeInfos = new ArrayList<>();

    // TODO write this javadoc
    public ClassFieldTableEntry(byte[] classBytes) {

        final int attributeInfoCount = ByteUtil.readByte(classBytes, 7);
        int offset = 8;

        for (int currentAttributeIndex = 0; currentAttributeIndex < attributeInfoCount; currentAttributeIndex++) {
            AttributeInfo currentAttributeInfo = new AttributeInfo(Arrays.copyOfRange(classBytes, offset, classBytes.length));

            this.attributeInfos.add(currentAttributeInfo);
            offset += currentAttributeInfo.getLength();
        }

        this.accessFlags = ByteUtil.readWORD(classBytes, 0);
        this.nameIndex = ByteUtil.readWORD(classBytes, 2);
        this.descriptorIndex = ByteUtil.readWORD(classBytes, 4);
        this.length = offset;
    }

    public int getLength() {
        return length;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public ArrayList<AttributeInfo> getAttributeInfos() {
        return attributeInfos;
    }
}