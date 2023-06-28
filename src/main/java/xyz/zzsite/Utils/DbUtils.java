package xyz.zzsite.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbUtils {

    private static final String JDBC_DRIVER = "xyz.zzsite.Utils.DbUtils";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/logistics?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "111111";

    public static void insertData(String tableName, String key, long count) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            String sql = "INSERT INTO " + tableName + " (keyname, count) VALUES (?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, key);
            stmt.setLong(2, count);
            stmt.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void initShoppingMode(String tableName, String key, double count) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            String sql = "INSERT INTO " + tableName + " (keyname, count) VALUES (?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, key);
            stmt.setDouble(2, count);
            stmt.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void initOrderProfit(String tableName, String key, double count) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            String sql = "INSERT INTO " + tableName + " (keyname, count) VALUES (?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, key);
            stmt.setDouble(2, count);
            stmt.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    public static void initOrderCountry(String tableName, String key, long count) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            String sql = "INSERT INTO " + tableName + " (keyname, count) VALUES (?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, key);
            stmt.setLong(2, count);
            stmt.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void initDaysForShapping(String tableName, String key, long count) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            String sql = "INSERT INTO " + tableName + " (keyname, count) VALUES (?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, key);
            stmt.setLong(2, count);
            stmt.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
