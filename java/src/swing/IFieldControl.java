package swing;

import javax.swing.JComponent;

import field.IField;

public interface IFieldControl
{
	public JComponent getComponent();
	public IField getField();
	public boolean isValidField();
	public String getValue();
	public String setValue(String s);
	public void setUpdateField(boolean bAutoUpdate);
	void updateFieldValue();
	void updateControlValue();
}
