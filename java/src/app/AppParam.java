package app;

import java.util.Hashtable;

import field.IField;



/**
 * Liste de paramètres
 * @author dl201
 *
 */
public class AppParam{
	public String name;
	public Hashtable<String,IField> fields = new Hashtable<String,IField>();

	public AppParam(){
		this(null);
	}

	public AppParam(String name){
		this.name = name;
	}
	
	/**
	 * Définit la valeur d'un paramètre
	 * @param name Nom du champ
	 * @param value Valeur du champ
	 * @return Valeur du champ, chaine vide si introuvable
	 */
	public String set(String name,String value){
		IField f = this.fields.get(name);
		if(f!=null){
			f.fromString(value);
			return value;
		}
		return "";
	}

	public String get(String name){
		return this.fields.get(name).toString();
	}
}

