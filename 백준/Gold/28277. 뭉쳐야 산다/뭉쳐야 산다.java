import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int q;
    static Set<Integer>[] elements;
    static int[][] queries;
    static int[] index;

    static void merge(int a, int b){
        int aSetIdx = index[a];
        int bSetIdx = index[b];
        if(elements[aSetIdx].size() < elements[bSetIdx].size()){
            for(int k : elements[aSetIdx]){
                elements[bSetIdx].add(k);
            }
            elements[aSetIdx].clear();
            index[a] = bSetIdx;
            index[b] = aSetIdx;
        }
        else{
            for(int k : elements[bSetIdx]){
                elements[aSetIdx].add(k);
            }
            elements[bSetIdx].clear();
//            index[bSetIdx] = aSetIdx;
        }
    }

    static int getSize(int a){
        return elements[index[a]].size();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        elements = new HashSet[n+1];
        index = new int[n+1];
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            int len = Integer.parseInt(st.nextToken());
            elements[i] = new HashSet();
            index[i] = i;
            for(int j = 0; j < len; j++){
                elements[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        queries = new int[q][3];


        for(int i = 0; i < q; i++){
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());
            queries[i][0] = type;
            queries[i][1] = f;
            if(type == 1){
                int s = Integer.parseInt(st.nextToken());
                queries[i][2] = s;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < q; i++){
            if(queries[i][0] == 1){
                int parent = queries[i][1];
                int child = queries[i][2];
                merge(parent, child);
//                System.out.println("Merge " + parent + "+" + child);
//                System.out.println(Arrays.toString(elements));
//                System.out.println(Arrays.toString(index));
            }
            else{
                sb.append(getSize(queries[i][1])).append("\n");
//                System.out.println("Get " + queries[i][1] + " : " + getSize(queries[i][1]));
            }

        }


        System.out.println(sb);

    }
}