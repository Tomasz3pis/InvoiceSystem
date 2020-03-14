package pl.futurecollars.invoices.helpers;

import static pl.futurecollars.invoices.helpers.CheckForNull.checkForNull;

import java.util.regex.Pattern;

public class CheckIdFormat {

    private static Pattern idPattern = Pattern.compile(
            "2(\\d){3}(([0][1-9])|([1][0-2]))"
                    + "(([0][1-9])|([1-2]\\d)|([3][0-1]))"
                    + "[_](\\d){4}");

    public static void checkIdFormat(String id) {
        checkForNull(id, "id");
        if (!idPattern.matcher(id).matches()) {
            throw new IllegalArgumentException("Provided id String: "
            + id + " is not a valid id format");
        }
    }
}
