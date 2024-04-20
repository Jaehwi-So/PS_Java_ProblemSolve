import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int v = 0;

        if(str.contains("-") == true){
            String start = str.substring(0, str.indexOf('-'));
            String value = str.substring(str.indexOf('-') + 1, str.length());
            if(!start.isEmpty()){
                String[] starts = start.split("[\\+, -]");
                for(int i = 0; i < starts.length; i++){
                    v += Integer.parseInt(starts[i]);
                }
            }

            String[] values = value.split("[\\+, -]");
            for(int i = 0; i < values.length; i++){
                v -= Integer.parseInt(values[i]);
            }
        }
        else{
            String[] values = str.split("[\\+, -]");
            for(int i = 0; i < values.length; i++){
                v += Integer.parseInt(values[i]);
            }
        }

        System.out.println(v);

    }
}
