import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Tetris extends JFrame{
	JButton[][] b= new JButton[15][9];
	int[][] save = new int[15][9];
	String input = "C:\\JAVA\\graphic.txt";
	
	Color[]c = {Color.BLUE, Color.GREEN , Color.CYAN};
	
	int[][] block = new int[2][4];
	
	final int[][] block_y = {{-1,0,0,1},
					   {-1,-1,0,1},
					   {-1,-1,0,1},
					   {-1,0,1,2},
					   {-1,-1,0,0},
					   {-1,0,0,1},
					   {-1,0,0,1},
					   {}};
	
	final int[][] block_x = {{4,4,5,4},
					   {3,4,4,4},
					   {4,5,4,4},
					   {4,4,4,4},
					   {3,4,3,4},
					   {5,4,5,4},
					   {4,4,5,5}};
	
	
	int[][][] rotate_y = {{{-1,0,1,1},{1,0,1,-1},{1,0,-1,-1},{-1,0,-1,1}},
						  {{-2,-1,0,1},{0,1,0,-1},{2,1,0,-1},{0,-1,0,1}},
						  {{-1,0,0,1},{1,2,0,-1},{1,0,0,-1},{-1,-2,0,1}},
						  {{-1,0,1,2},{1,0,-1,-2},{-1,0,1,2},{1,0,-1,-2}},
						  {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}},
						  {{0,0,1,1},{2,0,1,-1},{0,0,-1,-1},{-2,0,-1,1}},
						  {{-2,-1,0,1},{0,-1,0,-1},{2,1,0,-1},{0,1,0,1}}};
						
	
	int[][][] rotate_x = {{{1,0,1,-1},{1,0,-1,-1},{-1,0,-1,1},{-1,0,1,1}},
			   			  {{0,1,0,-1},{2,1,0,-1},{0,-1,0,1},{-2,-1,0,1}},
			   			  {{1,2,0,-1},{1,0,0,-1},{-1,-2,0,1},{-1,0,0,1}},
			   			  {{-1,0,1,2},{1,0,-1,-2},{-1,0,1,2},{1,0,-1,-2}},
			   			  {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}},
			   			  {{2,0,1,-1},{0,0,-1,-1},{-2,0,-1,1},{0,0,1,1}},
			   			  {{0,-1,0,-1},{2,1,0,-1},{0,1,0,1},{-2,-1,0,1}}};
	
	
	int rot = 0;
	int block_index = 0;
	boolean Oper= false;
	Random r = new Random();
	
	//테트리스 함수
	Tetris(){
		setLayout(new GridLayout(15,9));
		
		int index = r.nextInt(7);
		int co_index = r.nextInt(3);
		
		block_index = index;
		for(int i=0; i<4;i++) {
			int _x =block_x[index][i];
			int _y =block_y[index][i];
			block[0][i] = _y;
			block[1][i] = _x;
		}
		
		save_data();
		set();
		
		setSize(400,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	
		
		//움직이는 동작
		new Thread(new Runnable() {
			public void run() {
				try {
					
					while(true) {
						Thread.sleep(1000);
						
						//remove_full()과 같은 코드  : 한 줄이 다 차면 삭제하는 함수
						int[] remove = new int[15];
						
						for(int i=0; i<15;i++) {
							int num=0;
							for(int j=0; j<9;j++) {
								if(save[i][j]==1) {
									num++;
								}
							}
							
							if(num == 9) {
								remove[i] = 1;
							}
						}
						
						for(int i=0; i<15;i++) {
							if(remove[i]==1) {
								for(int j=i; j>0;j--) {
									for(int k=0; k<9;k++) {
										save[j][k]= save[j-1][k]; 
									}
								}
							}
						}
						//값 저장하고, 가져오기
						save_data();
						get_data();
						
						//back_set() 과 비슷한 함수 : 게임 화면을 설정해주는 함수
						for(int i=0; i<15;i++) {
							for(int j=0; j<9;j++) {
								if(save[i][j] == 1) {
									b[i][j].setBackground(Color.LIGHT_GRAY);
								}
								else {
									b[i][j].setBackground(Color.WHITE);
								}
							}
						}
						
						//시간에 따라 1씩 증가하기
						for(int i=0;i<4;i++) {				
							block[0][i]++;
						}
						
						//확인하기
						for(int i=0; i<4;i++){
							int y=block[0][i];
							int x=block[1][i];
							
							if(save[0][4] == 1) {
								end_game();
								break;
							}
							else if(y<14 && save[y+1][x] ==1) {
								Oper = false;
								break;
							}
							else if(y==14) {
								Oper = false;
								break;
							}
							else {
								Oper = true;
								continue;
							}
						}
						
						//색칠하기
						int index = r.nextInt(7);
						for(int j=0; j<4;j++) {
							JButton butto = b[block[0][j]][block[1][j]];
							butto.setBackground(Color.DARK_GRAY);
							
							if(Oper == false) {
								save[block[0][j]][block[1][j]] = 1;
								int _x =block_x[index][j];
								int _y =block_y[index][j];
								block[0][j] = _y;
								block[1][j] = _x;
								rot=0;
								block_index= index;
							}
						}	
						
						save_data();
						get_data();
					}
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).run();
	}
	
	
	void set() {
		for(int i=0; i<15;i++) {
			for(int j=0; j<9;j++) {
				b[i][j] = new JButton();
				add(b[i][j]);
				b[i][j].setBackground(Color.WHITE);
				b[i][j].addKeyListener(new MykeyListenr());
			}
		}		
	}
	
	
	class MykeyListenr extends KeyAdapter{
	
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
				int keycode = e.getKeyCode();
				if(keycode == KeyEvent.VK_RIGHT) {
					int range = block[1][0];					//최대 range 찾기
					for(int i=0; i<4;i++) {
						range = range > block[1][i] ? range : block[1][i];
					}
					
					if(range<8) {								//range가 8보다 작아야 조건문 실행
						boolean next = true;
						for(int i=0; i<4;i++) {
							int y=block[0][i];
							int x=block[1][i];
							
							if(save[y][x+1] == 1 ) {			//만약 자기 오른쪽에 블록이 있다면 못 움직임.
								next = false;
								break;
							}
							else {
								next = true;
							}
						}
						
						if(next == true) {						//for문이 끝날 때까지 next 가 true면 자기 옆에 블록이 없다는 뜻이다. = 이동할 수 있다. 
							for(int i=0; i<4;i++) {
								block[1][i]++;
							}
						}
					}
					
					back_set();									//블록이동을 했으므로 background 다시 설정해주기
				}
				else if(keycode == KeyEvent.VK_LEFT) {	
					int range = block[1][0];					//최소 range 찾기
					for(int i=0; i<4;i++) {
						range = range < block[1][i] ? range : block[1][i];
					}
					
					
					if(range>0) {								//range가 0보다 커야 조건문 실행
						boolean next = true;
						for(int i=0; i<4;i++) {
							int y=block[0][i];
							int x=block[1][i];
							
							
							if(save[y][x-1] == 1 ) {			//만약 자기 왼쪽에 블록이 있다면 못 움직임.
								next = false;
								break;
							}
							else {
								next = true;
							}
						}
						
						
						if(next == true) {  		 			//for문이 끝날 때까지 next 가 true면 자기 옆에 블록이 없다는 뜻이다. = 이동할 수 있다. 
							for(int i=0; i<4;i++) {
								block[1][i]--;
							}
						}
					}
					
					back_set();									//블록이동을 했으므로 background 다시 설정해주기
				}
				else if(keycode == KeyEvent.VK_DOWN) {
					for(int i=0; i<4;i++) {
						block[0][i]++;
					}
					
					back_set();
				}
				else if(keycode == KeyEvent.VK_UP) {
					
					int[][] newblock = block;
					
					boolean next = true;
					for(int i=0;i<4;i++) {				
						newblock[0][i] += rotate_y[block_index][(rot+1)%4][i];
						newblock[1][i] += rotate_x[block_index][(rot+1)%4][i];
						if(save[newblock[0][i]][newblock[1][i]] == 1) {
							next =false;
							break;
						}
						else {
							next = true;
						}
					}
					
					if(next == true) {
						for(int i=0; i<4;i++) {
							block[0][i] = newblock[0][i];
							block[1][i] = newblock[1][i];
						}
						rot = (rot+1)%4;
					}
					
					back_set();
				}
			}
		};
	
	//게임 화면을 설정해주는 함수
	void back_set() {
		save_data();
		get_data();
		
		for(int i=0; i<4;i++){
			int y=block[0][i];
			int x=block[1][i];
			
			if(save[0][4] == 1) {
				end_game();
				break;
			}
			else if(y<14 && save[y+1][x] ==1) {
				Oper = false;
				break;
			}
			else if(y==14) {
				Oper = false;
				break;
			}
			else {
				Oper = true;
				continue;
			}
		}
		
		int index = r.nextInt(7);
		for(int j=0; j<4;j++) {
			JButton butto = b[block[0][j]][block[1][j]];
			butto.setBackground(Color.DARK_GRAY);
			
			if(Oper == false) {
				save[block[0][j]][block[1][j]] = 1;
				int _x =block_x[index][j];
				int _y =block_y[index][j];
				block[0][j] = _y;
				block[1][j] = _x;
				rot=0;
				block_index= index;
			}
		}
		save_data();
		get_data();
		remove_full();
		
		
		
		for(int i=0; i<15;i++) {
			for(int j=0; j<9;j++) {
				if(save[i][j] == 1) {
					b[i][j].setBackground(Color.LIGHT_GRAY);
				}
				else {
					b[i][j].setBackground(Color.WHITE);
				}
			}
		}
		
		if(save[0][4] == 1) {
			end_game();
		}
		
		if(Oper == true) {
			for(int j=0; j<4;j++) {
				JButton butto = b[block[0][j]][block[1][j]];
				butto.setBackground(Color.DARK_GRAY);
			}
		}
	}
	
	//한 줄이 다 차면 삭제하는 함수
	void remove_full() {
		save_data();
		get_data();
		int[] remove = new int[15];
		
		for(int i=0; i<15;i++) {
			int num=0;
			for(int j=0; j<9;j++) {
				if(save[i][j]==0) {
					num++;
				}
			}
			
			if(num == 15) {
				remove[i] = 1;
			}
		}
		
		for(int i=0; i<15;i++) {
			if(remove[i]==1) {
				if(i==0)									//0에서 삭제가 일어날 경우
					for(int j=0;j<9;j++) {
						save[0][j] =0;
					}
				else {
					for(int j=i; j>0;j++) {					//0보다 큰 값에서 삭제가 일어날 경우
						for(int k=0; k<9;k++) {
							save[j][k]= save[j-1][k]; 
						}
					}
				}
				
			}
		}
		save_data();
	}
	
	//파일에서 데이터를 가져오는 함수
	/*
	 * 기능은 잘 수행되나 중간에 get_data()함수때문에 오류가 발생해서 thread가 실행을 멈춥니다. 
	 * 따라서 게임의 실행을 위해 주석 처리 하겠습니다.ㅠㅠ
	 */
	void get_data() {
		try {
			FileReader filereader = new FileReader(input);
			BufferedReader bufReader = new BufferedReader(filereader);
			 
			 String line ="";
			 int num=0;
			 while((line = bufReader.readLine()) !=null) {
				String[] txt_to_array = line.split(" ");
				for(int i=0; i<9;i++) {
					save[num][i] = Integer.parseInt(txt_to_array[i]);
				}
				num++;
			 }
		}
		catch(IOException e) {
		}
	}

	
	//파일에 데이터는 저장하는 함수
	void save_data() {
		try (FileOutputStream fos = new FileOutputStream(input);){
			for(int i=0; i<15;i++) {
				for(int j=0; j<9;j++) {
					String s ="";
					s = s+save[i][j];
					fos.write(s.getBytes());
					fos.write(' ');
				}
				fos.write('\n');
			}
		}
		catch(IOException e) {
		}
	}

	//게임을 종료하는 함수
	void end_game() {
		Thread.interrupted();
		JOptionPane.showMessageDialog(null, "Game Over!","",JOptionPane.WARNING_MESSAGE);
		 
		for(int i=0; i<15;i++) {
			for(int j=0; j<9;j++) {
				b[i][j].setBackground(Color.WHITE);
				b[i][j].addKeyListener(null);
			}
		}	
		
		setVisible(false);
		System.exit(0);
	}
	
	//메인함수
	public static void main(String[] args) {
		new Tetris();
	}
}





