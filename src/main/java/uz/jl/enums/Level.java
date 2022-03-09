package uz.jl.enums;

import uz.jl.utils.Print;

import java.awt.datatransfer.FlavorEvent;

public enum Level {
    EASY, MEDIUM, HARD;

    public static Level getLevel(String s) {
        for (Level value : values()) {
            if (value.toString().equalsIgnoreCase(s)) return value;
        }
        return MEDIUM;
    }
    public static void getLevels() {
        for (Level value : values()) {
            Print.println( value.name() );
        }
    }


}
