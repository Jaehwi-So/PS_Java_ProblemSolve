import java.util.*;

class Solution {

    static long calc(long t, int[] cores){
        long count = 0;
        for(int i = 0; i < cores.length; i++){
            count += (t / cores[i]);
        }
        return count + cores.length; // 초기 시작 작업 포함
    }

    public int solution(int n, int[] cores) {

        if(cores.length >= n){
            return n;
        }

        long start = 0;
        long end = (long) (100000L * n);
        long target = n;

        //모든 작업이 Core에 "할당" 완료되는 시간 구하기
        while(start < end){ 
            long mid = (start + end) / 2;
            long job = calc(mid, cores);
            if(job >= target){
                end = mid;
            }
            else{
                start = mid + 1;
            }
        }

        long prevJobs = calc(end - 1, cores); //T-1 시점에 할당되는 작업수
        long leftJobs = n - prevJobs; //T 시점에 추가로 할당되어야하는 작업수

        int answer = 0;
        for(int i = 0; i < cores.length; i++){
            if(end % cores[i] == 0){
                answer = i + 1;
                leftJobs--;
            }
            if(leftJobs <= 0) break;
        }

        return answer;
    }
}

/**

//3초 시점(5개의 작업을 완료하는 시점)의 남은 작업량
/**
1. 0 (3초에 끝남)
2. -1 (2초에 끝남)
3. 0 (3초에 끝남)

13, [1,2,3]
// 7초 시점(12개의 작업을 완료하는 시점)의 남은 작업량
1. 0 (7초에 끝남)
2. -1 (6초에 끝남)
3. -1 (6초에 끝남)
**/
        
