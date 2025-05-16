import java.util.*;

class Solution {
    static Map<Integer, Integer> map = new HashMap<>();
    static Map<Integer, Integer> mapToGetNumber = new HashMap<>();
    static List<Integer>[] graph;
    static int[] childs; //해당 노드의 자식 개수
    

    public int[] solution(int[] nodes, int[][] edges) {
        map = new HashMap<>();
        mapToGetNumber = new HashMap<>();
        int n = 0;
        for(int node : nodes){
            n++;
            map.put(node, n);
            mapToGetNumber.put(n, node);
        }
        
        childs = new int[n+1];
        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList();
        }
        
        for(int[] edge : edges){
            int a = map.get(edge[0]);
            int b = map.get(edge[1]);
            graph[a].add(b);
            graph[b].add(a);
            childs[a]++;
            childs[b]++;
        }
        
        boolean[] visited = new boolean[n+1];
        int[] answer = {0, 0};
        for(int i = 1; i <= n; i++){
            if(!visited[i]){
                int[] counts = new int[4]; // 홀수 노드, 짝수 노드, 역홀수 노드, 역짝수 노드
                Queue<Integer> queue = new LinkedList();
                queue.offer(i);
                while(!queue.isEmpty()){
                    int current = queue.poll();
                    visited[current] = true;
                    int index = mapToGetNumber.get(current);
                    
                    // 해당 노드를 루트 노드라고 가정하고 판별
                    
                    if(index % 2 == 0){ //현재 노드가 짝
                        if(childs[current] % 2 == 0){ //짝수 노드
                            counts[1]++;
                        }
                        else{ //역짝수 노드
                            counts[3]++;
                        }
                        
                    }
                    else{ //현재 노드가 홀
                        if(childs[current] % 2 == 0){ //역홀수 노드
                            counts[2]++;
                        }
                        else{ //홀수 노드
                            counts[0]++;
                        }
                    }
                    
                    for(int next : graph[current]){
                        if(!visited[next]){
                            queue.offer(next);
                        }
                    }
                }
                
                // 최종 판별 -> 루트 노드로 판별했기 때문에, 자식 노드가 된다면 자식의 개수가 전체적으로 -1이 된다.
                // 1개를 제외한 모든 것은 반대 성격의 노드여야 함
                if(counts[0] + counts[1] == 1){ //홀짝 트리 
                    answer[0]++;
                }
                if(counts[2] + counts[3] == 1){ //역홀짝 트리
                    answer[1]++;
                }
            }
        }

    
        return answer;
    }
}

//16 00000 00000