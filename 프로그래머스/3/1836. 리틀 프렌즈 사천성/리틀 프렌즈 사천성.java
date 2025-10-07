import java.util.*;

class Solution {
    
    
    static boolean available(char chs, int srow, int scol, int erow, int ecol, char[][] matrix){
        
        boolean success = true;
        boolean success2 = true;
        
        if(scol <= ecol){
            for(int i = scol; i <= ecol; i++){
                if(matrix[srow][i] != '.' && matrix[srow][i] != chs){
                    success = false;
                }
                if(matrix[erow][i] != '.' && matrix[erow][i] != chs){
                    success2 = false;
                }
            }
        }
        else{
            for(int i = scol; i >= ecol; i--){
                if(matrix[srow][i] != '.' && matrix[srow][i] != chs){
                    success = false;
                }
                if(matrix[erow][i] != '.' && matrix[erow][i] != chs){
                    success2 = false;
                }
            }
        }
        
        if(srow <= erow){
            for(int i = srow; i <= erow; i++){
                if(matrix[i][ecol] != '.' && matrix[i][ecol] != chs){
                    success = false;
                }
                if(matrix[i][scol] != '.' && matrix[i][scol] != chs){
                    success2 = false;
                }
            }
        }
        else{
            for(int i = srow; i >= erow; i--){
                if(matrix[i][ecol] != '.' && matrix[i][ecol] != chs){
                    success = false;
                }
                if(matrix[i][scol] != '.' && matrix[i][scol] != chs){
                    success2 = false;
                }
            }
        }
        
        
        
        if(success || success2){
            matrix[srow][scol] = '.';
            matrix[erow][ecol] = '.';
            return true;
        }
        
        return false;
        
    }

    
    public String solution(int m, int n, String[] board) {
    
        char[][] matrix = new char[m][n];
        
        HashMap<Character, int[]> hm = new HashMap();
        for(int i = 0; i < m; i++){
            matrix[i] = board[i].toCharArray();
            for(int j = 0; j < n; j++){
                if(matrix[i][j] != '.' && matrix[i][j] != '*'){
                    if(!hm.containsKey(matrix[i][j])){
                        hm.put(matrix[i][j], new int[]{i, j, 0, 0});
                    }
                    else{
                        int[] point = hm.get(matrix[i][j]);
                        point[2] = i;
                        point[3] = j;
                        hm.put(matrix[i][j], point);
                    }
                    
                }
            }
        }
        
        TreeMap<Character, int[]> map = new TreeMap(hm);
        
        // System.out.println(map);
        
        // for(char key : map.keySet()){
        //     System.out.println(key + " : " + Arrays.toString(map.get(key)));
        // }
        StringBuilder sb = new StringBuilder();
        
        while(true){
            boolean exit = true;
            
            for(char key : map.keySet()){
                int[] point = map.get(key);
                if(matrix[point[0]][point[1]] == '.') continue;
                if(available(key, point[0], point[1], point[2], point[3], matrix)){
                    sb.append(key);
                    exit = false;
                    break;
                }
            }

            if(exit) break;
        }
        
        String answer = sb.toString();
        if(answer.length() < map.size()) answer = "IMPOSSIBLE";
        return answer;
    }
}

/**
AAB
...
BCC

2 2 ["ZA", "ZA"] "AZ"
1 2 ["AA"] "A"
3 3 ["A.B", "B.A", "C.C"] "IMPOSSIBLE"
3 3 ["AZA", "BZB", "**"] "ZAB"
1 8 ["ABC.CBA"] "CBA"
6 1 ["A", "B", "E", "E", "B", "A"] "EBA"
4 4 ["A..C", "..CB", "B...", "...A"] "BAC"
3 3 ["CCB", "A.B", "AEE"] "ABCE"
**/