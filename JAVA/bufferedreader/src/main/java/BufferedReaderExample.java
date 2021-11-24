import java.io.*;
public class BufferedReaderExample{
        public static boolean name() throws IOException {

        InputStreamReader r=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(r);
        InputStreamReader r2=new InputStreamReader(System.in);
        BufferedReader br2=new BufferedReader(r2);
        System.out.println("Enter your name");
        String name=br.readLine();
        String name2 = br2.readLine();
        if(name==name2){
            return true;
        }
        while (name.equals(name2)){
            System.out.println("Welcome "+name);
            System.out.println("Welcome "+name);

        }
        return false;
    }
    public static void main(String args[])throws Exception {
           Boolean response = name();
           System.out.println(response);
    }
    }