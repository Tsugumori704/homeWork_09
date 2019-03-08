package Task02.Enum;

public enum Units {

    PIECES("Шт.");

    private final String nameUnits;

    Units(String s) {
        nameUnits = s;
    }

    public String getNameUnits() {
        return nameUnits;
    }

}
