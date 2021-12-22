package database;

public class Smarthome {

        public String id_smarthome ="1" ;
        public String rua="rua st margarida";
        public String num_porta = "0000";
        public String freguesia = "palmeira";
        public String concelho = "Braga";
        public String codigopostal = "Braga";
        public String pais = "Braga";
        public String ip = "Braga";

    public Smarthome(String id_smarthome, String rua, String num_porta, String freguesia, String concelho, String codigopostal, String pais, String ip) {
        this.id_smarthome = id_smarthome;
        this.rua = rua;
        this.num_porta = num_porta;
        this.freguesia = freguesia;
        this.concelho = concelho;
        this.codigopostal = codigopostal;
        this.pais = pais;
        this.ip = ip;
    }
    public Smarthome(){

    }


    public String  getId_smarthome() {
        return id_smarthome;
    }

    public void setId_smarthome(String id_smarthome) {
        this.id_smarthome = id_smarthome;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNum_porta() {
        return num_porta;
    }

    public void setNum_porta(String num_porta) {
        this.num_porta = num_porta;
    }

    public String getFreguesia() {
        return freguesia;
    }

    public void setFreguesia(String freguesia) {
        this.freguesia = freguesia;
    }

    public String getConcelho() {
        return concelho;
    }

    public void setConcelho(String concelho) {
        this.concelho = concelho;
    }

    public String getCodigopostal() {
        return codigopostal;
    }

    public void setCodigopostal(String codigopostal) {
        this.codigopostal = codigopostal;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
