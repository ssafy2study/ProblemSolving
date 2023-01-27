import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//시간초과
public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] stars = new int[K][2];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine()," ");
			stars[i][0] = Integer.parseInt(st.nextToken());
			stars[i][1] = Integer.parseInt(st.nextToken());
		}
		int limitX=N-L;
		int limitY=M-L;
		int cnt = K;
		for (int x = 0; x < limitX; x++) {
			for(int y = 0; y < limitY; y++){
				int maxX = x+L;
				int maxY = y+L;
				int starCount = K;
				for (int[] star:stars) {
					if(star[0] <=  maxX && star[0]  >= x && star[1] <= maxY  && star[1] >= y){
						starCount--;
					}
				}
				cnt = cnt > starCount ? starCount : cnt;
			}
		}
		System.out.println(cnt);
	}
}
