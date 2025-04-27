import java.io.*;
import java.util.*;

public class Main {
    static int m;
    static int s;
    static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static List<int[]> current = new ArrayList<>();
    static List<int[]> before = new ArrayList<>();
    static int[][] matrix = new int[5][5];
    static int[] shark = new int[2];

    static void move(int turn){
        for(int[] fish : current){
            for(int i = 1; i <= 8; i++){
                int dir = ((fish[2] - i + 8) % 8) + 1;
                int row = fish[0] + dy[dir];
                int col = fish[1] + dx[dir];
                if(row <= 0 || col <= 0 || row > 4 || col > 4) continue;
                if(matrix[row][col] >= turn - 2) continue;
                if(shark[0] == row && shark[1] == col) continue;

                fish[0] = row;
                fish[1] = col;
                fish[2] = dir;
                break;
            }
        }
    }

    static void sharkMove(int turn){
        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, -1, 0, 1};

        int max = -1;
        int[][] points = new int[3][2];

        int[][] counts = new int[5][5];
        for(int[] fish : current){
            counts[fish[0]][fish[1]]++;
        }

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                for(int k = 0; k < 4; k++){
                    int[][] p = new int[4][2];
                    boolean[][] visited = new boolean[5][5];
                    int cnt = 0;
                    p[0][0] = shark[0];
                    p[0][1] = shark[1];
                    boolean success = true;
                    for(int l = 1; l <= 3; l++){
                        int dir = 0;
                        if(l == 1) dir = i;
                        else if(l == 2) dir = j;
                        else if(l == 3) dir = k;
                        int row = p[l-1][0] + dy[dir];
                        int col = p[l-1][1] + dx[dir];
                        if(row <= 0 || col <= 0 || row > 4 || col > 4){
                            success = false;
                            break;
                        }
                        p[l][0] = row;
                        p[l][1] = col;
                        if(!visited[row][col]){
                            visited[row][col] = true;
                            cnt += counts[row][col];
                        }
                    }
                    if(success){
                        if(max < cnt){
                            max = cnt;
                            for(int l = 0; l < 3; l++){
                                points[l][0] = p[l+1][0];
                                points[l][1] = p[l+1][1];
                            }
                        }
                    }
                }
            }
        }

        List<int[]> temp = new ArrayList<>();
        for(int[] fish : current){
            boolean delete = false;
            for(int i = 0; i < 3; i++){
                if(fish[0] == points[i][0] && fish[1] == points[i][1]){
                    delete = true;
                    break;
                }
            }
            if(delete){
                matrix[fish[0]][fish[1]] = turn;
            }
            else{
                temp.add(fish);
            }
        }

        current.clear();
        for(int[] fish : temp){
            current.add(fish);
        }
        for(int[] fish : before){
            current.add(fish);
        }
        before.clear();

        shark[0] = points[2][0];
        shark[1] = points[2][1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            current.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        st = new StringTokenizer(br.readLine());
        shark[0] = Integer.parseInt(st.nextToken());
        shark[1] = Integer.parseInt(st.nextToken());

        for(int[] line : matrix){
            Arrays.fill(line, -10);
        }

        for(int t = 1; t <= s; t++){
            // 1. 물고기 복사 준비
            for(int[] fish : current){
                before.add(Arrays.copyOf(fish, 3));
            }

            // 2. 물고기 이동
            move(t);

            // 3. 상어 이동 및 복제
            sharkMove(t);
        }

        System.out.println(current.size());

    }
}