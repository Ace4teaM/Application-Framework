package app;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;


/**
 * Groupe les commandes en paquet dans pour un même contexte
 * @author dl201
 *
 */
public class AppCommandGroup {
	private String name;
	private AppCommand cmdList[];
	private Object context;

	public AppCommandGroup(String name,Object context) {
		this.name    = name;
		this.context = context;
		this.cmdList = new AppCommand[0];
	}
	
	public AppCommandGroup(String name,Object context, ArrayList<AppCommand> cmdList) {
		this.name    = name;
		this.context = context;
		this.cmdList = cmdList.toArray(this.cmdList);
	}

	public AppCommandGroup(String name,Object context, AppCommand cmdList[]) {
		this.name    = name;
		this.context = context;
		this.cmdList = cmdList;
	}
	
	public String getName(){
		return this.name;
	}
	
	public AppCommand[] getCommandList(){
		return this.cmdList;
	}
	
	public JMenuBar makeToolBar(){
		return null;
	}

	public JMenu makeMenu(){
		return null;
		/*
		//this.menu = new JMenuBar();
		//this.setJMenuBar(this.menu);
		
		//menu serveur
		JMenu file_menu = new JMenu();
		file_menu.setText(this.name);
		file_menu.setMnemonic(KeyEvent.VK_S);
		//this.menu.add(file_menu);
		
		//ajoute les commandes principales
		for(int i=0;i<this.cmdList.size();i++){
			AppCommand cur = this.cmdList.get(i);
			AppMenuItem item = new AppMenuItem();
			item.cmd = cur;
			item.setText(cur.getName());
			item.setIcon(cur.getIcon());
			item.setMnemonic(cur.key);
			item.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					AppMenuItem item = (AppMenuItem)e.getSource();
		        	//obtient les champs depuis l'utilisateur
					boolean ok
		        	if(item.cmd.fieldPrompt(context))
		        	{
			        	//execute la commande
						Result result = cmd.exec(context);
						
						//test le resultat
						if(result.code != Result.ERR_CODE_OK){
							JOptionPane.showMessageDialog(getSelectedWindow(Window.getOwnerlessWindows()),result.getResultDesc());
							System.err.println(cmd.getName()+" -> "+result);
						}
						else
							System.out.println(cmd.getName()+" -> "+result);
		        	}
		        	
				}
			});
			//item.addMouseMotionListener(this);
			file_menu.add(item);
		}
		
		return file_menu;*/
		
	}
}
