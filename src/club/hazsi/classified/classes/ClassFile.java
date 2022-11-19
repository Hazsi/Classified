package club.hazsi.classified.classes;

import club.hazsi.classified.classes.components.ClassConstantPool;
import club.hazsi.classified.classes.components.ClassMajorVersion;
import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Getter
public class ClassFile {
    private final byte[] rawBytes;
    private final int minorVersion;
    private final ClassMajorVersion majorVersion;
    private final ClassConstantPool constantPool;

    public ClassFile(Path classPath) throws IOException {
        this(Files.readAllBytes(classPath));
    }

    public ClassFile(byte[] classBytes) {
        if (!ClassUtil.isValidClassFile(classBytes)) throw new ClassFormatError();

        this.rawBytes = classBytes;
        this.minorVersion = Byte.toUnsignedInt(classBytes[5]);
        this.majorVersion = new ClassMajorVersion(classBytes);
        this.constantPool = new ClassConstantPool(classBytes);
    }
}