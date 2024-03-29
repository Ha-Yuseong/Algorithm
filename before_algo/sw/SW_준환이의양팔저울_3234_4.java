package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// MEMOI
// 순열의 경우의 수를 계산
// 1,2,3,4,5,6 추가 있다고 가정
// - 왼쪽 1,2,3 이 올라가면 오른쪽 : 456, 465, 546, 564, 645, 654 = 3*2*1 = 6
// - 왼쪽 1,3,2 = 6
public class SW_준환이의양팔저울_3234_4 {
    
	static int T, N, ans;
	static int[] choo;
	static int[][] memoi;

    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        
        for(int t = 1; t <= T; t++) {
            
            N = Integer.parseInt(br.readLine());
            choo = new int[N];
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sum = 0;
            for(int i = 0; i < N; i++) {
                int num = Integer.parseInt(st.nextToken());
                choo[i] = num;
                sum += num;
            }
            
            // memoi 구성
            memoi = new int[sum+1][1<<N];
            ans = perm(0, 0, 0, 0);
            
            System.out.println("#" + t + " " + ans);
        }

    }
    
    // 1,2,3,4,5,6
    // 1,2,3     = 6

    static int perm( int tgtIdx, int leftSum, int rightSum, int mask) {
        // 기저 조건
        if(tgtIdx==N) {
        	return 1;
        }
        
        if(memoi[leftSum][mask] != 0) return memoi[leftSum][mask];
        
        int cnt = 0;
        for(int i = 0; i <N; i++) {
        	// 이미 사용한 추 제외
            if( ( mask & 1 << i) != 0) continue;
            
            // 2가지의 재귀호출
            // #1 왼쪽에 추를 올리는 경우
            cnt += perm(tgtIdx+1, leftSum+choo[i], rightSum, mask | 1<<i);
            // #2 오른쪽에 추를 올리는 경우 (문제의 조건+가지치기)
            if(leftSum>=rightSum+choo[i]) {
            	cnt += perm(tgtIdx+1, leftSum, rightSum+choo[i], mask | 1 <<i);
            }
        }
        
        memoi[leftSum][mask] = cnt;
        
        return cnt;
        
    }
}