package swing;
import javax.swing.JMenuItem;

import app.AppCommand;
import app.IAppCommand;



/**
 * Version modifier de la classe JMenuItem.
 * AppMenuItem ajoute une référence à la commande (AppCommand) qu'il execute
 * @author dl201
 */
public class AppMenuItem extends JMenuItem implements IAppCommand{
	private static final long serialVersionUID = 6151357456036781486L;
	/**
	 * Commande associé au menu
	 */
	AppCommand cmd;
	
	@Override
	public AppCommand getAppCommand() {
		return cmd;
	}
}
