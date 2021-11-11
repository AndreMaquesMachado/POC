package configs;

public class ConfiguracoesIniciais_id_iplocal {
    String id = "1";
    String idfortherequest = "1";
    String IPlocal = "192.168.1.75:8080";
    Double nmax = 1.0;


    public ConfiguracoesIniciais_id_iplocal(){

    }


    public String getId() {
        return id;
    }
    public String getIdfortherequest() {
        return idfortherequest;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getIPlocal() {
        return IPlocal;
    }

    public void setIPlocal(String IPlocal) {
        this.IPlocal = IPlocal;
    }

    public Double getNmax() {
        return nmax;
    }

    public void setNmax(Double nmax) {
        this.nmax = nmax;
    }
}

