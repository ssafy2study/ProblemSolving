/*
 * 백준 1987 <알파벳>
 * https://www.acmicpc.net/problem/1987
 */

package study2.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 말은 상하좌우로 인접한 네 칸 중의 한 칸으로 이동할 수 있는데, 
 * 새로 이동한 칸에 적혀 있는 알파벳은 지금까지 지나온 모든 칸에 적혀 있는 알파벳과는 달라야 한다. 
 * 즉, 같은 알파벳이 적힌 칸을 두 번 지날 수 없다.
 * 좌측 상단에서 시작해서, 말이 최대한 몇 칸을 지날 수 있는지를 구하는 프로그램을 작성하시오. 
 * 말이 지나는 칸은 좌측 상단의 칸도 포함된다.
 */
public class _230210_1987_알파벳 {
	
	static int R, C;
	static char[][] board;
	static boolean[] alphabet;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		String[] line = br.readLine().split(" ");
		R = Integer.parseInt(line[0]); // 세로 (1 ≤ R ≤ 20) 
		C = Integer.parseInt(line[1]); // 가로 (1 ≤ C ≤ 20) 
		
		board = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			board[i] = br.readLine().toCharArray();
		}
		
		alphabet = new boolean[26];
		visited = new boolean[R][C];
		
		
		//알고리즘
		dfs(0, 0, 1);
		
		
		//출력
		bw.write(max + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static void dfs(int x, int y, int cnt) {
		visited[x][y] = true;
		alphabet[board[x][y] - 'A'] = true;
		
		if (max < cnt) {
			max = cnt;
		}
		
		for (int k = 0 ; k < 4; k++) {
			int newX = x + dx[k];
			int newY = y + dy[k];
			
			if (newX >= 0 && newX < R && newY >= 0 && newY < C 
					&& !visited[newX][newY] && !alphabet[board[newX][newY] - 'A']) {
			
				dfs(newX, newY, cnt + 1);
			}
		}
		
		visited[x][y] = false;
		alphabet[board[x][y] - 'A'] = false;
	}

}
