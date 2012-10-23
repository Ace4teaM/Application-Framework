package field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.Result;

public class GenericField implements IField  {
		protected String name;
		protected String value;
		protected boolean valid;

		public GenericField(String name,String value){
			this.name = name;
			this.fromString(value);
		}
		
		//IField: Nom du champs
		@Override
		public String getName(){
			return this.name;
		}
		
		//IField: Retourne le type du champs (Webframework)
		@Override
		public String getTypeName(){
			return "generic";
		}

		//IField: Retourne le champs au format texte
		@Override
		public String toString(){
			return this.value;
		}

		//IField: Intialise le champs depuis un format texte
		@Override
		public boolean fromString(String value){
			this.value = value;
			this.valid = this.checkValue(null);
			return this.valid;
		}

		//IField: Test la validité du champs au format texte
		@Override
		public boolean isValid() {
			return this.valid;
		}

		//Test la validité du champ donnée
		public boolean checkValue(String value){
			
			return GenericField.validate((value == null) ? this.value : value);
		}
		
		//Test la validité du champ (static)
		public static boolean validate(String value){
			Pattern p = Pattern.compile("^.*$");
			Matcher m = p.matcher(value);
			//System.out.println(m.find());
			return m.find();
		}

		@Override
		public int getResultCode() {
			return (this.isValid() ? Result.ERR_CODE_OK : Result.ERR_CODE_FAILED);
		}

		@Override
		public String getResultInfo() {
			return (this.isValid() ? "error_ok" : "invalid_string_format");
		}

		@Override
		public String getResultDesc() {
			return (this.isValid() ? "Format de chaine valide" : "Format de chaine invalide");
		}

		@Override
		public int getMaxLength() {
			return 0;
		}
	}
