import java.util.*;

class Solution {
    static int n, m, start, end; //m = 트랩의 개수
    static int[] traps;
    static final int MAX = 1000000000;
    static List<int[]>[] graph;
    
    static int bfs(){
        Queue<int[]> queue = new LinkedList();
        int[][] D = new int[n+1][(int)Math.pow(2, m)];
        for(int[] line : D) Arrays.fill(line, MAX);
        D[start][0] = 0;
        queue.offer(new int[]{start, 0, 0});

        
        int result = MAX;
        
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            if(D[current[0]][current[2]] < current[1]) continue;
            
            
            if(current[0] == end){
                result = Math.min(result, current[1]);
                continue;
            }
            
            int nextT = current[2];
            if(traps[current[0]] != -1){
                nextT = (current[2] ^ (1 << traps[current[0]]));
            }
            
            
            for(int[] next : graph[current[0]]){
                int s = current[0];
                int e = next[0];
                if(next[2] != 0){
                    boolean reverse = false;
                    if(traps[s] != -1 && (nextT & (1 << traps[s])) != 0) reverse = !reverse; //시작점이 트랩발동?
                    if(traps[e] != -1 && (nextT & (1 << traps[e])) != 0) reverse = !reverse; //종료점이 트랩발동?

                    if(reverse && next[2] == 1) continue;
                    if(!reverse && next[2] == -1) continue;
                }
                
                if(D[next[0]][nextT] > current[1] + next[1]){
                    D[next[0]][nextT] = current[1] + next[1];
                    queue.offer(new int[]{next[0], D[next[0]][nextT], nextT});
                }
            }
        }
        
        return result;
    }
    
    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        this.n = n;
        this.m = traps.length;
        this.start = start;
        this.end = end;
        this.traps = new int[n+1];
        Arrays.fill(this.traps, -1);
        for(int i = 0; i < traps.length; i++){
            this.traps[traps[i]] = i;
        }
        this.graph = new ArrayList[n+1];
        
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList();
        }
        
        for(int[] edge : roads){ //기본 = 0, 정상시 활성화 = 1, 역행시 활성화 = -1
            if(this.traps[edge[0]] != -1 || this.traps[edge[1]] != -1){
                graph[edge[0]].add(new int[]{edge[1], edge[2], 1});
                graph[edge[1]].add(new int[]{edge[0], edge[2], -1});
            }
            else{
                graph[edge[0]].add(new int[]{edge[1], edge[2], 0});
            }
        }
        
        int answer = bfs();
        return answer;
    }
}

/**
                    System.out.println("Reverse Edge");
                    System.out.println(traps[current[0]] + " " + traps[next[0]]);
                    System.out.println((nextT & (1 << traps[s])));
                    System.out.println((nextT & (1 << traps[e])));
                    System.out.println(reverse);
**/