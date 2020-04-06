package pl.futurecollars.invoices.providers;

import pl.futurecollars.invoices.model.PostalAddress;

public class TestAddressProvider {

    static PostalAddress addressRoseville() {
        return new PostalAddress(
                "Integer Rd.",
                "11",
                "",
                "11-111",
                "Roseville");
    }

    static PostalAddress addressLatrobe() {
        return new PostalAddress(
                "Viverra Avenue",
                "101",
                "12b",
                "01-111",
                "Latrobe");
    }

    static PostalAddress addressRhodeIsland() {
        return new PostalAddress(
                "Nunc Road",
                "1101",
                "",
                "10-100",
                "Rhode Island");
    }

    static PostalAddress addressWoodruff() {
        return new PostalAddress(
                "Curabitur Rd.",
                "99",
                "9",
                "99-999",
                "Woodruff");
    }

    static PostalAddress addressIdahoFalls() {
        return new PostalAddress(
                "Lacinia Avenue",
                "909",
                "",
                "09-999",
                "Idaho Falls");
    }

    static PostalAddress addressFortDodge() {
        return new PostalAddress(
                "Aliquet St.",
                "9",
                "",
                "90-900",
                "Fort Dodge");
    }

    static PostalAddress addressWithOffset(int offset) {
        return new PostalAddress(
                "SomeStreetName " + offset,
                "10" + String.format("%02d", offset).substring(0, 2),
                "" + String.format("%02d", offset).substring(0, 2),
                "99-" + String.format("%03d", offset).substring(0, 3),
                "SomeCityName " + offset);
    }
}
