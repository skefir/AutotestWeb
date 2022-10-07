package data;

public enum Currencies implements OptionFilterable  {
    AUD("Australian Dollar"),
    BRL("Brazilian real"),
    CAD("Canadian dollar"),
    CHF("Swiss frank"),
    CNY("Chinese yuan"),
    EUR("Euro"),
    GBP("Pound sterling"),
    HKD("Hong Kong dollar"),
    INR("Indian rupee"),
    JPY("Japanese yen"),
    KRW("South Korean won"),
    MXN("Mexican peso"),
    NOK("Norwegian Krone"),
    NZD("New Zealand dollar"),
    SEK("Swedish krona"),
    SGD("Singapore dollar"),
    USD("US dollar"),
    ZAR("South African rand");

    private String currencyName;


    Currencies(String currencyName) {
        this.currencyName = currencyName;
    }


    @Override
    public String getTitle() {
        return this.name() + " - " + currencyName;
    }
}
