package database.mysql;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import configs.ConfiguracoesIniciais_id_iplocal;
import determinateRandom.numberOperations.Specifications;

import java.sql.*;
import java.text.ParseException;

public class DatabaseMysql {
    ConfiguracoesIniciais_id_iplocal configuracoesIniciaisIdiplocal = new ConfiguracoesIniciais_id_iplocal();

    Integer x,id_md,id_ms,id_medicaofonteenergia;
    private Connection connec = null;
    public Statement st = null;
    public ResultSet rs = null;
    public PreparedStatement ps = null;
    private static final String SQLPath = "jdbc:mysql://localhost/LocalDatabase?characterEncoding=latin1&useConfigs=maxPerformance";
    //?autoReconnect=true&useSSL=false&serverTimezone=Europe/Lisbon
    private static final String SQLuser = "root";
    private static final String SQLpass = "password";

    public DatabaseMysql() throws ClassNotFoundException, SQLException {
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
//              connec = DriverManager.getConnection(SQLPath,SQLuser,SQLpass);
            //Class.forName("com.mysql.jdbc.Driver");// include this line in your code.
            Class.forName("com.mysql.jdbc.Driver");

            connec = DriverManager.getConnection(SQLPath, SQLuser, SQLpass);
            connec.createStatement();
            System.out.println("Aqui "+ connec);
            System.out.println("Connection to Database");
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } finally{
            /*This block should be added to your code
             * You need to release the resources like connections
             */
            if(connec!=null)
                connec.close();
        }
    }

    public void resetDBCentral() throws SQLException {
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
//              connec = DriverManager.getConnection(SQLPath,SQLuser,SQLpass);
            //Class.forName("com.mysql.jdbc.Driver");// include this line in your code.
            Class.forName("com.mysql.jdbc.Driver");

            connec = DriverManager.getConnection(SQLPath, SQLuser, SQLpass);
            connec.createStatement();
            System.out.println("Connection to Database");

            try {
                st = connec.createStatement();
                st.execute("drop database if exists LocalDatabase");
                st.execute("create database LocalDatabase");
                st.execute("use LocalDatabase");

            } catch (SQLException e) {
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("VendorError: " + e.getErrorCode());
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally{
            /*This block should be added to your code
             * You need to release the resources like connections
             */
            if(connec!=null)
                connec.close();
        }


    }

    public void createTablesCentral() throws SQLException, ClassNotFoundException {
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
//              connec = DriverManager.getConnection(SQLPath,SQLuser,SQLpass);
            //Class.forName("com.mysql.jdbc.Driver");// include this line in your code.
            Class.forName("com.mysql.jdbc.Driver");

            connec = DriverManager.getConnection(SQLPath, SQLuser, SQLpass);
            connec.createStatement();
            System.out.println("A criar as tabelas de base de dados.");
            //tabela do smarthome
            //Tabela de utilizador_smarthome


            //Tabela de operador
            st = connec.createStatement();
            String localSmarthome =
                    "create table smarthome" +
                            "(id_smarthome varchar(255) not null ," +
                            "rua varchar(255) not null," +
                            "num_porta varchar(255) not null," +
                            "freguesia varchar(255) not null," +
                            "concelho varchar(255) not null," +
                            "codigopostal varchar(255) not null," +
                            "pais varchar(255) not null," +
                            "ip varchar(255) not null," +
                            "primary key(id_smarthome))";

            ;
            System.out.println("Tabela localSmarthome criada");
            st.executeUpdate(localSmarthome);
            st = connec.createStatement();


            st = connec.createStatement();

            String localmedicaoglobal =
                    "create table medicaosmarthome" +
                            "(id_ms int not null auto_increment," +
                            "valor float not null," +
                            "timestamp timestamp not null," +
                            "medicaonormal int not null," +
                            "medicaoalgoritmo int not null," +
                            "medicaobateria int not null," +
                            "id_smarthome varchar(255) not null," +
                            "primary key(id_ms)," +
                            "foreign key(id_smarthome) references smarthome(id_smarthome) on update cascade on delete cascade)";
            System.out.println("Tabela utilizador criada");
            st.executeUpdate(localmedicaoglobal);
            st = connec.createStatement();

            String localtomadas =
                    "create table tomadas" +
                            "(id_tomadas int not null auto_increment," +
                            "estado varchar(255) not null," +
                            "localizacao varchar(255) not null," +
                            "nome varchar(255) not null," +
                            "tipoaparelho varchar(255) not null," +
                            "ip varchar(255) not null," +
                            "id_smarthome varchar(255) not null," +
                            "primary key(id_tomadas)," +
                            "foreign key(id_smarthome) references smarthome(id_smarthome) on update cascade on delete cascade)";
            System.out.println("Tabela tomadas criada");
            st.executeUpdate(localtomadas);

            st = connec.createStatement();
            String localmedicaoindividual =
                    "create table medicaodispositivo" +
                            "(id_md int not null auto_increment," +
                            "valor float not null," +
                            "timestamp timestamp not null," +
                            "medicaonormal int not null," +
                            "medicaoalgoritmo int not null," +
                            "medicaobateria int not null," +
                            "id_tomadas int not null," +
                            "primary key(id_md)," +
                            "foreign key(id_tomadas) references tomadas(id_tomadas) on update cascade on delete cascade)";
            System.out.println("Tabela medição individual");
            st.executeUpdate(localmedicaoindividual);
            st = connec.createStatement();


            st = connec.createStatement();

            String localfonteenergia =
                    "create table medicaoFonteEnergia" +
                            "(id_medicaofonteenergia int not null auto_increment," +
                            "nome varchar(255) not null," +
                            "id_smarthome varchar(255) not null," +
                            "primary key(id_medicaofonteenergia)," +
                            "foreign key(id_smarthome) references smarthome(id_smarthome) on update cascade on delete cascade)";
            System.out.println("Tabela fonte de energia criada");
            st.executeUpdate(localfonteenergia);
            st = connec.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
