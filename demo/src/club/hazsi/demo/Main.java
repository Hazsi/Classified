package club.hazsi.demo;

import club.hazsi.classified.classes.ClassFile;

public class Main {
    public static void main(String[] args) {
        try {
            ClassFile classFile = ClassFile.fromDisk("C:/Users/demo/Desktop/Notification.class");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
