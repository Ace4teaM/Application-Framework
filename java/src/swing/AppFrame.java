package swing;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.*;

import app.AppCommand;
import app.AppCommandGroup;
import app.IAppCommand;
import app.Result;

import field.*;


@SuppressWarnings("serial")
public class AppFrame extends JFrame implements ActionListener,MouseMotionListener {
	/** Liste des commandes */
	ArrayList<AppCommand> cmds = new ArrayList<AppCommand>();
//	Hashtable<String,IField> params = new Hashtable<String,IField>();
	
	/** Menu principale */
	JMenuBar menu;

	/** Bar d'outils principale */
	JToolBar toolbar;

	/** Bar de statut */
	JLabel statusbar;
	
	/** Conteneur des fenêtres */
	JDesktopPane desktop;

	AppCommandGroup cmdGroup[];
	
	/**
	 * constructeur
	 */
	public AppFrame(String title,AppCommandGroup cmdGroup[],boolean bToolBar,boolean bMenuBar){
		
		//Utilise le style du système
		try { 
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
			//UIManager.setLookAndFeel( "com.sun.java.swing.plaf.motif.MotifLookAndFeel"  );
			//UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		} 
		catch(Exception e) {
			System.err.println("Impossible d'utiliser le style personnalisé");
		}
		
		//Initialise les codes d'erreurs 
		initErrorsCodes();
		
		//initialise
		this.setTitle(title);
		this.setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
		this.setResizable(true); //On interdit la redimensionnement de la fenêtre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//dimensionne la fenêtre
		Dimension rc = Toolkit.getDefaultToolkit().getScreenSize();
		int width  = (int)Math.max(rc.width*0.61803399,320);
		int height = (int)Math.max(rc.height*0.61803399,240);
		this.setSize(width,height);
		
		//positionne (sur l'ecran principale)
		this.setLocation((rc.width/2)-(width/2), (rc.height/2)-(height/2));

		//intialise les commandes
		this.cmdGroup = cmdGroup;
		 
		//liste les commandes
		for(int i=0;i<this.cmdGroup.length;i++){
			AppCommand group[] = this.cmdGroup[i].getCommandList();
			for(int x=0;x<group.length;x++)
				this.cmds.add(group[x]);
		}
		
		//initialise le model "multiple document interface"
		this.desktop = new JDesktopPane();
		this.add( desktop );
		
		//intialise le menu
		if(bMenuBar)
			this.initMenu();

		//intialise la barre d'outils
		if(bToolBar)
			this.initToolbar();

		//intialise la barre de statut
		this.initStatusbar();

		this.addMouseMotionListener(this);
	}

	/**
	 * Initialise les codes d'erreurs
	 */
	private void initErrorsCodes(){
		Result.desc_list.put("cant_open_file", "Can't open file");
		Result.desc_list.put("invalid_path_parameter", "Chemin d'accès invalide");
	}

	/**
	 * Ajoute un code d'erreur
	 */
	public void addErrorCode(String name,String desc){
		Result.desc_list.put(name, desc);
	}
	
	/**
	 * Evenements
	 */
	public void actionPerformed(ActionEvent e) {
        //obtient la commande
        AppCommand cmd = null;
        
		//La source est une commande?
        Class interfaceList[] = e.getSource().getClass().getInterfaces();
        for(int i=0;i<interfaceList.length;i++){
//    		System.out.println(interfaceList[i].getName());
    		//herite de l'interface 'IAppCommand' ?
        	if(interfaceList[i].getName()=="app.IAppCommand"){
//       		System.out.println(e.getSource().getClass().getName());
        		cmd = ((IAppCommand)e.getSource()).getAppCommand();
        		break;
        		//i=e.getSource().getClass().getInterfaces().length;
        	}
        }

        //execute la commande
        if(cmd != null){
        	//obtient les champs depuis l'utilisateur
        	if(cmd.fieldPrompt(this))
        	{
	        	//execute la commande
				Result result = cmd.exec(this);
				
				//test le resultat
				if(result.code != Result.ERR_CODE_OK){
					this.showResultBox(result);
					//JOptionPane.showMessageDialog(getSelectedWindow(Window.getOwnerlessWindows()),result.getResultDesc());
					System.err.println(cmd.getName()+" -> "+result);
				}
				else
					System.out.println(cmd.getName()+" -> "+result);
        	}
        }
    }

	/**
	 * Retourne la fenêtre active
	 * @param windows Liste des fenêtres a analyser (généralement Window.getOwnerlessWindows())
	 * @return Instance de la fenêtre active
	 */
	Window getSelectedWindow(Window[] windows) { 
		Window result = null; 
		for (int i = 0; i < windows.length; i++) {   
			Window window = windows[i];  
			if (window.isActive()) {
			System.out.println(window.getName());     
				result = window;      
			}
			else {  
				Window[] ownedWindows = window.getOwnedWindows();    
				if (ownedWindows != null) {        
					result = getSelectedWindow(ownedWindows);  
				}    
			}   
		}  
		return result;
	} 
	
	/**
	 * Ajoute une frame au conteneur de fenetres
	 * @param panel
	 */
	public JInternalFrame addFrame(JPanel panel){
		//ajoute un conteneur de test
		JInternalFrame frame = new JInternalFrame( "Internal Frame", true, true, true, true );
		frame.add( panel, BorderLayout.CENTER );
		this.desktop.add(frame);
		frame.pack();
		frame.setVisible( true );
		
		return frame;
	}
	
	/**
	 * Initialise le Menu Principale
	 * 
	 */
	public void initMenu(){
		this.menu = new JMenuBar();
		this.setJMenuBar(this.menu);
		
		//menus
		for(int x=0;x<this.cmdGroup.length;x++){
			JMenu file_menu = new JMenu();
			file_menu.setText(this.cmdGroup[x].getName());
			file_menu.setMnemonic(KeyEvent.VK_S);
			this.menu.add(file_menu);
			
			AppCommand cmdList[]  = this.cmdGroup[x].getCommandList();
			
			//ajoute les commandes principales
			for(int i=0;i<cmdList.length;i++){
				AppCommand cur = cmdList[i];
				AppMenuItem item = new AppMenuItem();
				item.cmd = cur;
				item.setText(cur.getName());
				item.setIcon(cur.getIcon());
				item.setMnemonic(cur.key);
				item.addActionListener(this);
				item.addMouseMotionListener(this);
				file_menu.add(item);
			}
		}
	}
	

	/**
	 * Initialise le Menu Principale
	 * 
	 */
	public void initToolbar(){
		//toolbar
		toolbar = new JToolBar();
		toolbar.setOrientation(JToolBar.HORIZONTAL);
		
		//ajoute les commandes
		for(int x=0;x<this.cmdGroup.length;x++){
			AppCommand cmdList[]  = this.cmdGroup[x].getCommandList();
			boolean insertSep = false;
			for(int i=0;i<cmdList.length;i++){
				AppCommand cur = cmdList[i];
				if(cur.getIcon() != null){
					AppButton item = new AppButton();
					item.cmd = cur;
					item.setToolTipText(cur.getName());
					item.setIcon(cur.getIcon());
					item.setMnemonic(cur.key);
					item.addActionListener(this);
					item.addMouseMotionListener(this);
					toolbar.add(item);
					
					insertSep = true;
				}
			}
			
			if(insertSep)
				toolbar.addSeparator();
		}
		this.add(toolbar, BorderLayout.NORTH);
	}

	/**
	 * Initialise le Menu Principale
	 * 
	 */
	public void initStatusbar(){
		statusbar = new JLabel(" ");
		statusbar.setBorder(BorderFactory.createLoweredBevelBorder());
		this.add(statusbar, BorderLayout.SOUTH);
	}

	/**
	 * Affiche le resultat d'une commande dans une boite de dialogue
	 * @param result
	 */
	public void showResultBox(Result result){
		Component curWnd = getSelectedWindow(Window.getOwnerlessWindows());
		switch(result.getResultCode()){
			case Result.ERR_CODE_OK:
				JOptionPane.showMessageDialog(curWnd,result.getResultDesc(),"Succès",JOptionPane.INFORMATION_MESSAGE);
				break; 
			default:
				JOptionPane.showMessageDialog(curWnd,result.getResultDesc(),"Oups !",JOptionPane.WARNING_MESSAGE);
				break; 
		}
	}
	
	/*
	 * Evenements
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		//commande de la barre d'outils
		if(e.getSource().getClass().getName() == "AppButton"){
			AppButton btn = (AppButton)e.getSource();
			statusbar.setText(btn.cmd.getDesc());
		}
		//commande du menu
		else if(e.getSource().getClass().getName() == "AppMenuItem"){
			AppMenuItem btn = (AppMenuItem)e.getSource();
			statusbar.setText(btn.cmd.getDesc());
		}
		else{
			statusbar.setText(" ");
		}
		
	}
}
