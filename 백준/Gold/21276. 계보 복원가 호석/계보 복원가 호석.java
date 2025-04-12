import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        String[] names = new String[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            names[i] = st.nextToken();
        }
        Arrays.sort(names);
        HashMap<String, Integer> map = new HashMap<>();
        HashMap<Integer, String> reverseMap = new HashMap<>();
        for(int i = 0; i < n; i++){
            map.put(names[i], i);
            reverseMap.put(i, names[i]);
        }

        List<Integer>[] graph = new ArrayList[n];
        int[] D = new int[n];
        for(int i = 0; i < n; i++){
            graph[i] = new ArrayList();
        }

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int child = map.get(st.nextToken());
            int parent = map.get(st.nextToken());
            D[parent]++;
            graph[child].add(parent);
        }

        List<Integer>[] trace = new ArrayList[n];
        for(int i = 0; i < n; i++){
            trace[i] = new ArrayList<>();
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < n; i++){
            if(D[i] == 0){
                queue.offer(i);
            }
        }
        int[] parents = new int[n];
        Arrays.fill(parents, -1);

        while(!queue.isEmpty()){
            int current = queue.poll();
            for(int child : trace[current]){
                if(parents[child] == -1) parents[child] = current;
            }
            for(int next : graph[current]){
                if(D[next] > 0){
                    trace[next].add(current);
                    D[next]--;
                    if(D[next] == 0){
                        queue.offer(next);
                    }
                }
            }
        }


        List<Integer>[] result = new ArrayList[n];
        for(int i = 0; i < n; i++){
            result[i] = new ArrayList<>();
        }

        StringBuilder sb = new StringBuilder();

        int count = 0;
        for(int i = 0; i < n; i++){
            if(parents[i] != -1) result[parents[i]].add(i);
            else{
                sb.append(reverseMap.get(i)).append(" ");
                count++;
            }
        }
        sb.append("\n");
        sb.insert(0, count + "\n");

        for(int i = 0; i < n; i++){
            sb.append(reverseMap.get(i)).append(" ");
            sb.append(result[i].size()).append(" ");
            for(int child : result[i]){
                sb.append(reverseMap.get(child)).append(" ");
            }
            sb.append("\n");

        }

        System.out.println(sb);
    }
}