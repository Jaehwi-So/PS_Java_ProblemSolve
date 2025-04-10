import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] sequence;
    static int[] answer;
    static int[][][][] dp;   // 현재 번호, 현재 상태, 두번째 상태, 세번째 상태

    static int getDiff(int number, int origin, int dir){
        int count = 0;
        if(dir == 0){ //감소
            count = (number - origin + 10) % 10;
        }
        else{ //증가
            count = (origin - number + 10) % 10;
        }
        return count;
    }

    static int operate(int index, int a, int b, int c){
        if(index > n) return 0;
        a = (a + 10) % 10;
        b = (b + 10) % 10;
        c = (c + 10) % 10;

        if(dp[index][a][b][c] == -1){

            if(answer[index] == a){
                dp[index][a][b][c] = operate(index + 1, b, c, sequence[index + 3]);
            }
            else{
                int next = Integer.MAX_VALUE;
                for(int d = 0; d <= 1; d++){
                    int range = getDiff(a, answer[index], d);
                    int dir = d == 0 ? -1 : 1;
                    for(int i = 0; i <= range; i++){ //두번째 칸이 움직일 수 있는 범위
                        for(int j = 0; j <= i; j++){ //세번째 칸이 움직일 수 있는 범위
                            int cnt = 0;
                            cnt += Math.ceil((range - i) / 3.0);
                            cnt += Math.ceil((i - j) / 3.0);
                            cnt += Math.ceil(j / 3.0);
                            next = Math.min(next, operate(index + 1,b + (dir * i), c + (dir * j), sequence[index+3]) + cnt);
                        }
                    }
                }

                dp[index][a][b][c] = next;
            }
        }

        return dp[index][a][b][c];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        sequence = new int[n+4];
        answer = new int[n+1];

        st = new StringTokenizer(br.readLine());
        char[] chs = st.nextToken().toCharArray();
        st = new StringTokenizer(br.readLine());
        char[] chs2 = st.nextToken().toCharArray();
        for(int i = 1; i <= n; i++){
            sequence[i] = Character.getNumericValue(chs[i-1]);
            answer[i] = Character.getNumericValue(chs2[i-1]);
        }

        dp = new int[n+1][10][10][10];

        for(int[][][] m : dp){
            for(int[][] mat : m){
                for(int[] line : mat){
                    Arrays.fill(line, -1);
                }
            }
        }


        int result = operate(1, sequence[1], sequence[2], sequence[3]);
        System.out.println(result);


    }
}