import java.io.*;
import java.util.*;

public class Main {
    static final int max = 500000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] visited = new int[max+1][2]; //짝, 홀
        for(int[] line : visited){
            Arrays.fill(line, max);
        }
        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> temp = new LinkedList<>();

        int step = 0;
        int time = 0;
        queue.offer(n);
        visited[n][0] = 0;
        int result = -1;
        for(int i = m; i <= max; i += step){
            int odd = time % 2 == 1 ? 1 : 0;
            if(visited[i][odd] <= time){
                result = time;
                break;
            }

            step++;
            time++;

            while(!temp.isEmpty()){
                queue.offer(temp.poll());
            }
            while(!queue.isEmpty()){
                int k = queue.poll();
                int nextOdd = (odd == 0 ? 1 : 0);
                if(k+1 <= max && visited[k+1][nextOdd] > time){
                    temp.offer(k+1);
                    visited[k+1][nextOdd] = time;
                }
                if(k-1 >= 0 && visited[k-1][nextOdd] > time){
                    temp.offer(k-1);
                    visited[k-1][nextOdd] = time;
                }
                if(k * 2 <= max && visited[k*2][nextOdd] > time){
                    temp.offer(k*2);
                    visited[k*2][nextOdd] = time;
                }
            }
        }
        System.out.println(result);

//        for(int i = 0; i < 100; i++){
//            System.out.println(i + " : " + visited[i][0] + ", " + visited[i][1]);
//        }
    }
}