package study.ssafy.bundler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 컨베이어벨트위의로봇_정세권 {
	
	static int N, K, M, duCunt, cycle;
	static int[] belt, robot;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		M = 2*N;
		belt = new int[M];
		robot = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++)
			belt[i] = Integer.parseInt(st.nextToken());
		
		while (duCunt < K) {
			setRobot();
			moveBelt();
			moveRobot();
			++cycle;
		}
		
		System.out.println(cycle);
		
		
		System.out.println(Arrays.toString(belt));
		
		
	}
	
	private static void setRobot() {
		if (belt[0] != 0) {
			robot[0] = 1;
			belt[0] -= 1;
			if (belt[0] == 0) ++duCunt;
		}
	}
	
	private static void moveBelt() {
		// belt만 돌리기
		int beltLast = belt[M-1];
		for (int i = M-1; i > 0; i--)
			belt[i] = belt[i-1];
		belt[0] = beltLast;
		
		// 로봇만 돌리기
		for (int i = N-1; i > 0; i--)
			robot[i] = robot[i-1];
		robot[N-1] = 0;
	}
	
	private static void moveRobot() {
		for (int i = N-2; i >0; i--) {
			if (robot[i] == 0 || belt[i+1] == 0) continue;
			robot[i+1] = robot[i];
			belt[i+1] -= 1;
			if (belt[i+1] == 0) --duCunt;
		}
		robot[N-1] = 0;
	}

}
