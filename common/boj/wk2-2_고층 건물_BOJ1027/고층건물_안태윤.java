import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());  //1  N =50
		int[] heights = new int[N];  //높이를 저장하는 1차원 배열
		int[] numberInSites = new int[N];  // 해당인덱스에서 볼 수 있는 건물의 수를 저장하는 배열
		StringTokenizer st = new StringTokenizer(br.readLine(), " "); //높이 입력

		for (int i = 0; i < N; i++) {
			heights[i] = Integer.parseInt(st.nextToken());
		}

		int maxCnt = 0;
		 //i=0 인 것부터
		for (int i = 0; i< N; i++) {
			double maxSlope = -Integer.MIN_VALUE; // 최대 기울값 저장 위한 변수
			int h = heights[i];  //높이

			//자신의 오른쪽 건물 순차적으로 선정
			for (int j = i + 1; j < N; j++) {
				int jh = heights[j]; //오른쪽 건물의 높이
				double a = getSlope(i,h,j,jh);//기울기 계산
				//기울기가 최대값보다 크면 최대값 갱신 및 현재 카운트 증가,
				if(maxSlope < a){
					maxSlope = a;
					numberInSites[i]++;
					numberInSites[j]++;
				}

			}
			maxCnt = numberInSites[i] >  maxCnt ? numberInSites[i] : maxCnt; //이전까지 최대 개수보다 크면 갱신
		}
		System.out.println(maxCnt);
	}
	 //두점을 잇는 선분의 기울기 구하는 함수
	public static double getSlope(int x1,int y1, int x2, int y2){
		return 	(y1-y2) / (double) (x1-x2);
	}

}
