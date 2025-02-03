import java.io.*;
import java.util.*;

public class Main {
    static int[][] cards;
    static double[][][][][][][][][] dp;
    static int parse(char ch){
        if(ch == 'T') return 1;
        else if(ch == 'J') return 2;
        else if(ch == 'Q') return 3;
        else if(ch == 'K') return 4;
        else if(ch == 'A') return 5;
        else return Character.getNumericValue(ch);
    }

    static double operate(int[] step){
        if(dp[step[0]][step[1]][step[2]][step[3]][step[4]][step[5]][step[6]][step[7]][step[8]] == -1){
            List<Integer> list = new ArrayList<>();
            List<Integer> index = new ArrayList<>();
            for(int i = 0; i < 9; i++){
                if(step[i] == 0) continue;
                list.add(cards[i][step[i]]);
                index.add(i);
            }


            if(list.size() == 0){
                dp[step[0]][step[1]][step[2]][step[3]][step[4]][step[5]][step[6]][step[7]][step[8]] = 1.0;
            }
            else{
                double sum = 0;
                int bottom = 0;
                for(int i = 0; i < list.size() - 1; i++) {
                    for (int j = i + 1; j < list.size(); j++) {
                        if(list.get(i) == list.get(j)){
                            bottom++;
                        }
                    }
                }

                for(int i = 0; i < list.size() - 1; i++) {
                    for (int j = i + 1; j < list.size(); j++) {
                        if(list.get(i) == list.get(j)){
                            int[] next = Arrays.copyOf(step, step.length);
                            next[index.get(i)]--;
                            next[index.get(j)]--;
                            sum += (1.0 / bottom) * operate(next);
                        }
                    }
                }
                dp[step[0]][step[1]][step[2]][step[3]][step[4]][step[5]][step[6]][step[7]][step[8]] = sum;
            }


        }
        return dp[step[0]][step[1]][step[2]][step[3]][step[4]][step[5]][step[6]][step[7]][step[8]];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        cards = new int[9][5];
        dp = new double[5][5][5][5][5][5][5][5][5];

        for(int a = 0; a < 5; a++){
            for(int b = 0; b < 5; b++){
                for(int c = 0; c < 5; c++){
                    for(int d = 0; d < 5; d++){
                        for(int e = 0; e < 5; e++){
                            for(int f = 0; f < 5; f++){
                                for(int g = 0; g < 5; g++){
                                    for(int h = 0; h < 5; h++){
                                        for(int k = 0; k < 5; k++){
                                            dp[a][b][c][d][e][f][g][h][k] = -1;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        for(int i = 0; i < 9; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= 4; j++){
                String s = st.nextToken();
                cards[i][j] = parse(s.charAt(0));
            }
        }

        double result = operate(new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4});
        System.out.printf("%.6f", result);


    }
}