import java.io.*;
import java.util.*;

/*
 * 스포일러 방지를 위해 맨 밑에 주석으로 풀이가 있습니다.
 * */

public class Main {

    static int N,M;
    static int[] memory, cost;
    static long[][] dp;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        memory = new int[N+1];
        cost = new int[N+1];

        st = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        for(int i=1; i<=N; i++){
            memory[i] = Integer.parseInt(st.nextToken());
            cost[i] = Integer.parseInt(st2.nextToken());
        }

        dp = new long[N+1][100001];

        int answer = Integer.MAX_VALUE;

        for(int i=1; i<=N; i++){

            // 재채점으로 j=1부터에서 시작을 j=0부터로 수정함
            for(int j=0; j<=100000; j++){

                if(cost[i]<=j){
                    // 재채첨으로 새로 추가한 if문 재기동 시간이 0인 경우는 다 더해줌
                    if(j==0){
                        dp[i][j] = dp[i-1][j] + memory[i];
                    }
                    else dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-cost[i]]+memory[i]);
                }
                else dp[i][j] = dp[i-1][j];

                if(dp[i][j]>=M) answer = Math.min(answer, j);
            }
        }

        System.out.println(answer);
    }
}

/*
 * 다이나믹 프로그래밍 문제
 *
 * 해당 문제는 배낭문제(knapsack)를 안다는 전제에서 설명하겠습니다.
 *
 * knapsack의 응용 문제입니다. 문제의 핵심 포인트는 어느 값을 무게 값으로 두어야할까.
 *
 * 배낭 문제에서 무게값에 대하여 가장 가치가 높은 값들을 넣었는데 문제의 경우 그런 무게에 해당할 것 같은 M값이 너무 커서 시간초과가 당연하다는 문제가 있습니다.
 * 그렇다면 무게에 해당하는 값을 다른 값으로 두어야하지 않을까 라는 의문이 듭니다.
 *
 * 거기서 해결방법은 '시간에 해당하는 값을 무게처럼 사용하면 된다' 입니다.
 * N과 c1...cN 이 모두 0이상 100 이하의 값입니다. 그러면 시간 최대 값은 100*100 = 10000이라는 뜻이기 때문에 최대 100*10000번 반복하게 되면 시간상 크게 문제가 없습니다.
 * 그렇다면 시간에 관하여 가장 용량을 많이 쓰는 값을 dp를 통해 기록하다가 목표하는 M보다 큰 값을 가진 시간 값 중 가장 최소 값을 출력하면 됩니다.
 *
 * 2023-11-20 수정
 * 재채점으로 반례가 발견되어 코드를 수정하였습니다.
 * 반례는 재기동하는데 사용되는 값 0이 여러개일 경우 잘못된 정답을 출력하는 경우였습니다.
 * 예시 입력은 이고 정답은 1입니다. 예전 코드에서는 정답을 2로 도출했습니다.
 * 5 60
 * 20 30 10 20 30
 * 0 0 1 2 3
 *
 * 예전 코드는 아래와 같습니다.
 * for(int i=1; i<=N; i++){
 *   for(int j=1; j<=10000; j++){
 *        if(cost[i]<=j){
 *               dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-cost[i]]+memory[i]);
 *         }
 *         else dp[i][j] = dp[i-1][j];
 *         if(dp[i][j]>=M) answer = Math.min(answer, j);
 *      }
 *}
 * 이 코드를 따라서 수행한 냅색 배열의 상황을 보면
 *    0   1   2   3   4   5  ...  10000
 * 0  0   0   0   0   0   0  ...  0
 * 1  0  20  20  20  20  20  ...  20
 * 2  0  50  50  50  50  50  ...  50
 * 3  0  !50! !60! ...
 * ...
 *
 * 위에서 !50! !60! 부분을 보면
 * 3번째 10의 차례일 때 10은 1의 시간이 있으면 50+10으로 더해줘야하는데
 * dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-cost[i]]+memory[i]);
 * 이 코드에 의해서 Math.max(50, 0+10)이 되버려서 50이 그 자리를 차지하는 경우가 발생하였습니다.
 *
 * 이를 해결하기 위해 j=0부터 시작하여 j가 0이면 무조건으로 더해주기만 수행하게 해주어서
 *    0   1   2   3   4   5  ...  10000
 * 0  0   0   0   0   0   0  ...  0
 * 1  20  20  20  20  20  20  ...  20
 * 2  50  50  50  50  50  50  ...  50
 * 3  50  60  60 ...
 * ...
 *
 * 냅색이 정상적으로 작동할 수 있도록 하였습니다.
 *
 * */