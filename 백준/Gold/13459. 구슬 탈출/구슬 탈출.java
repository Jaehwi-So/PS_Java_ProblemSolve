import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static char[][] matrix;
    static int[] rpos = new int[2];
    static int[] bpos = new int[2];
    static int[] dy = {0, -1, 0, 1};
    static int[] dx = {-1, 0, 1, 0};
    static boolean result = false;

    static boolean move(int color, int dir){
        int[] pos = rpos;
        int[] anotherPos = bpos;
        if(color == 1){
            pos = bpos;
            anotherPos = rpos;
        }
        while(pos[0] >= 0 || pos[1] >= 0 || pos[0] < n || pos[1] < m){
            int row = pos[0] + dy[dir];
            int col = pos[1] + dx[dir];
            if(matrix[row][col] == 'O'){
                pos[0] = -1;
                pos[1] = -1;
                return true;
            }
            if(matrix[row][col] == '#') return false;
            if(row == anotherPos[0] && col == anotherPos[1]) return false;
            pos[0] = row;
            pos[1] = col;
        }

        return false;
    }

    static void dfs(int sequence){
        if(sequence == 11) return;
        if(result) return;

//        System.out.println(sequence + " " + Arrays.toString(rpos) + " " + Arrays.toString(bpos));

        for(int i = 0; i < 4; i++){
            boolean success, fail;
            int[] tmpR = Arrays.copyOf(rpos, 2);
            int[] tmpB = Arrays.copyOf(bpos, 2);
            if(i % 2 == 0){ //좌우 이동
                if(rpos[0] == bpos[0] && ((i == 0 && (bpos[1] < rpos[1])) || (i == 2 && (bpos[1] > rpos[1]))) ){
                    fail = move(1, i);
                    success = move(0, i);
                }
                else{
                    success = move(0, i);
                    fail = move(1, i);
                }
            }
            else{ //상하 이동
                if(rpos[1] == bpos[1] && ((i == 1 && (bpos[0] < rpos[0])) || (i == 3 && (bpos[0] > rpos[0])))){
                    fail = move(1, i);
                    success = move(0, i);
                }
                else{
                    success = move(0, i);
                    fail = move(1, i);
                }
            }
//            System.out.println(i + " " + success + " " + fail);
            if(fail){
                rpos = tmpR;
                bpos = tmpB;
            }
            else if(success){
                result = true;
                return;
            }
            else{
                dfs(sequence + 1);
                rpos = tmpR;
                bpos = tmpB;
            }

        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new char[n][m];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            char[] chs = st.nextToken().toCharArray();
            for(int j = 0; j < m; j++){
                if(chs[j] == 'R'){
                    rpos = new int[]{i, j};
                    chs[j] = '.';
                }
                else if(chs[j] == 'B'){
                    bpos = new int[]{i, j};
                    chs[j] = '.';
                }
                matrix[i][j] = chs[j];
            }
        }

//        if(bpos[0] > rpos[0])
        dfs(1);

        if(result) System.out.println(1);
        else System.out.println(0);

    }
}