package club.hazsi.classified.util;

// TODO write this javadoc
public record Tuple3<J, K, L>(J value1, K value2, L value3) {
    @Override
    public String toString() {
        return "Tuple{" + value1 + ", " + value2 + ", " + value2 + '}';
    }
}