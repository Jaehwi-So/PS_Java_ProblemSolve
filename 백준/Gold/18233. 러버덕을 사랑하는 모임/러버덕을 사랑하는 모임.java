import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static boolean[] visited;
    static int[] result;
    static int[][] array;
    static int n;
    static int p;
    static int e;
    static boolean success = false;
    static StringBuilder sb = new StringBuilder();

    static void getResult(){
        int count = e;
        for(int i = 0; i < result.length; i++){
            if(visited[i] == true){
                result[i] = array[i][0];
                count -= array[i][0];
            }
        }
        for(int i = 0; i < result.length; i++){
            if(visited[i] == true && count > 0){
                if(array[i][1] - array[i][0] <= count){
                    result[i] = array[i][1];
                    count -= (array[i][1] - array[i][0]);
                }
                else{
                    result[i] += count;
                    count = 0;
                }
            }
            sb.append(result[i]).append(" ");
        }
    }
    static void backtrack(int step, int index){
        if(success == true){
            return;
        }
        if(step == p){
            int min = 0;
            int max = 0;
            for(int i = 0 ; i < n; i++){
                if(visited[i] == true){
                    min += array[i][0];
                    max += array[i][1];
                }
            }
            if(min <= e && max >= e){
                getResult();
                success = true;
            }
        }
        else{
            for(int i = index; i < n; i++){
                if(visited[i] == false && success == false){
                    visited[i] = true;
                    backtrack(step + 1, i);
                    visited[i] = false;
                }
            }
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        array = new int[n][2];
        visited = new boolean[n];
        result = new int[n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            array[i][0] = Integer.parseInt(st.nextToken());
            array[i][1] = Integer.parseInt(st.nextToken());
        }


        if(n < p){
            System.out.println(-1);
            return;
        }

        backtrack(0, 0);

        if(success == false){
            System.out.println(-1);
        }
        else{
            System.out.println(sb);
        }
    }
}
