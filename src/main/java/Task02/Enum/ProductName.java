package Task02.Enum;

public enum ProductName {

    TELEVISION("Телевизор"),
    CUPBOARD("Шкаф"),
    PEN("Ручка"),
    WINDOW("Окно");

    private final String name;

    ProductName(String s) {
        name = s;
    }

    public String getName() {
        return name;
    }
}
