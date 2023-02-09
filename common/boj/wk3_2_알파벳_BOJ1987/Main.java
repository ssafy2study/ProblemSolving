package wk3_2_알파벳_BOJ1253;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	static int[] dr = { -1, 1, 0, 0 };// 사방탐색 dr
	static int[] dc = { 0, 0, -1, 1 };// 사방탐색 dc
	static int[][] matrix;//대문자 저장
	static int R,C;//현재 위치
	static int max = 0;//최대 깊이

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		matrix = new int[R][C];

		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				matrix[i][j] = str.charAt(j) - 'A'; // 'A'부터 시작이므로 int 로저장
			}
		}

		dfs(1, 0, 0,1 << matrix[0][0]);// 비트마스킹 방문처리 dfs 시작

		System.out.println(max);
	}

	static void dfs(int cnt, int r, int c, int visited) {
		if (max == 26) return; // 대문자 개수 26개 이므로 26이 최대값
		max = cnt > max ? cnt : max; // 
		for (int j = 0; j < 4; j++) { // 4방탐색
			int nr = r + dr[j];
			int nc = c + dc[j];

			if (nr >= 0 && nr < R && nc >= 0 && nc < C) { // 범위 체크
				int nChar = matrix[nr][nc]; // 다음 대문자
				if ((visited & (1 << nChar)) == 0) { // 다음 대문자 방문 확인
					//연산 결과를 전달하기 때문에 방문체크를 해제할 필요가 없다.
					dfs(cnt + 1, nr, nc, visited | 1 << nChar);
				}
			}
		}
	}
}

