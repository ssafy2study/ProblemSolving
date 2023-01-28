import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	static int N, M, L, K;
  static List<int[]> stars;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]); //별똥별이 떨어지는 구역의 가로길이
		M = Integer.parseInt(line[1]); //별똥별이 떨어지는 구역의 세로길이
		L = Integer.parseInt(line[2]); //트램펄린의 한 변의 길이
		K = Integer.parseInt(line[3]); //별똥별의 수
		
		stars = new ArrayList<>();
		
		int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
		int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
		
		for(int i = 0 ; i < K ; i++){
			line = br.readLine().split(" ");
			int x = Integer.parseInt(line[0]);
      int y = Integer.parseInt(line[1]);
      stars.add(new int[]{x, y});
    }
		
    int res = Integer.MIN_VALUE;

    for(int[] s1 : stars){
        for(int[] s2 : stars){
            res = Math.max(res, boundStar(s1[0], s2[1]));
        }
    }
		
		
		//출력
		bw.write(K - res + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	private static int boundStar(int i, int j) {
      int res = 0;

      for(int[] s : stars){
          if(i <= s[0] && s[0] <= i + L && j <= s[1] && s[1] <= j + L ) {
            res++;
          }
      }

      return res;
  }

}
