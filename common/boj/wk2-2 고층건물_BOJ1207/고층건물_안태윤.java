import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1027 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());  1  N =50
		int[] heights = new int[N];  높이를 저장하는 1차원 배열
		StringTokenizer st = new StringTokenizer(br.readLine(), ); 높이 입력

		for (int i = 0; i  N; i++) {
			heights[i] = Integer.parseInt(st.nextToken());
		}

		boolean[][] matrix = new boolean[N][N];  서로 볼 수 있는지 기록하는 2차원 배열 0 자기자신 볼수 있으면 1, 없으면 -1
		int maxCnt = 0;
		 i=0 인 것부터
		for (int i = 0; i  N; i++) {
			int tempCnt = 0;
			int h = heights[i];  높이
			자신의 오른쪽 건물 순차적으로 선정
			for (int j = i + 1; j  N; j++) {
				int jh = heights[j]; 오른쪽 건물의 높이
				double a = getSlope(i,h,j,jh);기울기 계산
				boolean canSee = true;  볼 수 있는지 체크하는 변수
				 두 건물 사이에 있는 건물 순회
				for (int k = i+1; k j ; k++) {
					int kh = heights[k];
					double m = 	getSlope(i,h,k,kh);기울기 계산
					if(m = a ){ 두 건물 사이에 기울기가 더 크거나 같은 건물이 있으면 false, break
						canSee = false;
						break;
					}
				}
				if(canSee) {볼 수 있으면 tempCnt 증가, matrix 표시
					tempCnt++;
					matrix[i][j] = true;
				};
			}
			자신의 왼쪽 건물과의 결과 중 1 인 것만큼 증가
			for (int j = i-1; j = 0 ; j--) {
				if(matrix[j][i]) tempCnt++;
			}
			maxCnt = tempCnt  maxCnt  tempCnt  maxCnt; 이전까지 최대 개수보다 크면 갱신
		}
		System.out.println(maxCnt);
	}
	 두점을 잇는 선분의 기울기 구하는 함수
	public static double getSlope(int x1,int y1, int x2, int y2){
		return 	(y1-y2)  (double) (x1-x2);
	}

}
