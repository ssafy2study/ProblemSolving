package study.ssafy.bundler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_알파벳_정세권 {
	
	static int R, C;
	static char[][] map;
	
	static int cnt, maxCnt;
	static boolean[] visited = new boolean[91]; // A = 65, Z = 90
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 기본 값 받기
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // row 값
		C = Integer.parseInt(st.nextToken()); // column 값
		map = new char[R][C];
		
		// 알파벳 배열 받기
		for (int i = 0; i < R; i++) {
			char[] temp = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				map[i][j] = temp[j];
			}
		}
		
		
		// main logic
		cnt = 1;
		visited[map[0][0]+0] = true; // map[0][0] 위치 방문 처리
		
		bfs(0, 0); // bfs 탐색
		
		// 가장 긴 배열 출력
		System.out.println(maxCnt);
		
	}
	
	// 메인 bfs logic
	private static void bfs(int row, int col) {
		// 최댓값 갱신
		maxCnt = maxCnt > cnt? maxCnt: cnt;
		
		// 4방 탐색
		for (int i = 0; i < 4; i++) {
			int nextR = row + dr[i];
			int nextC = col + dc[i];
			
			// 경계 조건 처리 : (1) 경계 외 값 (2) 이미 방문한 문자 제외
			if (nextR < 0 || nextR >= R || nextC < 0 || nextC >=C 
					||visited[map[nextR][nextC]+0]) continue;
			
			// 방문 처리, cnt 1 증가
			visited[map[nextR][nextC]+0] = true;
			++cnt;
			
			// bfs
			bfs(nextR, nextC);
			
			// 미방문 처리, cnt 1 감소
			visited[map[nextR][nextC]+0] = false;
			--cnt;
		}
	}
}
