package com.stiggles.smp5;

public class Colors {
    public static final String[] colors = {
           "WHITE",       //  0
            "LIGHT_GRAY", //  1
            "GRAY",       //  2
            "BLACK",      //  3
            "BROWN",      //  4
            "RED",        //  5
            "ORANGE",     //  6
            "YELLOW",     //  7
            "LIME",       //  8
            "GREEN",      //  9
            "CYAN",       //  10
            "LIGHT_BLUE", //  11
            "BLUE",       //  12
            "PURPLE",     //  13
            "MAGENTA",    //  14
            "PINK"        //  15
    };

    public static String getColor (int index) {
        if (index < colors.length)
            return null;
        return colors[index];
    }
}
