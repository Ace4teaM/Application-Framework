package field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Champs de type identificateur (identifer)
 * 
 * @author AUGUEY Thomas
 *
 */
public class IdentifierField extends GenericField  {

	public IdentifierField(String name, String value) {
		super(name, value);
	}

	//IField: Retourne le type du champs (Webframework)
	@Override
	public String getTypeName(){
		return "identifier";
	}

	//Test la validité du champ ou d'une valeur passé
	@Override
	public boolean checkValue(String value){
		return IdentifierField.validate((value == null) ? this.value : value);
	}
	
	//Test la validité du champ au format texte
	public static boolean validate(String value){
		Pattern p = Pattern.compile("^[a-zA-Z_][0-9a-zA-Z_]*$");
		Matcher m = p.matcher(value);
		//System.out.println(m.find());
		return m.find();
	}

	@Override
	public int getMaxLength() {
		return 32;
	}
}
