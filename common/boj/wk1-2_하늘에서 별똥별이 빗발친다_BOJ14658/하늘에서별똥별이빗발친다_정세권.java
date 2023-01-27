package study.ssafy.bundler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * [결과] SUCCESS
 * 
 * [상태] 구글링 함^^
 * 
 * [접근] (1) 완탐으로 문제 품 -> 메모리 초과
 * 		  (2) Map을 -> bar -> comp : 압축 하여 풀었음 -> 메모리 초과
 * 				=> 원래 map 자체가 너무 커서 메모리 초과가 난걸로 유추 중..
 * 		  (3) Star의 좌표 기준으로 3중 for문으로 품 ->
 */

public class 하늘에서별똥별이빗발친다_정세권 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 구역 가로 길이
		int M = Integer.parseInt(st.nextToken()); // 구역 세로 길이
		int L = Integer.parseInt(st.nextToken()); // 정사각형 트램펄린 한 변의 길이
		int K = Integer.parseInt(st.nextToken()); // 별똥별의 수
		
		int[][] star = new int[K][2];
		
		// 별의 좌표 값을 저장 하는 2차 배열
		int x = 0, y = 0;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			star[i][0] = x;
			star[i][1] = y;
		}

		
		// (1) 완탐 -> 메모리 초과 
//		while(width <= N-L) { // 가로의 최대 길이
//			while(height <= M-L) { // 세로의 최대 길이
//				
//				for (int i = width; i <= width+L; i++) {
//					for (int j = height; j <= height+L; j++) {
//						if (map[i][j] == 0) continue;
//						tempStar++;
//					}
//				} // 트램펄린 한바퀴를 돌았을때 막는 Star의 수 = tempStar
//				
//				maxStar = (maxStar>tempStar)? maxStar: tempStar; // 트램펄린을 구역의 지도별로 탐색했을때, 가장 별을 많이 막은 갯수로 갱신
//				tempStar = 0; // 해당 트램펄린 막은 별의 갯수 0으로 초기화
//				++height; // 세로 1 증가
//			}
//			height = 0; // 높이 0으로 초기화
//			++width; // 가로 1 증가
//		}
		
		
		// (2) map -> bar -> comp -> 메모리 초과
//		int temp = 0;
//		for (int j = 0; j <= N; j++) {
//			for (int i = 0; i <= L; i++) {
//				temp += map[i][j];
//			}
//			bar[0][j] = temp;
//			temp = 0;
//		}
//		
//		for (int i = 1; i <= M-L; i++) {
//			for (int j = 0; j <= N; j++) {
//				bar[i][j] = bar[i-1][j] + map[i+L][j] - map[i-1][j];				
//			}
//		}
//		
//		temp = 0;
//		for (int i = 0; i <= M-L; i++) {
//			for (int j = 0; j <= L; j++) {
//				temp += bar[i][j];
//			}
//			comp[i][0] = temp;
//			temp = 0;
//			maxStar = (maxStar > comp[i][0])? maxStar: comp[i][0];
//		}
//		
//		for (int i = 0; i <= M-L; i++) {
//			for (int j = 1; j <= N-L; j++) {
//				comp[i][j] = comp[i][j-1] + bar[i][j+L] - bar[i][j-1];
//				maxStar = (maxStar > comp[i][j])? maxStar: comp[i][j];
//			}
//		}
		
		// (3) star 좌표 기준으로 완탐 -> 별의 x좌표와 y좌표를 이중 for문으로 탐색하여 그 안에서 +L방향의 트램펄린 안에 포함되는 star의 갯수 count
		int maxStar = 0;
		int tempStar = 0;
		int starX = 0, starY = 0;
		for (int i = 0; i < K; i++) { // star의 x좌표 분해
			for (int j = 0; j < K; j++) { // star의 y좌표 분해 -> 즉, x와 y의 수직 좌표의 교차점을 모두 구함
				x = star[i][0];
				y = star[j][1];
				tempStar = 0;
				
				for (int l = 0; l < K; l++) { // 좌표의 교차점을 모두 구했으므로 트램펄린 양의 방향 +L만 고려하면 됨
					starX = star[l][0];
					starY = star[l][1];
					if (x<=starX && starX<=x+L && y<=starY && starY<=y+L) tempStar++; // +L의 방향으로 포함되는 별의 갯수 카운팅
				}
				maxStar = (maxStar > tempStar)? maxStar: tempStar; // 최댓값 매번 갱신
			}
		}
		
		System.out.println(K-maxStar);
	}

}
