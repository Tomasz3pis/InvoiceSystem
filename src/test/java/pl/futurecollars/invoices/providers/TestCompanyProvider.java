package pl.futurecollars.invoices.providers;

import static pl.futurecollars.invoices.providers.TestAddressProvider.addressFortDodge;
import static pl.futurecollars.invoices.providers.TestAddressProvider.addressIdahoFalls;
import static pl.futurecollars.invoices.providers.TestAddressProvider.addressLatrobe;
import static pl.futurecollars.invoices.providers.TestAddressProvider.addressRhodeIsland;
import static pl.futurecollars.invoices.providers.TestAddressProvider.addressRoseville;
import static pl.futurecollars.invoices.providers.TestAddressProvider.addressWoodruff;

import pl.futurecollars.invoices.model.Company;

public class TestCompanyProvider {

    public static Company companyBuySome() {
        return new Company(
                "1111111111",
                "BuySome",
                addressRoseville());
    }

    public static Company companyBuyMore() {
        return new Company(
                "1011111111",
                "Buy More inc.",
                addressLatrobe());
    }

    public static Company companyGiftShops() {
        return new Company(
                "1011234567",
                "GiftShops",
                addressRhodeIsland());
    }

    public static Company companyBestBuy() {
        return new Company(
                "9999999999",
                "BestBuy",
                addressWoodruff());
    }

    public static Company companyPurchasey() {
        return new Company(
                "3",
                "Purchasey",
                addressIdahoFalls());
    }

    public static Company companyBoughtt() {
        return new Company(
                "2",
                "Boughtt",
                addressFortDodge());
    }

    public static Company futureCollars() {
        return new Company(
                "1",
                "FutureCollars",
                addressLatrobe());
    }
}
