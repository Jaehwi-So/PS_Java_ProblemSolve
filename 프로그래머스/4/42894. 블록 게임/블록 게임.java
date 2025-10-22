import java.util.*;

class Block{
    int index;
    int srow = 50;
    int scol = 50;
    int erow, ecol = 0;
    int canDelete = -1;
    String reason;
    
    public Block(int index){
        this.index = index;
    }
    public void setBlock(int row, int col){
        this.srow = Math.min(row, srow);
        this.scol = Math.min(col, scol);
        this.erow = Math.max(row, erow);
        this.ecol = Math.max(col, ecol);
    }
    
    public String toString(){
        return "[" + index + " : " + srow + ", " + scol + " || " + erow + ", " + ecol + " #Result : " + canDelete + " " + reason + "] \n";
    }
    
    public int calc(int[][] board, List<Block> blocks){
    
        if(canDelete == -1) {
            canDelete = 1;
            
            for(int j = scol; j <= ecol; j++){
                if(board[erow][j] != index){
                    canDelete = 0;
                    reason = "블록 자체가 불가능";
                    return canDelete;
                }
            }
            
            for(int i = erow; i >= srow; i--){
                for(int j = scol; j <= ecol; j++){
                    
                    if(board[i][j] != index){
                        for(int r = i; r >= 0; r--){
                            if(board[r][j] > 0){
                                canDelete = blocks.get(board[r][j]).calc(board, blocks);
                                if(canDelete == 0){
                                    reason = board[r][j] + "번 블록에 의해 막힘";
                                    return canDelete;
                                }
                            } 
                        }
                    }
                }
            }
        }
        return canDelete;
    }
}


class Solution {
    static int n, m;
    static int k;
    static List<Block> blocks;
    static int[][] board;
    
    public int solution(int[][] board) {
        blocks = new ArrayList();
        blocks.add(new Block(0));
        this.n = board.length;
        this.m = board[0].length;
        this.k = 0;
        this.board = board;
        
        Set<Integer> set = new HashSet();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(board[i][j] > 0){
                    k = Math.max(k, board[i][j]);
                    set.add(board[i][j]);
                }
            }
        }
        
        for(int i = 1; i <= k; i++){
            blocks.add(new Block(i));
        }
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(board[i][j] > 0){
                    blocks.get(board[i][j]).setBlock(i, j);
                }
            }
        }
        

        int answer = 0;
        for(int i : set){
            answer += blocks.get(i).calc(board, blocks);
        }
    
        // System.out.println(blocks);
        
    
        return answer;
    }
}

/**
[[1, 2, 0, 0],
[1, 2, 2, 2],
[1, 1, 0, 0],
[0, 0, 0, 0]]


[[0,0,0,0,0,0,0,0,0,0]
,[0,0,0,2,2,0,0,0,0,0]
,[0,0,0,2,1,0,0,0,0,0]
,[0,0,0,2,1,0,0,0,0,0]
,[0,0,0,0,1,1,0,0,0,0]
,[0,0,0,0,0,0,0,0,0,0]]

1
**/