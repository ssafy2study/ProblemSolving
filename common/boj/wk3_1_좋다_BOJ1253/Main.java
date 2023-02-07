package wk3_1_좋다_BOJ1253;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 1<= N <= 2000
		int[] numbers = new int[N];// N개의 숫자를 저장할 배열
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		HashMap<Integer, Integer> numberCnt = new HashMap<>();//N개의 숫자중 값이 같은 수의 개수를 저장하는 키-밸류
		HashMap<Integer, Boolean> visited = new HashMap<>();//이미 한 번 처리한 숫자 값을 저장할 키-밸류
		
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken()); // +- 1_000_000_000, 숫자 입력
			Integer cur  = numberCnt.get(numbers[i]); // 앞에 같은 값을 가지는 수가 몇개 나왔는지 검사
			cur = cur == null ? 0 : cur;
			numberCnt.put(numbers[i],cur+1);// 해당하는 값의 숫자++
		}
		
		int goodCnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				int sum = numbers[i]+numbers[j];
				Integer cnt = numberCnt.get(sum);
				if(cnt != null && visited.get(sum) == null) {
					goodCnt += cnt;
					visited.put(sum, true);
				}
			}
		}
		System.out.println(goodCnt);
	}
}
