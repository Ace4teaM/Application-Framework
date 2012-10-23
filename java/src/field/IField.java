package field;

import app.IResult;

/**
 * Interface de champs de donn�es
 * 
 * @author AUGUEY Thomas
 *
 */
public interface IField extends IResult {
	/**
	 * Retourne le nom du champs
	 * @return Nom du champs
	 * */
	public String getName();
	/**
	 * Retourne le type du champs (Webframework)
	 * @return Type du champs
	 * */
	public String getTypeName();
	/**
	 * Retourne le texte du champs
	 * @return Texte du champs
	 * */
	public String toString();
	/**
	 * Initialise le texte du champs
	 * @param value Texte du champs
	 * @return true Si le champs est initialis�, false si le format est invalide.
	 * */
	public boolean fromString(String value);
	/**
	 * Test la validit� du champs
	 * @return true Si le champs est valide, sinon false.
	 * */
	public boolean isValid();
	/**
	 * Test la validit� du champs donn�e
	 * @param value Valeur a tester. Si null, la valeur en cours est test�e
	 * @return true si le champs est valide, sinon false.
	 */
	public boolean checkValue(String value);
	/**
	 * Retourne la taille maximal du champ.
	 * @return Taille maximal du champ en nombre de caract�res. Une valeur n�gative indique qu'aucune limite n'est appliqu�e
	 * */
	public int getMaxLength();
	
}
