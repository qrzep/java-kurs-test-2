package pl.kurs.zad1.model.enums;

public enum Gender {
    DAUGHTER("c"),
    SON("s");

    private final String code;

    Gender(String code) {
        this.code = code;
    }

    public static Gender fromString(String code) {
        for (Gender g : values()) {
            if (g.code.equals(code)) return g;
        }
        throw new IllegalArgumentException("Invalid gender code: " + code);
    }

}
