package com.stiggles.smp5.towns;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Town {
    CubeField field;


    public Town (CubeField f) {
        this.field = f;

    }

}
