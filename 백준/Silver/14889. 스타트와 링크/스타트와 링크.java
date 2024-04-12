import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] chart;
    static int n;
    static int[] visit;
    static int min = Integer.MAX_VALUE;



    static void result(){
        int linkVal = 0;
        int startVal = 0;
        for(int i = 0; i < n - 1; i++){
            for(int j = i + 1; j < n; j++){
                if(visit[i] == visit[j]){
                    if(visit[i] == 1){
                        startVal += chart[i][j];
                        startVal += chart[j][i];
                    }
                    else{
                        linkVal += chart[i][j];
                        linkVal += chart[j][i];
                    }
                }
            }
        }
        min = Math.min(min, Math.abs(startVal - linkVal));
    }

    static boolean getResult(int index, int step){

        if(step == (n / 2)){
            result();
            return true;
        }

        //1번 친구가 누구랑 할 것인가
        for(int i = index; i < n; i++){
            if(visit[i] == 0){
                visit[i] = 1;
                getResult(i + 1, step + 1);
                visit[i] = 0;
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        chart = new int[n][n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                chart[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        visit = new int[n];
        Arrays.fill(visit, 0);
        visit[0] = 1;
        getResult(1, 1);
        System.out.println(min);
    }
}
