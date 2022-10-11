package data;

public interface OptionFilterable {
    String getTitle();

    default String getAltTitle() {
        return getTitle();
    }
}
