import java.io.*;
import java.util.*;

public class Main {
    static int len;
    static int n;
    static int k;
    static String[] numbers; // n+1
    static long[][] dp; //방문한 것, 1~100 = 개수 // Math.pow(2, n) * 101
    static int[] digits = new int[1001]; //뒤의 자릿수
    static int[] numbersMod;

    static int calcMod(String number){
        if(number.length() < 10){
            return Integer.parseInt(number) % k;
        }
        int mod = 0;
        for (int i = 0; i < number.length(); i++) {
            mod = (mod * 10 + (number.charAt(i) - '0')) % k;
        }
        return mod;
    }

    static long operate(int vidx, int mod){
        if(dp[vidx][mod] == -1){
            dp[vidx][mod] = 0;
            int seq = 0;
            for(int i = 1; i <= n; i++){
                if((vidx & (1 << i - 1)) != 0) seq += numbers[i].length();
            }
            for(int i = 1; i <= n; i++){
                if((vidx & (1 << i - 1)) != 0) continue;
                String num = numbers[i];
                int cmod = 0;
                if(seq == 0) cmod = numbersMod[i];
                else cmod = (numbersMod[i] * digits[seq]) % k;

                if(seq + num.length() == len){
                    if(mod == cmod) dp[vidx][mod] = 1;
                }
                else{
                    int v = (int)Math.pow(2, i-1);
                    int next = 0;
                    if(cmod > mod) next = mod + (k - cmod);
                    else if (cmod < mod) next = mod - cmod;
                    dp[vidx][mod] += operate(vidx + v, next);
                }
            }
//            System.out.println(seq + " " + Arrays.toString(visited) + " " + mod);
//            System.out.println(dp[seq][vidx][mod]);
        }
        return dp[vidx][mod];
    }

    static long factorial(int num){
        if(num == 1) return 1;
        else{
            return num * factorial(num - 1);
        }
    }
    static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        numbers = new String[n+1];
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            numbers[i] = st.nextToken();
            len += numbers[i].length();
        }
        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());

        int current = 10;
        for(int i = 1; i <= 5; i++){
            digits[i] = current % k;
            current *= 10;
        }

        for(int i = 6; i <= 1000; i++){
            digits[i] = ((100 % k) * (digits[i-2] % k)) % k;
        }

        numbersMod = new int[n+1];
        for(int i = 1; i <= n; i++){
            numbersMod[i] = calcMod(numbers[i]);
        }

        dp = new long[(int)Math.pow(2, n)][101];
        for(long[] line : dp){
            Arrays.fill(line, -1);
        }

        long child = operate(0, 0);
        long parent = factorial(n);

        if(child == 0) System.out.println("0/1");
        else{
            long g = gcd(child, parent);
            System.out.println(child / g + "/" + parent / g);
        }
    }
}