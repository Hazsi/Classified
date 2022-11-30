package club.hazsi.classified.classes;

import club.hazsi.classified.classes.loader.ByteClassLoader;
import club.hazsi.classified.util.ClassUtil;

import java.io.IOException;
import java.nio.file.*;

/**
 * Represents an abstract class file, either on a disk, loaded in the JVM, or in raw bytes. Contains a
 * {@link ClassAttributes} object for the class that can be manipulated and read. ClassFile instances retain their raw
 * bytes to be read or placed back into the JVM, and are automatically updated when the {@link ClassAttributes} are
 * modified. A classes raw bytes can manually be set, automatically regenerating the ClassAttributes with the new
 * class data.<br><br>
 *
 * <h2>Fields</h2>
 * When a ClassFile is instantiated, the class itself is automatically analyzed and parsed into information that is
 * accessible and modifiable.
 *
 * <ul>
 *     <li>{@code rawBytes} - A raw byte array storing the bytes which compose the class. The byte array can be
 *     manually set through the {@link #setRawBytes(byte[])} method, which automatically regenerates other fields
 *     to match the newly defined class file. The byte array can be manually refreshed through invoking the
 *     {@link #refreshRawBytes()} method.</li>
 *     <li>{@code attributes} - A {@link ClassAttributes} instance parsing and storing all class data (constant pool,
 *     interface/field/method tables, etc) that can be read and modified. Automatically generated on instantiation.
 *     Cannot be directly set, but is automatically regenerated when the raw class bytes are set via the
 *     {@link #setRawBytes(byte[])} method.</li>
 * </ul>
 *
 * @since 1.0
 * @author Hazsi
 */
public final class ClassFile {
    private byte[] rawBytes;
    private ClassAttributes attributes;

    /**
     * Constructs a new ClassFile from the provided path, as a String. If the provided path isn't valid, a
     * {@link java.nio.file.NoSuchFileException} is thrown. If an IO error occurs while reading the provided path, a
     * {@link IOException} is thrown instead.
     *
     * @param classPath The full String path of an existent class file to be loaded
     * @throws IOException If an IO error occurs while reading from the provided path and the file cannot be read
     * @throws ClassFormatError If the provided file is not a class, is malformed and does not follow the class
     * specifications, or is otherwise invalid
     * @throws NoSuchFileException If the provided path is missing or cannot be found
     *
     * @since 1.0
     */
    public static ClassFile fromDisk(String classPath) throws IOException, ClassFormatError {
        return new ClassFile(Files.readAllBytes(Paths.get(classPath)));
    }

    /**
     * Constructs a new ClassFile from the provided path, as a {@link Path}. If the provided path isn't valid, a
     * {@link java.nio.file.NoSuchFileException} is thrown. If an IO error occurs while reading the provided path, a
     * {@link IOException} is thrown instead.
     *
     * @param classPath The full {@link Path} path of an existent class file to be loaded
     * @throws IOException If an IO error occurs while reading from the provided path and the file cannot be read
     * @throws ClassFormatError If the provided file is not a class, is malformed and does not follow the class
     * specifications, or is otherwise invalid
     * @throws NoSuchFileException If the provided path is missing or cannot be found
     *
     * @since 1.0
     */
    public static ClassFile fromDisk(Path classPath) throws IOException, ClassFormatError {
        return new ClassFile(Files.readAllBytes(classPath));
    }

    /**
     * Constructs a new ClassFile from the provided array of byte composing the class. If the provided bytes do not
     * accurately resemble a valid class, a {@link ClassFormatError} is thrown with details given.
     *
     * @param classBytes A byte array composing the class file to be loaded
     * @throws ClassFormatError If the provided file is not a class, is malformed and does not follow the class
     * specifications, or is otherwise invalid
     *
     * @since 1.0
     */
    public static ClassFile fromBytes(byte[] classBytes) throws ClassFormatError {
        return new ClassFile(classBytes);
    }

    /**
     * Constructs a new ClassFile from a class loaded in the current JVM, given the fully qualified class name. If the
     * provided class does not accurately resemble a valid class, a {@link ClassFormatError} is thrown with details
     * given, which should be impossible (the class was already checked being loaded into the JVM!). If the provided
     * class name is not found in the JVM, a {@link ClassNotFoundException} is thrown instead.
     *
     * @param className The fully qualified class name of a class file loaded in the current JVM
     * @throws ClassNotFoundException If the provided class couldn't be found in the JVM, or otherwise cannot
     * be accessed
     * @throws ClassFormatError If the provided file is not a class, is malformed and does not follow the class
     * specifications, or is otherwise invalid
     * @throws IOException If some generic error occurs during class serialization
     *
     * @since 1.0
     */
    public static ClassFile fromJVM(String className) throws ClassNotFoundException,
            ClassFormatError, IOException {
        final Class<?> clazz = Class.forName(className); // todo does this always work?
        return new ClassFile(ClassUtil.getClassBytes(clazz));
    }

    /**
     * Private constructor to  a new ClassFile from the provided array of byte composing the class. If the provided
     * bytes do not accurately resemble a valid class, a {@link ClassFormatError} is thrown with details given.
     *
     * @param classBytes A byte array composing the class file to be loaded
     * @throws ClassFormatError If the provided file is not a class, is malformed and does not follow the class
     * specifications, or is otherwise invalid
     *
     * @since 1.0
     */
    private ClassFile(byte[] classBytes) throws ClassFormatError {
        if (!ClassUtil.isClassFile(classBytes))
            throw new ClassFormatError("not a valid class!");

        this.rawBytes = classBytes;
        this.attributes = new ClassAttributes(classBytes, this);
    }

    // TODO - regenerate class bytes from class attributes
    public void refreshRawBytes() {

    }

    /**
     * Loads the class (as defined by the current {@code rawBytes}) into the current JVM. This method should not
     * be used if a class by the same package and name already exists in the JVM.
     *
     * @since 1.0
     */
    public void load() {
        ByteClassLoader.load(this.rawBytes);
    }

    /**
     * Sets the class' raw bytes. Similar to the private constructor, this method ensures that the provided bytes
     * are a valid class -- throwing a {@link ClassFormatError} if this is not the case -- and regenerating the
     * ClassAttributes instance to match the new class definition.
     *
     * @param classBytes The new class bytes to define the class by
     * @throws ClassFormatError If the provided file is not a class, is malformed and does not follow the class
     * specifications, or is otherwise invalid
     * @since 1.0
     */
    public void setRawBytes(byte[] classBytes) throws ClassFormatError {
        if (!ClassUtil.isClassFile(classBytes))
            throw new ClassFormatError("not a valid class!");

        this.rawBytes = classBytes;
        this.attributes = new ClassAttributes(classBytes, this);
    }

    /**
     * @return The raw bytes making up the original class
     */
    public byte[] getRawBytes() {
        return this.rawBytes;
    }

    /**
     * @return The {@link ClassAttributes} instance belonging to this class
     */
    public ClassAttributes getAttributes() {
        return this.attributes;
    }
}