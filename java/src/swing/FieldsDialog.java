package swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.*;

import field.IField;

import app.IResult;
import app.Result;

/**
 * Dialogue d'édition de champs
 * @author Auguey Thomas
 *
 */

public class FieldsDialog extends JDialog implements ActionListener,IResult{
	private static final long serialVersionUID = 3176008368445864674L;
	public  IField[] m_fields;
//	public  IFieldControl[] m_controls;
	//public  JPanel   m_panel;
	public  JButton  m_btnCancel;
	public  JButton  m_btnOK;
	private boolean  m_bOK = false;
	private boolean  m_bCancel = true;
	private Result   m_result = Result.ERR_OK;
	private BorderLayout m_borderLayout;
	private FieldListPanel   m_controlsPanel;//JPanel extends
	private JPanel   m_buttonPanel;
	
	/**
	 * constructeur
	 */
	public FieldsDialog(){
		//initialise le dialogue
		this.setTitle("Champs de données...");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setModal(true);
	}
	public FieldsDialog(IField[] fields,boolean bOpen){
		this();
		this.setFieldsList(fields);
	}
	public FieldsDialog(Collection<IField> fields,boolean bOpen){
		this();
		this.setFieldsList(fields);
	}

	public Result getResult(){
		return this.m_result;
	}
	 
	/**
	 * Ouvre le dialogue
	 * @return true si l'utilisateur a confirmé l'action et tous les champs sont valides (bouton OK), sinon false.
	 */
	public boolean open()
	{
		//this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setSize(380, 160);
		((JPanel)this.getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		//layout principale
		m_borderLayout = new BorderLayout(10,10);
		this.setLayout(m_borderLayout);

		/*
		 * Liste des champs (Millieu)
		 * */
		m_controlsPanel = new FieldListPanel(this.m_fields);
		JScrollPane scrollPane = new JScrollPane(m_controlsPanel);
		scrollPane.setBorder(null);
		this.add(scrollPane,BorderLayout.CENTER);
		
		/*JPanel panelPrincipale = new JPanel();
		panelPrincipale.setLayout(new BoxLayout(panelPrincipale,BoxLayout.Y_AXIS));
		for(IField f : this.m_fields){
			FieldPanel panel = new FieldPanel(null,f);
			panelPrincipale.add(panel);
		}
		this.add(panelPrincipale,BorderLayout.CENTER);*/
		
		
		/*
		 * Boutons de confirmation (BAS)
		 * */
		m_buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		//insert le bouton Annuler
		m_btnCancel = new JButton();
		m_btnCancel.setText("Annuler");
		m_btnCancel.addActionListener(this);
		m_btnCancel.setPreferredSize(new Dimension(100,26));
		m_buttonPanel.add(m_btnCancel);
		
		//insert le bouton OK
		m_btnOK = new JButton();
		m_btnOK.setText("OK");
		m_btnOK.addActionListener(this);
		m_btnOK.requestFocusInWindow();
		m_btnOK.setPreferredSize(new Dimension(100,26));
		m_buttonPanel.add(m_btnOK);

		this.add(m_buttonPanel,BorderLayout.SOUTH);
		
		//bouton OK par défaut
		getRootPane().setDefaultButton(m_btnOK);

		/*
		 * affiche le dialogue
		 * */
		this.setVisible(true);
		

		return this.m_bOK;
	}
	/**
	 * Définit la liste des champs (HastTable,Array,...)
	 * @param fields Collection de champs
	 * @return Référence sur le tableau des champs
	 */
	public IField[] setFieldsList(Collection<IField> fields)
	{
		//copie la référence des champs dans un tableau
		this.m_fields = new IField[fields.size()];
		fields.toArray(this.m_fields);
		
		return this.m_fields;
	}
	/**
	 * Définit la liste des champs
	 * @param fields Tableau de champs
	 * @return Tableau de champs passé en argument
	 */
	public IField[] setFieldsList(IField[] fields)
	{
		return this.m_fields = fields;
	}
	/**
	 * Retourne l'état du dialogue
	 * @return true si le dialogue à été confirmé et que tous les champs sont valides
	 */
	public boolean isOK()
	{
		return m_bOK;
	}

	public boolean isCanceled() {
		return m_bCancel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		/*
			Bouton "OK"...
		 	Test les valeurs entrée et ferme le dialogue
		 */
		if(e.getSource() == m_btnOK){			
			int maxInput = this.m_controlsPanel.getFieldCount();
			
			//vérifie que les champs sont valides
			for(int i=0;i<maxInput;i++){
				IFieldControl field = this.m_controlsPanel.getFieldControl(i);
				if(!field.isValidField()){
					//resultat 
					this.m_result = new Result(
							field.getField().getResultCode(),
							field.getField().getResultInfo(),
							field.getField().getResultDesc()+" ("+field.getField().getName()+")" );
					//message...
					//this.app.showResultBox(this.m_result);
					JOptionPane.showMessageDialog(this,this.m_result.getResultDesc(),"Oups !",JOptionPane.WARNING_MESSAGE);
					return;
				}
			}

			//actualise la valeur des champs
			/*for(int i=0;i<maxInput;i++){
				IFieldControl field = this.getFieldControl(i);
				field.updateFieldValue();
			}*/
			
			m_bCancel = false;
			m_bOK = true;
			this.setVisible(false);
			
			this.m_result = Result.ERR_OK;
			
			return;
		}

		/*
			Bouton "Annuler"...
			Ferme le dialogue
		 */
		if(e.getSource() == m_btnCancel){
			this.setVisible(false);
			m_result = new Result(Result.ERR_CODE_OK,"dialog_canceled","Dialogue annulé");
			return;
		}
	}
	
	@Override
	public int getResultCode() {
		return this.m_result.getResultCode();
	}
	
	@Override
	public String getResultInfo() {
		return this.m_result.getResultInfo();
	}

	@Override
	public String getResultDesc() {
		return this.m_result.getResultDesc();
	}
	
	
}

/**
 * Panneau de champ
 * @author dl201
 *
 */
@SuppressWarnings("serial")
class FieldPanel extends JPanel{
	IField field;
	IFieldControl ctrl;
	String name;
	
//	private final int preferredHeight = 26;
	
	public FieldPanel(String name,IField field) {
		
		//labels
		//JPanel left = new JPanel();
		//left.setLayout(layout);
		JLabel label = new JLabel((name==null ? field.getName() : name));
		//taille
		/*label.setPreferredSize(new Dimension(100,preferredHeight));
		label.setMaximumSize(new Dimension(5000,preferredHeight));
		label.setMinimumSize(new Dimension(50,preferredHeight));*/
		//insert
		//left.add(label);
		
		//zone d'edition
		//JPanel right = new JPanel();
		IFieldControl ctrl = this.createControl(field);

		//sauve
		this.ctrl  = ctrl;
		this.field = field;
		this.name  = name;
		
		//content
		this.setLayout(new BorderLayout(10,10));
		this.add(label,BorderLayout.WEST);
		this.add(this.ctrl.getComponent(),BorderLayout.CENTER);

	}
	 
	/**
	 * Insert un champ dans un panel
	 * @param panel Panel recevant les deux nouveaux contrôles JTextField et JLabel
	 * @param field Champs à inserer
	 * @return Le nouveau controle inseré
	 */
	public IFieldControl createControl(IField field)
	{
		IFieldControl ctrl;
		if(field.getTypeName() == "boolean"){
			ctrl  = new JBooleanFieldControl(field);
		}
		else{
			ctrl  = new JTextFieldControl(field);
		}
		
		//taille
		/*ctrl.getComponent().setPreferredSize(new Dimension(200,preferredHeight));
		ctrl.getComponent().setMaximumSize(new Dimension(5000,preferredHeight));
		ctrl.getComponent().setMinimumSize(new Dimension(50,preferredHeight));*/

		//pas d'actualisation auto
		ctrl.setUpdateField(true);

		return ctrl;
	}
}

/**
 * Panneau de pied de page
 * @author dl201
 *
 */
@SuppressWarnings("serial")
class FieldListPanel extends JPanel{
	private ArrayList<IFieldControl> ctrlList = new ArrayList<IFieldControl>();
	private final int preferredHeight = 26;
	
	public FieldListPanel(IField fields[]) {
		
		
		BoxLayout layout;
		
		//labels
		JPanel left = new JPanel();
		//GridLayout layout = new GridLayout(2,1);
		layout = new BoxLayout(left,BoxLayout.Y_AXIS);
		//layout.setHgap(10);
		//layout.setVgap(10);
		left.setLayout(layout);
		for(int i=0;i<fields.length;i++) {
			JLabel label = new JLabel(fields[i].getName());
			//taille
			//label.setPreferredSize(new Dimension(100,preferredHeight));
			label.setMaximumSize(new Dimension(5000,preferredHeight));
			label.setMinimumSize(new Dimension(50,preferredHeight));
			//insert
			left.add(label);
		}
		
		//zone d'edition
		JPanel right = new JPanel();
		//layout = new java.awt.GridLayout(2,1);
		layout = new BoxLayout(right,BoxLayout.Y_AXIS);
		//layout.setHgap(10);
		//layout.setVgap(10);
		right.setLayout(layout);
		for(int i=0;i<fields.length;i++) {
			this.insertField(right, fields[i]);
		}

		//content
		/*this.setLayout(new GridLayout(1,1));
		this.add(left);
		this.add(right);*/

		//content
		/*this.setLayout(new BorderLayout(10,10));
		this.add(left,BorderLayout.WEST);
		this.add(right,BorderLayout.CENTER);*/

		//content
		this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		this.add(left);
		this.add(right);
	}

	/**
	 * Retourne le nombre de champs
	 */
	public int getFieldCount(){
		return this.ctrlList.size();
	}

	/**
	 * Retourne un controle
	 */
	public IFieldControl getFieldControl(int i){
		return this.ctrlList.get(i);
	}

	/**
	 * Retourne un champ du panel
	 * @param label Nom du champ
	 * @return Le controle
	 */
	public IFieldControl findFieldControl(String label){
		for(int i=0;i<this.getFieldCount();i++){
			IFieldControl ctrl = this.ctrlList.get(i);
			if(ctrl.getField().getName() == label)
				return ctrl;
		}
		return null;
	}
	 
	/**
	 * Insert un champ dans un panel
	 * @param panel Panel recevant les deux nouveaux contrôles JTextField et JLabel
	 * @param field Champs à inserer
	 * @return Le nouveau controle inseré
	 */
	public IFieldControl insertField(JPanel panel, IField field)
	{
		IFieldControl ctrl;
		if(field.getTypeName() == "boolean"){
			ctrl  = new JBooleanFieldControl(field);
		}
		else{
			ctrl  = new JTextFieldControl(field);
		}
		
		//taille
		//ctrl.getComponent().setPreferredSize(new Dimension(200,preferredHeight));
		ctrl.getComponent().setMaximumSize(new Dimension(5000,preferredHeight));
		ctrl.getComponent().setMinimumSize(new Dimension(50,preferredHeight));

		//pas d'actualisation auto
		ctrl.setUpdateField(true);

		panel.add(ctrl.getComponent());

		//ajoute a la liste
		ctrlList.add(ctrl);
		
		return ctrl;
	}
}