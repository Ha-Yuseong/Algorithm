import java.io.*;
import java.util.*;

/*
 * 스포일러 방지를 위해 맨 밑에 주석으로 풀이가 있습니다.
 * */

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int[][] dp = new int[N+1][N+1];

        // 첫 번째 배열은 K를 뜻함
        for(int i=1; i<=N; i++){
            // 두 번째 배열은 N을 뜻함
            for(int j=1; j<=N; j++){

                if(i==1){
                    dp[i][j] = j;
                }else{

                    if(j<i*2){
                        continue;
                    }
                    else if(j==i*2){
                        dp[i][j] = 2;
                    }
                    else {
                        dp[i][j] = ( dp[i-1][j-2] % 1_000_000_003 + dp[i][j-1] % 1_000_000_003 ) % 1_000_000_003;
                    }

                }
            }
        }

        System.out.println(dp[K][N]);

    }

}

/*
 * 다이나믹 프로그래밍 문제
 *
 * 해당 문제는 포인트는 점화식을 찾는 것 입니다. 저는 최대 N을 10 최대 K를 3까지만 설정하고 다음과 같은 방법으로 규칙을 찾아냈습니다.
 * 1. K == 1일 때는 조합의 갯수가 N의 값과 같습니다.
 * 2. K*2 이상의 값이 된 시점에 2개 조합을 만들 수 있습니다.
 *
 * 위의 내용을 배열로 나타내면 다음과 같고 미리 이 형태로 세팅해줘야합니다.
 *
 * [K==1] 1 2 3 4 5 6 7 8 9 10 ...
 * [K==2] 0 0 0 2 x x x x x x ...
 * [K==3] 0 0 0 0 0 2 x x x x ...
 * [K==4] 0 0 0 0 0 0 0 2 x x ...
 * [K==5] 0 0 0 0 0 0 0 0 0 2 ...
 * [K==6] 0 0 0 0 0 0 0 0 0 0 0 2 ...
 *
 * 여기서 K==2 일때로 간단하게 조합을 만들어보겠습니다.
 * N이 4일 때 색을 각각 {a,b,c,d}라고 한다면 가능한 조합은 {a,c}, {b,d} 조합으로 2개입니다.
 * N이 5일 때 색이 각각 {a,b,c,d,e}라면 바로 전의 결과인 {a,c}, {b,d} 에 + {a,d}, {b,e}, {c,e} 가 추가되어 2 + '3' = 5입니다.
 * N이 6일 때 색이 각각 {a,b,c,d,e,f}라면 바로 전의 결과인 {a,c}, {b,d}, {a,d}, {b,e}, {c,e}에 + {a,e}, {b,f}, {c,f}, {d,f} 가 추가되어 5 + '4' 입니다.
 *
 * 그러면 지금 바로 전의 값이 계속해서 더하니까 점화식은 dp[K][N] = dp[K][N-1] + y 라는 것은 알 수 있습니다.
 * 그리고 y에 해당하는 값 3,4는 어떻게 결정되는지 배열을 보면 찾을 수 있습니다.
 *
 * [K==1] 1 2 3 4 5 6 7 8 9 10 ...
 * [K==2] 0 0 0 2 5 9 x x x x ...
 * ...
 *
 * 배열을 보면 K-1 N-2의 위치에 3, 4가 있는 것을 확인할 수 있습니다.
 * 그러면 점화식은 dp[K][N] = dp[K][N-1] + dp[K-1][N-2] 임을 알 수 있습니다.
 *
 * 이후는 모듈레이션(%)을 잘 적용해주며 이 점화식이 동작할 수 있도록 코드로 구현하면 문제를 쉽게 해결할 수 있습니다.
 *
 * */