package Task02.Enum;

public enum CompanyStorage {
    STORAGE01("Склад 1"),
    STORAGE02("Склад 2"),
    STORAGE03("Склад 3");

    private final String nameStorage;

    CompanyStorage(String s) {
        nameStorage = s;
    }

    public String getNameStorage() {
        return nameStorage;
    }
}
