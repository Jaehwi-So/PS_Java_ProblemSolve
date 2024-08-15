import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;

    static void union(int p1, int p2){
        int k1 = find(p1);
        int k2 = find(p2);
        if(k1 > k2){
            parent[k2] = k1;
        }
        else{
            parent[k1] = k2;
        }
    }
    static int find(int p){
        if(parent[p] != p){
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int[] rPeople = new int[k];
        for(int i = 0; i < k; i++){
            rPeople[i] = Integer.parseInt(st.nextToken());
        }

        parent = new int[n+1];
        for(int i = 1; i <= n; i++){
            parent[i] = i;
        }

        int[] plen = new int[m];
        int[][] party = new int[m][51];


        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            plen[i] = Integer.parseInt(st.nextToken());
            for(int j = 0; j < plen[i]; j++){
                party[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < m; i++){
            int before = 0;
            for(int j = 0; j < plen[i]; j++){
                if(before != 0){
                    union(party[i][j], before);
                }
                before = party[i][j];
            }
        }

        int result = 0;
        for(int i = 0; i < m; i++){
            int cnt = 1;
            for(int j = 0; j < plen[i]; j++){
                for(int l = 0; l < rPeople.length; l++){
                    if(find(party[i][j]) == find(rPeople[l])){
                        cnt = 0;
                        break;
                    }
                }
                if(cnt == 0){
                    break;
                }
            }
            result += cnt;
        }

        System.out.println(result);

    }
}