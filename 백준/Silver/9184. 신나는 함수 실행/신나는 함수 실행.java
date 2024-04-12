import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static Map<String, Integer> map = new HashMap<>();
    static int w(int a, int b, int c){
        if(a <= 0 || b <= 0 || c <= 0){
            return 1;
        }

        if(a > 20 || b > 20 || c > 20){
            return w(20, 20,20);
        }

        String s = String.format("w(%d, %d, %d)", a, b, c);
        if(map.containsKey(s)){
            return map.get(s);
        }
        else{
            if(a < b && b < c){
                map.put(s, w(a, b, c-1) + w(a, b-1, c-1) - w(a, b-1, c));
                return map.get(s);
            }

            map.put(s, w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - + w(a-1, b-1, c-1));
            return map.get(s);
        }



    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        while(true){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            if(a == -1 && b == -1 && c == -1){
                break;
            }
            sb.append(String.format("w(%d, %d, %d) = %d", a, b, c, w(a,b,c))).append("\n");
        }
        System.out.println(sb);

    }
}
