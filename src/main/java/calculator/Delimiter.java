package calculator;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Delimiter {
    private static final Pattern pattern = Pattern.compile("//(.)\n(.*)");
    public static final String DEFAULT_REGEX = "[,:]";

    public static Numbers split(InputText input) {
        Matcher matcher = pattern.matcher(input.toString());
        if(matcher.find()) {
            return splitCustom(matcher);
        }
       return splitDefault(input);
    }

    private static Numbers splitDefault(InputText input) {
        String[] tokens = input.toString().split(DEFAULT_REGEX);

        return new Numbers(Arrays.stream(tokens)
                .map(Integer::parseInt)
                .collect(Collectors.toList()));
    }

    private static Numbers splitCustom(Matcher matcher) {
        String customDelimiter = matcher.group(1);
        String[] tokens = matcher.group(2)
                .split(customDelimiter);

        return new Numbers(Arrays.stream(tokens)
                .map(Integer::parseInt)
                .collect(Collectors.toList()));
    }
}
