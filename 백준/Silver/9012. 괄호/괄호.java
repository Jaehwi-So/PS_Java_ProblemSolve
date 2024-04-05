import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            char[] chs = sc.nextLine().toCharArray();
            int temp = 0;
            for(char c : chs){
                if(c == '('){
                    temp++;
                }
                else{
                    if(temp <= 0){
                        temp = -1;
                        break;
                    }
                    else{
                        temp--;
                    }
                }
            }
            if(temp == 0){
                sb.append("YES").append("\n");
            }
            else{
                sb.append("NO").append("\n");
            }

        }

        System.out.println(sb);
    }
}
