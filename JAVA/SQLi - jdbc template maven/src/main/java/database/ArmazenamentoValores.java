package database;


import java.util.HashMap;
import java.util.Map;

public class ArmazenamentoValores {


    private Map<String, String> Hora_kwtotal = new HashMap<String, String>();

    public static ArmazenamentoValores instance;

    //Inicio do ingleton
    public ArmazenamentoValores(){

    }
    //Init do singleton
    public static ArmazenamentoValores getInstance(){
        if (instance == null){
            instance = new ArmazenamentoValores();
        }
        return instance;
    }









}
