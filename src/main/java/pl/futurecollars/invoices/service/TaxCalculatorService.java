package pl.futurecollars.invoices.service;

import pl.futurecollars.invoices.model.Company;
import pl.futurecollars.invoices.model.Invoice;
import pl.futurecollars.invoices.model.PostalAddress;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.math.BigDecimal.*;


public class TaxCalculatorService {

    final Company futureCollars = new Company("0001", "FutureCollars Sp. z o.o.", new PostalAddress("Koronawirusa", "1", "1", "01-001", "Warszawa"));
    List<Invoice> invoices = new ArrayList<>();
    Map<Integer, Invoice> invoiceMap = new HashMap<>();
    Invoice invoice;
    Integer integer;
    Function<Invoice, BigDecimal> function;
    BigDecimal sum;


    public BigDecimal taxCalculator(Company company) {
        invoices
                .stream()
                .filter(value -> (invoice.getBuyer().getTaxIdentificationNumber() == company.getTaxIdentificationNumber()))
                .forEach(value -> sum +=  );
        return valueOf(001);
    }

}




//TODO policzyć wartości income VAT   outcome VAT     /income  costs  / Ostatnia wartość liczona jest z poprzednich income cost (income - costs)
//TODO napisać 4 funkcji które będą obliczać wartości
//TODO Podajemy ID swojej firmy , iterujemy po wszystkich fakturach na koniec liczymy income, outcome , income VAT, costs oraz income cost(income - costs)
//TODO zahardkodować swoją firmę
//TODO policzone wartości zwracamy w Json
//TODO Stworzyć generyczną funkcję, która przyjmuje funkcję którą wsadzamy w jednego if
