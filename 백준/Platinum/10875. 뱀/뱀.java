import java.io.*;
import java.util.*;

public class Main {
    static int l;
    static int n;
    static char[] direct;
    static int[] distance;
    static int dir = 0;
    static int[] dy = {0, -1, 0, 1}; //우 상 좌 하 (L회전시)
    static int[] dx = {1, 0, -1, 0};
    static List<int[][]> lines = new ArrayList<>();

    static void rotate(char r){
        if(r == 'L'){
            dir = (dir + 1) % 4;
        }
        else{
            dir = (dir + 4 - 1) % 4;
        }
    }

    static int compare(int[][] line1, int[][] line2){
        int line1XMax = Math.max(line1[0][0], line1[1][0]);
        int line1YMax = Math.max(line1[0][1], line1[1][1]);
        int line2XMax = Math.max(line2[0][0], line2[1][0]);
        int line2YMax = Math.max(line2[0][1], line2[1][1]);

        int line1XMin = Math.min(line1[0][0], line1[1][0]);
        int line1YMin = Math.min(line1[0][1], line1[1][1]);
        int line2XMin = Math.min(line2[0][0], line2[1][0]);
        int line2YMin= Math.min(line2[0][1], line2[1][1]);

        int compX = Math.max(line1XMax, line2XMax) - Math.min(line1XMin, line2XMin) + 1;
        int compY = Math.max(line1YMax, line2YMax) - Math.min(line1YMin, line2YMin) + 1;

        int totalX = (line1XMax - line1XMin) + 1 + (line2XMax - line2XMin) + 1;
        int totalY = (line1YMax - line1YMin) + 1 + (line2YMax - line2YMin) + 1;


        // ax + by + c = 0;
        // y = 3 : b = 1, c = -3
        // x = 3 : a = 1, c = -3
        if(compX < totalX && compY < totalY){
            if(line2XMax == line2XMin){  // 가로줄 -> x좌표와의 거리
                if(line1XMax == line1XMin){ // 가로줄 vs 가로줄
                    //return Math.min(Math.abs(line2[0][0] - line1XMax), Math.abs(line2[0][0] - line1XMin));
                    return Math.min(Math.abs(line2[0][1] - line1YMax), Math.abs(line2[0][1] - line1YMin));
                }
                else{   //가로줄 vs 세로줄
                    return Math.abs(line2[0][1] - line1YMax);
                }
            }
            else{ //세로줄 -> y좌표와의 거리
                if(line1YMax == line1YMin){ // 세로줄 vs 세로줄
                    return Math.min(Math.abs(line2[0][0] - line1XMax), Math.abs(line2[0][0] - line1XMin));
                }
                else{ //세로줄 vs 가로줄
                    return Math.abs(line2[0][0] - line1XMax);
                }
            }
        }

        return -1;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        l = (Integer.parseInt(st.nextToken()) * 2) + 1;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        distance = new int[n+1];
        direct = new char[n+1];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            distance[i] = Integer.parseInt(st.nextToken());
            direct[i] = st.nextToken().charAt(0);
        }

        //마지막 회전 이후 진행 가능하다면 최대치로 직진
        distance[n] = l + 100;
        direct[n] = 'L';

        // 0-8
        // 1 2 3 4 5 6 7
        int[] points = new int[]{(l/2) + 1, (l/2) + 1};
//        System.out.println(Arrays.toString(points));
        lines.add(new int[][]{{0, 0}, {0, l+1}});
        lines.add(new int[][]{{0, 0}, {l+1, 0}});
        lines.add(new int[][]{{l+1, 0}, {l+1, l+1}});
        lines.add(new int[][]{{0, l+1}, {l+1, l+1}});
        lines.add(new int[][]{{0, l+1}, {l+1, l+1}}); //제외되는 직전간선을 위해서 더미 데이터

        long time = 0;
        boolean exit = false;
        for(int i = 0; i < n+1; i++){
            int[][] current = new int[][]{
                    {points[0], points[1]},
                    {(points[0] + (dx[dir] * distance[i])), (points[1] + (dy[dir] * distance[i]))}};

//            System.out.println(String.format("Current : (%d, %d), (%d, %d)", current[0][0], current[0][1], current[1][0], current[1][1]));

            int min = Integer.MAX_VALUE;
            for(int j = 0; j < lines.size() - 1; j++){ //만나는 간선 확인(직전간선 제외). 기존 간선과 만난다면 최소거리 계산 후 종료
                int[][] line = lines.get(j);
                int comp = compare(line, current);

//                System.out.println(String.format("Compare (%d, %d), (%d, %d) vs (%d, %d), (%d, %d) = %d", line[0][0], line[0][1], line[1][0], line[1][1],
//                        current[0][0], current[0][1], current[1][0], current[1][1], comp));

                if(comp != -1){
                    exit = true;
                    min = Math.min(min, comp);
                }
            }

            if(exit){
                time += (min);
                break;
            }

            points[0] = current[1][0];
            points[1] = current[1][1];
            lines.add(current);
            time += distance[i];
            rotate(direct[i]);
        }


//        for(int[][] line : lines){
//            System.out.println(String.format("(%d, %d), (%d, %d)", line[0][0], line[0][1], line[1][0], line[1][1]));
//        }


        System.out.println(time);

    }

}