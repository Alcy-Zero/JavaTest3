//import org.yaml.snakeyaml.Yaml;
//
//import java.io.FileInputStream;
//import java.net.URL;
//import java.sql.*;
//import java.util.Map;
//
//public class App {
//    public static void main(String[] args) {
//        String IP;
//        String PORT;
//        String DATABASE;
//        String USER;
//        String PASSWORD;
//            Yaml yaml = new Yaml();
//            URL url = Test.class.getClassLoader().getResource("D:\\IdeaProjects\\JavaTest3\\docker-compose.yml");
//            if (url != null) {
//                //获取test.yaml文件中的配置数据，然后转换为obj，
//                Object obj =yaml.load(new FileInputStream(url.getFile()));
//                System.out.println(obj);
//                //也可以将值转换为Map
//                Map map =(Map)yaml.load(new FileInputStream(url.getFile()));
//                System.out.println(map);
//                IP = map.get("IP");
//                //通过map我们取值就可以了.
//            }
//        queryCountry(70);
//        queryCustomer(2);
//    }
//
//    public static Connection getConnection(){
//        Connection conn = null;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection("jdbc:mysql://192.168.99.100:3306/sakila","root","123456");
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return conn;
//    }
//
//    public static void queryCountry(int COUNTRY_ID) throws SQLException {
//        String sql ="SELECT city_id,city FROM city WHERE country_id="+COUNTRY_ID;
//        Connection conn = getConnection();
//        Statement st;
//        ResultSet rs = null;
//        try {
//            st = conn.createStatement();
//            rs = st.executeQuery(sql);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        while(rs.next()){
//            System.out.print("city_id:"+rs.getInt("city_id")+"   ");
//            System.out.print("city:"+rs.getString("city")+"   ");
//            System.out.println("   ");
//        }
//    }
//
//    public static void queryCustomer(int CUSTOMER_ID) throws SQLException {
//        String sql ="SELECT inventory_id FROM rental WHERE customer_id="+CUSTOMER_ID;
//        Connection conn = getConnection();
//        Statement st;
//        ResultSet rs = null;
//        try {
//            st = conn.createStatement();
//            rs = st.executeQuery(sql);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
////        while(rs.next()){
////            System.out.print("film_id:"+rs.getInt("film_id")+"   ");
////            System.out.print("film_name:"+rs.getString("film_name")+"   ");
////            System.out.print("rent_time:"+rs.getString("rent_time")+"   ");
////            System.out.println("   ");
////        }
//    }
//
//}
