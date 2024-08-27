import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        Integer[] crains = new Integer[n];
        for(int i = 0; i < n; i++){
            crains[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(crains, new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        Integer[] boxes = new Integer[m];


        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++){
            int k = Integer.parseInt(st.nextToken());
            boxes[i] = k;
        }

        Arrays.sort(boxes, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        boolean[] visited = new boolean[m];
        int[] idx = new int[n];

        int cnt = 0;
        int result = 0;
        while(cnt < m){
            for(int i = 0; i < n; i++){
                int start = idx[i];
                for(int j = start; j < m; j++){
                    idx[i] = j + 1;
                    if(boxes[j] <= crains[i] && !visited[j]){
                        visited[j] = true;
                        cnt++;
                        break;
                    }
                }
            }
            if(idx[0] == m && cnt < m){
                result = -1;
                break;
            }
            result++;
        }
        System.out.println(result);
    }
}
