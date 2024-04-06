import java.util.*;

public class Main {

    //2, 3, 5, 7
    static boolean isPrime(long k){
        
        if(k == 1 || k == 0){
            return false;
        }
        
        //400억
        for(int i = 2; i < (long)Math.sqrt(k) + 1; i++){
            if(k % i == 0){
                return false;
            }
        }
        return true;

    }


    static long calc(long k){
        for(long i = k; i < Long.MAX_VALUE; i++){
            if(isPrime(i)){
                return i;
            }
        }
        return 0;
    }

    //40백억

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for(int i = 0; i < n; i++){
            long k = sc.nextLong();
            System.out.println(calc(k));
        }
    }
}
