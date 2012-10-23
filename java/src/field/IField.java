package field;

import app.IResult;

/**
 * Interface de champs de données
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
	 * @return true Si le champs est initialisé, false si le format est invalide.
	 * */
	public boolean fromString(String value);
	/**
	 * Test la validité du champs
	 * @return true Si le champs est valide, sinon false.
	 * */
	public boolean isValid();
	/**
	 * Test la validité du champs donnée
	 * @param value Valeur a tester. Si null, la valeur en cours est testée
	 * @return true si le champs est valide, sinon false.
	 */
	public boolean checkValue(String value);
	/**
	 * Retourne la taille maximal du champ.
	 * @return Taille maximal du champ en nombre de caractères. Une valeur négative indique qu'aucune limite n'est appliquée
	 * */
	public int getMaxLength();
	
}
