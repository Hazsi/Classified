package club.hazsi.classified.classes.components.fieldtable;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;

// TODO write this javadoc
@Getter
public class ClassFieldTable {
    private final ArrayList<ClassFieldTableEntry> fields = new ArrayList<>();
    private final int length;

    // TODO write this javadoc
    public ClassFieldTable(byte[] classBytes) {
        final int tableSize = Byte.toUnsignedInt(classBytes[1]);

        int offset = 2;

        for (int currentTableIndex = 1; currentTableIndex <= tableSize; currentTableIndex++) {
            ClassFieldTableEntry currentTableEntry = new ClassFieldTableEntry(
                    Arrays.copyOfRange(classBytes, offset, classBytes.length));

            this.fields.add(currentTableEntry);
            offset += currentTableEntry.getLength();
        }

        this.length = offset + 2;
    }
}
