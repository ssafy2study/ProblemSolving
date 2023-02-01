import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main2 {

	static int N, min;
	static int[][] map;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static class Weight implements Comparable<Weight> {
		int r, c, w;

		public Weight(int r, int c, int w) {
			super();
			this.r = r;
			this.c = c;
			this.w = w;
		}

		@Override
		public int compareTo(Weight o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.w, o.w);
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = 0;
		while (true) {
			tc++;
			N = Integer.parseInt(br.readLine());
			if (N == 0) {
				break;
			}
			min = Integer.MAX_VALUE;
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0, idx = 0; j < N; j++, idx += 2) {
					map[i][j] = line.charAt(idx)-'0';
				}
			}
			int[][] minWeight = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(minWeight[i], Integer.MAX_VALUE);
			}
			minWeight[0][0] = map[0][0];
			boolean[][] visited = new boolean[N][N];

			Queue<Weight> queue = new PriorityQueue<Weight>();
			queue.offer(new Weight(0, 0, map[0][0]));
			x: while (!queue.isEmpty()) {
				int size = queue.size();

				for (int k = 0; k < size; k++) {

					Weight pos = queue.poll();

					int r = pos.r;
					int c = pos.c;
					if (r == (N - 1) && c == (N - 1)) {
						break x;
					}
					if (visited[r][c])
						continue;
					visited[r][c] = true;

					for (int i = 0; i < 4; i++) {
						int nr = r + dr[i];
						int nc = c + dc[i];
						if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
							if (!visited[nr][nc] && (minWeight[nr][nc] > minWeight[r][c] + map[nr][nc])) {
								minWeight[nr][nc] = minWeight[r][c] + map[nr][nc];
								queue.offer(new Weight(nr, nc, minWeight[nr][nc]));
							}
						}
					}
				}
			}
			sb.append("Problem ").append(tc).append(": ").append(minWeight[N - 1][N - 1]).append("\n");

		}

		System.out.println(sb);
	}

}
