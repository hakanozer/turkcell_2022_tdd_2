package com.works.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class Util {

    public Connection conn = null;
    public Connection con() {
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:file:~/turkcell_tdd_2","sa","sa");
            return conn;
        }catch (Exception ex) {
            System.err.println("DB Error : " + ex);
            return null;
        }
    }

    public boolean close() {
        try {
            if ( conn != null && conn.isClosed() ) {
                return true;
            }else {
                return false;
            }
        }catch (Exception ex) {
            System.err.println("Close Error : " + ex);
            return false;
        }
    }

}
