import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        Map<Integer, Integer> map = new HashMap();
        for(int i = 0; i < n; i++){
            int number = sc.nextInt();
            if(map.containsKey(number)){
                int current = map.get(number);
                map.replace(number, ++current);
            }
            else{
                map.put(number, 1);
            }
        }

        int m = sc.nextInt();

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m; i++){
            int number = sc.nextInt();
            if(map.containsKey(number)){
                sb.append(map.get(number)).append(' ');
            }
            else{
                sb.append(0).append(' ');
            }
        }
        System.out.println(sb);
    }
}
