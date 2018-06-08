import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * GUI representation of the Heist Game.
 * @author Matthew Hoover
 */
public class Heist implements Observer{

	private List<Boolean> myAlarmlist;
	private HeistModel myheist;
	private ArrayList<JButton> myButtons;
	private Timer myTimer;
	private JTextField tex;
	private JPanel top;
	private JButton emp;
	private JButton clearButton;
	
	/**
	 * GUI representation of the Heist Game with the Heist Model.
	 * @param heistModel
	 */
	public Heist(HeistModel heistModel){
		myheist = heistModel;
		//Creates the frame for the game
		JFrame frame = new JFrame("Heist Game");
		frame.setLayout(new BorderLayout());
		
		//Creates the top bar for the game
		top = new JPanel();
		top.setLayout(new BoxLayout(top, BoxLayout.LINE_AXIS));
		tex = new JTextField();
		tex.addActionListener(listen);
		top.add(tex);
		
		//Creates the game board
		int d = heistModel.getDim();
		int re = heistModel.getRefreshRate();
		myAlarmlist = heistModel.getAlarms();
	    int b = 0;
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(d, d, 1, 1));
		
		myButtons = new ArrayList<JButton>();
		for(int r = 0; r < d; ++r){ //Loop through buttons
			for(int c = 0; c < d; ++c){
				while(b < myAlarmlist.size()){//Loop through list of alarms
					myButtons.add(new JButton());
					myButtons.get(b).addActionListener(listen);
					if(myAlarmlist.get(b) == true){//Color button blue
						myButtons.get(b).setBorderPainted(false);
						myButtons.get(b).setContentAreaFilled(false);
						myButtons.get(b).setOpaque(true);
						myButtons.get(b).setBackground(Color.blue);
						center.add(myButtons.get(b));
					}
					if(myAlarmlist.get(b) == false){//Color button white
						myButtons.get(b).setBorderPainted(false);
						myButtons.get(b).setContentAreaFilled(false);
						myButtons.get(b).setOpaque(true);
						myButtons.get(b).setBackground(Color.white);
						center.add(myButtons.get(b));
					}
					b++;
				}
			}
		}
		JButton button = myButtons.get((d-1)*d);
		try {
		    Image img = ImageIO.read(getClass().getResource("Thief.JPG"));
		    button.setIcon(new ImageIcon(img));
		  } catch (IOException ex) {
		  }
		JButton btn = myButtons.get(d-1);
		try {
		    Image img = ImageIO.read(getClass().getResource("Jewels.JPG"));
		    btn.setIcon(new ImageIcon(img));
		  } catch (IOException ex) {
		  }
		
		//Creates the bottom bar for the game
		JPanel bottom = new JPanel();
		JTextField text = new JTextField("ENTER/EXIT");
		text.setMaximumSize(new Dimension(100, 40));
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.LINE_AXIS));
		bottom.add(text);
		Box.Filler fill = new Box.Filler(new Dimension(0, 0), new Dimension(50, 50), new Dimension(1000, 50));
		bottom.add(fill);
		emp = new JButton("EMP");
		emp.addActionListener(listen);
		bottom.add(emp);
		clearButton = new JButton("RESET");
		clearButton.addActionListener(listen);
		bottom.add(clearButton);
		
		//Creates the game content
		Container pane = frame.getContentPane();
		pane.add(top, BorderLayout.NORTH);
		pane.add(center, BorderLayout.CENTER);
		pane.add(bottom, BorderLayout.SOUTH);
		
		//Makes the game visible
		frame.pack();
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		myTimer = new Timer(re, listen);
		myTimer.start();
	}
    
	/**
	 * Runs the GUI representation of the Heist game from an input 
	 * config file.
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException{
		
	    if(args.length != 1){ 
	        String us = "Usage: java Heist <config-file>";
	        System.out.println(us);
	        return;
	    }
	    
	    new Heist(new HeistModel(args[0]));
	}

	/**
	 * Updates the observer
	 */
	public void update(Observable arg0, Object arg1) {
		System.out.println("update called");
	}
	
	/**
	 * Updates the alarms to simulate a sercurity system.
	 */
	public void updateAlarms(){
		int d = myheist.getDim();
		myAlarmlist = myheist.getAlarms();
	    int b = 0;
	    JPanel center = new JPanel();
		center.setLayout(new GridLayout(d, d));
	    ArrayList<JButton> buttons = new ArrayList<JButton>();
	    for(int r = 0; r < d; ++r){//repeat to activate and deactivate alarms
			for(int c = 0; c < d; ++c){
				while(b < myAlarmlist.size()){
					buttons.add(new JButton());
					buttons.get(b).addActionListener(listen);
					if(myAlarmlist.get(b) == true){
						buttons.get(b).setBorderPainted(false);
						buttons.get(b).setContentAreaFilled(false);
						buttons.get(b).setOpaque(true);
						buttons.get(b).setBackground(Color.blue);
						center.add(buttons.get(b));
					}
					if(myAlarmlist.get(b) == false){
						buttons.get(b).setBorderPainted(false);
						buttons.get(b).setContentAreaFilled(false);
						buttons.get(b).setOpaque(true);
						buttons.get(b).setBackground(Color.white);
						center.add(buttons.get(b));
					}
					b++;
				}
			}
	    }
	}
    
	/**
	 * Actionlistener to simulate the game when a certain button is pressed.
	 */
	ActionListener listen = new ActionListener(){
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == myTimer){//when timer activates activate alarm pattern
			myTimer.stop();
			myheist.updateAlarmPattern();
			int d = myheist.getDim();
			myAlarmlist = myheist.getAlarms();
		    int b = 0;
		    for(int r = 0; r < d; ++r){
				for(int c = 0; c < d; ++c){
					while(b < myAlarmlist.size()){
						if(myAlarmlist.get(b) == true){
							myButtons.get(b).setBackground(Color.blue);
						}
						else{
							myButtons.get(b).setBackground(Color.white);
						}
						b++;
					}
				}
		    }
	    myTimer.start();
		}
		else{
			int d = myheist.getDim();
			int b = 0;
			myAlarmlist = myheist.getAlarms();
			for(int r = 0; r < d; ++r){
				for(int c = 0; c < d; ++c){
					if(e.getSource() == myButtons.get(b)){//when game button is pressed move player if possible
							int thiefold = myheist.getThiefLocation();
							int jewels = myheist.getJewelsLocation();
							Boolean win = myheist.getAreJewelsStolen();
							myheist.selectCell(b);
							int m = myheist.getMoveCount();
			                JButton btn = myButtons.get(b);
			                JButton btn2 = myButtons.get(thiefold);
			                JButton btn3 = myButtons.get(jewels);
							int thiefnew = myheist.getThiefLocation();
							if(!(thiefnew == thiefold)){//thief location
			                
								try {
								    Image img = ImageIO.read(getClass().getResource("Thief.JPG"));
								    btn.setIcon(new ImageIcon(img));
								    btn2.setIcon(null);
								  } catch (IOException ex) {
								  }
							    
								tex.setText("Moves: " + m);
								top.add(tex);
								if(myAlarmlist.get(b) == true){//display message when player triggers alarm
									tex.setText("Moves: " + m + " GAME OVER - ALARM TRIGGERED.");
									top.add(tex);
								}
							
								if(myAlarmlist.get(b) == false && win == true){//display message when player acquires jewels
									tex.setText("Moves: " + m + " CONGRATULATIONS - RUN TO EXIT.");
									top.add(tex);
									try {
									    Image img = ImageIO.read(getClass().getResource("Escape.JPG"));
									    btn.setIcon(new ImageIcon(img));
									    btn2.setIcon(null);
									    btn3.setIcon(null);
									  } catch (IOException ex) {
									  }
									if(myButtons.get(b) == myButtons.get((d-1)*d)){//display message when player wins
										tex.setText("Moves: " + m + " YOU WIN - JEWELS STOLEN.");
										top.add(tex);
									}
								}
							}
						}
						b++;
						if(e.getSource() == emp){//when emp is pressed disable the current alarm
							myheist.disableAlarm();
						}
						if(e.getSource() == clearButton){//when reset is pressed reset the game
							int thief = myheist.getThiefLocation();
							JButton button = myButtons.get((d-1)*d);
							JButton button2 = myButtons.get(thief);
							try {
							    Image img = ImageIO.read(getClass().getResource("Thief.JPG"));
							    button2.setIcon(null);
							    button.setIcon(new ImageIcon(img));
							  } catch (IOException ex) {
							  }
							int jewe = myheist.getJewelsLocation();
							JButton bn = myButtons.get(d-1);
							JButton bn2 = myButtons.get(jewe);
							try {
							    Image img = ImageIO.read(getClass().getResource("Jewels.JPG"));
							    bn2.setIcon(null);
							    bn.setIcon(new ImageIcon(img));
							  } catch (IOException ex) {
							  }
							myheist.reset();
						}
					}
						}
						}
						}
				}
				;}
	
		
