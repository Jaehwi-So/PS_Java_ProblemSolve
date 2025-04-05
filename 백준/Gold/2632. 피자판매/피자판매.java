import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long size = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int[] aArr = new int[a*2];
        int[] bArr = new int[b*2];

        for(int i = 1; i <= a; i++){
            st = new StringTokenizer(br.readLine());
            aArr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = a+1; i < a*2; i++){
            aArr[i] = aArr[i-a];
        }

        for(int i = 1; i <= b; i++){
            st = new StringTokenizer(br.readLine());
            bArr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = b+1; i < b*2; i++){
            bArr[i] = bArr[i-b];
        }

        long[] aPrArr = new long[a*2];
        long[] bPrArr = new long[b*2];

        for(int i = 1; i < a*2; i++){
            aPrArr[i] = aPrArr[i-1] + aArr[i];
        }
        for(int i = 1; i < b*2; i++){
            bPrArr[i] = bPrArr[i-1] + bArr[i];
        }

        List<Long> aList = new ArrayList<>();
        List<Long> bList = new ArrayList<>();
        Map<Long, Integer> aCount = new HashMap<>();
        Map<Long, Integer> bCount = new HashMap<>();

        for(int i = a; i < a*2; i++){
            for(int j = 1; j < a; j++){
                long number = aPrArr[i] - aPrArr[i-j];
                if(!aCount.containsKey(number)){
                    aCount.put(number, 1);
                    aList.add(number);
                }
                else{
                    aCount.put(number, aCount.get(number) + 1);
                }
            }
        }

        for(int i = b; i < b*2; i++){
            for(int j = 1; j < b; j++){
                long number = bPrArr[i] - bPrArr[i-j];
                if(!bCount.containsKey(number)){
                    bCount.put(number, 1);
                    bList.add(number);
                }
                else{
                    bCount.put(number, bCount.get(number) + 1);
                }
            }
        }

        aList.add(aPrArr[a]);
        aCount.put(aPrArr[a], 1);
        bList.add(bPrArr[b]);
        bCount.put(bPrArr[b], 1);
        Collections.sort(aList);
        Collections.sort(bList);

//        System.out.println(aCount);
//        System.out.println(bCount);

        long result = 0;
        for(long current : aList){
            long findNumber = size - current;
            if(Collections.binarySearch(bList, findNumber) > -1){
                result += (aCount.get(current) * bCount.get(findNumber));
            }
        }


        if(aCount.containsKey(size)) result += aCount.get(size);
        if(bCount.containsKey(size)) result += bCount.get(size);


        System.out.println(result);
    }
}