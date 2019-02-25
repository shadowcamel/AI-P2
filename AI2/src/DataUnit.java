import java.util.*;

public class DataUnit {
	public int m=3;
	public int n=3;
	public int board[][];
	public int cur=1;
	public int winner=0;
	public boolean isAI[]=new boolean[3];
	
	public void init(int num) {
		board=new int[m][n];
		cur=1;
		winner=0;
		for(int i=1;i<3;i++)
			if(num<i)
				isAI[i]=false;
			else
				isAI[i]=true;
		check();
	}
	
	public void check() {
		boolean isend=false;
		boolean tof;
		//check
		for(int i=0;i<m;i++) {
			for(int j=0;j<m;j++) {
				if(board[i][j]>0) {
					if(i<=m-n&&j<=m-n) {
						tof=true;
						for(int k=1;k<n;k++)
							if(board[i][j]!=board[i+k][j+k])
								tof=false;
						if(tof) 
							isend=true;
					}
					if(i<=m-n) {
						tof=true;
						for(int k=1;k<n;k++)
							if(board[i][j]!=board[i+k][j])
								tof=false;
						if(tof) 
							isend=true;
					}
					if(j<=m-n) {
						tof=true;
						for(int k=1;k<n;k++)
							if(board[i][j]!=board[i][j+k])
								tof=false;
						if(tof) 
							isend=true;
					}
				}
			}
		}
		if(isend)
			winner=cur;
		//shift current player
		if(cur==1)cur=2;
		else cur=1;
		if(isAI[cur]==true)
			playAI();
	}
	
	
	private int heuristic() {
		int res=0;
		for(int i=0;i<m;i++) { 
			for(int j=0;j<m;j++) {
				if(board[i][j]==1)
					res+=i+j;
				else if(board[i][j]==2)
					res-=i+j;
			}
		}
		return res;
	}
	
	public static final int maxdepth=8;
	public int[] minmax(int depth,int player,int alpha,int beta) {
		int res[]= {0,-1,-1};
		int temp;
		//generate possible nextmove
		List<int[]> nextMoves = new ArrayList<int[]>();;
		for(int i=0;i<m;i++)
			for(int j=0;j<m;j++)
				if(board[i][j]==0)
					nextMoves.add(new int[] {i,j});
		
		if(nextMoves.isEmpty()==true&&depth>=maxdepth) {
			res[0]=heuristic();
		}
		else {
			for(int[] move : nextMoves) {
				board[move[0]][move[1]]=cur;
				if(depth%2!=player%2) {
					//maximizing player
					temp=minmax(depth+1,player,alpha,beta)[0];
					if(alpha<temp) {
						alpha=temp;
						res[0]=temp;
						res[1]=move[0];
						res[2]=move[1];
					}
				}
				else {
					//maximizing player
					temp=minmax(depth+1,player,alpha,beta)[0];
					if(beta>temp) {
						beta=temp;
						res[0]=temp;
						res[1]=move[0];
						res[2]=move[1];
					}
				}
				board[move[0]][move[1]]=0;					
			}
		}
		return res;
	}
	
	public void playAI() {
		if(winner>0)return;
		int next[]=minmax(0,cur,Integer.MIN_VALUE,Integer.MAX_VALUE);
		board[next[1]][next[2]]=cur;
		check();
	}
	
	public void play(int x,int y) {
		if(winner>0)return;
		board[x][y]=cur;
		check();
	}
	
	public void setmn(int a,int b) {
		m=a;
		n=b;	
	}
}
