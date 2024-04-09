import java.util.*;

public class Main {
    static long factorial(long i){
        if(i <= 1){
            return 1;
        }
        return i * factorial(i - 1);
    }

    static long factorialUpper(long i, long k){
        //5 3 -> 5 4 3 / 3 2 1
        if(i == k || k == 0){
            return 1;
        }
        long t = i;
        for(long j = 1; j < k; j++){
            i *= --t;
        }
        return i;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        StringBuilder sb = new StringBuilder();



        for(int i = 0; i < k; i++){
            long n = sc.nextLong();
            long m = sc.nextLong();
            long result;

            if((m-n) < n){
                result = factorialUpper(m, (m-n)) / factorial(m-n);
            }
            else{
                // 5 * 4 * 3 /
                result = factorialUpper(m, n) / factorial(n);
            }


            sb.append(result).append("\n");

        }

        System.out.println(sb);


    }
}
