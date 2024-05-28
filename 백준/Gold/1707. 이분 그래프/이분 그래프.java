import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] isVisited;
    static String bfs(List<List<Integer>> adj_list){

        Queue<Integer> queue = new LinkedList<>();

        for(int j = 1; j < adj_list.size(); j++){
            if(isVisited[j] == 0){
                queue.offer(j);
                isVisited[j] = 1;
            }

            while(!queue.isEmpty()){
                int k = queue.poll();
                for(Integer i : adj_list.get(k)){
                    if(isVisited[i] == 0){
                        queue.offer(i);
                        if(isVisited[k] == 1){
                            isVisited[i] = 2;
                        }
                        else if(isVisited[k] == 2){
                            isVisited[i] = 1;
                        }
                    }
                    else if(isVisited[i] == isVisited[k]){
                        return "NO";
                    }
                }
            }

//            System.out.println(Arrays.toString(isVisited));
        }
        return "YES";
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for(int l = 0; l < t; l++){
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            List<List<Integer>> adj_list = new ArrayList<>();
            for(int i = 0; i <= v; i++){
                adj_list.add(new LinkedList<>());
            }
            for(int i = 0; i < e; i++){
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                adj_list.get(start).add(end);
                adj_list.get(end).add(start);
            }


            isVisited = new int[adj_list.size()];

            sb.append(bfs(adj_list)).append("\n");
        }
        System.out.println(sb);

    }
}