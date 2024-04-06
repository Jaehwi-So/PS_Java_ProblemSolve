import java.util.Scanner;

public class Main {

    static boolean isPrime(long k){

        if(k == 1 || k == 0){
            return false;
        }

        for(int i = 2; i < (long)Math.sqrt(k) + 1; i++){
            if(k % i == 0){
                return false;
            }
        }
        return true;

    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();

        StringBuilder sb = new StringBuilder();
        for(int i = m; i <= n; i++){
            if(isPrime(i)){
                sb.append(i).append("\n");
            }
        }
        System.out.println(sb);
    }
}
