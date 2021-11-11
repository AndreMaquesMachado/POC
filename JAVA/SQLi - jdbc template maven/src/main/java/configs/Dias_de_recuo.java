package configs;

public class Dias_de_recuo {
    public static Dias_de_recuo instance;
    public Dias_de_recuo(){
    }
    //Init do singleton
    public static Dias_de_recuo getInstance(){
        if (instance == null){
            instance = new Dias_de_recuo();
        }
        return instance;
    }
    int recuo = -1 ;


    public int getRecuo() {
        return recuo;
    }

    public void setRecuo(int recuo) {
        this.recuo = recuo;
    }
}
