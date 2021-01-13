import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Memo extends JFrame implements ActionListener {
	class MyPanel extends JPanel {
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawString("메모장입니다!",20,20);
		}
	}
	Memo(){
		setTitle("메모장");
		setSize(400,300);
		makeMenu();
		add(new MyPanel());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	void makeMenu() {
		JMenuItem item;
		KeyStroke key;
		JMenuBar mb = new JMenuBar();
		
		JMenu m1 = new JMenu("파일(F)");
		m1.setMnemonic(KeyEvent.VK_F);
		item = new JMenuItem("새로 만들기(N)",KeyEvent.VK_N);
		item.addActionListener(this);
		m1.add(item);
		item = new JMenuItem("새창(W)",KeyEvent.VK_W);
		item.addActionListener(this);
		m1.add(item);
		item = new JMenuItem("열기(O)",KeyEvent.VK_O);
		item.addActionListener(this);
		m1.add(item);
		item = new JMenuItem("저장(S)",KeyEvent.VK_S);
		item.addActionListener(this);
		m1.add(item);
		item = new JMenuItem("다른 이름으로 저장(A)",KeyEvent.VK_A);
		item.addActionListener(this);
		m1.add(item);
		m1.addSeparator();
		item = new JMenuItem("페이지 설정(U)",KeyEvent.VK_U);
		item.addActionListener(this);
		m1.add(item);
		item = new JMenuItem("인쇄(P)",KeyEvent.VK_P);
		item.addActionListener(this);
		m1.add(item);
		m1.addSeparator();
		item = new JMenuItem("끝내기(X)",KeyEvent.VK_X);
		item.addActionListener(this);
		m1.add(item);
		
		JMenu m2 = new JMenu("편집(E)");
		m2.setMnemonic(KeyEvent.VK_E);
		item = new JMenuItem("실행 취소(U)",KeyEvent.VK_N);
		item.addActionListener(this);
		m2.add(item);
		m2.addSeparator();
		item = new JMenuItem("잘라내기(T)",KeyEvent.VK_T);
		item.addActionListener(this);
		m2.add(item);
		item = new JMenuItem("복사(C)",KeyEvent.VK_C);
		item.addActionListener(this);
		m2.add(item);
		item = new JMenuItem("붙여넣기(P)",KeyEvent.VK_P);
		item.addActionListener(this);
		m2.add(item);
		item = new JMenuItem("삭제(L)	",KeyEvent.VK_L);
		item.addActionListener(this);
		m2.add(item);
		m2.addSeparator();
		item = new JMenuItem("Bing으로 검색(S)",KeyEvent.VK_S);
		item.addActionListener(this);
		m2.add(item);
		item = new JMenuItem("찾기(F)",KeyEvent.VK_F);
		item.addActionListener(this);
		m2.add(item);
		item = new JMenuItem("다음 찾기(N)",KeyEvent.VK_N);
		item.addActionListener(this);
		m2.add(item);
		item = new JMenuItem("이전 찾기(V)",KeyEvent.VK_V);
		item.addActionListener(this);
		m2.add(item);
		item = new JMenuItem("바꾸기(R)",KeyEvent.VK_R);
		item.addActionListener(this);
		m2.add(item);
		item = new JMenuItem("이동(G)",KeyEvent.VK_G);
		item.addActionListener(this);
		m2.add(item);
		m2.addSeparator();
		item = new JMenuItem("모두 선택(A)",KeyEvent.VK_A);
		item.addActionListener(this);
		m2.add(item);
		item = new JMenuItem("시간/날짜(D)	",KeyEvent.VK_D);
		item.addActionListener(this);
		m2.add(item);
		
		JMenu m3 = new JMenu("서식(O)");
		m3.setMnemonic(KeyEvent.VK_O);
		item = new JMenuItem("자동 줄 바꿈(W)",KeyEvent.VK_W);
		item.addActionListener(this);
		m3.add(item);
		item = new JMenuItem("글꼴(F)",KeyEvent.VK_F);
		item.addActionListener(this);
		m3.add(item);
		
		JMenu m4 = new JMenu("보기(V)");
		m4.setMnemonic(KeyEvent.VK_V);
		item = new JMenuItem("확대하기/축소하기",KeyEvent.VK_W);
		item.addActionListener(this);
		m4.add(item);
		item = new JMenuItem("상태 표시줄(S)",KeyEvent.VK_S);
		item.addActionListener(this);
		m4.add(item);
		
		JMenu m5 = new JMenu("도움말(H)");
		m5.setMnemonic(KeyEvent.VK_H);
		item = new JMenuItem("도움말 보기(H)",KeyEvent.VK_H);
		item.addActionListener(this);
		m5.add(item);
		item = new JMenuItem("피드백 보내기(F)",KeyEvent.VK_F);
		item.addActionListener(this);
		m5.add(item);
		m5.addSeparator();
		item = new JMenuItem("메모장 정보(A)",KeyEvent.VK_A);
		item.addActionListener(this);
		m5.add(item);
		
		mb.add(m1);
		mb.add(m2);
		mb.add(m3);
		mb.add(m4);
		mb.add(m5);
		setJMenuBar(mb);
		

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem mi = (JMenuItem) (e.getSource());
		System.out.println(mi.getText() + "메뉴를 선택했습니다.");
	}
	
	
	public static void main(String[] args) {
		new Memo();
	}
}
