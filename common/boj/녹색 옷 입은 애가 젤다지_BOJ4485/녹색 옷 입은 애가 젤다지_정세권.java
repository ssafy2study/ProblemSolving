package study.ssafy.bundler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * [결과] FAIL
 * 
 * [상태] Test Case는 통과 -> 50% 에서 Fail
 * 
 * [질문] 단순 Que에 넣어서 weight 정렬은 하지 않았지만.. 왜 틀린건지 잘 모르겠습니다
 * 		  weight 정렬 하는 이유는 시간 단축을 위해서 라고 생각 했지만 아닌가 그게..? 
 */

public class 녹색옷입은애가젤다지_정세권  {
	
	private static int N; // N = map의 크기
	private static int[][] mapOrigin, mapMin; // mapOrigin = 기본 주어진 map, mapMin = 주어진 map에서 목표지점까지 가는 최단거리를 저장한 map
	private static StringBuilder sb = new StringBuilder();
	private static int[] dr = {-1, 0, 1, 0}; // 북 동 남 서
	private static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		int t = 1;
		
		while (N!=0) {
			mapOrigin = new int[N][N];
			mapMin = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					mapOrigin[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			mapMin[0][0] = mapOrigin[0][0]; // mapOrigin 시작 값과 mapMin 시작 값은 같다
			int ans = findMinRoot(mapOrigin, mapMin);
			sb.append("Problem ").append(t++).append(": ").append(ans).append("\n");	
			
			N = Integer.parseInt(br.readLine());
		} // while문 종료
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		
	}
	
	// mapOrigin -> mapMin 가는 최단 루트 갱신 함수
	private static int findMinRoot(int[][] mapOrigin, int[][] mapMin) {
		Deque<int[]> dq = new ArrayDeque<>(); // BFS 탐색
		dq.add(new int[] {0, 0}); // 시작 값 Que에 저장
		
		// Dq 탐색
		while (!dq.isEmpty()) {
			int[] cur = dq.poll(); // 현재 있는 위치
			
			for (int i = 0; i < 4; i++) { // 4방 탐색
				int nextR = cur[0] + dr[i];
				int nextC = cur[1] + dc[i];
				
				// 경계조건을 벗어 나거날 경우 탐색 할 필요가 없음
				if (nextR<0 || nextR>=N || nextC<0 || nextC>=N) continue;
				
				// 탐색하는 지점이 방문 했던 곳이고 갱신하려는 값이 min 값이 아닌경우 탐색 할 필요가 없음
				if (mapMin[nextR][nextC]!=0 && mapMin[nextR][nextC] <= mapMin[cur[0]][cur[1]]+mapOrigin[nextR][nextC]) continue;
				
				// 즉, 새로 탐색하는 구간이 미방문 지점이거나 min 값으로 갱신 된다면 mapMin 값 갱신 및 dq에 새로운 좌표 추가
				mapMin[nextR][nextC] = mapMin[cur[0]][cur[1]] + mapOrigin[nextR][nextC];
				dq.add(new int[] {nextR, nextC});
				
			}
			
		}
		
		// 모든 map을 min 값으로 갱신 한 후, 최종 방문 지점인 [N-1][N-1] 값을 리턴
		return mapMin[N-1][N-1];
	}

}
