import database.SmarthomeDao;
import database.SmarthomeRowMapper;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import database.ArmazenamentoValores;
import database.Smarthome;
//import database.SmarthomeRowMapper;
import database.mysql.DatabaseMysql;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.awt.image.SampleModel;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * @author andre
 */
public class mainJavafx  {
    @Autowired
    JdbcTemplate jdbcTemplate;
    float temp = 0.0f;
    DatabaseMysql databaseMysql = new DatabaseMysql();
    ArmazenamentoValores av = ArmazenamentoValores.getInstance();
    String resposta;

    public  mainJavafx() throws SQLException, ClassNotFoundException {
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException, JSONException, InterruptedException, ParseException, IOException {


            one();


    }
    static public void one() throws SQLException, ClassNotFoundException, JSONException, InterruptedException, ParseException, IOException {
        System.out.println("static method");
//two(); --> this will give you an error bcoz method two is non-static method
        mainJavafx m = new mainJavafx();
        m.inicio1();
    }
    public  void inicio1() throws SQLException, ParseException, JSONException, IOException, ClassNotFoundException, InterruptedException {
        databaseMysql.resetDBCentral();
        databaseMysql.createTablesCentral();
        ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
        SmarthomeDao dao=(SmarthomeDao) ctx.getBean("edao");
        SmarthomeDao smarthomeDao = new SmarthomeDao();

        int status=dao.saveEmployee(new Smarthome("1","Amit","braga","braga","braga","braga","braga","braga"));
        dao.saveEmployee(new Smarthome("2","Amit","braga","braga","braga","braga","braga","braga"));

        System.out.println("status: "+ status);

        SimpleDriverDataSource ds = new SimpleDriverDataSource();
        ds.setDriver(new com.mysql.jdbc.Driver());
        ds.setUrl("jdbc:mysql://127.0.0.1:3306/LocalDatabase?characterEncoding=latin1");
        ds.setUsername("root");
        ds.setPassword("password");
        JdbcTemplate jtm = new JdbcTemplate(ds);
        //String sql = "SELECT * from smarthome where id_smarthome = ? ";
        //Smarthome car = (Smarthome) jtm.queryForObject(sql, new Object[]{"1' or '1' ='1"}, new SmarthomeRowMapper());
        String sql = String.format("SELECT * from smarthome where id_smarthome = ?");
        String id = "1";
        Smarthome smarthome = new Smarthome();
        SqlParameterSource named = new BeanPropertySqlParameterSource(smarthome);
        String car = (String)jtm.queryForObject(sql, new Object[]{"1' or '1' ='1"}, String.class);
        System.out.println(car);
        System.out.println(sql);
        //System.out.println(value);
        //GET os dados da smarthome ao servidor central
    }
}


