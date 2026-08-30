package utils.enums;

public enum NavBar {
    LOGO("//div[@class='header']//img"),
    SEARCH("//div[@class='header']/a[2]"),
    LET_THE_CAR_WORK("//div[@class='header']/a[3]"),
    TERMS_OF_USE("//div[@class='header']/a[4]"),
    SIGN_UP("//div[@class='header']/a[5]"),
    LOGIN("//div[@class='header']/a[6]"),
    LOGOUT("//div[@class='header']/a[5]"),
    DELETE_ACCOUNT("//div[@class='header']/a[6]");

    private final String locator;

    NavBar(String locator) {
        this.locator = locator;
    }

    public String getLocator() {
        return locator;
    }
}
