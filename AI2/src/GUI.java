import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.WindowConstants;


public class GUI {
	DataUnit du=new DataUnit();
	
	JFrame console = new JFrame ("Console");
	JPanel jp= new JPanel(null);
	
	//Control Button
	public static final int con_num=3;
	JButton con_b[]=new JButton[con_num];
	String constr[]={"Set m/n","Start","NextMove","Display Res"};

	//Control Button Listener
	class ConListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
        	String target=e.getActionCommand();
			if(constr[0].equals(target)){
				du.setmn(Integer.valueOf(tfield[1].getText()), Integer.valueOf(tfield[2].getText()));
			}
			else if(constr[1].equals(target)){
				du.init(Integer.valueOf(tfield[3].getText()));
				display();
    		}
			else if(constr[2].equals(target)){
				int x=Integer.valueOf(tfield[4].getText());
				int y=Integer.valueOf(tfield[5].getText());
				if(x<0||x>=du.m||y<0||y>=du.m||du.board[x][y]>0)
					tfield[6].setText("Incorrect parameter");
				else du.play(x,y);
				display();
			}
			else if(constr[3].equals(target)){
				display();
			}
        }
    }
	ConListener conlis=new ConListener();
	
	public static final int text_num=7;
	JLabel label[]=new JLabel[text_num];
	String labelstr[]={"Board","Borad size m","Wincon n","Number of AI","next move x","next move Y","Help"};
	JTextArea tfield[]=new JTextArea[text_num];
	String tfieldstr[]={"Not Start","3","3","1","0","0","See User Guide"};

	void display() {			
		String str="";
		
		for(int i=0;i<du.m;i++) {
			for(int j=0;j<du.m;j++)
				str+=du.board[i][j];
			str+="\r\n";
			
		}
		if(du.winner>0)
			str+="player "+du.winner+" win!";
		tfield[0].setText(str);
	}
	
	GUI(){
		console.setSize(1000, 800);
		console.setLocationRelativeTo(null);
		console.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		//init button
		for(int i=0;i<con_num;i++){
			con_b[i]=new JButton(constr[i]);
			con_b[i].setBounds(20+150*i, 600, 140, 30);
			con_b[i].addActionListener(conlis);
			jp.add(con_b[i]);
		}
		
		
		//init Label and TextField
		for(int i=0;i<text_num;i++){
			label[i]=new JLabel(labelstr[i]);
			label[i].setBounds(30, 20+45*i, 120, 40);
			jp.add(label[i]);
			
			tfield[i]=new JTextArea(tfieldstr[i]);
			tfield[i].setBounds(160, 20+45*i, 120, 40);
			jp.add(tfield[i]);
		}
		tfield[0].setBounds(400, 20, 400, 400);
		label[0].setBounds(580, 450, 120, 40);
		
		
		console.setContentPane(jp);
		console.setVisible(true);
	}
	
	public static void main(String[] args) {
		new GUI();
	}
}
