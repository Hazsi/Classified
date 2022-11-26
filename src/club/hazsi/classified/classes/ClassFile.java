package club.hazsi.classified.classes;

import club.hazsi.classified.util.ClassUtil;

import java.io.IOException;
import java.nio.file.*;

public final class ClassFile {
    private final ClassAttributes attributes;

    /**
     * Constructs a new ClassFile from the provided path, as a String. If the provided path isn't valid, a
     * {@link java.nio.file.NoSuchFileException} is thrown. If an IO error occurs while reading the provided path, a
     * {@link IOException} is thrown instead.
     *
     * @param classPath The full String path of an existent class file to be loaded
     * @throws IOException If an IO error occurs while reading from the provided path
     * @throws ClassFormatError If the provided class is corrupt or otherwise invalid
     */
    public ClassFile(String classPath) throws IOException, ClassFormatError {
        this(Paths.get(classPath));
    }

    /**
     * Constructs a new ClassFile from the provided path, as a {@link Path}. If the provided path isn't valid, a
     * {@link java.nio.file.NoSuchFileException} is thrown. If an IO error occurs while reading the provided path, a
     * {@link IOException} is thrown instead.
     *
     * @param classPath The full {@link Path} path of an existent class file to be loaded
     * @throws IOException If an IO error occurs while reading from the provided path
     * @throws ClassFormatError If the provided class is corrupt or otherwise invalid
     */
    public ClassFile(Path classPath) throws IOException, ClassFormatError {
        this(Files.readAllBytes(classPath));
    }

    /**
     * Constructs a new ClassFile from the provided array of byte composing the class. If the provided bytes do not
     * accurately resemble a valid class, a {@link ClassFormatError} is thrown with details given.
     *
     * @param classBytes A byte array composing the class file to be loaded
     * @throws ClassFormatError If the provided class is corrupt or otherwise invalid
     */
    public ClassFile(byte[] classBytes) throws ClassFormatError {
        if (!ClassUtil.isClassFile(classBytes))
            throw new ClassFormatError("not a valid class!");

        this.attributes = new ClassAttributes(classBytes);
    }

    public ClassAttributes getAttributes() {
        return this.attributes;
    }
}