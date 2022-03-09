package uz.jl.enums;

import uz.jl.utils.Print;

import javax.management.remote.JMXPrincipal;
import java.awt.*;

public enum Category {
    MATH, CHEMISTRY, GEOGRAPHY, ENGLISH, UNDEFINED;

    public static Category getCategory(String s) {
        for (Category value : values()) {
            if (value.toString().equalsIgnoreCase(s)) return value;
        }
        return UNDEFINED;
    }

       public static void getC( ) {
        for (Category value : values()) {
            if (!value.name().equalsIgnoreCase( "UNDEFINED" )){
                Print.println(value.name());
            }

        }
    }


}
