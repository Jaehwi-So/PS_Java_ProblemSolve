import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static char[][] matrix;
    static int[] dy = {0, 0, -1, 1, -1, -1, 1, 1};
    static int[] dx = {1, -1, 0, 0, -1, 1, -1, 1};
    static Stack<int[]> stack = new Stack<>();
    static Stack<int[]> temp = new Stack<>();
    static Set<Integer> last = new HashSet<>();
    static Set<Integer> removed = new HashSet<>();
    static Map<Integer, Integer> reference = new HashMap();
    static int sequence = 1;

    static void dfs(){

        while(!stack.isEmpty()){
            int[] current = stack.pop();
            int row = current[0];
            int col = current[1];
            int index = current[2];
            int ref = current[3];

            if(matrix[row][col] == '.'){
                matrix[row][col] = '-';
                for(int i = 0; i < 4; i++){
                    int nrow = row + dy[i];
                    int ncol = col + dx[i];
                    if(nrow < 0 || ncol < 0 || nrow >= n || ncol >= m) continue;
                    if(matrix[nrow][ncol] == 'x'){
                        stack.push(new int[]{nrow, ncol, sequence, ref});
                        sequence++;
                    }
                    else if(matrix[nrow][ncol] == '.'){
                        stack.push(new int[]{nrow, ncol, 0, ref});
                    }
                }
            }
            else if(matrix[row][col] == 'x'){
//                matrix[row][col] = (char)(sequence + 64);
                matrix[row][col] = 'o';
                reference.put(index, ref);
                last.remove(ref);
                removed.add(ref);
                if(!removed.contains(index)) last.add(index);
                for(int i = 0; i < 8; i++){
                    int nrow = row + dy[i];
                    int ncol = col + dx[i];
                    if(nrow < 0 || ncol < 0 || nrow >= n || ncol >= m) continue;
                    if(matrix[nrow][ncol] == 'x'){
                        stack.push(new int[]{nrow, ncol, index, ref});
                    }
                    else if(matrix[nrow][ncol] == '.'){
                        temp.push(new int[]{nrow, ncol, 0, index});
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()) + 2;
        m = Integer.parseInt(st.nextToken()) + 2;
        matrix = new char[n][m];
        for(char[] line : matrix){
            Arrays.fill(line, '.');
        }
        for(int i = 1; i < n - 1; i++){
            st = new StringTokenizer(br.readLine());
            char[] chs = st.nextToken().toCharArray();
            for(int j = 1; j < m - 1; j++){
                matrix[i][j] = chs[j-1];
            }
        }

        stack.push(new int[]{0, 0, 0, -1});


        while(!stack.isEmpty()){
            dfs();
            while(!temp.isEmpty()){
                stack.push(temp.pop());
            }
        }

        Map<Integer, Integer> calc = new HashMap<>();
        Queue<int[]> queue = new LinkedList<>();
        for(int k : last){
            queue.offer(new int[]{k, 1});
        }


        while(!queue.isEmpty()){
            int[] current = queue.poll();
            if(!calc.containsKey(current[0])){
                calc.put(current[0], current[1]);
            }
            else{
                calc.put(current[0], Math.max(calc.get(current[0]), current[1]));
            }
            if(reference.get(current[0]) != -1){
                queue.offer(new int[]{reference.get(current[0]), current[1] + 1});
            }
        }


        int[] result = new int[m+1];
        int max = 0;

        for(int key : calc.keySet()){
            result[calc.get(key)]++;
            max = Math.max(max, calc.get(key));
        }

        StringBuilder sb = new StringBuilder();
        if(max == 0) sb.append(-1);
        for(int i = 1; i <= max; i++){
            sb.append(result[i]).append(" ");
        }

        System.out.println(sb);
    }
}