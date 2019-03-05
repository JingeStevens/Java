/*
 * Author: Wang Jinge	
 * Date:  3/4/2019
 * Jin`s Editor functions:
 *	1.text area.
 * 	2.can save file to address that user wanted.
 *	3.can open file search by fileChooser.
 *	4.can choose color for text and background.(colorChooser)
 *	
 *extra: 
 *	1.add button for deleting all.
 *	2.design a blank at top so that can add menus & items in the future.	
 */
package editor;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class jinsEditor extends JFrame{
	
	JComboBox box = new JComboBox();
	String [] str = new String[3];
	static JPanel panel_c = new JPanel();
	static JPanel panel_s = new JPanel();
	JTextArea text = new JTextArea("");
	
	public static void main(String [] args){
		new jinsEditor();
	}
	
	jinsEditor(){
		menuSetup();
		for(int i =0;i<3;i++){
			box.addItem(str[i]);
		}
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLayout(new BorderLayout());
		setBounds(400,100,dim.width/2,dim.height/2);
		
		new Thread(new PanelSouth()).start();
		add(box,BorderLayout.NORTH);
		add(panel_c,BorderLayout.CENTER);
		add(panel_s,BorderLayout.SOUTH);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void menuSetup(){
		//can add something in the future in the toping blank.
		str[0] = null;
		str[1] = null;
		str[2] = null;
	}
	
	public void openFile(){
		JFileChooser open = new JFileChooser();
		open.setFileSelectionMode(JFileChooser.FILES_ONLY );
		open.setCurrentDirectory(new File("f:/"));
		int result = open.showOpenDialog(null);
		File f = null;
		if(result == JFileChooser.APPROVE_OPTION){
			f = open.getSelectedFile();
		}
				
		FileReader input = null;
		if(f!=null){
			try {
				input = new FileReader(f);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		BufferedReader buffer = null;
		if(input!=null)	{
			try {				
				buffer = new BufferedReader(input);
				String str1, str2 = "";
				while((str1= buffer.readLine())!=null){
					str2 += str1;
					text.setText(str2);				
				}
				input.close();
				buffer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void saveFile(){
		JFileChooser save = new JFileChooser("F:/");
		save.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);		
		int result= save.showSaveDialog(null); 
		File f = null;
		if(result == JFileChooser.APPROVE_OPTION){		
			f = save.getSelectedFile();
		}
		
		FileWriter writer = null;
		BufferedReader reader = null;		
		if(f!=null){
			try {
				writer = new FileWriter(f);
				reader = new BufferedReader(new StringReader(text.getText()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(reader!=null){
			try {		
				String str =null;
				while((str=reader.readLine())!=null){
					//System.out.println("这里有错");//调试语句
					writer.write(str);
				}
				reader.close();
				writer.close();
				
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		}
	
	class PanelSouth implements Runnable{
		
		JButton button[] = new JButton[5];
		JColorChooser color = new JColorChooser(Color.yellow);
		
		public void run() {
			
			center();
			String [] str = {"foreground","background","open","save","deleteall"};
			for(int i =0;i<5;i++){
				button[i]= new JButton(str[i]);
			}
			
			panel_s.setLayout(new FlowLayout());
			button();
			for(int i = 0;i< 5;i++){
				panel_s.add(button[i]);
			}
		}	
		
		public void center(){ 
			panel_c.setLayout(new GridLayout(1,2));
			text.setLineWrap(true);
			panel_c.add(text);
			panel_c.add(color);
		}
		
		public void button(){
			button[0].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					text.setForeground(color.getColor());
				}
			});
			button[1].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					text.setBackground(color.getColor());
				}
			});
			button[2].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					openFile();
				}
			});
			button[3].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					saveFile();
				}
			});
			button[4].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					text.setText("");
				}
			});
		}
	}
}

