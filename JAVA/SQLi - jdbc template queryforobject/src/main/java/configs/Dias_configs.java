package configs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Dias_configs {
    Dias_de_recuo singletonrecuoData = Dias_de_recuo.getInstance();
    //formatos
    SimpleDateFormat data_yyyyhmmhdd = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat dateandtimeformat_yyyyhmmhdd_hhPmmPss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat hora_hhPmmPss = new SimpleDateFormat("HH:mm:ss");

    //dia 1
    Calendar dia1 = Calendar.getInstance();
    String dia1_yyyymmdd;
    int dia1_month = dia1.get(Calendar.MONTH);



    //dia2
    Calendar dia2 = Calendar.getInstance();
    String dia2_yyyymmdd ;
    int dia2_month = dia2.get(Calendar.MONTH);


    public Dias_configs() throws ParseException {
        int vartmp = (singletonrecuoData.getRecuo())+1;
        //System.out.println("DIAS config:" + singletonrecuoData.getRecuo()+" e "+vartmp);
        dia1.add(Calendar.DATE, singletonrecuoData.getRecuo());
        dia2.add(Calendar.DATE, vartmp);
        dia1_yyyymmdd = data_yyyyhmmhdd.format(dia1.getTime());
        dia2_yyyymmdd = data_yyyyhmmhdd.format(dia2.getTime());
        //"2021-02-23"
        Date data1 = new SimpleDateFormat("yyyy-MM-dd").parse(dia1_yyyymmdd);
        Date data2 = new SimpleDateFormat("yyyy-MM-dd").parse(dia2_yyyymmdd);

        dia1_yyyymmdd = data_yyyyhmmhdd.format(data1);
        dia2_yyyymmdd = data_yyyyhmmhdd.format(data2);
    }



    public String getDia1_yyyymmdd() {
        return dia1_yyyymmdd;
    }

    public void setDia1_yyyymmdd(String dia1_yyyymmdd) {
        this.dia1_yyyymmdd = dia1_yyyymmdd;
    }

    public String getDia2_yyyymmdd() {
        return dia2_yyyymmdd;
    }

    public void setDia2_yyyymmdd(String dia2_yyyymmdd) {
        this.dia2_yyyymmdd = dia2_yyyymmdd;
    }

    public int getDia1_month() {
        return dia1_month;
    }

    public void setDia1_month(int dia1_month) {
        this.dia1_month = dia1_month;
    }

    public int getDia2_month() {
        return dia2_month;
    }

    public void setDia2_month(int dia2_month) {
        this.dia2_month = dia2_month;
    }

    public String convert_StringyyyyHmmHdd_hhPmmPss_em_yyyyHmmHdd(String timestamp) throws ParseException {

        Date yyyymmdd_HHmmss = dateandtimeformat_yyyyhmmhdd_hhPmmPss.parse(timestamp);
        String data_yyyyHmmHdd = data_yyyyhmmhdd.format(yyyymmdd_HHmmss);
        return data_yyyyHmmHdd;
    }

    public String convert_StringyyyyHmmHdd_hhPmmPss_em_hhPmmPss(String timestamp) throws ParseException {

        Date yyyymmdd_HHmmss = dateandtimeformat_yyyyhmmhdd_hhPmmPss.parse(timestamp);
        String hora_hhPmmPss_string = hora_hhPmmPss.format(yyyymmdd_HHmmss);
        return hora_hhPmmPss_string;
    }
}

