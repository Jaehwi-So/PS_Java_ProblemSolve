import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        List<Integer>[] tree = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            tree[i] = new ArrayList();
        }
        for(int i = 0; i < n - 1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }
        int[] roots = new int[n+1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        roots[1] = 1;

        while(!queue.isEmpty()){
            int current = queue.poll();
            for(int next : tree[current]){
                if(roots[next] == 0){
                    roots[next] = current;
                    queue.offer(next);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 2; i <= n; i++){
            sb.append(roots[i]).append("\n");
        }
        System.out.println(sb);
    }
}