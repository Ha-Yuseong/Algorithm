package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_요세푸스_1258 {
	static int N, K;
	static int[] input;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		input = new int[N+1];
		for(int i=1; i<=N; i++) {
			input[i] = i;
		}
		
		int count = 0; // 죽은 사람이 결정되면 하나씩 증가 => 모두 죽으면 count == N 종료
		int idx = 1; // 순회하는 index, 죽은 사람이 결정되면 그 사람 input[idx] = 0 으로
		int step = 1; // 1 부터 증가, 살아있는 사람만 계산하기 위함
		
		sb.append("<");
		while(true) {
			// 기조 조건
			if(count==N) break;
			
			if(input[idx] != 0) { // 살아 있는
				if(step % K == 0) { // 살아 있는 사람 중에 K 번째
					sb.append(input[idx]).append(", ");
					input[idx] = 0;
					count++;
				}
				step++;
			}
			idx++;
			if(idx>N) idx = 1;
		}
		
		// 뒷 부분 출력 정리
		sb.setLength(sb.length()-2);
		sb.append(">");
		System.out.println(sb);
	}

}
