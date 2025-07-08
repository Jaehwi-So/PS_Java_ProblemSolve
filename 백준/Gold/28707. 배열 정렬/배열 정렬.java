import java.io.*;
import java.util.*;

class Pair implements Comparable<Pair>{
    String key;
    int value;
    public Pair(String key, int value){
        this.key = key;
        this.value = value;
    }

    public int compareTo(Pair p){
        return this.value - p.value;
    }
}

public class Main {
    static int n;
    static int m;
    static int[] origin;
    static int[] answer;
    static int[][] command;
    static Map<String, Integer> map = new HashMap<>();

    static String parse(int[] array){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < array.length; i++){
            sb.append(array[i]);
        }
        return sb.toString();
    }

    static void dijkstra(String origin){
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.offer(new Pair(origin, 0));
        map.put(origin, 0);

        while(!pq.isEmpty()){
            Pair current = pq.poll();
            if(map.get(current.key) == current.value){
                for(int i = 0; i < m; i++){
                    char[] next = current.key.toCharArray();

                    char temp = next[command[i][0]];
                    next[command[i][0]] = next[command[i][1]];
                    next[command[i][1]] = temp;

                    String nextStr = String.valueOf(next);
//                    System.out.println(map);
                    int nextVal = current.value + command[i][2];

                    if(!map.containsKey(nextStr) || map.get(nextStr) > nextVal){
                        map.put(nextStr, nextVal);
                        pq.offer(new Pair(nextStr, nextVal));
                    }
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        origin = new int[n];
        answer = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            origin[i] = Integer.parseInt(st.nextToken()) - 1;
            answer[i] = origin[i];
        }
        Arrays.sort(answer);


        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        command = new int[m][3];
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            command[i][0] = Integer.parseInt(st.nextToken()) - 1;
            command[i][1] = Integer.parseInt(st.nextToken()) - 1;
            command[i][2] = Integer.parseInt(st.nextToken());
        }

        dijkstra(parse(origin));
//        System.out.println(map);
        String ans = parse(answer);
        if(!map.containsKey(ans)){
            System.out.println(-1);
        }
        else{
            System.out.println(map.get(ans));
        }
    }
}