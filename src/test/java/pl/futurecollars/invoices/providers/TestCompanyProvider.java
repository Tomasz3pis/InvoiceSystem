package pl.futurecollars.invoices.providers;

import static pl.futurecollars.invoices.providers.TestAddressProvider.addressFortDodge;
import static pl.futurecollars.invoices.providers.TestAddressProvider.addressIdahoFalls;
import static pl.futurecollars.invoices.providers.TestAddressProvider.addressLatrobe;
import static pl.futurecollars.invoices.providers.TestAddressProvider.addressRhodeIsland;
import static pl.futurecollars.invoices.providers.TestAddressProvider.addressRoseville;
import static pl.futurecollars.invoices.providers.TestAddressProvider.addressWithOffset;
import static pl.futurecollars.invoices.providers.TestAddressProvider.addressWoodruff;

import pl.futurecollars.invoices.model.Company;

public class TestCompanyProvider {

    static Company companyBuySome() {
        return new Company(
                "1111111111",
                "BuySome",
                addressRoseville());
    }

    static Company companyBuyMore() {
        return new Company(
                "1011111111",
                "Buy More inc.",
                addressLatrobe());
    }

    static Company companyGiftShops() {
        return new Company(
                "1011234567",
                "GiftShops",
                addressRhodeIsland());
    }

    static Company companyBestBuy() {
        return new Company(
                "9999999999",
                "BestBuy",
                addressWoodruff());
    }

    static Company companyPurchasey() {
        return new Company(
                "9009998765",
                "Purchasey",
                addressIdahoFalls());
    }

    static Company companyBoughtt() {
        return new Company(
                "9990000999",
                "Boughtt",
                addressFortDodge());
    }

    static Company sellerWithOffset(int offset) {
        return new Company(
                String.format("%010d", offset).substring(0, 10),
                "John Doe Sales " + offset,
                addressWithOffset(offset));
    }

    static Company buyerWithOffset(int offset) {
        return new Company(
                String.format("9%09d", offset).substring(0, 10),
                "Jane Doe Buyers " + offset,
                addressWithOffset(offset));
    }
}
