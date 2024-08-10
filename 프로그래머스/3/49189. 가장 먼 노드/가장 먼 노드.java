import java.util.*;

class Node{
    int index;
    int distance;
    public Node(int index, int distance){
        this.index = index;
        this.distance = distance;
    }
}
class Solution {
    static List<Integer>[] graph;
    static boolean[] visited;
    static int max = Integer.MIN_VALUE;
    static int cnt = 0;
    
    static void bfs(){
        Queue<Node> queue = new LinkedList();
        queue.offer(new Node(1, 0));
        visited[1] = true;
        
        while(!queue.isEmpty()){
            Node current = queue.poll();
            if(current.distance > max){
                max = current.distance;
                cnt = 1;
            }
            else if(current.distance == max){
                cnt++;
            }
            
            for(int next : graph[current.index]){
                if(!visited[next]){
                    queue.offer(new Node(next, current.distance + 1));
                    visited[next] = true;
                }
            }
        }
        
    }
    
    
    public int solution(int n, int[][] edge) {
        
        graph = new LinkedList[n+1];
        visited = new boolean[n+1];
        
        for(int i = 1; i <= n; i++){
            graph[i] = new LinkedList();
        }
        
        for(int[] e : edge){
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        
        bfs();
            
        int answer = cnt;
        return answer;
    }
}