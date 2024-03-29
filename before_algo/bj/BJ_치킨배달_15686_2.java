package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// NP + Comb
public class BJ_치킨배달_15686_2 {
	static int N, M, min;
	static List<int[]> house, src;
	
	static int[] index; // np() 의해서 가장 작은 값 --> 가장 큰 값으로 갱신되어가는 배열, src 에서 어느 인덱스가 선택되었는지를 표현
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		house = new ArrayList<>();
		src = new ArrayList<>(); // 전체 치킨집
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++){
				int n = Integer.parseInt(st.nextToken());
				if(n==1) house.add(new int[] {i,j});
				else if(n==2) src.add(new int[] {i,j});
			}
		}
		
		// np + comb 위한 index 배열을 생성
		int srcSize = src.size();
		index = new int[srcSize];
		
		for(int i=srcSize -M; i<srcSize; i++) {
			index[i] = 1;
		}
		
		min = Integer.MAX_VALUE;
		int size = house.size();
		
		while(true) {
			// complete code
			// dist 계산 --> 합 --> min 갱신
			int sum = 0;
			
			for(int i=0; i<size; i++) { // 모든 집 각각에 대해서
				int dist = Integer.MAX_VALUE; // i번째 집의 치킨 거리
				int[] h = house.get(i); // 이 집으로부터 선택된 M개의 치킨집의 거리를 계산해서 최소값을 선택
				for(int j=0; j<index.length; j++) {
					if(index[j] == 1) {
						int[] c = src.get(j);
						dist = Math.min(dist, Math.abs(h[0] - c[0]) + Math.abs(h[1] - c[1]));
					}
					
				}
				
				sum += dist; // 현재 M개 선택의 치킨거리의 누적 합
			}
			min = Math.min(sum, min);
			
			if(!np()) break;
		}
		
		System.out.println(min);
	}
	
	private static boolean np() {
	    int[] src = index;
	    int i = src.length - 1;
	    while( i>0 && src[i-1]>=src[i] ) --i;
	    
	    if( i == 0 ) return false;
	    
	    int j = src.length - 1;
	    while(src[i-1]>=src[j])    --j;
	    swap(src,i-1,j);
	    
	    // reverse
	    int k = src.length - 1;
	    while(i<k) {
	        swap(src,i++,k--);            
	    }
	    return true;
	}


	private static void swap(int num[],int i,int j) {
	    int temp = num[i];
	    num[i] = num[j];
	    num[j] = temp;
	}
	
}
