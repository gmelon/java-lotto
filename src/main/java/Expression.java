import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class Expression {
    private final Numbers numbers;
    private final Splitters splitters;

    public Expression(String sentence, Splitters splitters) {
        this.numbers = new Numbers(
                Arrays.stream(sentence.split(splitters.getRegex()))
                        .map(Number::new)
                        .collect(Collectors.toList()));
        this.splitters = splitters;

    }

    public Number sum() {
        return numbers.sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expression that = (Expression) o;
        return Objects.equals(numbers, that.numbers) &&
                Objects.equals(splitters, that.splitters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers, splitters);
    }

    public Numbers getNumbers() {
        return numbers;
    }
}