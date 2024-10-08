import java.util.*;

class Node implements Comparable<Node>{
    String s;
    int n;
    public Node(String s, int n){
        this.s = s;
        this.n = n;
    }
    public int compareTo(Node n){
        return n.n - this.n;
    }
    public String toString(){
        return this.s + " : " + this.n;
    }
}

class Solution {
    static Map<String, Integer> map = new HashMap();
    static boolean[] visited;
    static char[] order;
    
    static void dfs(String word, int step, int len, int index){
        if(step > 0){
            if(map.containsKey(word)){
                map.put(word, map.get(word) + 1);
            }
            else{
                map.put(word, 1);
            }
        }
        if(step == len){
            return;
        }
        
        else{
            for(int i = index; i < len; i++){
                if(!visited[i]){
                    visited[i] = true;
                    dfs(word + order[i], step + 1, len, i + 1);
                    visited[i] = false;
                }
            }
        }
    }
    
    public String[] solution(String[] orders, int[] course) {


        for(int i = 0; i < orders.length; i++){
            order = orders[i].toCharArray();
            visited = new boolean[order.length];
            Arrays.sort(order);
            dfs("", 0, order.length, 0);
            
        }
        
        int cm = course[course.length -1];
        int[] max = new int[cm + 1];
        Arrays.fill(max, -1);
        List<Node>[] list = new ArrayList[cm + 1];
        for(int i = 1; i <= cm; i++){
            list[i] = new ArrayList();
        }
        
        for(String s : map.keySet()){
            int len = s.length();
            if(len > cm) continue;
            if(map.get(s) < 2) continue;
            list[len].add(new Node(s, map.get(s)));
        }
            
        
        List<Node> result = new ArrayList();
        for(int i = 0; i < course.length; i++){
            List<Node> current = list[course[i]];
            Collections.sort(current);
            if(current.size() == 0){
                continue;
            }
            int m = current.get(0).n;
            result.add(current.get(0));
            int idx = 1;
            while(idx < current.size() && m == current.get(idx).n){
                result.add(current.get(idx));
                idx++;
            }
        }
        String[] answer = new String[result.size()];
        for(int i = 0; i < result.size(); i++){
            answer[i] = result.get(i).s;
        }
        Arrays.sort(answer);

        return answer;
    }
}