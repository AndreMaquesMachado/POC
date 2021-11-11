package determinateRandom;

public class RandomDivisao {
    RandomOperations ro = new RandomOperations();
    String divisao;
    String divisao_roupa;
    public String get_divisao(){
        Integer decisao = ro.decisao(13);
        if (decisao==0){
            divisao="Sala de jantar";
        }else  if (decisao==1){
            divisao="Cozinha";
        }else if (decisao==2){
            divisao="Sala de estar";
        }else if (decisao==3){
            divisao="Escritório";
        }else if (decisao==4){
            divisao="Quarto principal";
        }else if (decisao==5){
            divisao="Quarto bebé";
        }else if (decisao==6){
            divisao="Quarto do filho mais novo";
        }else if (decisao==7){
            divisao="Quarto do filho mais velho";
        }else if (decisao==8){
            divisao="Quarto de hóspedes ";
        }else if (decisao==9){
            divisao="Escritório secundário";
        }else if (decisao==10){
            divisao="Salão de jogos";
        }else if (decisao==11){
            divisao="Ginásio";
        }else {
            divisao="Espaço aberto entre divisões";
        }
        return  divisao;
    }

    public String getDivisaoroupa(){
        Integer decisao = ro.decisao(2);
        if (decisao==0){
            divisao_roupa="Marquise";
        }else {
            divisao_roupa="Sala das máquinas";

        }
        return divisao_roupa;
    }
}
