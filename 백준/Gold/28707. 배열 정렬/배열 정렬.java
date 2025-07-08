import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[] origin;
    static int[] answer;
    static int[][] command;
    static Map<Integer, Integer> map = new HashMap<>();

    static int parse(int[] array){
        int result = 0;
        int mul = 1;
        for(int i = array.length - 1; i >= 0; i--){
            result += array[i] * mul;
            mul *= 10;
        }
        return result;
    }

    static int reverse(int number, int idx1, int idx2){
        int mul1 = (int)Math.pow(10, n - idx1 - 1);
        int mul2 = (int)Math.pow(10, n - idx2 - 1);

        int n1 = (number / mul1) % 10;
        int n2 = (number / mul2) % 10;
        number -= n1 * mul1;
        number -= n2 * mul2;
        number += n1 * mul2;
        number += n2 * mul1;
        return number;
    }

    static void dijkstra(int origin){
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        pq.offer(new int[]{origin, 0});
        map.put(origin, 0);

        while(!pq.isEmpty()){
            int[] current = pq.poll();
            if(map.get(current[0]) < current[1]) continue;

            for(int i = 0; i < m; i++){
                int nextKey = reverse(current[0], command[i][0], command[i][1]);
                int nextVal = current[1] + command[i][2];

                if(map.containsKey(nextKey) && map.get(nextKey) <= nextVal) continue;
                map.put(nextKey, nextVal);
                pq.offer(new int[]{nextKey, nextVal});
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
        int ans = parse(answer);
        if(!map.containsKey(ans)){
            System.out.println(-1);
        }
        else{
            System.out.println(map.get(ans));
        }
    }
}