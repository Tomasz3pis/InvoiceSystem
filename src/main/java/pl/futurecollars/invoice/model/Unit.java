package pl.futurecollars.invoice.model;

public enum Unit {
    ;

    private UnitDropDownList dropDownList;

    public enum UnitDropDownList {
        quantity,
        hours,
        days,
        months,
        kilometers,
        square_kilometers,
        kilograms,
        other;
    }

}
