import java.util.*;

class Location        //sets the location value       
{
	int a,b;
	Location(int x, int y)
	{
		a=x;
		b=y;
	}
}

class Tic
{
	int mat[][];
	Tic()
	{
		int i,j;
		mat=new int[3][3];
		for(i=0;i<3;i++)
		{
			for(j=0;j<3;j++)
			{
				mat[i][j]=2;
			}
		}
	}

	void display()        // displays the current situation of the game
	{
		int i,j;
		char ch;
		System.out.println("\n.........................");
		for(i=0;i<3;i++)
		{
			System.out.print("|");
			for(j=0;j<3;j++)
			{
				if(mat[i][j]==2)
				{
					ch=' ';
				}
				else if(mat[i][j]==3)
				{
					ch='X';
				}
				else
				{
					ch='O';
				}
				System.out.print(ch+"\t|");
			}
			System.out.println("\n.........................");
		}
	}

	void user_move()       //sets the user move
	{
		int x,y;
		Scanner sc=new Scanner(System.in);
		System.out.println("\n Enter new position : ");
		x=sc.nextInt();
		y=sc.nextInt();
		x=x-1;
		y=y-1;
		if(x<0||y<0||x>=3||y>=3)
		{
			System.out.println("\n Invalid Position");
			user_move();
		}
		else if(mat[x][y]!=2)
		{
			System.out.println("\n Invalid position");
			user_move();
		}
		else
		{
			mat[x][y]=3;
		}
	}
	
	void computer_move(Location ob)       //sets the computer move
	{
		mat[ob.a][ob.b]=5;
	}
	
	Location random_cell()           // for finding out random blank cell around an user occupied corner
	{
		if(mat[0][0]==3)
		{
			if(mat[0][1]==2)
			{
				return new Location(0,1);
			}
			if(mat[1][0]==2)
			{
				return new Location(1,0);
			}
			if(mat[1][1]==2)
			{
				return new Location(1,1);
			}
		}
		if(mat[0][2]==3)
		{
			if(mat[0][1]==2)
			{
				return new Location(0,1);
			}
			if(mat[1][2]==2)
			{
				return new Location(1,2);
			}
			if(mat[1][1]==2)
			{
				return new Location(1,1);
			}
		}
		if(mat[2][0]==3)
		{
			if(mat[1][0]==2)
			{
				return new Location(1,0);
			}
			if(mat[2][1]==2)
			{
				return new Location(2,1);
			}
			if(mat[1][1]==2)
			{
				return new Location(1,1);
			}
		}
		if(mat[2][2]==3)
		{
			if(mat[2][1]==2)
			{
				return new Location(2,1);
			}
			if(mat[1][2]==2)
			{
				return new Location(1,2);
			}
			if(mat[1][1]==2)
			{
				return new Location(1,1);
			}
		}
		return null;
	}

	Location corner_cell()        // to find any blank corner cell
	{
		if(mat[0][0]==2)
		{
			return new Location(0,0);
		}
		if(mat[0][2]==2)
		{
			return new Location(0,2);
		}
		if(mat[2][0]==2)
		{
			return new Location(2,0);
		}
		if(mat[2][2]==2)
		{
			return new Location(2,2);
		}
		return null;
	}


	Location free_space()
	{
		int i,j;
		for(i=0;i<3;i++)
		{
			for(j=0;j<3;j++)
			{
				if (mat[i][j]==2)
					{
						return new Location(i,j);
					}	
			}
		}
		return null;
	}
	
	Location posswin_comp()  // determines the winning move by the computer
	{
		int s,i;
		for(i=0;i<3;i++)
		{
			s=mat[i][0]*mat[i][1]*mat[i][2];
			if(s==50)
			{
				if(mat[i][0]==2)
				{
					return new Location(i,0);	
				}
				else if(mat[i][1]==2)
				{
					return new Location(i,1);
				}
				else
				{
					return new Location(i,2);
				}
	
			}
		}

		for(i=0;i<3;i++)
		{
			s=mat[0][i]*mat[1][i]*mat[2][i];
			if(s==50)
			{
				if(mat[0][i]==2)
				{
					return new Location(0,i);	
				}
			
				else if(mat[1][i]==2)
				{
					return new Location(1,i);
				}
				else
				{
					return new Location(2,i);
				}
			
			}
		}
		
		s=mat[0][0]*mat[1][1]*mat[2][2];
		if(s==50)
		{
			if(mat[0][0]==2)
			{
				return new Location(0,0);	
			}
			else if(mat[1][1]==2)
			{
				return new Location(1,1);
			}
			else
			{
				return new Location(2,2);
			}
		}

		s=mat[0][2]*mat[1][1]*mat[2][0];
		if(s==50)
		{
			if(mat[0][2]==2)
			{
				return new Location(0,2);	
			}
			else if(mat[1][1]==2)
			{
				return new Location(1,1);
			}
			else
			{
				return new Location(2,0);
			}
		}
		return null;

	}
	int win_check()       // checks whether the user already won
	{
		int s,i;
		for(i=0;i<3;i++)
		{
			s=mat[i][0]*mat[i][1]*mat[i][2];
			if(s==27)
			{
				return 1;
			}
		}
		for(i=0;i<3;i++)
		{
			s=mat[0][i]*mat[1][i]*mat[2][i];
			if(s==27)
			{
				return 1;
			}
		}
		
		s=mat[0][0]*mat[1][1]*mat[2][2];
		if(s==27)
		{
			return 1;
		}

		s=mat[0][2]*mat[1][1]*mat[2][0];
		if(s==27)
		{
			return 1;
		}
		return 0;	
	}


	Location posswin_user()     // determines the winning move by the user to block it later
	{
		int s,i;
		for(i=0;i<3;i++)
		{
			s=mat[i][0]*mat[i][1]*mat[i][2];
			if(s==18)
			{
				if(mat[i][0]==2)
				{
					return new Location(i,0);	
				}
			
				else if(mat[i][1]==2)
				{
					return new Location(i,1);
				}
				else
				{
					return new Location(i,2);
				}
	
			}
		}
		for(i=0;i<3;i++)
		{
			s=mat[0][i]*mat[1][i]*mat[2][i];
			if(s==18)
			{
				if(mat[0][i]==2)
				{
					return new Location(0,i);	
				}
			
				else if(mat[1][i]==2)
				{
					return new Location(1,i);
				}
				else
				{
					return new Location(2,i);
				}
			}
		}
		
		s=mat[0][0]*mat[1][1]*mat[2][2];
		if(s==18)
		{
			if(mat[0][0]==2)
			{
				return new Location(0,0);	
			}
			else if(mat[1][1]==2)
			{
				return new Location(1,1);
			}
			else
			{
				return new Location(2,2);
			}
		}

		s=mat[0][2]*mat[1][1]*mat[2][0];
		if(s==18)
		{
			if(mat[0][2]==2)
			{
				return new Location(0,2);	
			}
			else if(mat[1][1]==2)
			{
				return new Location(1,1);
			}
			else
			{
				return new Location(2,0);
			}
		}
		return null;

	}
}

class Game
{
	public static void main(String[]args)
	{
		Tic t1=new Tic();
		Location p,q,q2;
		int flag,count=1,check;
		Scanner sc=new Scanner(System.in);
		System.out.println("\n..........................WELCOME TO TIC TAC TOE.............................\n ");
		System.out.println("\n First Move :: Enter 1 for Computer Move or 2 for User Move : ");
		flag=sc.nextInt();
		if(flag==1)
		{
			do
			{
			switch(count)
			{
				case 1:
					System.out.println("\n Computer Move");
					t1.computer_move(new Location(1,1));
					System.out.println("\n  After Computer Move");
					t1.display();
					break;
				case 2:
				case 4:
				case 6:
				case 8:
					System.out.println("\n User Move");
					t1.user_move();
					System.out.println("\n After User Move");
					t1.display();
					break;
				case 3:
					System.out.println("\n Computer Move");
					q=t1.random_cell();
					t1.computer_move(q);
					System.out.println("\n After Computer Move");
					t1.display();
					break;
				case 5:
					System.out.println("\n Computer Move");
					p=t1.posswin_comp();
					if(p!=null)
					{
						t1.computer_move(p);
						System.out.println("\n Computer Won");
						System.out.println("\n Result :");
						t1.display();
						System.out.println("\n..........................THE END.............................\n ");
						System.exit(0);
					}
					else
					{
						q=t1.posswin_user();
						if(q!=null)
						{
							t1.computer_move(q);
						}
						else
						{
							q=t1.corner_cell();
							t1.computer_move(q);
						}
						System.out.println("\n After Computer Move");
						t1.display();
					}
					break;
				case 7:
				case 9:
					System.out.println("\n After Computer Move");
					p=t1.posswin_comp();
					if(p!=null)
					{
						t1.computer_move(p);
						System.out.println("\nComputer Won");
						t1.display();
						System.out.println("\n..........................THE END.............................\n ");
						System.exit(0);
					}
					else
					{
						q=t1.posswin_user();
						if(q!=null)
						{
							t1.computer_move(q);
						}
						else
						{
							q=t1.free_space();
							t1.computer_move(q);
						}
						System.out.println("\n After Computer Move");
						t1.display();
					}
					break;
			}
			count=count+1;
			}while(count<=9);
		}
		else if(flag==2)
		{
			do
			{
			switch(count)
			{
				case 1:
				case 3:
					System.out.println("\n User Move");
					t1.user_move();
					System.out.println("\n After User Move");
					t1.display();
					break;
				case 5:
				case 7:
				case 9:
					System.out.println("\n User Move");
					t1.user_move();
					check=t1.win_check();
					System.out.println("\n After User Move");
					if(check==1)
					{
						System.out.println("\n User Won");
						t1.display();
						System.out.println("\n..........................THE END.............................\n ");
						System.exit(0);
					}
					t1.display();
					break;
				case 2:
					System.out.println("\n Computer Move");
					q=t1.corner_cell();
					t1.computer_move(q);
					System.out.println("\n After Computer Move");
					t1.display();
					break;
				case 4:
					System.out.println("\n Computer Move");
					q=t1.posswin_user();
					if(q!=null)
					{
						t1.computer_move(q);
					}
					else
					{
						q2=t1.corner_cell();
						t1.computer_move(q2);
					}
					System.out.println("\n After Computer Move");
					t1.display();
					break;
				case 6:
					System.out.println("\n Computer Move");
					p=t1.posswin_comp();
					if(p!=null)
					{
						t1.computer_move(p);
						System.out.println("\n Computer Won");
						System.out.println("\n Result :");
						t1.display();
						System.out.println("\n..........................THE END.............................\n ");
						System.exit(0);
					}
					else
					{
						q=t1.posswin_user();
						if(q!=null)
						{
							t1.computer_move(q);
						}
						else
						{
							q=t1.corner_cell();
							t1.computer_move(q);
						}
						System.out.println("\n After Computer Move");
						t1.display();
					}
					break;
				case 8:
					System.out.println("\n After Computer Move");
					p=t1.posswin_comp();
					if(p!=null)
					{
						t1.computer_move(p);
						System.out.println("\nComputer Won");
						t1.display();
						System.out.println("\n..........................THE END.............................\n ");
						System.exit(0);
					}
					else
					{
						q=t1.posswin_user();
						if(q!=null)
						{
							t1.computer_move(q);
						}
						else
						{
							q=t1.free_space();
							t1.computer_move(q);
						}
						System.out.println("\n After Computer Move");
						t1.display();
					}
					break;
			}
			count=count+1;
		}while(count<=9);
		}
		else
		{
			System.out.println("\n Please Enter Valid Option: ");
		}
	System.out.println("\n ........Draw......");
	System.out.println("\n..........................THE END.............................\n ");
	}
}

