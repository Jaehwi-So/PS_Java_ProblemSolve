import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] graph;
    static boolean[] visited;
    static boolean[] result;
    static int count;

    static void dfs(int index){
        // 이미 방문 -> 싸이클 형성
        if(visited[index]){
            result[index] = true;
            count++;
        }
        // 방문 처리
        else{
            visited[index] = true;
        }

        // 해당 학생이 선택한 학생이 구성을 마치지 않은 경우 탐색
        if(!result[graph[index]]){
            dfs(graph[index]);
        }

        visited[index] = false; // 방문 처리 초기화
        result[index] = true; // 구성여부 처리 완료

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for(int l = 0; l < t; l++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());

            result = new boolean[n+1];
            visited = new boolean[n+1];
            graph = new int[n+1];

            for(int i = 1; i <= n; i++){
                graph[i] = Integer.parseInt(st.nextToken());
            }

            count = 0;
            for(int i = 1; i <= n; i++){
                if(result[i] == false){
                    dfs(i);
                }
            }
            sb.append(n - count).append("\n");
        }
        System.out.println(sb);

    }
}
