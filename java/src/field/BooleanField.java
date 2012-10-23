package field;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BooleanField extends GenericField {

	public BooleanField(String name, Boolean value) {
		super(name, value.toString());
	}

	//IField: Retourne le type du champ (Webframework)
	@Override
	public String getTypeName(){
		return "boolean";
	}

	//Test la validité du champ ou d'une valeur passé
	@Override
	public boolean checkValue(String value){
		return BooleanField.validate((value == null) ? this.value : value);
	}
	
	//Test la validité du champs au format texte
	public static boolean validate(String value){
		Pattern p = Pattern.compile("^true|false|on|off|yes|no|y|n|0|1$");
		Matcher m = p.matcher(value);
		//System.out.println(m.find());
		return m.find();
	}

	//IField: Intialise le champs depuis un format texte
	@Override
	public boolean fromString(String v){
		v = v.toLowerCase();
		
		if(v == "true" || v == "1" || v == "on" || v == "yes" || v == "y")
			return super.fromString("true");
		
		return super.fromString("false");
	}

	@Override
	public int getMaxLength() {
		return 5;
	}


	public static boolean toBoolean(String v) {
		v = v.toLowerCase();
		if(v == "true" || v == "1" || v == "on" || v == "yes" || v == "y")
			return true;
		return false;
	}
	
}
