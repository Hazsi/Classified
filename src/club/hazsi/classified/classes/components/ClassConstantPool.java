package club.hazsi.classified.classes.components;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;

@Getter
public class ClassConstantPool {
    private final int tableSize;
    private final int length = 0;
    private final ArrayList<ClassConstantPoolEntry> entries = new ArrayList<>();

    public ClassConstantPool(byte[] classBytes) {
        this.tableSize = Byte.toUnsignedInt(classBytes[9]) - 1;

        int entryOffset = 10;   // Used to keep track of the byte offset of the entry currently being parsed.
                                // Per the classfile specification, the offset of the first entry will always be
                                // exactly 10 bytes from the start of the file

        for (int currentEntryIndex = 0; currentEntryIndex < this.tableSize - 1; currentEntryIndex++) {
            byte[] currentEntryBytes = Arrays.copyOfRange(classBytes, entryOffset, classBytes.length);
            ClassConstantPoolEntry currentEntry = new ClassConstantPoolEntry(currentEntryBytes);

            entryOffset += currentEntry.getSize();
            this.entries.add(currentEntry);
        }
    }

    /**
     * As mentioned in section 4.1 of the Java
     *  <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html">class file format specifications</a>,
     *  the constant_pool_count byte in a class file (index 8 and 9, as seen in the constructor) is equal to the number
     *  of entries in the constant_pool table <b>plus one.</b> Classified opts to use the accurate size of the constant
     *  pool table in most places ({@code size} field), but provides this method to return the constant_pool_count if
     *  needed for some reason.
     *
     * @return The constant pool count value of the class file, NOT the accurate constant pool <b>size.</b>
     */
    public int getCount() {
        return this.tableSize + 1;
    }
}