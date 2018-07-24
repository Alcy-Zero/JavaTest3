import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.*;
import java.util.Map;

public class App {

    private static String IP;
    private static int PORT;
    private static String DATABASE;
    private static String USER;
    private static String PASSWORD;



    public static void main(String[] args) throws FileNotFoundException, SQLException, ClassNotFoundException {
        System.out.println(System.getenv("IP"));
        Yaml yaml = new Yaml();
        URL url = Yaml.class.getClassLoader().getResource("docker-compose.yml");
        int COUNTRY_ID = 0;
        int CUSTOMER_ID = 0;
        if (url != null) {
            //获取test.yaml文件中的配置数据，然后转换为obj，
            Object obj =yaml.load(new FileInputStream(url.getFile()));
            System.out.println(obj);
            //也可以将值转换为Map
            Map map =(Map)yaml.load(new FileInputStream(url.getFile()));
            System.out.println(map);
            Map services = (Map) map.get("services");
            Map mysql = (Map) services.get("mysql");
            if(mysql!=null) {
                Map env1 = (Map) mysql.get("environment");
                IP = (String) env1.get("IP");
                System.out.println(IP);
                PORT = (int) env1.get("PORT");
                System.out.println(PORT);
                DATABASE = (String) env1.get("DATABASE");
                System.out.println(DATABASE);
                USER = (String) env1.get("USER");
                System.out.println(USER);
                PASSWORD = (String) env1.get("PASSWORD");
                System.out.println(PASSWORD);
            } else {
                System.out.println("未读取mysql配置");
            }
            Map java = (Map) services.get("java");
            if(java!=null) {
                Map env2 = (Map) java.get("environment");
                COUNTRY_ID = (int) env2.get("COUNTRY_ID");
                CUSTOMER_ID = (int) env2.get("CUSTOMER_ID");
            } else {
                System.out.println("未读取java配置");
            }
        }

        queryCountry(COUNTRY_ID);
        queryCustomer(CUSTOMER_ID);
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://"+IP+":"+PORT+"/"+DATABASE,USER,PASSWORD);
        return conn;
    }

    public static void queryCountry(int COUNTRY_ID) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        String sql ="SELECT city_id,city FROM city WHERE country_id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,COUNTRY_ID);
        ResultSet rs = null;
        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while(rs.next()){
            System.out.print("city_id:"+rs.getInt("city_id")+"   ");
            System.out.print("city:"+rs.getString("city")+"   ");
            System.out.println("   ");
        }
    }

    public static void queryCustomer(int CUSTOMER_ID) throws SQLException, ClassNotFoundException {
        String sql1 ="select r.rental_date,i.film_id,f.title " +
                "from rental as r,inventory as i,film as f "+
                "where r.inventory_id=i.inventory_id " +
                "and i.film_id=f.film_id " +
                "and r.customer_id=? " +
                "order by rental_date desc";
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql1);
        ps.setInt(1,CUSTOMER_ID);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            System.out.print(rs.getInt("rental_date")+"   ");
            System.out.print(rs.getString("film_id")+"   ");
            System.out.print(rs.getString("title")+"   ");
            System.out.println("   ");
        }
    }

}
