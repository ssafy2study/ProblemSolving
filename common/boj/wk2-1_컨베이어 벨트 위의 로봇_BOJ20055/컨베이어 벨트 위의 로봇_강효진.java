/*
 * 백준 20055 <컨베이어벨트 위의 로봇>
 * https://www.acmicpc.net/problem/20055
 */

package study2.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static int N, K;
	static int beltLen;
	static int[] durabilities;
	static boolean[] robots;
	static int count0;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]); //컨베이어 벨트 1개의 길이
		K = Integer.parseInt(line[1]); //내구도가 0인 칸의 개수
		
		line = br.readLine().split(" ");
		beltLen = 2 * N; //컨베이어 벨트 2개의 길이
		durabilities = new int[beltLen]; //칸의 내구도
		
		for (int i = 0; i < beltLen; i++) {
			durabilities[i] = Integer.parseInt(line[i]);
		}
		
		robots = new boolean[N + 1]; //컨베이어 벨트에 위치한 로봇들 표시
		
		
		//알고리즘
		int step = 0;
		
		do {
			seq1();
			seq2();
			seq3();
			step++;
		} while (count0 < K);
		
		
		//출력
		bw.write(step + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static void seq1() {
		//벨트 회전
		int prev = durabilities[0];
		
		for (int i = 1; i < beltLen; i++) { //2N - 1까지 뒤로 밀기
			int cur = durabilities[i];
			durabilities[i] = prev;
			prev = cur;
		}
		
		durabilities[0] = prev;
		
		//로봇 회전
		for (int i = N - 2; i >= 0; i--) {
			if (robots[i]) { //자리에 로봇이 있으면
				robots[i] ^= true; 
				robots[i + 1] ^= true; //한 칸 이동
			}
		}
		
		if (robots[N - 1]) { //N번 칸일 때 내림
			robots[N - 1] ^= true;
		}
	}
	
	static void seq2() {
		//로봇 회전
		for (int i = N - 2; i >= 0; i--) {
			if (robots[i] && !robots[i + 1] && durabilities[i + 1] != 0) { //자리에 로봇이 있고, 이동하려는 칸에 로봇이 없고, 그 칸의 내구도가 0이 아닐 때
				robots[i] ^= true; //내 칸 지우고
				robots[i + 1] ^= true; //한 칸 이동
				
				if (--durabilities[i + 1] == 0) { //내구도 - 1
					count0++;
				}
			}
		}
		
		if (robots[N - 1]) { //N번 칸일 때 내림
			robots[N - 1] ^= true;
		}
	}
	
	static void seq3() {
		if (durabilities[0] != 0) { //올리는 위치에 있는 칸의 내구도가 0이 아니면
			robots[0] = true; //로봇 올린다.
			
			if (--durabilities[0] == 0) { //내구도 - 1
				count0++;
			}
		}
	}

}
