package pl.futurecollars.invoices.providers;

import static pl.futurecollars.invoices.providers.TestAddressProvider.buyerAddressWithOffset;
import static pl.futurecollars.invoices.providers.TestAddressProvider.defaultAddress;
import static pl.futurecollars.invoices.providers.TestAddressProvider.firstBuyerAddress;
import static pl.futurecollars.invoices.providers.TestAddressProvider.firstSellerAddress;
import static pl.futurecollars.invoices.providers.TestAddressProvider.secondBuyerAddress;
import static pl.futurecollars.invoices.providers.TestAddressProvider.secondSellerAddress;
import static pl.futurecollars.invoices.providers.TestAddressProvider.sellerAddressWithOffset;
import static pl.futurecollars.invoices.providers.TestAddressProvider.thirdBuyerAddress;
import static pl.futurecollars.invoices.providers.TestAddressProvider.thirdSellerAddress;

import pl.futurecollars.invoices.model.Company;

public class TestCompanyProvider {

    static Company secondSeller() {
        return new Company(
                "1011111111",
                "Sprzedam Wszystko Adam Kowalski",
                secondSellerAddress());
    }

    static Company thirdSeller() {
        return new Company(
                "1011234567",
                "SPEDEX SP. Z O.O.",
                thirdSellerAddress());
    }

    static Company firstBuyer() {
        return new Company(
                "9999999999",
                "U Basi Barbara Nowak",
                firstBuyerAddress());
    }

    static Company secondBuyer() {
        return new Company(
                "9009998765",
                "Nowak & Nowak",
                secondBuyerAddress());
    }

    static Company thirdBuyer() {
        return new Company(
                "9990000999",
                "Exitus S.A.",
                thirdBuyerAddress());
    }

    static Company firstSeller() {
        return new Company(
                "1111111111",
                "FHU Hurtex Sp. z o.o.",
                firstSellerAddress());
    }

    static Company defaultCompany() {
        return new Company(
                "0000000000",
                "",
                defaultAddress());
    }

    static Company sellerWithOffset(int offset) {
        return new Company(
                String.format("%010d", offset).substring(0, 10),
                "John Doe Sales " + offset,
                sellerAddressWithOffset(offset));
    }

    static Company buyerWithOffset(int offset) {
        return new Company(
                String.format("9%09d", offset).substring(0, 10),
                "Jane Doe Buyers " + offset,
                buyerAddressWithOffset(offset));
    }
}
