import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


public class ShutdownTimer extends JFrame implements ActionListener{
	
	JPanel mainPanel,panel1, panel2, panel3, panel3First, panel3Second;
	JButton stopButton, startButton;
	JLabel colon1, colon2;
	JTextField hourField, minuteField, secondField;
	JRadioButton shutdownButton, restartButton, sleepButton;
	Timer timer;
	JMenuBar menuBar;
	JMenu optionsMenu, languageMenu;
	JMenuItem aboutItem;
	JCheckBoxMenuItem trItem, enItem;
	String warningString, errorString, errorTitle;
	int hours = 0;
	int minutes = 0;
	int seconds = 0;
	boolean isRadioButtonEnabled = false;
	boolean isTimerSet;
	
	ShutdownTimer(){
		
		//JTextField strings
		String hoursString = String.format("%02d", hours); 
		String minutesString = String.format("%02d", minutes); 
		String secondsString = String.format("%02d", seconds);
		
		//JTextFields
		hourField = new JTextField();
		hourField.setDocument
        (new JTextFieldLimit(2));
		hourField.setText(hoursString);
		
		minuteField = new JTextField();
		minuteField.setDocument
        (new JTextFieldLimit(2));
		minuteField.setText(minutesString);
		
		secondField = new JTextField();
		secondField.setDocument
        (new JTextFieldLimit(2));
		secondField.setText(secondsString);
		
		colon1 = new JLabel(":");
		colon2 = new JLabel(":");
		
		//Created my own timer
		timer = new Timer(1000, new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(seconds != 0) 
					seconds--;
				
				else if(seconds == 0 && minutes !=0) {
					seconds = 59;
					minutes--;
				}
				else if((seconds == 0 && minutes ==0) && hours != 0) {
					seconds = 59;
					minutes  = 59;
					hours--;
				}
				else if(hours == 0 && seconds == 0 && minutes ==0) {
					timer.stop();
					try {
						
						if(shutdownButton.isSelected())
							Shutdown.shutdown();
						else if(restartButton.isSelected())
							Shutdown.restart();
						else if(sleepButton.isSelected())
							Shutdown.sleep();
						
					} catch (RuntimeException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} 
				String hoursString = String.format("%02d", hours); 
				String minutesString = String.format("%02d", minutes); 
				String secondsString = String.format("%02d", seconds);
				hourField.setText(hoursString);
				minuteField.setText(minutesString);
				secondField.setText(secondsString);
				
				System.out.println(hoursString + " " + minutesString + " " + secondsString);
			}
		});
		
		//JButtons
		startButton = new JButton("Başlat");
		startButton.addActionListener(this);
		startButton.setEnabled(false);
		startButton.setFocusable(false);
		
		stopButton= new JButton("Durdur");
		stopButton.addActionListener(this);
		stopButton.setEnabled(false);
		stopButton.setFocusable(false);
		
		//JRadioButtons
		shutdownButton = new JRadioButton("Kapat");
		restartButton = new JRadioButton("Yeniden başlat");
		sleepButton = new JRadioButton("Uykuya al");
		
		shutdownButton.setFocusable(false);
		restartButton.setFocusable(false);
		sleepButton.setFocusable(false);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(shutdownButton);
		buttonGroup.add(restartButton);
		buttonGroup.add(sleepButton);
		shutdownButton.addActionListener(this); 
		restartButton.addActionListener(this); 
		sleepButton.addActionListener(this); 
		
		//Navigation
		menuBar = new JMenuBar();
		languageMenu = new JMenu("Dil");
		optionsMenu = new JMenu("Ayarlar");
		trItem = new JCheckBoxMenuItem("Türkçe");
		enItem= new JCheckBoxMenuItem("English");
		aboutItem = new JMenuItem("Hakkında");
		
		trItem.addActionListener(this);
		enItem.addActionListener(this);
		aboutItem.addActionListener(this);
		
		languageMenu.add(trItem);
		languageMenu.add(enItem);
		optionsMenu.add(languageMenu);
		optionsMenu.add(aboutItem);
		menuBar.add(optionsMenu);
		
		this.setJMenuBar(menuBar);
		
		//JPanels and layouts
		mainPanel = new JPanel();
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();	
		panel3First = new JPanel();
		panel3Second = new JPanel();
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 40));
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.LINE_AXIS));
		panel3First.setLayout(new BoxLayout(panel3First, BoxLayout.PAGE_AXIS));
		panel3Second.setLayout(new BoxLayout(panel3Second, BoxLayout.LINE_AXIS));
		panel3First.add(Box.createHorizontalGlue()); //Anchors the radiobuttons to west
		
		//JTextField(timerlabels) design
		hourField.setBorder(BorderFactory.createBevelBorder(0));
		hourField.setOpaque(true);
		hourField.setHorizontalAlignment(JTextField.CENTER);
		minuteField.setBorder(BorderFactory.createBevelBorder(0));
		minuteField.setOpaque(true);
		minuteField.setHorizontalAlignment(JTextField.CENTER);
		secondField.setOpaque(true);
		secondField.setHorizontalAlignment(JTextField.CENTER);
		secondField.setBorder(BorderFactory.createBevelBorder(0));
		
		//JFrame and adding components to panels
		panel2.add(hourField);
		panel2.add(colon1);
		panel2.add(minuteField);
		panel2.add(colon2);
		panel2.add(secondField);
		
		panel3First.add(shutdownButton);
		panel3First.add(restartButton);
		panel3First.add(sleepButton);
		panel3First.add(Box.createRigidArea(new Dimension(10, 10)));
		panel3Second.add(Box.createRigidArea(new Dimension(100, 0)));
		panel3Second.add(startButton);
		panel3Second.add(Box.createRigidArea(new Dimension(5, 0)));
		panel3Second.add(stopButton);
		panel3Second.add(Box.createRigidArea(new Dimension(10, 0)));
		
		panel3.add(panel3First);
		panel3.add(panel3Second);
		mainPanel.add(panel1);
		mainPanel.add(panel2);
		mainPanel.add(panel3);
		
		this.add(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Shutdown Timer");
		this.setResizable(false);
		this.pack();
		setLanguageEN();
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startButton) { 
			start();
		}
		if(e.getSource() == stopButton) {
			stop();
		}
		
		if(isRadioButtonEnabled != true && (e.getSource() == shutdownButton || e.getSource() == restartButton || e.getSource() == sleepButton)) {
			startButton.setEnabled(true);
			stopButton.setEnabled(true);
			isRadioButtonEnabled = true; //For not checking the condition again
		}
		if(e.getSource() == sleepButton) { //I couldn't figure out the others sleep mode command lol
			if(Shutdown.OPERATING_SYSTEM.equals("Linux") || Shutdown.OPERATING_SYSTEM.equals("Mac OS X")) {
				JOptionPane.showMessageDialog(null, errorString, errorTitle, JOptionPane.ERROR_MESSAGE);
				sleepButton.setEnabled(false);
				startButton.setEnabled(false);
				stopButton.setEnabled(false);
				isRadioButtonEnabled = false;
			}
		}
		
		if(e.getSource() == aboutItem)
			JOptionPane.showMessageDialog(null, "Github profile:\ngithub.com/furkanyldz0", "About", JOptionPane.INFORMATION_MESSAGE);
	
		if(e.getSource() == trItem) 
			setLanguageTR();
		
		if(e.getSource() == enItem) 
			setLanguageEN();
		
	}
	
	public void start() {
		try {
			hours = Integer.parseInt(hourField.getText());
			minutes = Integer.parseInt(minuteField.getText());
			seconds = Integer.parseInt(secondField.getText());
			
			if(hours == 0 && minutes == 0 && seconds == 0)
				isTimerSet = false;
			else
				isTimerSet = true;
				
			
			if(isTimerSet) {
				if(minutes > 60) {
					minutes = 60;
					minuteField.setText("60");
				}
				if(seconds > 60) {
					seconds = 60;
					secondField.setText("60");
				}	
				timer.start();
				
				hourField.setEditable(false);
				minuteField.setEditable(false);
				secondField.setEditable(false);
				shutdownButton.setEnabled(false);
				restartButton.setEnabled(false);
				sleepButton.setEnabled(false);
			}
			
			else
				JOptionPane.showMessageDialog(null, warningString, errorTitle, JOptionPane.WARNING_MESSAGE);
				
			
		}catch(NumberFormatException e) {//For invalid values like strings, blank fields etc.
			System.out.println(e);
			JOptionPane.showMessageDialog(null, warningString, errorTitle, JOptionPane.WARNING_MESSAGE);
			hours = 0;
			minutes = 0;
			seconds = 0;
			hourField.setText("00");
			minuteField.setText("00");
			secondField.setText("00");
		}
	}
	public void stop() {
		timer.stop();
		hourField.setEditable(true);
		minuteField.setEditable(true);
		secondField.setEditable(true);
		shutdownButton.setEnabled(true);
		restartButton.setEnabled(true);
		sleepButton.setEnabled(true);
	}
	
	public void setLanguageEN() {
		startButton.setText("Start");
		stopButton.setText("Stop");
		shutdownButton.setText("Shutdown");
		restartButton.setText("Restart");
		sleepButton.setText("Sleep");
		optionsMenu.setText("Options");
		languageMenu.setText("Language");
		aboutItem.setText("About");
		enItem.setSelected(true);
		trItem.setSelected(false);
		warningString = "Make sure that values given to the timer are valid.";
		errorString = "Sleep mode is not compatible with your operating system!"; 
		errorTitle = "Error";
		
	}
	public void setLanguageTR() {
		startButton.setText("Başlat");
		stopButton.setText("Durdur");
		shutdownButton.setText("Kapat");
		restartButton.setText("Yeniden başlat");
		sleepButton.setText("Uykuya al");
		optionsMenu.setText("Ayarlar");
		languageMenu.setText("Dil");
		aboutItem.setText("Hakkında");
		enItem.setSelected(false);
		trItem.setSelected(true);
		warningString = "Zamanlayıcıya uygun değerler veriniz.";
		errorString = "Uyku modu işletim sisteminizle uyumlu değil!";
		errorTitle = "Hata";
	}
	
	
}


class JTextFieldLimit extends PlainDocument {
  private int limit;


  JTextFieldLimit(int limit) {
   super();
   this.limit = limit;
   }

  public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
    if (str == null) return;

    if ((getLength() + str.length()) <= limit) {
      super.insertString(offset, str, attr);
    }
    
  }
}