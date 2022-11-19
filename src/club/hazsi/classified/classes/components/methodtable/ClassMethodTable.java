package club.hazsi.classified.classes.components.methodtable;

import club.hazsi.classified.classes.components.fieldtable.ClassFieldTableEntry;
import lombok.Getter;

import java.util.ArrayList;

// TODO write this javadoc
@Getter
public class ClassMethodTable {
    private final ArrayList<ClassFieldTableEntry> fields = new ArrayList<>();
    private final int length;

    // TODO write this javadoc
    public ClassMethodTable(byte[] classBytes) {
        final int tableSize = Byte.toUnsignedInt(classBytes[1]);

        int offset = 0;

//        for (int currentTableIndex = 1; currentTableIndex <= tableSize; currentTableIndex++) {
//            this.fields.add(Byte.toUnsignedInt(classBytes[currentTableIndex * 2 + 2]));
//        }

        this.length = offset + 2;
    }
}
