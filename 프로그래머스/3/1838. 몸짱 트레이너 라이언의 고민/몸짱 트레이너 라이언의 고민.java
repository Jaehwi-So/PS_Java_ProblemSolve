import java.util.*;

class Solution {
    static int n, m;
    static int[][] timetable;
    static int max;
    
    public void getMax(){
        
        PriorityQueue<int[]> pq = new PriorityQueue(new Comparator<int[]>(){
            public int compare(int[] a1, int[] a2){
                return a1[1] - a2[1];
            }
        });
        
        for(int i = 0; i < m; i++){
            pq.offer(timetable[i]);
            while(!pq.isEmpty() && pq.peek()[1] < timetable[i][0]){
                pq.poll();
            }
            max = Math.max(max, pq.size());
        }
    }
    
    static int calc() {
        for (int dist = (n * 2) - 2; dist > 0; dist--) {
            
            for (int sy = 0; sy < n; sy++) {
                for (int sx = 0; sx < n; sx++) {
                    List<int[]> placed = new ArrayList<>();
                    placed.add(new int[]{sy, sx});

                    for (int y = sy; y < n; y++) {
                        for (int x = 0; x < n; x++) {
                            if (y == sy && x <= sx) continue;

                            boolean canPlace = true;
                            for (int[] p : placed) {
                                int manhattan = Math.abs(p[0] - y) + Math.abs(p[1] - x);
                                if (manhattan < dist) {
                                    canPlace = false;
                                    break;
                                }
                            }

                            if (canPlace) {
                                placed.add(new int[]{y, x});
                                if (placed.size() == max) {
                                    return dist;
                                }
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

    
    // static boolean calc(List<int[]> nodes, int row, int col, int dist){
    //     for(int[] node : nodes){
    //         if(Math.abs(row - node[0]) + Math.abs(col - node[1]) < dist){
    //             return false;
    //         }
    //     }
    //     return true;
    // }
    
    public int solution(int n, int m, int[][] timetable) {
        
        Arrays.sort(timetable, new Comparator<int[]>(){
            public int compare(int[] a1, int[] a2){
                return a1[0] - a2[0];
            }
        });
        
        this.n = n;
        this.m = m;
        this.timetable = timetable;
        this.max = 0;
        
        getMax();
        
        if(max <= 1) return 0;
//         System.out.println(max);
        
        
        int answer = calc();
        return answer;
    }
}

/**
xooox
ooxoo
xooox
ooxoo
xooox

xooxo
ooxoo
xooox
ooxoo
xooox

xxoxxoxx
oxxxxxxo
xxxoxxxx
xoxxxoxx
xxxxxxxo
oxxoxxxx
xxxxxoxx
xoxxxxxo


**/