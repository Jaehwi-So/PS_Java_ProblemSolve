import java.util.*;
import java.io.*;

class Meeting implements Comparable<Meeting> {

    int start;
    int end;

    public Meeting(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Meeting m){
        if(m.end == this.end){
            return this.start - m.start;
        }
        return this.end - m.end;
    }

    public String toString(){
        return this.start + "," + this.end;
    }

}

public class Main{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        List<Meeting> meetings = new ArrayList();

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            meetings.add(new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }


        Collections.sort(meetings);

        int[] rooms = new int[k];
        int result = 0;
        for (Meeting m : meetings) {
            int maxEnd = -1;
            int idx = -1;

            // 현재 회의실 중 가장 격차가 적은 회의실 선택
            for (int i = 0; i < k; i++) {
                if (rooms[i] < m.start && maxEnd < rooms[i]) {
                    idx = i;
                    maxEnd = rooms[i];
                }
            }

            if (idx != -1) {
                result++;
                rooms[idx] = m.end;
            }
        }

        System.out.println(result);

    }
}