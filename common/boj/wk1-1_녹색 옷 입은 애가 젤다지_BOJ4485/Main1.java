import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Main1 {
	
	static int N;
	static int[][] map;
	static boolean visit[][];
	static int size[][];
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int nowX, nowY;
	
	private static final int INF = Integer.MAX_VALUE / 4;
	
	static class Node implements Comparable<Node> {
		int x;
		int y;
		int size;

		public Node(int x, int y, int size) {
			this.x = x;
			this.y = y;
			this.size = size;
		}

		@Override
		public int compareTo(Node o) {
			return size - o.size;
		}
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringBuilder sb = new StringBuilder(); 
		int count = 1;
		
		String line = "";
		while( !(line = br.readLine()).isEmpty()  ) {
			N = Integer.parseInt(line);
			if(N == 0) {
				break;
			}

			map = new int[N][N];
			visit = new boolean[N][N];
			size = new int[N][N];

			for(int i = 0; i < N; i++) {
				String[] tmp = br.readLine().split(" ");
		
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(tmp[j]);	
					size[i][j] = INF;
				}
			}

			BFS(0, 0, map[0][0]);
			sb.append("Problem ").append(count).append(": ").append(size[N - 1][N - 1]).append("\n");
			count++;
		}

		
		//출력
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	private static void BFS(int x, int y, int roopy) {
		PriorityQueue<Node> que = new PriorityQueue<>();
		visit[x][y] = true;
		que.offer(new Node(x, y, roopy));

		while(!que.isEmpty()) {
			Node node = que.poll();

			for(int i = 0; i < 4; i++) {
				nowX = node.x + dx[i];
				nowY = node.y + dy[i];

				if( range_check() && !visit[nowX][nowY] 
						&& size[nowX][nowY] > (map[nowX][nowY] + node.size) ) {
					size[nowX][nowY] = map[nowX][nowY] + node.size;
					visit[nowX][nowY] = true;
					que.offer(new Node(nowX, nowY, size[nowX][nowY]));
				}
			}
		}
	} 
	
	private static boolean range_check() {
		return (nowX >= 0 && nowY >= 0 && nowX < N && nowY < N);
	} 

}
