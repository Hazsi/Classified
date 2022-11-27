package club.hazsi.demo;

import club.hazsi.classified.classes.ClassFile;
import club.hazsi.classified.classes.components.constantpool.ClassConstantPool;

import java.io.IOException;

public class ClassifiedDemo {

    public ClassFile classFile;

    public ClassifiedDemo() {
        try {
            this.classFile = ClassFile.fromDisk("C:/Users/cwest/Desktop/Notification.class");

            final ClassConstantPool constantPool = this.classFile.getAttributes().getConstantPool();

            for (int i = 0; i < classFile.getAttributes().getFieldTable().getFields().size(); i++) {
                final int nameIndex = classFile.getAttributes().getFieldTable().getFields().get(i).getNameIndex();
                final int descIndex = classFile.getAttributes().getFieldTable().getFields().get(i).getDescriptorIndex();
                final int flags = classFile.getAttributes().getFieldTable().getFields().get(i).getAccessFlags();

                System.out.println(constantPool.getEntries().get(nameIndex).getParsedData() + "\t" +
                        constantPool.getEntries().get(descIndex).getParsedData() + "\t" + String.format("0x%04X", flags));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

//        try {
////            this.classFile = new ClassFile(Paths.get("C:/Users/cwest/Desktop/Main.class"));
//            this.classFile = ClassFile.fromDisk("C:/Users/cwest/Desktop/Notification.class");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        System.out.println(Arrays.toString(this.classFile.getRawBytes()));

//        System.out.println("~~~~~~~~~~~~~~~~~~~~ Basic Information ~~~~~~~~~~~~~~~~~~~~");
//        System.out.println("Class is valid (class magic bytes present)");
//        System.out.println("Class minor version: " + this.classFile.getMinorVersion());
//        System.out.println("Class major version: " + this.classFile.getMajorVersion().name());
//
//        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~ Constant Pool ~~~~~~~~~~~~~~~~~~~~~~");
//        System.out.println("Constant Pool length: " + this.classFile.getConstantPool().getLength() + " bytes");
//        System.out.println("Constant Pool table size: " + this.classFile.getConstantPool().getTableSize() +
//                " (count: " + this.classFile.getConstantPool().getCount() + ")");
//
//        for (int i = 1; i < this.classFile.getConstantPool().getEntries().size() + 1; i++) {
//            ClassConstantPoolEntry<?> entry = this.classFile.getConstantPool().getEntries().get(i - 1);
//
//            System.out.println("\tConstant Pool entry #" + i + ":");
//            System.out.println("\t\tEntry type: " + entry.getType() + ", entry size: " + entry.getSize());
//
//            if (entry.getType().equals(ClassConstantPoolEntryType.UTF8)) {
//                System.out.println("\t\tEntry data: \"" + entry.getParsedData().toString() + "\"");
//            } else {
//                System.out.println("\t\tEntry data: " + entry.getParsedData().toString());
//            }
//        }
//
//        System.out.println("\n~~~~~~~~~~~~~~~~~~~ More Misc Information ~~~~~~~~~~~~~~~~~~~");
//
//        // TODO ACCESS FLAGS BITMASK
//        final byte classIndex = this.classFile.getRawBytes()[13 + this.classFile.getConstantPool().getLength()];
//        final ClassConstantPoolEntry<?> className = this.classFile.getConstantPool().getEntries().get(classIndex);
//        System.out.println("This class: " + className.getParsedData() + " (class pool index " + classIndex + ")");
//
//        final byte superclassIndex = this.classFile.getRawBytes()[15 + this.classFile.getConstantPool().getLength()];
//        final ClassConstantPoolEntry<?> superclassName = this.classFile.getConstantPool().getEntries().get(superclassIndex);
//        System.out.println("Superclass: " + superclassName.getParsedData() + " (class pool index " + superclassIndex + ")");
//
//        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~ Interface Table ~~~~~~~~~~~~~~~~~~~~~");
//        System.out.println("Interface Table length: " + this.classFile.getInterfaceTable().getLength() + " bytes");
//        System.out.println("Interface Table table size: " + this.classFile.getInterfaceTable().getInterfaces().size());
//
//        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~ Field Table ~~~~~~~~~~~~~~~~~~~~~~~");
//        System.out.println("Field Table length: " + this.classFile.getFieldTable().getLength() + " bytes");
//        System.out.println("Field Table table size: " + this.classFile.getFieldTable().getFields().size());
//
//        for (int i = 1; i < this.classFile.getFieldTable().getFields().size() + 1; i++) {
//            ClassFieldTableEntry entry = this.classFile.getFieldTable().getFields().get(i - 1);
//
//            System.out.println("\tField Table entry #" + i + ":");
//            System.out.println("\t\tEntry access flags: " + entry.getAccessFlags());
//            System.out.println("\t\tEntry name: \"" + this.classFile.getConstantPool().getEntries().get(entry.getNameIndex() - 1).getParsedData() +
//                    "\", entry descriptor: \"" + this.classFile.getConstantPool().getEntries().get(entry.getDescriptorIndex() - 1).getParsedData() + "\"");
//            System.out.println("\t\tEntry AttributeInfo count: " + entry.getAttributeInfos().size());
//        }

//        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~ Method Table ~~~~~~~~~~~~~~~~~~~~~~~");
//        System.out.println("Method Table length: " + this.classFile.getMethodTable().getLength() + " bytes");
//        System.out.println("Method Table table size: " + this.classFile.getMethodTable().getMethods().size());
//
//        for (int i = 1; i < this.classFile.getMethodTable().getMethods().size() + 1; i++) {
//            ClassMethodTableEntry entry = this.classFile.getMethodTable().getMethods().get(i - 1);
//
//            System.out.println("\tMethod Table entry #" + i + ":");
//            System.out.println("\t\tEntry access flags: " + entry.getAccessFlags());
//            System.out.println("\t\tEntry name: \"" + this.classFile.getConstantPool().getEntries().get(entry.getNameIndex() - 1).getParsedData() +
//                    "\", entry descriptor: \"" + this.classFile.getConstantPool().getEntries().get(entry.getDescriptorIndex() - 1).getParsedData() + "\"");
//            System.out.println("\t\tEntry AttributeInfo count: " + entry.getAttributeInfos().size());
//        }
    }

    public static void main(String[] args) {
        new ClassifiedDemo();
    }
}