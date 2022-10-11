package data;

public interface OptionFilterable {
    String getTitle();

    //альтернативное название опции если по умолчанию равно нормальному названию
    default String getAltTitle() {
        return getTitle();
    }
}
