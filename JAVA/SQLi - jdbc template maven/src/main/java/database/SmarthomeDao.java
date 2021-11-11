package database;
import org.springframework.jdbc.core.JdbcTemplate;
import database.Smarthome;
public class SmarthomeDao {
private JdbcTemplate jdbcTemplate;

public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
}


public int saveEmployee(Smarthome e){
	String query="insert into smarthome values('"+e.getId_smarthome()+"','"+e.getRua()+"','"+e.getNum_porta()+"','"+e.getFreguesia()+"','"+e.getConcelho()+"','"+e.getCodigopostal()+"','"+e.getPais()+"','"+e.getIp()+"')";
	return jdbcTemplate.update(query);
}
public int updateEmployee(Smarthome e){
	String query="update smarthome set name='"+e.getId_smarthome()+"',salary='"+e.getRua()+"' where id='"+e.getConcelho()+"' ";
	return jdbcTemplate.update(query);
}
public int deleteEmployee(Smarthome e){
	String query="delete from smarthome where id='"+e.getId_smarthome()+"' ";
	return jdbcTemplate.update(query);
}

}
