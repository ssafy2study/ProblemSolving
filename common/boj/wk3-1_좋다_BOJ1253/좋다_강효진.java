
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;


/*
 * 0일 때가 문제임! 그래서 틀렸음
 */
public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		int N = Integer.parseInt(br.readLine()); //수의 개수
		
		String[] line = br.readLine().split(" ");
		
		int[] nums = new int[N];
		HashMap<Integer, Integer> map = new HashMap<>(N);
		
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(line[i]);
			nums[i] = n;
			map.put(n, map.getOrDefault(n, 0) + 1);
		}
		
		
		//알고리즘
		int goodCnt = 0;
		
		for (int i = 0 ; i < N; i++) {
			int a = nums[i];
			
			for (int j = i + 1; j < N; j++) {
				int b = nums[j];
				
				int sum = a + b;
				
				if (map.containsKey(sum)) {
					if (a == 0 && b == 0) { //둘 다 0이면
						if (map.get(0) == 2) { //0이 전체 중에 2개 밖에 없으면 
							continue;
						}
					} else if (a == 0) { //a만 0이면
						if (map.get(b) == 1) { //0이 아닌 숫자가 전체 중에 1개 밖에 없으면 
							continue;
						}
					} else if (b == 0) { //b만 0이면
						if (map.get(a) == 1) { //0이 아닌 숫자가 전체 중에 1개 밖에 없으면 
							continue;
						}
					}
		
					goodCnt += map.get(sum);
					map.remove(sum);
				}
			}
		}
		
		
		//출력
		bw.write(goodCnt + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}

}
