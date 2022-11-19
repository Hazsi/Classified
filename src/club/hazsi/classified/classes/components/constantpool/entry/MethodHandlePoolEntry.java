package club.hazsi.classified.classes.components.constantpool.entry;

import club.hazsi.classified.classes.components.constantpool.ClassConstantPoolEntry;
import club.hazsi.classified.util.Tuple2;

public class MethodHandlePoolEntry extends ClassConstantPoolEntry<Tuple2<Integer, Integer>> {

    /**
     * Constructs a ClassConstantPoolEntry instance from the raw bytes that make it up. Since the length of this data
     * depends on the tag type and other data which is calculated here, the length of the data provided is irrelevant;
     * the data provided here is expected to be from the start of the entry to the end of the file, as everything after
     * the entry's data is disregarded. <br><br>
     * <p>
     * The offset for the next entry can be found with the
     * {@code ClassConstantPoolEntry#getSize()} method. Calling this constructor with erroneous data that doesn't
     * accurately resemble a constant pool entry (plus any amount of arbitrary class data afterwards) may result in
     * undocumented behaviour.
     *
     * @param rawData The data representing the pool entry (type/tag data size + 1 for the tag itself), plus any
     *                number of arbitrary bytes afterwards which are disregarded
     */
    public MethodHandlePoolEntry(byte[] rawData) {
        super(rawData);
    }

    /**
     * Parses the {@code data} byte array as according to the specification of the tag/type format. Directly calling
     * this method on {@link ClassConstantPoolEntry} is not allowed, and must be implemented by a subclass.
     * @return A parsed representation of the {@code data} field according to tag format
     */
    @Override
    public Tuple2<Integer, Integer> getParsedData() {
        return new Tuple2<>(
                Byte.toUnsignedInt(this.getData()[0]),
                Byte.toUnsignedInt(this.getData()[2]) // todo same problem as everywhere else, should be 1 and 2
        );
    }
}