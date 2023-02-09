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

public class Main_2 {
	static int[] dr = {0,1,0,-1}; //4방 탐색위한 dr
	static int[] dc = {1,0,-1,0}; //4방 탐색위한 dc
	static char NULL = '\u0000'; // char 배열선언시 초기값
	
	static class State{ // BFS에서 현재 탐색중인 상태를 저장하기 위한 클래스
		int r,c;
		boolean[] way = new boolean[26]; // 현재 위치 포함 지금까지 밟은 Character 저장
		State(int r,int c,boolean[] way){
			this.r=r;
			this.c=c;
			this.way=way;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken()); 	// 1<= R <=20
		int C = Integer.parseInt(st.nextToken());	// 1<= C <=20
		
		char[][] map = new char[R+2][C+2]; // 문자 저장할 map 2차원 배열 선언, 범위 검사 하기 싫어서 padding(+2)
		for (int r = 1; r <= R; r++) {// r=1 에서부터 시작
			String str = br.readLine();//한 줄 입력
			for (int c = 1; c <= C; c++) {//c=1에서부터 시작
				map[r][c] = str.charAt(c-1);// 저장
			}
		}
		
		Queue<State> q = new LinkedList<>();
		
		boolean[] startWay = new boolean[26];
		startWay[map[1][1]-'A'] = true;
		State start = new State(1,1,startWay);// 좌측 상단 1,1 에서 시작
		
		q.add(start);
		
		int dist= 0;
		//bfs
		while(!q.isEmpty()) {
			dist++; // 거리 카운트
			for (int r = 0; r < q.size(); r++) {// 현재 들어가 있는 큐는 같은 거리에 있음			
				State current = q.poll();// 현재 밟은 자리
				
				for (int i = 0; i < 4; i++) {// 현재 밟은 자리로부터 4방 탐색
					int nr = current.r + dr[i];
					int nc = current.c + dc[i];
					char nChar = map[nr][nc];// 다음 밟을 자리의 대문자
					if(nChar != NULL && !current.way[nChar-'A']) {//어차피 입력은 전부 대문자라서 NULL 검사 할 필요 없음, 현재까지 밟은 거랑 같은지 검사
						//처음 밟는 대문자일 때
						boolean[] nWay = Arrays.copyOf(current.way, 26);//현재까지 밟은 기록 복사(참조 X)
						nWay[nChar-'A'] = true; //현재 위치 대문자 밟음 처리
						q.add(new State(nr,nc,nWay));// 다음 큐에 저장
					}
				}
			}
		}
		
		System.out.println(dist);//출력
		
		
	}
}
