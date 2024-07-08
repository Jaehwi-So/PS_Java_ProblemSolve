import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node implements Comparable<Node> {
    int index;  // 노드의 인덱스
    int weight; // 해당 노드까지의 가중치

    // 생성자
    public Node(int index, int weight) {
        this.index = index;
        this.weight = weight;
    }

    // 우선순위 큐에서 사용될 비교 메서드 (가중치 기준 오름차순 정렬)
    @Override
    public int compareTo(Node n) {
        return this.weight - n.weight;
    }
}



public class Main {
    static int V; // 정점의 개수
    static int E; // 간선의 개수
    static int T; // 목적지 후보 개수
    static int start; // 시작 인덱스
    static List<Node>[] graph; // 그래프를 표현하는 인접 리스트
    static int[] distance; // 최단 거리 배열
    static int[][] goal; // 목적지 후보
    static final int INF = 100000000; // 무한대를 나타내는 값

    public static void dijakstra(int start){
        boolean[] visited = new boolean[V+1];
        distance = new int[V+1];
        Arrays.fill(distance, INF);
        distance[start] = 0;
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(start, distance[start]));

        while(!queue.isEmpty()){
            Node k = queue.poll();
            if(!visited[k.index]){
                visited[k.index] = true;
                for(Node n : graph[k.index]){
                    if(!visited[n.index] && distance[k.index] + n.weight < distance[n.index]){
                        distance[n.index] = distance[k.index] + n.weight;
                        queue.offer(new Node(n.index, distance[n.index]));
                    }
                }
            }
        }

//        System.out.println(Arrays.toString(distance));

//        return distance[end];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for(int l = 0; l < t; l++){

            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            T = Integer.parseInt(st.nextToken());

            graph = new LinkedList[V+1];
            for(int i = 1; i <= V; i++){
                graph[i] = new LinkedList<>();
            }

            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            int fixedStart = Integer.parseInt(st.nextToken());
            int fixedEnd = Integer.parseInt(st.nextToken());
            int fixedWeight = 0;

            for(int i = 1; i <= E; i++){
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                graph[start].add(new Node(end, weight));
                graph[end].add(new Node(start, weight));
                if((start == fixedStart && end == fixedEnd) || (end == fixedStart && start == fixedEnd)){
                    fixedWeight = weight;
                }
            }

            dijakstra(start);

            goal = new int[T][4];
            for(int i = 0; i < T; i++){
                st = new StringTokenizer(br.readLine());
                goal[i][0] = Integer.parseInt(st.nextToken());
                goal[i][1] = distance[goal[i][0]];
            }



            int fixSDist = distance[fixedStart] + fixedWeight;
            int fixEDist = distance[fixedEnd] + fixedWeight;
            dijakstra(fixedEnd);

            List<Integer> result = new ArrayList<>();
            for(int i = 0; i < T; i++){
                goal[i][2] = fixSDist + distance[goal[i][0]];
                if(goal[i][1] == goal[i][2] && goal[i][1] < INF){
                    result.add(goal[i][0]);
                }
            }

            dijakstra(fixedStart);
            for(int i = 0; i < T; i++){
                goal[i][3] = fixEDist + distance[goal[i][0]];
                if(goal[i][1] == goal[i][3] && goal[i][2] != goal[i][3] && goal[i][1] < INF){
                    result.add(goal[i][0]);
                }
            }

            Collections.sort(result);
            for(Integer i : result){
                sb.append(i).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}