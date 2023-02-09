import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int head,tail,zeroCnt,step,N,M;
	// head 올리는 위치
	// tail 내리는 위치
	// zeroCnt 내구도 0 개수
	// step 현재 단계
	static boolean[] robots;
	static int[] hp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");//N,K입력
		N = Integer.parseInt(st.nextToken()); // 2 <= N <=100
		tail = N-1;// tail 초기값 = N-1
		M=N*2;//어차피 2N으로 쓰기 떄문에

		int K = Integer.parseInt(st.nextToken());

		hp = new int[M];//내구도 저장할 배열
		robots = new boolean[M]; // 해당 위치 로봇 여부 체크 배열

		st = new StringTokenizer(br.readLine()," ");

		for (int i = 0; i <M ; i++) {
			hp[i] = Integer.parseInt(st.nextToken());
		}

		while (true){
			step++;
			movebelt(); //벨트 이동
			robotOff();; // 로봇 내리기
			robotMove();//맨 앞에서부터 로봇 이동
			robotOn();//맨 앞에 로봇 올리기
			if(zeroCnt>=K){
				break;
			}
		}
		System.out.println(step);

	}
	public static int filter(int num){
		if(num<0) return M+num;
		if(num>=M) return num%M;
		return num;
	}
	public static void movebelt(){
		head = filter(--head);
		tail = filter(--tail);
	}
	public static void robotOff(){
		robots[tail] = false;
	}
	public static void robotMove(){
		int start = filter(tail-1); //검사 시작칸 맨 끝에선 이미 내렸으니깐. tail-1부터 시작
		int scope = N-1;//검사할 칸 수, 맨 끝칸 제외니깐 N-1;
		for (int i = 0; i < scope; i++) {
			int pos = filter(start-i);
			int next =filter(pos + 1);
			//로봇이 있고, 다음칸에 로봇이 없고 내구도가 0이 아니라면
			if (robots[pos] && !robots[next] && hp[next] > 0) {
				//현재 위치 비우고 다음칸으로 이동,
				robots[pos] = false;
				if (next != tail)//다음칸이 tail이라면 로봇 바로 내리기 떄문에 패스
					robots[next] = true;
				// 내구도-1, 0됐으면 zeroCnt +1
				if (--hp[next] == 0)//내구도 0되면 카운트 +1
				{
					zeroCnt++;
				}
			};
		}
	}
	public static void robotOn(){
		if(hp[head]>0) {
			if(--hp[head]==0){
				zeroCnt++;
			}
			robots[head] = true;
		};
	}
}
