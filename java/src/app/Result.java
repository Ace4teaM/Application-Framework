package app;

import java.util.Hashtable;

import javax.swing.JOptionPane;

public class Result {
	public int code;
	public String info;
	public String desc;

	//codes d'erreurs
	public static final int ERR_CODE_OK = 0; 
	public static final int ERR_CODE_FAILED = 1;
	//instances classiques
	public static final Result ERR_OK = new Result(Result.ERR_CODE_OK,"error_ok","Successful"); 
	public static final Result ERR_FAILED = new Result(Result.ERR_CODE_FAILED,"failed","Failed"); 

	//descriptions des erreurs
	public static Hashtable<String,String> desc_list = new Hashtable<String,String>();
	
	public Result(){
		this.code = Result.ERR_CODE_OK;
		this.info = "";
		this.desc = "";
	}

	public Result(int code){
		this.code = code;
		this.info = "";
		this.desc = "";
	}

	public Result(int code,String info){
		this.code = code;
		this.info = info;
		this.desc = Result.desc_list.get(info);
		if(this.desc == null)
			this.desc = "";
	}

	public Result(int code,String info,String desc){
		this.code = code;
		this.info = info;
		this.desc = desc;
		if(this.desc == null)
			this.desc = "";
	}

	public boolean isOK(){
		return (this.code == Result.ERR_CODE_OK) ? true : false;
	}

	@Override
	public String toString() {
		//return getSignature()+"\n"+this.code+" : "+this.info;
		return this.info+" ("+this.code+")"+" \""+this.desc+"\"";
	}
	
	/**
    * Get the signature of caller conforming to eclipse error parser.
    * @return
    */
   protected static String getSignature() {
      StackTraceElement trace = getCaller() ;
      String classNameParts[] = trace.getClassName().split(".");
      String className = classNameParts[classNameParts.length-1] ;
      String signature = className+"."+trace.getMethodName();
      signature += "(" + trace.getFileName() + ":" + trace.getLineNumber() + ")" ;
      return signature ;
   }
   /**
    * Get the caller by scanning the stack trace.
    * @return
    */
   protected static StackTraceElement getCaller() {
      String thisClassName = Result.class.getSimpleName() ;
      StackTraceElement[] traces = new Throwable().getStackTrace() ;
      for(StackTraceElement trace : traces) {
          String className = trace.getClassName() ;
          if(className.endsWith(thisClassName)==false)
             return trace ;
      }
      return null ;
   }

   //iResult ...
	public int getResultCode() {
		return code;
	}

	public String getResultInfo() {
		return info;
	}

	public String getResultDesc() {
		return desc;
	}
}
