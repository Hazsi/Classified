package club.hazsi.classified.classes.components;

import club.hazsi.classified.util.ByteUtil;
import lombok.Getter;

import java.util.ArrayList;

// TODO write javadoc (specs 4.7)
@Getter
public class AttributeInfo {
    private final int length;
    private final int nameIndex;
    private final ArrayList<Integer> attributes = new ArrayList<>();

    // TODO write javadoc
    public AttributeInfo(byte[] classBytes) {
        final int attributesSize =
            ByteUtil.readDWORD(classBytes, 2);   // As per section 4.7 of the Java class file format
                                                            // specifications, the amount of attributes contained by
                                                            // AttributeInfo will always fall on bytes 2 - 5.

        for (int currentAttributeIndex = 0; currentAttributeIndex < attributesSize; currentAttributeIndex++) {
            attributes.add(ByteUtil.readByte(classBytes, 6 + currentAttributeIndex));
        }

        this.nameIndex =
                ByteUtil.readWORD(classBytes, 0);     // An AttributeInfo's nameIndex will always fall on
                                                                 // bytes 0 - 1 as per section 4.7 of the specifications

        this.length = attributesSize + 2 + 4;       // The byte length of AttributeInfo. Each attribute takes up one
                                                    // byte, the nameIndex takes up two, and the amount of attributes
                                                    // contained will always take up four bytes.
    }
}