package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_다리놓기_1010 {
	
	// 공 7개 중에 4개를 뽑느다. (조합)
	// 	공 한개를 선택한 경우의 수
	// 	공 한개를 선택하지 않은 경우의 수
	// 7C4 = 6C3 + 6C4
	//	   = 5C2 + 5C3 + 5C3 + 5C4
	
	// => 중복이 많이 발생한다. => 미리 계산한 값을 저장할 수 있는 자료구조를 만들고, 반복되는 경우는 기록해 두고 재사용한다.
	// memoization
	// memoization 을 이용해서 
	
	static int T, N, M;
	static int[][] memoi; // 역할은 재귀호출을 줄여주는 효과
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			memoi = new int[M+1][N+1];
			
			System.out.println(comb(M,N));
		}
	}

	static int comb(int src, int tgt) {
		if(memoi[src][tgt] != 0) return memoi[src][tgt]; // 이미 계산된 것이 있다면 그걸 리턴
		
		// 현재 src, tgt 에 대한 계산된 값이 없다. (최초 계산)
		// 뻔한 값 전체를 다 선택하던가 하나도 선택하지 않던가
		if(src==tgt || tgt == 0) return memoi[src][tgt] = 1;
		
		// 7C4 == 6C3 + 6C4
		return memoi[src][tgt] = comb(src - 1, tgt -1 ) + comb(src - 1, tgt);
	}
}
