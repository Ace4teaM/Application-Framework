package swing;
import java.awt.event.*;
import javax.swing.*;

import field.IField;


@SuppressWarnings("serial")
public class JTextFieldControl extends JTextField implements IFieldControl, KeyListener {
	IField iField;
	boolean bProgressiveUpdate;

	JTextFieldControl(IField field){
		this(field,false);
	}
	
	JTextFieldControl(IField field,boolean bProgressiveUpdate){
		this.iField = field;
		this.bProgressiveUpdate = bProgressiveUpdate;
		this.setText(field.toString());

		//Modification du texte
		this.addKeyListener(this); 
	}

	
	/**
	 * Définit la valeur du controle dans le champ
	 * */
	public void updateFieldValue() {
		this.iField.fromString(this.getText());
	}

	/**
	 * Définit la valeur du champ dans le controle
	 * */
	public void updateControlValue() {
		this.iField.fromString(this.getText());
	}

	//Test la validité du champ
	public boolean isValidField() {
		if(this.bProgressiveUpdate == true)
			return this.iField.isValid();
		return this.iField.checkValue(this.getText());
		
	}

	@Override
	public String getValue() {
		return this.getText();
	}

	@Override
	public String setValue(String s) {
		this.setText(s);
		return s;
	}

	@Override
	public void setUpdateField(boolean bAutoUpdate) {
		this.bProgressiveUpdate = bAutoUpdate;
	}

	@Override
	public JComponent getComponent() {
		return this;
	}

	@Override
	public IField getField() {
		return this.iField;
	}

	/*-------------------------------------------------------------------
	 * KeyListener Event
	 -------------------------------------------------------------------*/
	@Override
	public void keyTyped(KeyEvent e) {
		//if(e.isActionKey())
			this.iField.fromString(this.getText()+e.getKeyChar());
		//this.updateFieldValue();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		/*
		if(!this.getField().checkValue(this.getValue()+e.getKeyChar()))
			e.cancel();*/
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}