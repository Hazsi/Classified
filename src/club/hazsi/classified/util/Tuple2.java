package club.hazsi.classified.util;

// TODO write this javadoc
public record Tuple2<J, K>(J value1, K value2) {
    @Override
    public String toString() {
        return "Tuple{" + value1 + ", " + value2 + '}';
    }
}
