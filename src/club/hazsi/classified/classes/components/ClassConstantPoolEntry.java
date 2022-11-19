package club.hazsi.classified.classes.components;

import lombok.Getter;

import java.util.Arrays;

/**
 * Represents a single entry in the constant pool. As per section 4.4 of the Java
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html">class file format specifications</a>, the
 * constant pool contains a table of entries, each containing a constant pool type (known as a tag) referred to
 * by a value (see {@link ClassConstantPoolType} for Classified's representation of these types). In addition to this,
 * each class pool entry is expected to contain an array of at least two bytes of information about the specific
 * constant.<br><br>
 *
 * Taken from the aforementioned section of the Java class file format specifications, a pseudocode representation of
 * a constant pool entry can be written as:
 *
 * <pre>{@code
 * cp_info {
 *     u1 tag;
 *     u1 info[]; // Must be at least 2 bytes, depending on the value of tag
 * }
 * }</pre>
 *
 * where info[] must be at least two bytes long. The data contained in info[] varies based on the tag being used.
 * These data formats are further elaborated on in the same section of the specifications, from 4.4.1 through to
 * 4.4.10.<br><br>
 *
 * In Classified's implementation of the constant pool entry, data is stored uniformly as a byte array. Instead of
 * parsing this data based on the type at instantiation, individual methods are provided for the parsing of this data
 * to each of the data (info[], from the pseudocode example) types in the specification.
 *
 * @author Hazsi
 * @since 11/18/22
 */
@Getter
public class ClassConstantPoolEntry {
    private final ClassConstantPoolType type;
    private final byte[] data;
    private final int size;

    /**
     * Constructs a ClassConstantPoolEntry instance from the raw bytes that make it up. Since the length of this data
     * depends on the tag type and other data which is calculated here, the length of the data provided is irrelevant;
     * the data provided here is expected to be from the start of the entry to the end of the file, as everything after
     * the entry's data is disregarded. <br><br>
     *
     * The offset for the next entry can be found with the
     * {@code ClassConstantPoolEntry#getSize()} method. Calling this constructor with erroneous data that doesn't
     * accurately resemble a constant pool entry (plus any amount of arbitrary class data afterwards) may result in
     * undocumented behaviour.
     * @param rawData The data representing the pool entry (type/tag data size + 1 for the tag itself), plus any
     *                number of arbitrary bytes afterwards which are disregarded
     */
    public ClassConstantPoolEntry(byte[] rawData) {
        final int tag = Byte.toUnsignedInt(rawData[0]);
        this.type = ClassConstantPoolType.getByValue(tag);

        final int dataSize = this.type.equals(ClassConstantPoolType.UTF8) ? // The UTF-8 tag does not have a
                this.determineUTFSize(rawData) : this.type.getDataSize();   // constant size, unlike all other tags,
                                                                            // and therefore we must determine data size
                                                                            // manually if the entry is a UTF-8 tag.

        this.size = dataSize + 1;   // Add one to the data size to account for the size of the tag itself (one byte).

        this.data = Arrays.copyOfRange(rawData, 1, this.size);
    }

    // TODO write this javadoc
    // INCLUDES THE TAG/TYPE!!! ONE BYTE PREFIXED COMPARED TO NORMAL DATA
    private int determineUTFSize(byte[] data) {
        if (!this.type.equals(ClassConstantPoolType.UTF8))      // This method should only be invoked on constant pool
            throw new UnsupportedOperationException();          // entries with the UTF-8 tag type.

        // TODO, this is supposed to be a 16 bit number across data bytes 0 and 1, but only 1 is used here.
        return Byte.toUnsignedInt(data[2]) + 2;
    }
}