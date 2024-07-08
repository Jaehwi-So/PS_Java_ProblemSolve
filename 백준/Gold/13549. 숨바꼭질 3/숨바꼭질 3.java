import java.util.*;

class Step{
    int step;
    int location;
    public Step(int step, int location){
        this.step = step;
        this.location = location;
    }
}
public class Main {
    static int MAX = 100001;
    static int bfs(int start, int end){
        Queue<Step> queue = new LinkedList<Step>();
        int[] visited = new int[MAX];
        Arrays.fill(visited, Integer.MAX_VALUE);
        queue.offer(new Step(0, start));
        visited[start] = 0;

        while(!queue.isEmpty()){
            Step k = queue.poll();

            if(k.location+1 < MAX && visited[k.location+1] > k.step + 1){
                visited[k.location+1] = k.step + 1;
                queue.offer(new Step(k.step+1,  k.location+1));
            }
            if(k.location-1 > -1 && visited[k.location-1] > k.step + 1){
                visited[k.location-1] = k.step + 1;
                queue.offer(new Step(k.step+1,  k.location-1));
            }
            if(k.location > 0 && 2 * k.location < MAX && visited[k.location * 2] > k.step){
                visited[2 * k.location] = k.step;
                queue.offer(new Step(k.step,  k.location * 2));
            }

        }

        return visited[end];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int start = sc.nextInt();
        int end = sc.nextInt();
        System.out.println(bfs(start, end));

    }
}
