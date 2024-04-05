import java.util.Scanner;

public class Main {
    static int calc(int a, int b){
        int k = a;
        int l = b;
        while(true){
            if(a > b){
                b += l;
            }
            else if(a < b){
                a += k;
            }
            else{
                break;
            }
        }
        return a;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int res = calc(a, b);
            sb.append(res).append("\n");
        }
        System.out.println(sb);
    }
}
