package pl.futurecollars.invoices.helpers;

public class CheckForNull {

    public static void checkForNull(Object object, String objectName) {
        if (objectName == null) {
            throw new IllegalArgumentException(
                    "Provided objectName String cannot be null");
        }
        if (object == null) {
            throw new IllegalArgumentException("Provided "
                            + objectName
                            + " Object cannot be null");
        }
    }
}
