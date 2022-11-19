package club.hazsi.classified.classes.components;

import lombok.Getter;

import java.util.ArrayList;

// todo write this javadoc
@Getter
public class ClassInterfaceTable {
    private final ArrayList<Integer> interfaces = new ArrayList<>();
    private final int length;

    // todo write this javadoc
    public ClassInterfaceTable(byte[] classBytes) {
        final int tableSize = Byte.toUnsignedInt(classBytes[1]);

        for (int currentTableIndex = 1; currentTableIndex <= tableSize; currentTableIndex++) {
            this.interfaces.add(Byte.toUnsignedInt(classBytes[currentTableIndex * 2 + 2]));
        }

        this.length = tableSize * 2 + 2;    // Each interface in the table has a size of two bytes, and the table
                                            // size itself takes up two bytes, hence the length of the whole table is
                                            // equal to size * 2 (size of all interfaces) + 2 (size of the size itself)
    }
}
