package ist.meic.pa;

import java.lang.annotation.*;
import javassist.*;
import java.io.*;
import java.lang.reflect.*;

public class KeywordConstructors {

	public static void main(String[] args) throws Exception {
		if (args.length < 1)
		{
			System.out.println("FIXME");
		}
		else
		{
			ClassPool pool = ClassPool.getDefault();
			CtClass ctClass = pool.get(args[0]);
			Class<?> rtClass = ctClass.toClass();
			//ctClass.detach();

			//nao sei se faço as coisas com a rtClass ou com a ctclass, ainda tenho que ler melhor a teorica, por agora faco com a ct

			System.out.println("\nClass gotten: " + rtClass);


			Constructor[] cons = rtClass.getConstructors();
            for(Constructor c : cons)
            {
            	//Nao da para ver annotation de uma ctclass
            	if(c.isAnnotationPresent(KeywordArgs.class))
            	{
            		System.out.println("Annotation KeywordArgs is present in the following Cons:");
            		System.out.println("Constructor -> " + c.toString() + "\n" );	
            		try
            		{

            			CtField[] classAttributes = ctClass.getDeclaredFields();
            			CtClass[] params = new CtClass[classAttributes.length];
            			int i=0;

            			for (CtField field : classAttributes) 
            			{
	            			System.out.println("Attribute -> Name: " + field.getName());
	            			params[i] = field.getType();
	            			System.out.println("Attribute -> Type: " + params[i]);


        				}
						System.out.println("1");
						CtConstructor ctConstructor = new CtConstructor(params, ctClass);
						System.out.println("2");
           				//ctConstructor.setExceptionTypes(ctExceptions);
            			ctConstructor.setBody("{ " + 
            								 	"width = 10;" +
            								 	"height = 10;" +
            								 	"margin = 10;" +
            				"}"	
            				);
						ctClass.addConstructor(ctConstructor);
						ctClass.writeFile();

						System.out.println("3");
            		
            		                     
        			} catch (Throwable ex) {
               		  System.out.printf("Test %s failed: %s %n", c, ex.getCause());
            }
            	}
            }

		}
	}
}
	