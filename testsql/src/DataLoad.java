import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.sql.*;

public class DataLoad {

    /**
     * Main method to Load data from given file to MySQL database
     * the database properties are stored in jdbc.properties
     * execute queries are stored in sqlquery
     * @param args
     */
    public static void main(String[] args) {
        try {
            String path = "sqlquery";
            String sql = readSQLFile(path);
            List<String> sql_arr = getSql(sql);
            DataLoad.execute(getConn(),sql_arr);
            System.out.println("Successfully executed!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Read from SQL file and put into string.
     * @param path SQL file Path
     * @return executable sql query string
     */
    public static String readSQLFile(String path) {
        File file = new File(path);
        if (!file.exists() || file.isDirectory()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        try{
            FileInputStream fis = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            line = br.readLine();
            while (line != null) {
                if (line.length() >= 2) {
                    String str1 = line.substring(0, 1);
                    String str2 = line.substring(0, 2);
                    if (str1.equals("#") || str2.equals("--") || str2.equals("/*") || str2.equals("//")) {
                        line = br.readLine();
                        continue;
                    }
                    sb.append(line + "\r\n");
                }
                line = br.readLine();
            }
            br.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * Divided sql string into query list.
     * @param sql sql string get from file
     * @return query list
     */
    public static List<String> getSql(String sql){
        String s = sql;
        s = s.replaceAll("\r\n", "\r");
        s = s.replaceAll("\r", "\n");
        List<String> res;
        String[] sql_arr = s.split(";");  //separate with ;
        sql_arr = filter(sql_arr);
        res = Arrays.asList(sql_arr);
        return res;
    }


    /**
     * Filter the empty query.
     * @param sql_arr sql query list
     * @return valid query array
     */
    public static String[] filter(String[] sql_arr) {
        List<String> strs = new ArrayList<>();
        for(String s : sql_arr){
            if (s != null && !s.equals("")) {
                strs.add(s);
            }
        }
        String[] result = new String[strs.size()];
        for(int i = 0; i < strs.size(); i++) {
            result[i] = strs.get(i);
        }
        return result;
    }


        /**
         * Load the properties of mysql and connect to DB.
         * @return SQL Connection
         */
    public static Connection getConn() {
        Connection conn = null;
        try {
            Properties prop = new Properties();
            InputStream is = DataLoad.class.getClassLoader().getResourceAsStream("jdbc.properties");
            prop.load(is);
            String USER = prop.getProperty("USER");
            String PASS = prop.getProperty("PASS");
            String DB_URL = prop.getProperty("DB_URL");
            String JDBC_DRIVER = prop.getProperty("JDBC_DRIVER");

            // Register for db driver
            Class.forName(JDBC_DRIVER);

            // Connect to database
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     *
     * @param conn SQL connection
     * @param sqlFile batch of SQL query
     */
    public static void execute(Connection conn, List<String> sqlFile) throws SQLException {
        Statement stmt = null;
        try {
            // initiate Statement and execute query
            stmt = conn.createStatement();
            for (String sql : sqlFile) {
                sql = sql.trim();
                if(!sql.equals("")) {
                   //System.out.println(sql);
                    stmt.addBatch(sql);
                }
            }
            stmt.executeBatch();

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            conn.close();
            e.printStackTrace();
        }
    }


}
