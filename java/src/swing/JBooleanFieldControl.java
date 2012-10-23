package swing;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import field.BooleanField;
import field.IField;

public class JBooleanFieldControl extends JCheckBox implements IFieldControl, ChangeListener {
	private static final long serialVersionUID = 5414488110903344403L;
	private final String enabledStr[] = {"Desactivé","Activé"};
	
	IField iField;
	boolean bProgressiveUpdate;

	JBooleanFieldControl(IField field){
		this(field,false);
	}
	
	JBooleanFieldControl(IField field,boolean bProgressiveUpdate){
		this.iField = field;
		this.bProgressiveUpdate = bProgressiveUpdate;
		this.updateControlValue();
		// Change Event
		this.addChangeListener(this); 
	}

	/** Evenement: Etat changé */
	@Override
	public void stateChanged(ChangeEvent e) {
		JBooleanFieldControl src = (JBooleanFieldControl)e.getSource();
		//System.out.println("stateChanged "+src.bProgressiveUpdate);
		if(src.bProgressiveUpdate != true)
			return;
		src.updateFieldValue();
		this.setText(this.enabledStr[(this.isSelected()?1:0)]);
	}

	/**
	 * Actualise la valeur du champ
	 * */
	public void updateFieldValue() {
		this.iField.fromString(this.getValue());
	}

	/**
	 * Actualise la valeur du controle
	 * */
	public void updateControlValue() {
		//actif/inactif ?
		this.setSelected(BooleanField.toBoolean(this.iField.toString()));
		this.setText(this.enabledStr[(this.isSelected()?1:0)]);
	}
	
	/**Test la validité du champ*/
	public boolean isValidField() {
		if(this.bProgressiveUpdate == true)
			return this.iField.isValid();
		return this.iField.checkValue(this.getValue());
		
	}

	@Override
	public String getValue() {
		return (this.isSelected() ? "true" : "false");
	}

	@Override
	public String setValue(String s) {
		this.setSelected(BooleanField.toBoolean(s));
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
	
}