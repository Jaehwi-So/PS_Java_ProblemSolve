import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[] parents;

    static int find(int n){
        if(n != parents[n]){
            parents[n] = find(parents[n]);
        }
        return parents[n];
    }

    static boolean union(int n1, int n2){
        int p1 = find(n1);
        int p2 = find(n2);
        if(p1 == p2){ //사이클 형성
            return false;
        }
        else{
            if(p1 < p2){
                parents[p2] = p1;
            }
            else{
                parents[p1] = p2;
            }
            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parents = new int[n+1];
        for(int i = 1; i <= n; i++){
            parents[i] = i;
        }

        List<int[]> edges = new ArrayList<>();
        int[] D = new int[n+1]; //리프노드 판별
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(union(a, b)){ //사이클 형성이 되지않는 간선 추가
                edges.add(new int[]{i+1, a, b, 0});
                D[a]++;
                D[b]++;
            }
        }

        for(int i = 1; i <= n; i++){
            find(i);
        }

        for(int[] edge : edges){
            edge[3] = find(edge[1]);
        }

        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 1; i <= n; i++){
            if(!map.containsKey(parents[i])){
                map.put(parents[i], 1);
            }
            else{
                map.put(parents[i], map.get(parents[i]) + 1);
            }

        }


        Set<Integer> node1 = new HashSet<>();
        Set<Integer> edge1 = new HashSet<>();
        Set<Integer> node2 = new HashSet<>();
        Set<Integer> edge2 = new HashSet<>();

        if(map.size() == 1){    //모두 연결된 경우
            boolean div = false;

            for(int i = 1; i <= n; i++){
                if(D[i] == 1 && !div){  //리프 노드 한 개 분리
                    div = true;
                    node2.add(i);
                    for(int[] edge : edges){
                        if(edge[1] != i && edge[2] != i){   //해당 리프노드를 포함하지 않는 간선 추가
                            edge1.add(edge[0]);
                        }
                    }
                }
                else{
                    node1.add(i);
                }
            }
        }
        else if(map.size() == 2){   //이미 두 개로 분할된 경우
            int seq = 0;
            for(int key : map.keySet()){
                for(int[] edge : edges){
                    if(edge[3] == key){
                        if(seq == 0){
                            edge1.add(edge[0]);
                        }
                        else{
                            edge2.add(edge[0]);
                        }
                    }
                }
                for(int i = 1; i <= n; i++){
                    if(key == parents[i]){
                        if(seq == 0) node1.add(i);
                        else node2.add(i);

                    }
                }
                seq++;
            }
        }

//        System.out.println(node1);
//        System.out.println(node2);

        StringBuilder sb = new StringBuilder();
        if(node1.size() > 0 && node2.size() > 0 && node1.size() != node2.size() && node1.size() + node2.size() == n){
            sb.append(node1.size()).append(" ").append(node2.size()).append("\n");
            for(int i : node1){
                sb.append(i).append(" ");
            }
            sb.append("\n");
            for(int i : edge1){
                sb.append(i).append(" ");
            }
            sb.append("\n");
            for(int i : node2){
                sb.append(i).append(" ");
            }
            sb.append("\n");
            for(int i : edge2){
                sb.append(i).append(" ");
            }
        }
        else{
            sb.append(-1);
        }

        System.out.println(sb);



    }
}