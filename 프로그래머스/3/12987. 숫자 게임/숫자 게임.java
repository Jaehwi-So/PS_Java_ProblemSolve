import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        
        int n = A.length;
        int aidx = 0;
        int bidx = 0;
        int answer = 0;
        
        while(aidx < n && bidx < n){
            if(A[aidx] < B[bidx]){
                aidx++;
                answer++;
            }
            bidx++;
        }
        

        return answer;
    }
}

/**
1 3 5 7
2 2 6 8


1 3 4 5
1 2 5 6


**/