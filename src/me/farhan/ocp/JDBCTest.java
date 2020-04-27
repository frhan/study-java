package me.farhan.ocp;

import java.sql.Driver;
import java.sql.DriverManager;

public class JDBCTest {

    public static void test1() throws Exception {
        Driver d = DriverManager.getDriver("");

    }
}
