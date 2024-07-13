import java.util.*;

class Node {
    int number;
    int time;
    int copy;
    public Node(int number, int time, int copy){
        this.number = number;
        this.time = time;
        this.copy = copy;
    }
}
public class Main {
    static int n;
    static boolean[][] visited;

    static int bfs(int number){
        
        Queue<Node> queue = new LinkedList<>();
        Node node = new Node(1, 0, 0);
        queue.offer(node);
        visited[0][1] = true;
        int result = -1;
        while(!queue.isEmpty()){
            Node k = queue.poll();

            if(k.number == number){
                result = k.time;
                break;
            }

            // 클립보드 저장
            queue.offer(new Node(k.number, k.time + 1, k.number));

            // 이모티콘 삭제
            int next = k.number -1;
            if(next > 0 && !visited[k.copy][next]){
                queue.offer(new Node(next, k.time + 1, k.copy));
                visited[k.copy][next] = true;
            }

            // 이모티콘 복사
            next = k.number + k.copy;
            if(next <= n && !visited[k.copy][next] && k.copy > 0){
                queue.offer(new Node(next, k.time + 1, k.copy));
                visited[k.copy][next] = true;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        visited = new boolean[n + 1][n + 1];
        System.out.println(bfs(n));
//        System.out.println(Arrays.toString(dp));

    }
}
