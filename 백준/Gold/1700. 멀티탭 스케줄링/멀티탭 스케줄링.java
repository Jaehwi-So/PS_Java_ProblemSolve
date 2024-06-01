import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] sequence = new int[k];
        boolean[] used = new boolean[k+1];
        boolean[] next = new boolean[k+1];

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < k; i++){
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        Set<Integer> set = new HashSet<>();
        int seq;
        for(seq = 0; seq < k && set.size() < n; seq++){
            set.add(sequence[seq]);
            used[sequence[seq]] = true;
        }


        int count = 0;
        while(seq < k){
            if(used[sequence[seq]] == false){
                Arrays.fill(next, false);
                set.clear();
                for(int i = seq; i < k; i++){
                    if(used[sequence[i]] == true && !set.contains(sequence[i])){
                        next[sequence[i]] = true;
                        set.add(sequence[i]);
                    }
                    if(set.size() == n - 1){
                        break;
                    }

                }
                for(int i = 1; i <= k; i++){
                    if(used[i] == true && next[i] == false){
                        count++;
                        used[i] = false;
                        used[sequence[seq]] = true;
                        break;
                    }
                }
            }

            seq++;
        }

        System.out.println(count);
    }
}
