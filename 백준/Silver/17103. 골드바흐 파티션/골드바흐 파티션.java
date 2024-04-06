import java.util.*;

public class Main {

    static boolean[] primes;
    static int max;


    // 에라토스테네스의 체
    static void setPrime(){

        Arrays.fill(primes, true);
        primes[0] = primes[1] = false;

        for(int i = 2; i < Math.sqrt(max) + 1; i++){
            if(primes[i]){
                for(int j = i * 2; j <= max; j += i){
                    primes[j] = false;
                }
            }
        }
    }

    static int goldBPath(int k){
        int count = 0;
        for(int i = 2; i < (k / 2) + 1; i++){
            int j = k - i;

            if(primes[i] && primes[j]){
                count++;
            }
        }
        return count;

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();


        StringBuilder sb = new StringBuilder();
        int[] numbers = new int[n];
        for(int i = 0; i < n; i++){
            int k = sc.nextInt();
            numbers[i] = k;
        }

        max = Arrays.stream(numbers).max().getAsInt();
        primes = new boolean[max + 1];
        setPrime();

        for(int i = 0; i < n; i++){
            sb.append(goldBPath(numbers[i])).append("\n");
        }

        System.out.println(sb);

    }
}
