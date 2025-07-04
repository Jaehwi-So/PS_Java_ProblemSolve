import java.util.*;

public class Main {
    static int k;
    static int n;
    static char[] numbers;
    static long[][][] dp; //N번째 인덱스, 방문 배열, 증가 완료 여부
    static int[] count;
    static long MAX = (long)Math.pow(10, 18) * 2;
//    static long MAX = 1000000L;

    static int getCount(int visited){
        if(count[visited] == -1){
            int cnt = 0;
            for(int i = 0; i <= 9; i++){
                if((visited & (1 << i)) != 0) cnt++;
            }
            count[visited] = cnt;
        }
        return count[visited];
    }

    static long operate(int index, int visited, int increased){
        if(index == n){
            int count = getCount(visited);
            if(count == k) return 0;
            else return MAX;
        }
        if(dp[index][visited][increased] == -1){
            int current = Character.getNumericValue(numbers[index]);
            int count = getCount(visited); //지금까지 선택된 숫자 개수
            dp[index][visited][increased] = MAX;
            if(increased == 0){ //같거나 큰 수만 선택

                for(int i = current; i <= 9; i++){
                    if((visited & (1 << i)) == 0 && count == k){
                        continue;
                    }
                    if(index == 0 && n == 19 && i != current) break; // 10^18자리인경우.
                    int next = visited | (1 << i);
                    long number = (long)Math.pow(10, n - index - 1) * i;
                    int nextInc = i == current ? 0 : 1;
                    // System.out.println("upper " + index + " " + i + " " + number + " " + operate(index + 1, next, nextInc) + " " + count);
                    number += operate(index + 1, next, nextInc);
                    number = Math.min(MAX, number);

                    dp[index][visited][increased] = Math.min(number, dp[index][visited][increased]);
                }
            }
            else { //(지금까지 선택된 수 + 남은 수) 중 최소 K개 안에서 선택
                int last = k - count;
                // 선택되지 않은 수로 최소값부터 설정
                for (int i = 0; i <= 9; i++) {
                    if (i == 0 && index == 0) continue;
                    long number = (long) Math.pow(10, n - index - 1) * i;
                    if ((visited & (1 << i)) == 0 && last > 0) { //선택되지 않은 수
                        int next = visited | (1 << i);
                        number += operate(index + 1, next, 1);
                        // System.out.println("idx " + index + " " + i + " " + operate(index + 1, next, 1));
                        number = Math.min(MAX, number);
                        dp[index][visited][increased] = Math.min(number, dp[index][visited][increased]);
                        last--;
                    } else if ((visited & (1 << i)) != 0) { //선택된 수
                        int next = visited;
                        number += operate(index + 1, next, 1);
                        // System.out.println("pickidx " + index + " " + i + " " + operate(index + 1, next, 1) + " " + count);
                        number = Math.min(MAX, number);
                        dp[index][visited][increased] = Math.min(number, dp[index][visited][increased]);
                    }
                }
            }
        }
        return dp[index][visited][increased];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String number = sc.next();
        k = sc.nextInt();
        int len = number.length();

        StringBuilder sb = new StringBuilder();
        int increase = 0;
        if(len < k){
            sb.append(1);
            increase = 1;
            len++;
        }
        while(len < k){
            sb.append(0);
            len++;

        }
        sb.append(number);
        number = sb.toString();
        numbers = number.toCharArray();
        n = numbers.length;
        dp = new long[n][1 << 10][2];
        count = new int[1 << 10];
        for(long[][] mat : dp){
            for(long[] line : mat){
                Arrays.fill(line, -1);
            }
        }
        Arrays.fill(count, -1);

        long result = operate(0, 0, increase);

        if(result == MAX){
            sb = new StringBuilder();
            sb.append(1);
            for(int i = 0; i < n; i++){
                sb.append(0);
            }
            number = sb.toString();
            numbers = number.toCharArray();
            n = numbers.length;
            dp = new long[n][1 << 10][2];
            count = new int[1 << 10];
            for(long[][] mat : dp){
                for(long[] line : mat){
                    Arrays.fill(line, -1);
                }
            }
            Arrays.fill(count, -1);
            result = operate(0, 0, 1);
        }

        System.out.println(result);
    }
}