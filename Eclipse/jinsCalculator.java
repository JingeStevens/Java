/*
 * Author: Wang Jinge			
 * Date: 3/4/2019	
 * Simple Calculator
 * functions:
 * 1.buttons for events(number,calculate).
 * 2.design for reasonable layout instead of "borderlayout" so that looks better
 * 3. +,-,*,/,sqrt,backspace,clean,
 * 4.some other functions still have bugs so that doesn`t work and fix in the future... :D
 */
package calculator;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/*
 * ʵ��һ���򵥵ļ�����
 */
public class jinsCalculator implements ActionListener{
	//����ı���
	JTextField jtf=new JTextField("0");
	//��Ӱ�ť
	String[] s1={"7","8","9","/","sqrt","4","5","6","*","%","1","2","3","-","1/x","0","+/-",".","+","="};
	String[] s2={"Backspace","CE","C"};
	String[] s3={"MC","MR","MS","M+"};
	JButton[] btn1=new JButton[s1.length];
	JButton[] btn2=new JButton[s2.length];
	JButton[] btn3=new JButton[s3.length];
	JButton btn4=new JButton("");
	boolean isOperPressed=true;//�ж��Ƿ�������ĵ�һ����
	boolean error=false;//�ж��Ƿ��д�
	//��ȡ�ı����ֵ
	double result=0;//�õ�������
	double get_num1=0;//��ȡ��������ǰ��ֵ
	double get_num2=0;//��ȡ�����������ֵ
	int tag=0;//�ж������,1Ϊ��+����2Ϊ��-����3Ϊ��*����4Ϊ��/����0Ϊ��ʼ��
//	String operate="=";//�����
	
	
	jinsCalculator(){
		//�������
		JFrame jf=new JFrame("Jin`s calculator, enjoy!");
		jf.setSize(450,300);//���ô�С
		jf.setResizable(false);//���ô�С���ɱ�
		jf.setVisible(true);//���ÿɼ�
		jf.setLocationRelativeTo(null);//��ܷ���Ļ�м�
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//��������
/*****************************************************************************************************************************************/		
		//�������
		JPanel jp=new JPanel();
		jp.setBackground(Color.white);//���ñ�����ɫ
		jp.setLayout(null);//ȡ��Ĭ�ϲ���
		jf.add(jp);
/*****************************************************************************************************************************************/		
		//����ı���
		jtf.setEditable(false);//���ɱ༭
		jtf.setFocusable(false);//����ʾ���
		jtf.setFont(new Font("����",Font.PLAIN,28));//���������ʽ
        jtf.setBackground(Color.WHITE);//�����ı��򱳾���ɫΪ��ɫ
		jtf.setBounds(10,10,jf.getSize().width-30,40);//jf.size().width����ȡ��ܵĿ��
		jtf.setHorizontalAlignment(JTextField.RIGHT);//�ı����е����ݲ����Ҷ��뷽ʽ
		jp.add(jtf);
/*****************************************************************************************************************************************/			
		//��Ӱ�ť
		int x,y;
		//��Ӱ�ťbtn1
		for(int i=0;i<s1.length;i++){
			btn1[i]=new JButton(s1[i]);
			x=jf.getSize().width-350+i%5*65;//jf.size().width����ȡ��ܵĿ��
			y=jf.getSize().height-180+35*(i/5);//jf.getSize().height����ȡ��ܵĸ߶�
			btn1[i].setBounds(x,y,60,30);//���ð�ť���꼰��С
			jp.add(btn1[i]);
		}
		//��Ӱ�ťbtn2
		for(int i=0;i<s2.length;i++){
			btn2[i]=new JButton(s2[i]);
			x=jf.getSize().width-350+i%3*110;//jf.getSize().width����ȡ��ܵĿ��
			y=btn1[0].getBounds().y-50;//btn1[0].getBounds().y����ȡbtn1[0]��y����
			btn2[i].setBounds(x,y,100,30);//���ð�ť���꼰��С
			btn2[i].setForeground(Color.red);//���ð�ť�ϵ�����Ϊ��ɫ
			jp.add(btn2[i]);
		}
		//��Ӱ�ťbtn3
		for(int i=0;i<s3.length;i++){
			btn3[i]=new JButton(s3[i]);
			x=btn1[0].getBounds().x-80;//btn1[0].getBounds().x����ȡbtn1[0]��x����
			y=jf.getSize().height-180+(i%4)*35;//jf.getSize().height����ȡ��ܵĸ߶�
			btn3[i].setBounds(x,y,60,30);//���ð�ť���꼰��С
			btn3[i].setForeground(Color.red);//���ð�ť�ϵ�����Ϊ��ɫ
			jp.add(btn3[i]);
		}
		//��Ӱ�ťbtn4
		btn4.setBounds(btn3[0].getBounds().x,btn2[0].getBounds().y,btn3[0].getBounds().width,btn3[0].getBounds().height);
		//btn4.setEnabled(false);//���ð�ť���ɵ��
		jp.add(btn4);
		//��������ú�ɫ��ʾ
        btn1[3].setForeground(Color.red);
        btn1[8].setForeground(Color.red);
        btn1[13].setForeground(Color.red);
        btn1[18].setForeground(Color.red);
        btn1[19].setForeground(Color.red);
/*****************************************************************************************************************************************/		
        //����ť��Ӽ�����
        for(int i=0;i<s1.length;i++){
			btn1[i].addActionListener(this);
		}
        for(int i=0;i<s2.length;i++){
			btn2[i].addActionListener(this);
		}
        for(int i=0;i<s3.length;i++){
			btn3[i].addActionListener(this);
		}
	}
/*****************************************************************************************************************************************/
	/*
	 * ��Ӱ�ť����¼�
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 
        String s=e.getActionCommand();// ��ȡ�����ť������
        if(s.equals("Backspace")){//��"Backspace"��,s.equals(btn3[0])�ж�s�Ƿ���btn3[0]���
            do_Backspace();
        }else if(s.equals("CE")){//��"CE"��
        	do_CE();
            //jtf.setText("0");
        }else if(s.equals("C")){//��"C"��
            do_C();
        }else if("0123456789.".contains(s)){//�����ּ�����С�����,a.contains(b) �ж�a���Ƿ����b
            do_Number(s);
        }else{//���������
            do_Operator(s);
        }
	}
	//����"Backspace"����ɾ�����һ������
	void do_Backspace(){
		String s=jtf.getText();//��ȡ�ı�������
		int i=s.length();//��ȡ�ı����ݳ���
		if(s.equals("0")){
			s = s;
		}else{
			if(i==1){
				s="0";
			}else{
				s=s.substring(0, i-1);//ȥ�����һ���ַ�	
			}
		}
		jtf.setText(s);
		isOperPressed=true;
	}
	//����"CE"���������ǰ����
	void do_CE(){
		jtf.setText("0");
		isOperPressed=true;
	}
	//����"C"����ȫ����������¿�ʼ����
	void do_C(){
		jtf.setText("0");
		isOperPressed=true;
		tag=0;
	}
	//�������ּ�����С�����
	void do_Number(String s){
		if (isOperPressed) {//����ĵ�һ������
            jtf.setText(s);
        } else if ((s.equals(".")) && (!jtf.getText().contains("."))) {//�������С���㣬����֮ǰû��С���㣬��С���㸽�ڽ���ı���ĺ���
        	jtf.setText(jtf.getText() + ".");
        } else if (!s.equals(".")) {//�������Ĳ���С���㣬�����ּ����ı���Ľ������           
            jtf.setText(jtf.getText() + s);
        }
		isOperPressed = false;
	}
	//�����������
	void do_Operator(String s){
		if(s.equals("=")){//��������
			get_num2=Double.parseDouble(jtf.getText());//��ȡ������ı����ֵ
			if(error){//���������
				warnInformation();			
			}else{
				switch (tag) {
				case 0://�������ֺ�ֱ�ӵ�Ⱥţ�û�������
					result=get_num2;
					break;
				case 1://�ӷ�
					result=get_num1+get_num2;
					break;
				case 2://����
					result=get_num1-get_num2;
					break;
				case 3://�˷�
					result=get_num1*get_num2;
					break;
				case 4://����
					if(get_num2==0){//��������Ϊ0
						warnInformation();//���������
					}else{
						result=get_num1/get_num2;												
					}	
					break;
				default:
					break;
				}
				//ȥ���������".0"�ַ�
				String s1=String.valueOf(result);
				int i=s1.length();
				if(s1.substring(i-2).equals(".0")){//��������λ�ַ�Ϊ".0"����ȥ��
					s1=s1.substring(0,i-2);
				}else{
					s1=s1;
				}
				jtf.setText(s1);//������
				isOperPressed=true;//ȡ��һֱ����
				tag=0;//��ʼ��
			}
		}else{
			get_num1=Double.parseDouble(jtf.getText());//��ȡ�ı����ֵ
			if(s.equals("+")){//�ӷ�����
				tag=1;
			}else if(s.equals("-")){//��������
				tag=2;				
			}else if(s.equals("*")){//�˷�����
				tag=3;
			}else if(s.equals("/")){//��������
				tag=4;
			}else if(s.equals("+/-")){//�����ű任
				result=get_num1*(-1);
			}else if(s.equals("sqrt")){//��������
				result=Math.sqrt(get_num1);
			}else if(s.equals("%")){//�ٷֺ�����
				result=get_num1/100;
			}else if(s.equals("1/x")){//��������
				if(get_num1==0){
					error=true;
				}else{
					result=1/get_num1;				
				}
			}
			isOperPressed=true;//ʹ�õ���������������������
		}
	}

/*****************************************************************************************************************************************/
	void warnInformation(){//�����
		JOptionPane.showMessageDialog(null, "������󣡣���","����",JOptionPane.ERROR_MESSAGE);
	}
/*****************************************************************************************************************************************/
	public static void main(String[] args){
		new jinsCalculator();
	}
}
