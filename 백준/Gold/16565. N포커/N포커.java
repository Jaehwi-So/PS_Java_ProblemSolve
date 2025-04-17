import java.util.Scanner;

public class Main {
    static final int MOD = 10007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[][] comb = new int[53][53]; // comb[a][b] = aCb

        comb[1][0] = 1;
        comb[1][1] = 1;

        for(int i = 1; i <= 52; i++){
            for(int j = 0; j <= i; j++){
                if(j == 0 || j == i){
                    comb[i][j] = 1;
                }
                else{
                    comb[i][j] = comb[i-1][j-1] + comb[i-1][j] % MOD;
                }
            }
        }

        int[][][] cases = new int[14][n+1][2]; // dp[a][b][c] = a종류의 카드로 b개를 뽑을 수 있는 조합의 수 (c = 0 : 정답 미포함, c = 1 : 정답 포함)

        for(int i = 1; i <= 13; i++){
            for(int j = 1; j <= n; j++){
                if(i * 4 < j) break;
                int total = comb[i*4][j]; //전체 경우의수
                int correct = 0;
                for(int k = 1; k <= j / 4; k++){ //정답(k*4개) * 나머지 (j-(k*4))개의 카드의 정답X경우의수
                    if(k * 4 == j){
                        correct = (correct + comb[i][k]) % MOD;
                    }
                    else{
                        correct = (correct + (comb[i][k] * cases[i-k][j-(k*4)][0])) % MOD;
                    }


                }
                cases[i][j][0] = total - correct;
                cases[i][j][1] = correct;
            }
        }

        int answer = cases[13][n][1];
        System.out.println(answer);

    }
}