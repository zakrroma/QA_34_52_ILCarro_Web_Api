package utils.enums;

public enum Fuel {
    DIESEL("//form//option[1]"),
    PETROL("//form//option[2]"),
    HYBRID("//form//option[3]"),
    ELECTRIC("//form//option[4]"),
    GAS("//form//option[5]"),
    NULL("");

    private String locator;

    Fuel(String locator) {
        this.locator = locator;
    }

    public String getLocator() {
        return locator;
    }
}
