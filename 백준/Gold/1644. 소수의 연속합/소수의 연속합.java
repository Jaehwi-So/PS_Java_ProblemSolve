import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        boolean[] isPrime = new boolean[n+1];
        Arrays.fill(isPrime, true);
        isPrime[1] = false;
        List<Integer> prime = new ArrayList<>();

        // 소수 판별 및 배수 제거
        for (int i = 2; i * i <= n; i++) { // i의 제곱이 n 이하인 경우만 확인
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false; 
                }
            }
        }

        // 결과를 리스트로 변환
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                prime.add(i);
            }
        }

        long[] dp = new long[prime.size() + 1];
        for(int i = 1; i < dp.length; i++){
            dp[i] = dp[i-1] + prime.get(i-1);
        }

        int result = 0;
        int left = 0;
        int right = 1;
        while(left < right){
            if(right == dp.length || left == dp.length){
                break;
            }
            long current = dp[right] - dp[left];
            if(current == n){
                result++;
                left++;
                right++;
            }
            else if(current < n){
                right++;
            }
            else{
                left++;
            }
        }
//        System.out.println(prime);
//        System.out.println(dp[prime.size()]);
        System.out.println(result);
    }
}