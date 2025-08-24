import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<Integer>[] group = new ArrayList[m];
        for(int i = 0; i < m; i++){
            group[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            for(int j = 0; j < k; j++){
                group[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        int[] lastInfested = new int[n+1];
        int[] afterInfested = new int[n+1];
        int[] firstInfested = new int[n+1];

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            lastInfested[i] = Integer.parseInt(st.nextToken());
            afterInfested[i] = lastInfested[i];
            firstInfested[i] = lastInfested[i]; // 최종 상태로 초기 후보 시작
        }

        for (int i = m - 1; i >= 0; i--) {
            boolean infested = true;
            for (int idx : group[i]) {
                if (afterInfested[idx] == 0) {
                    infested = false;
                }
            }
            if (!infested) {
                for (int idx : group[i]) {
                    firstInfested[idx] = 0;  // 그 모임엔 초기감염자가 없음
                    afterInfested[idx] = 0;  // 0을 앞 모임 판단에 전파
                }
            }
        }


        int[] compareInfested = Arrays.copyOf(firstInfested, n+1); // 초기 후보를 기준 상태로 시작

        for(int i = 0; i < m; i++){
            boolean infested = false;
            for(int j = 0; j < group[i].size(); j++){
                int idx = group[i].get(j);
                if(compareInfested[idx] == 1){
                    infested = true;
                }
            }

            if(infested) {
                for (int j = 0; j < group[i].size(); j++) {
                    int idx = group[i].get(j);
                    compareInfested[idx] = 1;
                }
            }
        }

        for(int i = 1; i <= n; i++){
            if(lastInfested[i] != compareInfested[i]){
                System.out.println("NO");
                return;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("YES").append("\n");
        for(int i = 1; i <= n; i++){
            sb.append(firstInfested[i]).append(" ");
        }
        System.out.println(sb);
    }
}