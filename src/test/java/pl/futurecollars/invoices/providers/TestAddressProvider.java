package pl.futurecollars.invoices.providers;

import pl.futurecollars.invoices.model.PostalAddress;

public class TestAddressProvider {

    static PostalAddress firstSellerAddress() {
        return new PostalAddress(
                "Sprzedajna",
                "11",
                "",
                "11-111",
                "Sprzedajnice");
    }

    static PostalAddress secondSellerAddress() {
        return new PostalAddress(
                "Al. Kupców Bławatnych",
                "101",
                "12b",
                "01-111",
                "Nowa Mała Wieś");
    }

    static PostalAddress thirdSellerAddress() {
        return new PostalAddress(
                "Wądołki Dolne",
                "1101",
                "",
                "10-100",
                "Zasiedmiogórogrodzie");
    }

    static PostalAddress firstBuyerAddress() {
        return new PostalAddress(
                "Pokupna",
                "99",
                "9",
                "99-999",
                "Wola Kupczycka");
    }

    static PostalAddress secondBuyerAddress() {
        return new PostalAddress(
                "Trakt Kupiecki",
                "909",
                "",
                "09-999",
                "Wojsławice");
    }

    static PostalAddress thirdBuyerAddress() {
        return new PostalAddress(
                "Kardynała Armanda Jeana Richelieu",
                "9",
                "",
                "90-900",
                "Stosy Kościelne");
    }

    static PostalAddress defaultAddress() {
        return new PostalAddress(
                "",
                "",
                "",
                "00-000",
                "");
    }

    static PostalAddress sellerAddressWithOffset(int offset) {
        return new PostalAddress(
                "Sprzedajna" + offset,
                "10" + String.format("%02d", offset).substring(0, 2),
                "" + String.format("%02d", offset).substring(0, 2),
                "99-" + String.format("%03d", offset).substring(0, 3),
                "Sprzedajnice" + offset);
    }

    static PostalAddress buyerAddressWithOffset(int offset) {
        return new PostalAddress(
                "Pokupna" + offset,
                "90" + String.format("%02d", offset).substring(0, 2),
                "9" + String.format("%02d", offset).substring(0, 2),
                "09-" + String.format("%03d", offset).substring(0, 3),
                "Wola Kupczycka " + offset);
    }
}
