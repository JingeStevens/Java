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
 * 实现一个简单的计算器
 */
public class jinsCalculator implements ActionListener{
	//添加文本框
	JTextField jtf=new JTextField("0");
	//添加按钮
	String[] s1={"7","8","9","/","sqrt","4","5","6","*","%","1","2","3","-","1/x","0","+/-",".","+","="};
	String[] s2={"Backspace","CE","C"};
	String[] s3={"MC","MR","MS","M+"};
	JButton[] btn1=new JButton[s1.length];
	JButton[] btn2=new JButton[s2.length];
	JButton[] btn3=new JButton[s3.length];
	JButton btn4=new JButton("");
	boolean isOperPressed=true;//判断是否是输入的第一个数
	boolean error=false;//判断是否有错
	//获取文本框的值
	double result=0;//得到运算结果
	double get_num1=0;//获取点击运算符前的值
	double get_num2=0;//获取点击运算符后的值
	int tag=0;//判断运算符,1为“+”，2为“-”，3为“*”，4为“/”，0为初始化
//	String operate="=";//运算符
	
	
	jinsCalculator(){
		//创建框架
		JFrame jf=new JFrame("Jin`s calculator, enjoy!");
		jf.setSize(450,300);//设置大小
		jf.setResizable(false);//设置大小不可变
		jf.setVisible(true);//设置可见
		jf.setLocationRelativeTo(null);//框架放屏幕中间
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//结束进程
/*****************************************************************************************************************************************/		
		//创建面板
		JPanel jp=new JPanel();
		jp.setBackground(Color.white);//设置背景颜色
		jp.setLayout(null);//取消默认布局
		jf.add(jp);
/*****************************************************************************************************************************************/		
		//添加文本框
		jtf.setEditable(false);//不可编辑
		jtf.setFocusable(false);//不显示光标
		jtf.setFont(new Font("宋体",Font.PLAIN,28));//设置字体格式
        jtf.setBackground(Color.WHITE);//设置文本框背景颜色为白色
		jtf.setBounds(10,10,jf.getSize().width-30,40);//jf.size().width：获取框架的宽度
		jtf.setHorizontalAlignment(JTextField.RIGHT);//文本框中的内容采用右对齐方式
		jp.add(jtf);
/*****************************************************************************************************************************************/			
		//添加按钮
		int x,y;
		//添加按钮btn1
		for(int i=0;i<s1.length;i++){
			btn1[i]=new JButton(s1[i]);
			x=jf.getSize().width-350+i%5*65;//jf.size().width：获取框架的宽度
			y=jf.getSize().height-180+35*(i/5);//jf.getSize().height：获取框架的高度
			btn1[i].setBounds(x,y,60,30);//设置按钮坐标及大小
			jp.add(btn1[i]);
		}
		//添加按钮btn2
		for(int i=0;i<s2.length;i++){
			btn2[i]=new JButton(s2[i]);
			x=jf.getSize().width-350+i%3*110;//jf.getSize().width：获取框架的宽度
			y=btn1[0].getBounds().y-50;//btn1[0].getBounds().y：获取btn1[0]的y坐标
			btn2[i].setBounds(x,y,100,30);//设置按钮坐标及大小
			btn2[i].setForeground(Color.red);//设置按钮上的文字为红色
			jp.add(btn2[i]);
		}
		//添加按钮btn3
		for(int i=0;i<s3.length;i++){
			btn3[i]=new JButton(s3[i]);
			x=btn1[0].getBounds().x-80;//btn1[0].getBounds().x：获取btn1[0]的x坐标
			y=jf.getSize().height-180+(i%4)*35;//jf.getSize().height：获取框架的高度
			btn3[i].setBounds(x,y,60,30);//设置按钮坐标及大小
			btn3[i].setForeground(Color.red);//设置按钮上的文字为红色
			jp.add(btn3[i]);
		}
		//添加按钮btn4
		btn4.setBounds(btn3[0].getBounds().x,btn2[0].getBounds().y,btn3[0].getBounds().width,btn3[0].getBounds().height);
		//btn4.setEnabled(false);//设置按钮不可点击
		jp.add(btn4);
		//运算符键用红色表示
        btn1[3].setForeground(Color.red);
        btn1[8].setForeground(Color.red);
        btn1[13].setForeground(Color.red);
        btn1[18].setForeground(Color.red);
        btn1[19].setForeground(Color.red);
/*****************************************************************************************************************************************/		
        //给按钮添加监听器
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
	 * 添加按钮点击事件
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 
        String s=e.getActionCommand();// 获取点击按钮的名称
        if(s.equals("Backspace")){//按"Backspace"键,s.equals(btn3[0])判断s是否与btn3[0]相等
            do_Backspace();
        }else if(s.equals("CE")){//按"CE"键
        	do_CE();
            //jtf.setText("0");
        }else if(s.equals("C")){//按"C"键
            do_C();
        }else if("0123456789.".contains(s)){//按数字键或者小数点键,a.contains(b) 判断a中是否包含b
            do_Number(s);
        }else{//按运算符键
            do_Operator(s);
        }
	}
	//按下"Backspace"键，删除最后一个数字
	void do_Backspace(){
		String s=jtf.getText();//获取文本框内容
		int i=s.length();//获取文本内容长度
		if(s.equals("0")){
			s = s;
		}else{
			if(i==1){
				s="0";
			}else{
				s=s.substring(0, i-1);//去掉最后一个字符	
			}
		}
		jtf.setText(s);
		isOperPressed=true;
	}
	//按下"CE"键，清除当前输入
	void do_CE(){
		jtf.setText("0");
		isOperPressed=true;
	}
	//按下"C"键，全部清除，重新开始输入
	void do_C(){
		jtf.setText("0");
		isOperPressed=true;
		tag=0;
	}
	//按下数字键或者小数点键
	void do_Number(String s){
		if (isOperPressed) {//输入的第一个数字
            jtf.setText(s);
        } else if ((s.equals(".")) && (!jtf.getText().contains("."))) {//输入的是小数点，并且之前没有小数点，则将小数点附在结果文本框的后面
        	jtf.setText(jtf.getText() + ".");
        } else if (!s.equals(".")) {//如果输入的不是小数点，则将数字加在文本框的结果后面           
            jtf.setText(jtf.getText() + s);
        }
		isOperPressed = false;
	}
	//按下运算符键
	void do_Operator(String s){
		if(s.equals("=")){//等于运算
			get_num2=Double.parseDouble(jtf.getText());//获取点击后文本框的值
			if(error){//弹出警告框
				warnInformation();			
			}else{
				switch (tag) {
				case 0://输入数字后直接点等号，没点运算符
					result=get_num2;
					break;
				case 1://加法
					result=get_num1+get_num2;
					break;
				case 2://减法
					result=get_num1-get_num2;
					break;
				case 3://乘法
					result=get_num1*get_num2;
					break;
				case 4://除法
					if(get_num2==0){//除数不能为0
						warnInformation();//弹出警告框
					}else{
						result=get_num1/get_num2;												
					}	
					break;
				default:
					break;
				}
				//去掉整数后的".0"字符
				String s1=String.valueOf(result);
				int i=s1.length();
				if(s1.substring(i-2).equals(".0")){//如果最后两位字符为".0"，则去掉
					s1=s1.substring(0,i-2);
				}else{
					s1=s1;
				}
				jtf.setText(s1);//输出结果
				isOperPressed=true;//取消一直运算
				tag=0;//初始化
			}
		}else{
			get_num1=Double.parseDouble(jtf.getText());//获取文本框的值
			if(s.equals("+")){//加法运算
				tag=1;
			}else if(s.equals("-")){//减法运算
				tag=2;				
			}else if(s.equals("*")){//乘法运算
				tag=3;
			}else if(s.equals("/")){//除法运算
				tag=4;
			}else if(s.equals("+/-")){//正负号变换
				result=get_num1*(-1);
			}else if(s.equals("sqrt")){//根号运算
				result=Math.sqrt(get_num1);
			}else if(s.equals("%")){//百分号运算
				result=get_num1/100;
			}else if(s.equals("1/x")){//倒数运算
				if(get_num1==0){
					error=true;
				}else{
					result=1/get_num1;				
				}
			}
			isOperPressed=true;//使得点击运算符后，重新输入数字
		}
	}

/*****************************************************************************************************************************************/
	void warnInformation(){//警告框
		JOptionPane.showMessageDialog(null, "输入错误！！！","警告",JOptionPane.ERROR_MESSAGE);
	}
/*****************************************************************************************************************************************/
	public static void main(String[] args){
		new jinsCalculator();
	}
}
