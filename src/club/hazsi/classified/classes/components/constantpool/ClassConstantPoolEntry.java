package club.hazsi.classified.classes.components.constantpool;

import lombok.Getter;

import java.util.Arrays;

/**
 * Represents a single entry in the constant pool. As per section 4.4 of the Java
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html">class file format specifications</a>, the
 * constant pool contains a table of entries, each containing a constant pool type (known as a tag) referred to
 * by a value (see {@link ClassConstantPoolEntryType} for Classified's representation of these types). In addition to this,
 * each class pool entry is expected to contain an array of at least two bytes of information about the specific
 * constant.<br><br>
 *
 * Taken from the aforementioned section of the Java class file format specifications, a C-like pseudocode
 * representation of a constant pool entry can be written as:
 *
 * <pre>{@code
 * cp_info {
 *     u1 tag; // The type ("tag") of the entry, values linked above
 *     u1 info[]; // Must be at least 2 bytes, depending on the value of tag
 * }
 * }</pre>
 *
 * where info[] must be at least two bytes long. The data contained in info[] varies based on the tag being used.
 * These data formats are further elaborated on in the same section of the specifications, from 4.4.1 through to
 * 4.4.10.<br><br>
 *
 * In Classified, each tag has their own implementation extending this class. These implementations <b>must override
 * the {@code getParsedData()} method</b> present in this class. These method implementations are expected to parse
 * the raw {@code data[]} byte array into clean data of type <b>T</b> (see type parameter comment).
 *
 * <br><br>
 *
 * In Classified's implementation of the constant pool entry, data is stored uniformly as a byte array. Instead of
 * parsing this data based on the type at instantiation, individual methods are provided for the parsing of this data
 * to each of the data (info[], from the pseudocode example) types in the specification.
 *
 * @param <T> The data type of which subsequent implementations of this class should parse their data to. Tags with
 *           multiple variables should use the {@code Tuple2} and {@code Tuple3} classes.
 * @author Hazsi
 * @since 11/18/22
 */
@Getter
public class ClassConstantPoolEntry<T> {
    private final ClassConstantPoolEntryType type;
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
     * undocumented behaviour.<br><br>
     *
     * <i>This constructor should only be used privately and by implementations of this method, this raw class itself
     * should almost never be used; instead, </i><b>the {@code factory(byte[])} method should be used instead</b><i> to
     * return a new instance of the appropriate implementation of this class.</i><br><br>
     *
     * @see ClassConstantPoolEntryFactory
     * @param classBytes The data representing the pool entry (type/tag data size + 1 for the tag itself), plus any
     *                number of arbitrary bytes afterwards which are disregarded.
     */
    protected ClassConstantPoolEntry(byte[] classBytes) {
        final int tag = Byte.toUnsignedInt(classBytes[0]);
        this.type = ClassConstantPoolEntryType.getByValue(tag);

        final int dataSize = type.equals(ClassConstantPoolEntryType.UTF8) ?     // The UTF-8 tag does not have a
                this.determineUTFSize(classBytes) : this.type.getDataSize();    // constant size, unlike all other tags,
                                                                                // and therefore we must determine size
                                                                                // manually if the entry is a UTF-8 tag.

        this.size = dataSize + 1;   // Add one to the data size to account for the size of the tag itself (one byte).
        this.data = Arrays.copyOfRange(classBytes, 1, this.size);
    }

    /**
     * Parses the {@code data} byte array as according to the specification of the tag/type format. Directly calling
     * this method on {@link ClassConstantPoolEntry} is not allowed, and must be implemented by a subclass.
     * @return A parsed representation of the {@code data} field according to tag format
     */
    public T getParsedData() {
        throw new UnsupportedOperationException("This method must be implemented!");
    }

    /**
     * Parses the data generated by the {@code getParsedData()} method implementations in a human-readable format.
     * Directly calling this method on {@link ClassConstantPoolEntry} is not allowed, and must be implemented by
     * a subclass.
     * @return A human-readable String representation of the parsed data provided by {@code getParsedData()}.
     */
    public String getParsedDataAsString() {
        throw new UnsupportedOperationException("This method must be implemented!");
    }

    /**
     * Used to calculate the variable length of a UTF-8 constant pool entry. This method should not be used publicly,
     * and should only be called internally when calculating the {@code size} field.
     *
     * @param data The raw data of the constant pool entry, <b>including the leading tag byte</b>
     * @return The integer data length, in bytes, of the UTF-8 constant pool entry, plus any number of arbitrary
     * bytes afterwards which are disregarded.
     */
    private int determineUTFSize(byte[] data) {
        if (!this.type.equals(ClassConstantPoolEntryType.UTF8))     // This method should only be invoked on constant
            throw new UnsupportedOperationException();              // pool entries with the UTF-8 tag type.

        // TODO, this is supposed to be a 16 bit number across data bytes 1 and 2, but only 2 is used here.
        return Byte.toUnsignedInt(data[2]) + 2;
    }
}