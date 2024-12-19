import java.io.*;
import java.util.*;

// 시작점 모두 큐에넣고 시작
// 대문자 만나면 키 있으면 탐색, 없으면 다음번 큐에 일단 저장
// 소문자 만나면 키 추가
// 탐색 시 키를 더이상 얻어내지 못했으면 bfs 반복 종료.

public class Main {
    static int n;
    static int m;
    static char[][] matrix;
    static boolean[] key;
    static Queue<int[]> queue = new LinkedList<>();
    static Queue<int[]> temp = new LinkedList<>();
    static int result = 0;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};
    static boolean[][] visited;

    static boolean bfs(){
        boolean hasNext = false;
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            char ch = matrix[current[0]][current[1]];
            int nch = (int)ch;

            if(ch >= 'A' && ch <= 'Z'){
                if(!key[nch - 'A']){
                    temp.offer(current);
                    continue;
                }

            }

            else if(ch == '$'){
                result++;
            }

            else if(ch >= 'a' && nch <= 'z'){
                if(!key[nch - 'a']){
                    key[nch - 'a'] = true;
                    hasNext = true;
                }
            }

            for(int i = 0; i < 4; i++){
                int row = current[0] + dy[i];
                int col = current[1] + dx[i];
                if(row < 0 || col < 0 || row >= n || col >= m){
                    continue;
                }
                if(matrix[row][col] == '*'){
                    continue;
                }
                if(!visited[row][col]){
                    visited[row][col] = true;
                    queue.offer(new int[]{row, col});
                }
            }
        }
        return hasNext;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int test = Integer.parseInt(st.nextToken());
        for(int t = 0; t < test; t++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            matrix = new char[n][m];
            visited = new boolean[n][m];
            key = new boolean[26];
            queue.clear();
            temp.clear();
            result = 0;
            for(int i = 0; i < n; i++){
                matrix[i] = br.readLine().toCharArray();
            }


            char[] keySet = br.readLine().toCharArray();
            if(keySet[0] != '0'){
                for(char c : keySet){
                    int nch = (int)c - 'a';
                    key[nch] = true;
                }
            }

            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    if(i == 0 || j == 0 || i == n-1 || j == m-1){
                        char ch = matrix[i][j];
                        if(ch != '*'){
                            queue.offer(new int[]{i ,j});
                            visited[i][j] = true;
                        }
                    }
                }
            }



            while(true){
                boolean next = bfs();
                if(next){
                    while(!temp.isEmpty()){
                        int[] point = temp.poll();
                        queue.offer(point);
                    }

                }
                else break;
            }

            sb.append(result).append("\n");
        }
        System.out.println(sb);

    }
}