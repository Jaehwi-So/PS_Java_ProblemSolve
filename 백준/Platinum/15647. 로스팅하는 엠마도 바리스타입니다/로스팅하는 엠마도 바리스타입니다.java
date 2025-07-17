import java.io.*;
import java.util.*;

public class Main {
    // 누가 부모인지, 내 자식이 몇명인지, 부모와의 거리가 얼마인지.

    // dp[child] = dp[parent] - (D * child + 1(자신포함)) + (D * (n - child + 1))
    // dp[child] = dp[parent] + (D * (n - (2 * child)))

    static int n;
    static List<int[]>[] edges;
    static int[] parents; //부모 인덱스
    static long[] D; //부모와의 거리
    static int[] child; //서브트리 크기
    static long[] dist;  //자식 노드들과의 거리의 합
    static long[] dp;    //모든 노드들과의 거리의 합

    static int getChild(int index){
        if(child[index] == -1){
            child[index] = 1;
            for(int[] next : edges[index]){
                if(child[next[0]] == -1){
                    child[index] += getChild(next[0]);
                    parents[next[0]] = index;
                    D[next[0]] = next[1];
                }
            }
        }
        return child[index];
    }

    static long getDist(int index){
        if(dist[index] == -1){
            dist[index] = 0;
            for(int[] next : edges[index]){
                if(dist[next[0]] == -1){
                    dist[index] += (getDist(next[0]) + child[next[0]] * next[1]);
                }
            }
        }
        return dist[index];
    }

    static long getResult(int index){
        if(dp[index] == -1){
            dp[index] = getResult(parents[index]) + (D[index] * (n - (2 * child[index])));
        }
        return dp[index];
    }

    // (D * (n - (2 * child)))

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        edges = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            edges[i] = new ArrayList<>();
        }

        parents = new int[n+1];
        child = new int[n+1];
        dist = new long[n+1];
        D = new long[n+1];
        dp = new long[n+1];

        Arrays.fill(child, -1);
        Arrays.fill(dist, -1L);
        Arrays.fill(dp, -1L);

        for(int i = 0; i < n - 1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges[a].add(new int[]{b, w});
            edges[b].add(new int[]{a, w});
        }
        getChild(1);
        dp[1] = getDist(1);

        for(int i = 1; i <= n; i++){
            getResult(i);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++){
            sb.append(dp[i]).append("\n");
        }
        System.out.println(sb);

    }
}