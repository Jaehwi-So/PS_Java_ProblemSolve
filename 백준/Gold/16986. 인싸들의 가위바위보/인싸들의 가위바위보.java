
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int k;
    static int[][] info;
    static boolean[] visited;
    static int[] win;
    static int[][] actions;
    static boolean success = false;
    static int[] step;

    static void dfs(int home, int bottom){

        for(int i = 0; i < 3; i++){
            if(win[i] == k){
                if(i == 0){
                    success = true;
                }
                return;
            }
        }

        // 나머지 애들끼리 할 때
        if(home != 0 && bottom != 0){
            int w = info[actions[bottom][step[bottom]]][actions[home][step[home]]];
            step[home]++;
            step[bottom]++;
            if(w == 2){
                win[bottom]++;
                dfs(bottom, 0);
                win[bottom]--;
            }
            else if(w == 0){
                win[home]++;
                dfs(home, 0);
                win[home]--;
            }
            else{
                int a = Math.max(home, bottom);
                win[a]++;
                dfs(a, 0);
                win[a]--;
            }
            step[home]--;
            step[bottom]--;
        }

        // 주인공이 경기할때
        else{
            for(int i = 1; i <= n; i++){
                if(!visited[i]){
                    visited[i] = true;
                    int w = 0;
                    if(home == 0){
                        w = info[i][actions[bottom][step[bottom]]];
                        step[bottom]++;
                        if(w == 2){
                            win[home]++;
                            dfs(home, bottom == 1 ? 2 : 1);
                            win[home]--;
                        }
                        else{
                            win[bottom]++;
                            dfs(bottom, bottom == 1 ? 2 : 1);
                            win[bottom]--;
                        }
                        step[bottom]--;
                    }
                    else{
                        w = info[i][actions[home][step[home]]];
                        step[home]++;
                        if(w == 2){
                            win[bottom]++;
                            dfs(bottom, home == 1 ? 2 : 1);
                            win[bottom]--;
                        }
                        else{
                            win[home]++;
                            dfs(home, home == 1 ? 2 : 1);
                            win[home]--;
                        }
                        step[home]--;
                    }
                    visited[i] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        info = new int[n+1][n+1];

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++){
                info[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[n+1];
        win = new int[3];
        actions = new int[3][20];
        step = new int[3];

        for(int i = 1; i < 3; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 20; j++){
                actions[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 1);
        System.out.println(success ? 1 : 0);


    }
}