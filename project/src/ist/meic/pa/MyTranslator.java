package ist.meic.pa;

import javassist.*;
import java.lang.annotation.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MyTranslator implements Translator {

	public void start(ClassPool pool)
        throws NotFoundException, CannotCompileException {}
        
    public void onLoad(ClassPool pool, String classname)
        throws NotFoundException, CannotCompileException
    {
    	String[] parts = null;
    	String[] subParts = null;
        Map<String, String> cheat = new HashMap<String, String>();

        cheat.put("int", "java.lang.Integer");
        cheat.put("java.lang.String", "java.lang.String");
    	
        CtClass ct = pool.get(classname);
        ct.setModifiers(Modifier.PUBLIC);


        CtConstructor[] cons = ct.getConstructors();
        for(CtConstructor c : cons)
        {
        	if(c.hasAnnotation(KeywordArgs.class))
        	{
        		try {
        			KeywordArgs k = (KeywordArgs) c.getAnnotation(KeywordArgs.class);
        			String value = k.value();
        			
        			parts = value.split(",");
        			Map<String, Object> defaultValues = new HashMap<String, Object>();

        			for(int i = 0; i < parts.length; i++) {
        				if(parts[i].indexOf('=') >= 0) {
        					subParts = parts[i].split("=");
        					defaultValues.put(subParts[0], subParts[1]);
        				} else {
        					System.out.println("FIXME");
        				}
        			}

        			CtField[] classAttributes = ct.getDeclaredFields();

        			for(CtField field : classAttributes)
        			{

                        
                                    c.insertAfter("{" +                    
                                        "this." + field.getName() + "=" + defaultValues.get(field.getName()) + ";" + 
                                        "for(int i = 0; i < $1.length; i++) {" +

                                            "if($1[i].equals(\"" + field.getName() + "\")) {" +

                                                "if(\"" + cheat.get(field.getType().getName()) +"\" == $1[i+1].getClass().getName()) {" +
                                                    
                                                    //este if elseif so funciona de uma das maneiras (para strings ou para ints)
                                                    //nao é suposto, mas para testar basta meterem o print em comentario e a linha
                                                    //de baixo sem comentario. O primeiro funciona para string:string e o segundo
                                                    //para string:int
                                                    "if(\"" + field.getType().getName() + "\" == \"java.lang.String\")" +
                                                        //"System.out.println(12);" +
                                                        "this." + field.getName() + "= $1[i+1].toString();" +
                                                    "else if(\"" + field.getType().getName() +"\" == \"int\")" +
                                                        "System.out.println(12);" +
                                                        //"this." + field.getName() + "= ((Integer) $1[i+1]).intValue();" +
                                                    //"this." + field.getName() + "=$1[i+1];" +
                                                
                                                "}" +
                                            "}" +
                                        "}" +
                                    "}");
        			}

        			

        			ct.writeFile();

        		} catch (Exception e) {}
        	}
        }
        	
    }

}