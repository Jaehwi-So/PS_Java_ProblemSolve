import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] score = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            score[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(score);
        long result = 0;

//        System.out.println(Arrays.toString(score));

        // -6 3 3 3 3 3 3
        for(int i = 0; i < n - 2; i++){
            int s = score[i] * -1;
            int left = i + 1;
            int right = n - 1;
            while(left < right){
                int k = score[left] + score[right];

                if(k == s){
                    if(score[left] == score[right]){
                        int total = (right - left) + 1;
                        long cnt = total * (total - 1) / 2;
                        result += cnt;
                        break;
                    }

                    int l = 1;
                    int r = 1;
                    while(left < right && score[left] == score[left + 1]){
                        l++;
                        left++;
                    }
                    while(left < right && score[right] == score[right - 1]){
                        r++;
                        right--;
                    }
                    left++;
                    right--;
                    result += l * r;

                }

                else if(k < s){
                    left++;
                }
                else{
                    right--;
                }
            }
        }
        System.out.println(result);

    }
}