import java.util.*;


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

    static int calc(int k){
        int count = 0;
        for(int i = k + 1; i <= 2*k; i++){
            if(isPrime(i)){
                count++;
            }
        }
        return count;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        while(true){
            int k = sc.nextInt();
            if(k == 0){
                break;
            }
            sb.append(calc(k)).append("\n");

        }

        System.out.println(sb);
    }
}
