package swing;
import javax.swing.JMenu;

import app.AppCommand;


/**
 * Version modifier de la classe JMenuItem.
 * AppMenuItem ajoute une r�f�rence � la commande (AppCommand) qu'il execute
 * @author dl201
 */
public class AppMenu extends JMenu{
	private static final long serialVersionUID = -2955399626153503015L;
	/**
	 * Commandes associ� au menu
	 */
	AppCommand cmds[];
}
