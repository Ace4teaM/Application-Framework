package swing;
import javax.swing.JButton;

import app.AppCommand;
import app.IAppCommand;


public class AppButton extends JButton implements IAppCommand{
	private static final long serialVersionUID = 4296540010994677330L;
	/**
	 * Commande associé au menu
	 */
	AppCommand cmd;

	@Override
	public AppCommand getAppCommand() {
		return cmd;
	}
}
