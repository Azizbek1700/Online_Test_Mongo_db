package uz.jl.enums;

public enum Language {
    UZ("UZ"), RU("RU"), EN("EN");

    Language(String code) {
    }

    public static Language getLanguage(String s) {
        for (Language value : values()) {
            if (value.toString().equalsIgnoreCase(s)) return value;
        }
        return RU;
    }

    public static void getLan() {
        int i=1;
        for (Language value : values()) {
            System.out.println(i++ +"."+value.name());
        }
    }
}
