import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int[][] array;
    static Map<Integer, Integer> map;

    static int check(int x1, int y1, int range){
        if(range == 1){
            return array[y1][x1];
        }
        else{
            for(int i = y1; i < y1 + range; i++){
                for(int j = x1; j < x1 + range; j++){
                    if(array[i][j] != array[y1][x1]){
                        return 2;
                    }
                }
            }
        }
        return array[y1][x1];
    }
    static void split(int x1, int y1, int range){ // 9
        int ck = check(x1, y1, range);
        if(ck == 2){
            int step = range / 3; // 3
            for(int i = y1; i < y1 + range; i+= step){
                for(int j = x1; j < x1 + range; j+= step){
                    split(j, i, step);
                }
            }
        }
        else{
            map.put(ck, map.get(ck) + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        array = new int[n][n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        map = new HashMap<>();
        map.put(-1, 0);
        map.put(0, 0);
        map.put(1, 0);

        split(0, 0, n);
        System.out.println(map.get(-1));
        System.out.println(map.get(0));
        System.out.println(map.get(1));


    }
}

