package pl.futurecollars.invoices.providers;

import pl.futurecollars.invoices.model.PostalAddress;

public class TestAddressProvider {

    public static PostalAddress addressRoseville() {
        return new PostalAddress(
                "Integer Rd.",
                "11",
                "",
                "11-111",
                "Roseville");
    }

    public static PostalAddress addressLatrobe() {
        return new PostalAddress(
                "Viverra Avenue",
                "101",
                "12b",
                "01-111",
                "Latrobe");
    }

    public static PostalAddress addressRhodeIsland() {
        return new PostalAddress(
                "Nunc Road",
                "1101",
                "",
                "10-100",
                "Rhode Island");
    }

    public static PostalAddress addressWoodruff() {
        return new PostalAddress(
                "Curabitur Rd.",
                "99",
                "9",
                "99-999",
                "Woodruff");
    }

    public static PostalAddress addressIdahoFalls() {
        return new PostalAddress(
                "Lacinia Avenue",
                "909",
                "",
                "09-999",
                "Idaho Falls");
    }

    public static PostalAddress addressFortDodge() {
        return new PostalAddress(
                "Aliquet St.",
                "9",
                "",
                "90-900",
                "Fort Dodge");
    }
}
