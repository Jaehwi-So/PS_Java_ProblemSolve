import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int[] isVisited = new int[100001];
    static int bfs(int start, int end){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        isVisited[start] = 1;
        while(!queue.isEmpty()){
            int k = queue.poll();
            if(k == end){
                return isVisited[k] - 1;
            }
            if(k + 1 >= 0 && k + 1 < 100001 && isVisited[k+1] == 0){
                queue.offer(k + 1);
                isVisited[k+1] = isVisited[k] + 1;
            }
            if(k - 1 >= 0 && k - 1 < 100001 && isVisited[k-1] == 0){
                queue.offer(k - 1);
                isVisited[k-1] = isVisited[k] + 1;
            }
            if(k * 2 >= 0 && k * 2 < 100001 && isVisited[k*2] == 0){
                queue.offer(k * 2);
                isVisited[k*2] = isVisited[k] + 1;
            }
        }
        return -1;

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        System.out.println(bfs(a, b));
//        System.out.println(Arrays.toString(isVisited));


    }
}
