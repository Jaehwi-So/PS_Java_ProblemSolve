import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    //"___'___'___"
    // 0~8, 3~5

    static void replace(char[] array, int left, int right){
        for(int i = left; i <= right; i++){
            array[i] = ' ';
        }
    }
    static void cantore(char[] array, int left, int right){
        if(right - left > 1){
            int range = (right - left + 1) / 3; // 3
            int leftM = left + range; // 3
            int rightM = right - range; // 5
            replace(array, leftM, rightM);
            cantore(array, left, leftM - 1); //0 - 2
            cantore(array, rightM + 1, right); //
        }
    }
    static void execute(char[] array){
        cantore(array, 0, array.length - 1);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            int n = Integer.parseInt(line);
            int length = (int) Math.pow(3, n);
            char[] array = new char[length];
            Arrays.fill(array, '-');
            execute(array);
            for(char c : array){
                sb.append(c);
            }
            sb.append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}
