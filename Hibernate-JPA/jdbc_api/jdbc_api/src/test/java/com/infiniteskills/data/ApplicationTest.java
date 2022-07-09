package com.infiniteskills.data;

import com.infiniteskills.data.entities.Bank;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.NotYetImplementedException;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.internal.SessionFactoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ApplicationTest {

    @Before
    public void setUp() throws Exception {
        Connection connection= getDbConnJava();
        ScriptUtils.executeSqlScript(connection, new ClassPathResource("populate.sql"));
        // ScriptUtils.executeSqlScript(connection, new ClassPathResource("trunc.sql"));
        connection.close();
    }

    @Test
    public void smoke (){
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction transaction = session.beginTransaction();
        try {
            Bank bank = (Bank) session.get(Bank.class, 1L);
            bank.setName("Something Different");
            System.out.println("Calling Flush");
            session.flush();

            bank.setAddressLine1("Another Address Line");
            System.out.println("Calling commit");
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }finally{
            session.close();
            HibernateUtil.getSessionFactory().close();
        }
    }

    public static Connection getDbConnJava()
            throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ifinances",
                "root", "pekall1234");
    }

    /*
    public static DataSource getMySQLDataSource() {
        Properties props = new Properties();
        FileInputStream fis = null;
        MysqlDataSource mysqlDS = null;
        try {
            fis = new FileInputStream("db.properties");
            props.load(fis);
            mysqlDS = new MysqlDataSource();
            mysqlDS.setURL(props.getProperty("MYSQL_DB_URL"));
            mysqlDS.setUser(props.getProperty("MYSQL_DB_USERNAME"));
            mysqlDS.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mysqlDS;
    }

    public static Connection getDbConnJava()
            throws Exception {
        Properties props = loadProperties();
        Class.forName(props.getProperty("hibernate.connection.driver_class"));
        return DriverManager.getConnection(
                props.getProperty("hibernate.connection.url"),
                props.getProperty("hibernate.connection.username"),
                props.getProperty("hibernate.connection.password"));
    }

    public static Connection getDbConnHibernate() throws SQLException {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        ConnectionProvider cp=((SessionFactoryImpl)factory).getConnectionProvider();
        Connection connection =  cp.getConnection();
        connection.setAutoCommit(true);
        return connection;
    }

    public static Connection getDbConnMySqlDataSource() throws SQLException {
        Properties props = loadHibernateProps();
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser(props.getProperty("hibernate.connection.username"));
        dataSource.setPassword("hibernate.connection.password");
        dataSource.setURL("hibernate.connection.url");

        Connection connection =  dataSource.getConnection();
        connection.setAutoCommit(true);
        return connection;
    }
*/

    public static Connection getDbConnDataSource() {
        /*
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("username");
        dataSource.setPassword("password");
        dataSource.setUrl("jdbc:mysql://<host>:<port>/<database>");
        dataSource.setMaxActive(10);
        dataSource.setMaxIdle(5);
        dataSource.setInitialSize(5);
        dataSource.setValidationQuery("SELECT 1");
        */
        throw new NotYetImplementedException();
    }

    public static Properties loadHibernateProps() {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            String filename = "hibernate.properties";
            input = ApplicationTest.class.getClassLoader().getResourceAsStream(filename);
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally{
            if(input!=null){
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;
    }

    public static Properties loadProperties() throws Exception {
        URL url = ClassLoader.getSystemResource("hibernate.properties");
        try (InputStream in = url.openStream()) {
            Properties p = new Properties();
            p.load(in);
            return p;
        }
    }
}