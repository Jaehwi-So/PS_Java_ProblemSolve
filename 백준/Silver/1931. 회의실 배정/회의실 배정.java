import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Time implements Comparable<Time>{
    long start;
    long end;

    public Time(long start, long end){
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Time t) {
        if(this.end > t.end){
            return 1;
        }
        else{
            return -1;
        }
    }

    public String toString(){
        return this.start + " : " + this.end;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Time> list = new ArrayList<>();
        int n = Integer.parseInt(st.nextToken());
        Map<Long, Long> map = new HashMap();
        int count = 0;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            long start = Integer.parseInt(st.nextToken());
            long end = Integer.parseInt(st.nextToken());
            if(!map.containsKey(start)){
                map.put(start, end);
            }
            //시작하자마자 끝나는 케이스(1~1..) 경우 숫자 늘려주고 맵은 갱신
            else if(map.get(start) == start){
                map.replace(start, end);
                count++;
            }
            //끝나는 시간이 더 빠른 것으로 갱신
            else if(map.get(start) > end){
                if(start == end){
                    count++;
                }
                else{
                    map.replace(start, end);
                }
            }


        }
        for(long key : map.keySet()){
            list.add(new Time(key, map.get(key)));
        }

        Collections.sort(list);
//        System.out.println(list);
//        System.out.println(count);

        long max = 0;
        long min = list.get(0).start;

        for(int i = 0; i < list.size(); i++){

            if(list.get(i).start >= max && list.get(i).start >= min){
//                System.out.println(list.get(i).start + " " + current);
                if(list.get(i).start != list.get(i).end){
                    max = list.get(i).end;
                    min = Math.min(min, list.get(i).start);
                }
                count++;
            }
        }
        System.out.println(count);


    }
}
