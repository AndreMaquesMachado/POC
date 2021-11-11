package database;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SmarthomeRowMapper implements RowMapper<Smarthome> {

    public Smarthome mapRow(ResultSet rs, int i) throws SQLException {
        Smarthome smarthome = new Smarthome ();
        smarthome.setId_smarthome(rs.getString("id_smarthome"));
        smarthome.setRua(rs.getString("rua"));
        smarthome.setNum_porta(rs.getString("num_porta"));
        smarthome.setFreguesia(rs.getString("freguesia"));
        smarthome.setConcelho(rs.getString("concelho"));
        smarthome.setCodigopostal(rs.getString("codigopostal"));
        smarthome.setPais(rs.getString("pais"));
        smarthome.setIp(rs.getString("ip"));

        return smarthome;
    }



}


