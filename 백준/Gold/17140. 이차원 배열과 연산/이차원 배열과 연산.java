import java.io.*;
import java.util.*;

public class Main {
    static int r;
    static int c;
    static int k;
    static int[][] matrix;
    static int row = 3;
    static int col = 3;

    static int[] sort(Map<Integer, Integer> map){
        List<int[]> sorted = new ArrayList();
        for(int key : map.keySet()){
            int value = map.get(key);
            sorted.add(new int[]{key, value});
        }
        Collections.sort(sorted, new Comparator<int[]>(){
            public int compare(int[] a1, int[] a2){
                if(a1[1] == a2[1]){
                    return a1[0] - a2[0];
                }
                return a1[1] - a2[1];
            }
        });

        int[] result = new int[100];
        int idx = 0;
        int ridx = 0;
        while(idx < sorted.size()){
            result[ridx] = sorted.get(idx)[0];
            result[ridx+1] = sorted.get(idx)[1];
            idx++;
            ridx+=2;
        }
        return result;
    }


    static void step(){
        int max = 0;
        if(row >= col){
            for(int i = 0; i < row; i++){
                HashMap<Integer, Integer> map = new HashMap<>();
                for(int j = 0; j < col; j++){
                    int number = matrix[i][j];
                    if(number != 0){
                        if(!map.containsKey(number)){
                            map.put(number, 1);
                        }
                        else{
                            map.put(number, map.get(number) + 1);
                        }
                    }
                }
                matrix[i] = sort(map);
                max = Math.max(max, map.size() * 2);
            }
            col = max;
        }

        else{
            for(int j = 0; j < col; j++){
                HashMap<Integer, Integer> map = new HashMap<>();
                for(int i = 0; i < row; i++){
                    int number = matrix[i][j];
                    if(number != 0){
                        if(!map.containsKey(number)){
                            map.put(number, 1);
                        }
                        else{
                            map.put(number, map.get(number) + 1);
                        }
                    }
                }
                int[] array = sort(map);
                for(int i = 0; i < 100; i++){
                    matrix[i][j] = array[i];
                }
                max = Math.max(max, map.size() * 2);
            }
            row = max;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        matrix = new int[100][100];
        for(int i = 0; i < 3; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;
        for(int i = 1; i <= 100; i++){

        }
        while(true){
            if(result > 100){
                result = -1;
                break;
            }
            else if(matrix[r-1][c-1] == k){
                break;
            }
            step();
            result++;
        }
        System.out.println(result);

    }
}