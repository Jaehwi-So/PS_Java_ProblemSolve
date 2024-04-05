import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();

        Map map = new HashMap<Integer, String>();
        Map reverseMap = new HashMap<String, Integer>();
        int number = 1;
        for(int i = 0; i < n; i++){
            String pokemon = sc.nextLine();
            if(!reverseMap.containsKey(pokemon)){
                map.put(number, pokemon);
                reverseMap.put(pokemon, number);
                number++;
            }
        }

        for(int i = 0; i < m; i++){
            String s = sc.next();
            try{
                int k = Integer.parseInt(s);
                System.out.println(map.get(k));
            }
            catch(NumberFormatException e){
                System.out.println(reverseMap.get(s));
            }

        }
    }
}