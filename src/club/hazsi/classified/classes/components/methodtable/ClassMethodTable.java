package club.hazsi.classified.classes.components.methodtable;

import club.hazsi.classified.classes.components.fieldtable.ClassFieldTableEntry;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;

// TODO write this javadoc
@Getter
public class ClassMethodTable {
    private final ArrayList<ClassMethodTableEntry> methods = new ArrayList<>();
    private final int length;

    // TODO write this javadoc
    public ClassMethodTable(byte[] classBytes) {
        final int tableSize = Byte.toUnsignedInt(classBytes[1]);

        int offset = 2;

        for (int currentTableIndex = 1; currentTableIndex <= tableSize; currentTableIndex++) {
            ClassMethodTableEntry currentTableEntry = new ClassMethodTableEntry(
                    Arrays.copyOfRange(classBytes, offset, classBytes.length));

            this.methods.add(currentTableEntry);
            offset += currentTableEntry.getLength();
        }

        this.length = offset + 2;
    }
}
