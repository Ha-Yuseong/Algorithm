package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BJ_스도쿠_2239 {
	
	static int[][] map;
	static ArrayList<Node> zero = new ArrayList<>();
	static boolean complete = false;
	static int size; // zero 의 size => 0으로 미완성인 좌표수
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[9][9];
		
		for(int i = 0; i<9; i++) {
			char[] line = br.readLine().toCharArray();
			for(int j=0; j<9; j++) {
				int n = line[j] - '0';
				map[i][j] = n;
				if(n==0) zero.add(new Node(i,j)); // 0인 미완성 좌표를ㄹ zero에 담는다.
			}
		}
		
		size = zero.size();
		dfs(0); // 0 좌표 첫번째부터 채운다.
		
	}
	
	static void dfs(int idx) {
		// 기저 조건
		if(complete) return;
		
		// 기저 조건 - 완성
		if(idx==size) {
			complete = true;
			
			for(int i=0; i<9; i++) {
				for(int j=0; j<9; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			return;
		}
		
		// 가로 -> 세로 -> 3x3 각각 이미 채워진 숫자를 제외한 숫자를 채워본다.
		
		// 현재 채우려고 하는 0의 위치
		
		int y = zero.get(idx).y;
		int x = zero.get(idx).x;
		
		// 이미 채워진 숫자를 표현하기 위한 자료 구조
		boolean[] visit = new boolean[10]; // 0 dummy visit[3] == true 3이 이미 사용되었다.
		
		// 가로부분
		for(int i=0; i<9; i++) {
			if(map[y][i] != 0 ) visit[map[y][i]] = true;
		}
		
		// 세로부분
		for(int i=0; i<9; i++) {
			if(map[i][x] != 0 ) visit[map[i][x]] = true;
		}
		
		// 3x3 부분
		// y:5, x:2
		int ny = (y/3)*3;
		int nx = (x/3)*3;
		
		for(int i=ny; i<ny+3; i++) {
			for(int j=nx; j<nx+3; j++) {
				if(map[i][j]!=0) visit[map[i][j]] = true;
			}
		}
		
		// 현재 0인 (idx) y, x 좌표에 위에서 사용되지 않은 숫자 (visit 에 없는 숫자를 하나씩 넣어본다.)
		// 1,4,6,7 이 사용되었으면 2,3,5,8,9 숫자를 현재 y,x 좌표에 각각 채워보고 다시 다음 idx+1 에 채우기 위해 dfs() 이어간다.
		for(int i=1; i<=9; i++) {
			if(visit[i]) continue;
			map[y][x] = i;
			dfs(idx+1);
			map[y][x] = 0;
		}
	}
	
	static class Node{ // 0인 (미완성) 좌표
		int y, x;
		Node(int y, int x){
			this.y = y;
			this.x = x;
		}
	}

}
