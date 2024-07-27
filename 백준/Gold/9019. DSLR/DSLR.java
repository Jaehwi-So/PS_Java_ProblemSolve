import java.io.*;
import java.util.*;

class Register{

    int numbers;
    String commands;

    public Register(int numbers, String commands){
        this.numbers = numbers;
        this.commands = commands;
    }


}
public class Main {
    static int target;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    static void bfs(Register start){
        Queue<Register> queue = new LinkedList<>();
        visited[start.numbers] = true;
        queue.offer(start);

        while(!queue.isEmpty()){
            Register current = queue.poll();
            int currentNum = current.numbers;

            if(currentNum == target){
                sb.append(current.commands).append("\n");
                return;
            }

            int D = (2 * currentNum) % 10000;
            int S = currentNum == 0 ? 9999 : currentNum - 1;
            int L = (currentNum % 1000) * 10 + currentNum / 1000;
            int R = (currentNum % 10) * 1000 + currentNum / 10;

            if(!visited[D]){
                queue.offer(new Register(D, current.commands + "D"));
                visited[D] = true;
            }
            if(!visited[S]){
                queue.offer(new Register(S, current.commands + "S"));
                visited[S] = true;
            }
            if(!visited[L]){
                queue.offer(new Register(L, current.commands + "L"));
                visited[L] = true;
            }
            if(!visited[R]){
                queue.offer(new Register(R, current.commands + "R"));
                visited[R] = true;
            }
        }
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        for(int l = 0; l < t; l++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            target = Integer.parseInt(st.nextToken());
            visited = new boolean[20000];
            bfs(new Register(start, ""));
        }
        
        System.out.println(sb);

    }
}