import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] board = new int[101][2];
    static int bfs(){
        int start = 1;
        Queue<Integer> queue = new LinkedList<>();
        board[start][0] = 1;
        queue.offer(start);

        while(!queue.isEmpty()){
            int k = queue.poll();
            if(k == 100){
                return board[k][0] - 1;
            }
            for(int i = 1; i <= 6; i++){
                if(k+i <= 100 && board[k+i][0] == 0){
                    int event = board[k+i][1];
                    if(event != 0){
                        if(board[event][0] == 0){
                            queue.offer(event);
                            board[event][0] = board[k][0] + 1;
                        }
                    }
                    else{
                        queue.offer(k+i);
                        board[k+i][0] = board[k][0] + 1;
                    }

                }
            }
        }
        return -1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int seq = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());
            board[seq][1] = next;
        }
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int seq = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());
            board[seq][1] = next;
        }

        System.out.println(bfs());
//        for(int i = 1; i <= 100; i++){
//            System.out.print(board[i][0] + " ");
//            if(i % 10 == 0){
//                System.out.println();
//            }
//        }

    }
}
