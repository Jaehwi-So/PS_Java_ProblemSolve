import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int m;
    static int[][] map;
    static int result = Integer.MAX_VALUE;

    static int totalChicken = 0;
    static int chicken = 0;

    static int calc(){
        int distance = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(map[i][j] == 1){
                    int min = Integer.MAX_VALUE;
                    for(int k = 0; k < n; k++){
                        for(int l = 0; l < n; l++){
                            if(map[k][l] == 2){
                                min = Math.min(min, (Math.abs(k-i) + Math.abs(l-j)));
                            }
                        }
                    }
                    distance += min;
                }
            }
        }
        return distance;
    }
// 2+2+1+2+1+2+1
    static void print(){
        for(int[] line : map){
            System.out.println(Arrays.toString(line));

        }
        System.out.println();
    }


    static void closeStore(int x, int y){
        if(chicken == m){
            int distance = calc();
//            print();
            result = Math.min(distance, result);
        }
        else{
            boolean find = false;

            if(y > n - 1){
                x++;
                y = 0;
            }
            if(x > n - 1){
                return;
            }

            for(int j = y; j < n; j++){
                if(map[x][j] == 2){
                    map[x][j] = 0;
                    chicken--;
                    closeStore(x, j+1);
                    map[x][j] = 2;
                    chicken++;
                    closeStore(x, j+1);
                    find = true;
                    break;
                }
            }

            if(find == false){
                for(int i = x+1; i < n; i++){
                    for(int j = 0; j < n; j++){
                        if(map[i][j] == 2){
                            map[i][j] = 0;
                            chicken--;
                            closeStore(i, j+1);
                            map[i][j] = 2;
                            chicken++;
                            closeStore(i, j+1);
                            find = true;
                            break;
                        }
                    }
                    if(find){
                        break;
                    }
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        m = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2){
                    totalChicken++;
                }
            }
        }
        chicken = totalChicken;
//        System.out.println(calc());
        closeStore(0, 0);
        System.out.println(result);

    }
}

/**
 * 3 1
 * 1 2 0
 * 0 0 0
 * 1 0 2
 */
