import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Swing based KeyBoard that highlights the keypressed using 
 * 		actionlisteners from individual JButtons
 * @author: Haroon Awan
 * @date: 09/12/2017 
 */
public class MyKeyboard extends JFrame implements KeyListener
{  
	//Individual keyboard rows  
	String strRow1[] = {"~","1","2","3","4","5","6","7","8","9","0","-","+","Backspace"};
	String strRow2[] = {"Tab","Q","W","E","R","T","Y","U","I","O","P","[","]","\\"};
	String strRow3[] = {"Caps","A","S","D","F","G","H","J","K","L",":","\"","Enter"};
	String strRow4[] = {"Shift","Z","X","C","V","B","N","M",",",".","?","^" };
	String strRow5[] = {" " ,"<" ,"v",">" };
	
	// variables used in @Override Key Events
	int keycode; 				//used for Button KeyListener 
	String usrInput;			//to store the string entered in JTextField
	
	// stores keycodes and corresponding JButton
	Map<Integer, JButton> map = new HashMap<>();

	/* use this color object to change the
	 * button's color when keyReleased 
	 */
	Color originalColor = new JButton().getBackground();

	//JButtons arrays corresponding to each rows 
	JButton first[];
	JButton second[];
	JButton third[];
	JButton fourth[];
	JButton fifth[];
	
	// Default Constructor
	public MyKeyboard()	{
		
		//set the instruction 
		JLabel info = new JLabel("<html>Type some text using your keyboard."
								+ " The keys you press will be highlighted and text will be displayed. <br>" 
								+ "Note: Clicking the buttons with your mouse will not perform any action. <br><br> </html>" );
		info.setFont(new Font("Arial",Font.BOLD,16));
		
		//set the user input JTextArea		
		JTextArea text = new JTextArea();
		text.setPreferredSize(new Dimension(700,200));
		text.addKeyListener(this);			

		//Set BorderLayout for JFrame
		setLayout(new BorderLayout());
		
		//panels for layout
		JPanel jpNorth = new JPanel();
		JPanel jpCenter = new JPanel();
		JPanel jpKeyboard = new JPanel();
		JPanel jpNote = new JPanel();
		add(jpNorth, BorderLayout.NORTH);
		add(jpNote);
		add(jpCenter, BorderLayout.CENTER);
		add(jpKeyboard, BorderLayout.SOUTH);

		jpNorth.setLayout(new BorderLayout());
		jpNorth.add(info, BorderLayout.WEST);
		jpNorth.add(info, BorderLayout.SOUTH);

		jpCenter.setLayout( new BorderLayout());
		jpCenter.add(text, BorderLayout.WEST);
		jpCenter.add(text, BorderLayout.CENTER);

		//layout for keyboard 
		jpKeyboard.setLayout(new GridLayout(5,1));
		
		//get the size of JButton Array
		first = new JButton[strRow1.length];
		//get the panel for the  row
		JPanel p = new JPanel(new GridLayout(1, strRow1.length));
		
		//loop creates JButtons and insert them in HashMap structure
		for(int i = 0; i < strRow1.length; ++i) 
		{
			first[i]= new JButton(strRow1[i]);
			first[i].setPreferredSize(new Dimension(100,50));
			first[i].setBackground(originalColor);
			map.put(getKeyCode(strRow1[i]), first[i]);
			p.add(first[i]);
		}
		jpKeyboard.add(p);

		second = new JButton[strRow2.length];
		//get the panel for the  row
		p = new JPanel(new GridLayout(1, strRow2.length));
		for(int i = 0; i < strRow2.length; ++i) 
		{
			second[i] = new JButton(strRow2[i]);
			map.put(getKeyCode(strRow2[i]), second[i]);
			p.add(second[i]);

		}
		jpKeyboard.add(p);

		third = new JButton[strRow3.length];
		p = new JPanel(new GridLayout(1, strRow3.length));
		for(int i = 0; i < strRow3.length; ++i)
		{
			third[i] = new JButton(strRow3[i]);
			map.put(getKeyCode(strRow3[i]), third[i]);
			p.add(third[i]);
		}
		jpKeyboard.add(p);

		fourth = new JButton[strRow4.length];
		p = new JPanel(new GridLayout(1, strRow4.length));
		for(int i = 0; i < strRow4.length; ++i)
		{			
			fourth[i] = new JButton(strRow4[i]);
			map.put(getKeyCode(strRow4[i]), fourth[i]);
			p.add(fourth[i]);
			if(i==strRow4.length-2)
				p.add(new JPanel());
		}
		p.add(new JPanel());
		jpKeyboard.add(p);

		fifth = new JButton[strRow5.length];
		p = new JPanel(new GridLayout(1, strRow5.length));
		/*put empty panel for layout adjustments */
		for(int i = 0; i < 1; ++i)
		{
			JPanel  spacePanel = new JPanel();
			p.add(spacePanel);
		}

		for(int i = 0; i < strRow5.length; ++i)
		{
			if(i==1) //space bar panel
			{
				fifth[i] = new JButton(strRow5[i]);
				fifth[i].setPreferredSize(new Dimension(400,10));
				fifth[i].setBounds(10, 10, 600, 100);
				map.put(getKeyCode(strRow5[i]), fifth[i]);
				//add empty panels for layout 
				p.add(new JPanel());p.add(new JPanel());p.add(new JPanel());p.add(new JPanel());p.add(new JPanel());p.add(new JPanel());p.add(new JPanel());p.add(new JPanel());
			}
			else
			{
				fifth[i]=new JButton(strRow5[i]);
				map.put(getKeyCode(strRow5[i]), fifth[i]);
			}
			if(i==0) //first black panel
			{
				//place a black panel at first
				JPanel  spacePanel = new JPanel();
				p.add(spacePanel);
			}
			p.add(fifth[i]);

		}
		jpKeyboard.add(p);

		//add keyListener to all the button
		for(JButton b : first)
			b.addKeyListener(this); 
		for(JButton b : second)
			b.addKeyListener(this); 
		for(JButton b : third)
			b.addKeyListener(this); 
		for(JButton b : fourth)
			b.addKeyListener(this); 
		for(JButton b : fifth)
			b.addKeyListener(this); 

	} //end of default Constructor  
		
	private int getKeyCode(String key) 
	{
		switch (key) {
		case "Backspace":
			return KeyEvent.VK_BACK_SPACE;
		case "Tab":
			return KeyEvent.VK_TAB;
		case "Caps":
			return KeyEvent.VK_CAPS_LOCK;
		case "Enter":
			return KeyEvent.VK_ENTER;
		case "Shift":
			return KeyEvent.VK_SHIFT;
		case " ":
			return KeyEvent.VK_SPACE;
		case "+":
			return KeyEvent.VK_EQUALS;
		case ":":
			return KeyEvent.VK_SEMICOLON;
		case "\"":
			return KeyEvent.VK_QUOTE;
		case "?":
			return KeyEvent.VK_SLASH;
		case "~":
			return KeyEvent.VK_BACK_QUOTE;
		case "^":
			return KeyEvent.VK_UP;
		case "v":
			return KeyEvent.VK_DOWN;
		case "<":
			return KeyEvent.VK_LEFT;
		case ">":
			return KeyEvent.VK_RIGHT;
		default:
			return (int) key.charAt(0);       
		}// end of switch

	}// end of getKeyCode
	
	public static void main(String[] args) {

		MyKeyboard myFrame = new MyKeyboard();
		myFrame.setTitle("Typing Tutor");
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setResizable(false);
		myFrame.setVisible(true);
		myFrame.setSize(new Dimension(1400,800));
		myFrame.setLocationRelativeTo(null);
	}

	@Override
	public void keyPressed(KeyEvent evt) {        
		keycode = evt.getKeyCode();
		usrInput = String.format("%s", KeyEvent.getKeyText(evt.getKeyCode()));

		JButton btn = map.get(keycode);
		if (btn != null) {
			map.get(keycode).setBackground(Color.WHITE);
		}
	}

	@Override
	public void keyReleased(KeyEvent evt) {        
		keycode = evt.getKeyCode();
		usrInput = String.format("%s", KeyEvent.getKeyText(evt.getKeyCode()));

		JButton btn = map.get(keycode);
		if (btn != null) {
			map.get(keycode).setBackground(originalColor);
		}
	}

	@Override
	public void keyTyped(KeyEvent evt) {      
		//Do Nothing!!!
	}


}//end of class