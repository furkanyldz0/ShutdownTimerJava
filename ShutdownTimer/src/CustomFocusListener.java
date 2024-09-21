import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class CustomFocusListener implements FocusListener{
	private JTextField textField;
	private String defaultText = "00";
	
	@Override
	public void focusGained(FocusEvent e) {
		String str = textField.getText();
		if(str.equals(defaultText)) 
			textField.setText("");
	}

	@Override
	public void focusLost(FocusEvent e) {
		String str = textField.getText();
		if(str.equals("")) {
			textField.setText(defaultText);
		}		
		
		else if(isNumeric(str)){
			int value = Integer.parseInt(str);
			String formattedValue = String.format("%02d", value);
			textField.setText(formattedValue);
			
		}
	}
	
	public void setFocusListener(JTextField textField) {
		this.textField = textField;
		textField.addFocusListener(this);
	}
	
	public boolean isNumeric(String str) {
		boolean check = false;
		
		if(str.matches("[0-9]+")) 
			check = true;
		
		return check;
	}

}
