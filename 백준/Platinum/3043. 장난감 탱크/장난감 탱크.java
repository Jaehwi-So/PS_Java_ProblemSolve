import java.io.*;
import java.util.*;

public class Main {
    static int total = 0;
    static StringBuilder sb = new StringBuilder();
    static PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
        public int compare(int[] o1, int[] o2) {
            if(o1[0] == o2[0]) return o1[1] - o2[2];
            return o1[0] - o2[0];
        }
    });
    static int[][] position;
    static void move(int col, int srow, int erow){
        if(srow < erow){
            for(int i = srow + 1; i <= erow; i++){
                if(position[i][col] != 0){
                    move(col, i, i+1);
                }
                sb.append(position[i-1][col] + " " + "D").append("\n");
                total++;
                position[i][col] = position[i-1][col];
                pq.offer(new int[]{i, col, position[i][col]});
                position[i-1][col] = 0;
            }
        }
        else{
            for(int i = srow - 1; i >= erow; i--){
                if(position[i][col] != 0){
                    move(col, i, i-1);
                }
                sb.append(position[i+1][col] + " " + "U").append("\n");
                total++;
                position[i][col] = position[i+1][col];
                pq.offer(new int[]{i, col, position[i][col]});
                position[i+1][col] = 0;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[][] array = new int[n][3];
        position = new int[n+1][n+1];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            array[i][0] = Integer.parseInt(st.nextToken());
            array[i][1] = Integer.parseInt(st.nextToken());
            array[i][2] = i+1;
            position[array[i][0]][array[i][1]] = array[i][2];
        }

        for(int i = 0 ; i < n; i++){
            pq.offer(array[i]);
        }

        int r = 1;
        int[][] rows = new int[n][2];
        while(r <= n){
            int[] current = pq.poll();
            if(position[current[0]][current[1]] == current[2]){

                move(current[1], current[0], r);

                position[r][current[1]] = 0;
                rows[r-1][0] = current[1];
                rows[r-1][1] = current[2];
                r++;
            }
        }

        Arrays.sort(rows, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

//        for(int[] line : rows){
//            System.out.println(Arrays.toString(line));
//        }


        for(int i = 1; i <= n; i++){
            if(rows[i-1][0] > i){
                for(int j = rows[i-1][0]; j > i; j--){
                    sb.append(rows[i-1][1] + " L").append("\n");
                    total++;
                }
            }
            else if(rows[i-1][0] < i){
                for(int j = rows[i-1][0]; j < i; j++){
                    sb.append(rows[i-1][1] + " R").append("\n");
                    total++;
                }
            }
        }
        System.out.println(total);
        System.out.println(sb);
    }
}