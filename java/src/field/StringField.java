package field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringField extends GenericField  {

	public StringField(String name, String value) {
		super(name, value);
	}

	//IField: Retourne le type du champs (Webframework)
	@Override
	public String getTypeName(){
		return "string";
	}

	//Test la validit� du champ ou d'une valeur pass�
	@Override
	public boolean checkValue(String value){
		return StringField.validate((value == null) ? this.value : value);
	}
	
	//Test la validit� du champs au format texte
	public static boolean validate(String value){
		Pattern p = Pattern.compile("^[^\"\n\r]*$");
		Matcher m = p.matcher(value);
		//System.out.println(m.find());
		return m.find();
	}

	@Override
	public int getMaxLength() {
		return 0;//pas de limite
	}
}
