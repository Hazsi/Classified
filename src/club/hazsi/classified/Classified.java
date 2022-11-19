package club.hazsi.classified;

import club.hazsi.classified.classes.ClassFile;
import club.hazsi.classified.classes.components.ClassConstantPoolEntry;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

public class Classified {

    public ClassFile classFile;

    public Classified() {
        try {
            this.classFile = new ClassFile(Paths.get("C:/Users/cwest/Desktop/Notification.class"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.toString(this.classFile.getRawBytes()));

        System.out.println("~~~~~~~~~~~~~~~~~~~~ Basic Information ~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Class is valid (class magic bytes present)");
        System.out.println("Class minor version: " + this.classFile.getMinorVersion());
        System.out.println("Class major version: " + this.classFile.getMajorVersion().name());

        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~ Constant Pool ~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Constant Pool table size: " + this.classFile.getConstantPool().getTableSize() +
                " (count: " + this.classFile.getConstantPool().getCount() + ")");

        for (int i = 0; i < this.classFile.getConstantPool().getEntries().size(); i++) {
            ClassConstantPoolEntry entry = this.classFile.getConstantPool().getEntries().get(i);

            System.out.println("Constant Pool entry #" + i + ":");
            System.out.println("\tEntry type: " + entry.getType());
            System.out.println("\tEntry size: " + entry.getSize());
        }

    }
}