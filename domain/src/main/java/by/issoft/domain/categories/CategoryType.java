package by.issoft.domain.categories;

public enum CategoryType {
    BIKE(1),
    PHONE(2),
    MILK(3);

    private final int index;

    CategoryType(int index) {
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }
}
