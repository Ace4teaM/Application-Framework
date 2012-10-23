package app;


import java.awt.Component;
import java.awt.event.KeyEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import swing.FieldsDialog;

import app.Result;


/**
 * Commande de l'application
 * @author dl201
 *
 */
public class AppCommand{
	/** Nom */  
	private String name;
	/** Description */  
	private String desc;
	/** Raccourci clavier */  
	public int key;
	/** Nom de l'icone */  
	private String iconName;
	/** Icone */
	private Icon icon;
	/** Paramètres de la commande */  
	public AppParam param;
	
	/** 
	 * Constructeur
	 * @param wnd        Fenêtre parent
	 * @param name       Nom court de la commande
	 * @param key        Raccourci clavier
	 * @param icon       Icone, null si inutilisé
	 * @param param      Paramètres de la commande
	 * @param desc       Description de la commande
	 * @param groupName  Nom du groupe de commande
	 */
	public AppCommand(String name, int key, String icon, AppParam param,String desc){

		this.iconName = icon;
		if(icon==null || icon.isEmpty())
			this.icon   = null;
		else
			this.icon = new ImageIcon(this.getClass().getResource("../icones/16/"+this.iconName));
		this.key    = key;
		this.name   = name;
		this.desc   = desc;
		this.param  = param;
	}

	public AppCommand(String name, int key, String icon, AppParam param){
		this(name,key,icon,param,"");
	}

	public AppCommand(String name, int key){
		this(name,key,null,null);
	}
	
	public AppCommand(){
		this("", KeyEvent.CHAR_UNDEFINED);
	}
	

	/**
	 * Utilise un dialogue de champs ?
	 */
	public boolean asFieldPrompt() {
		if(this.param == null)
			return false;
		return true;
	}
	
	/**
	 * Obtient les champs depuis l'utilisateur
	 */
	public boolean fieldPrompt(Component parent) {
		if(this.param == null)
			return true;
		
		//ouvre un dialogue pour obtenir la valeur des champs
		FieldsDialog dlg = new FieldsDialog(this.param.fields.values(),true);
		//FieldsDialog dlg = new FieldsDialog(this.param.fields.values(),true);
		dlg.setTitle(this.getName());
		dlg.setLocationRelativeTo(parent);
		if(dlg.open())
			return true;
		
		return false;
	}
	
	/**
	 * Execute la commande
	 * @return Resultat
	 */
	public Result exec(Object context){
		return Result.ERR_OK;
	}

	public String getName() {
		return name+(this.asFieldPrompt() ? "..." : "");
	}

	public String getDesc() {
		return desc;
	}

	public Icon getIcon() {
		return this.icon;
	}
	
	/** Liste des commandes enfants */
	public AppCommand[] getSubCommands(){
		return null;
	}
}