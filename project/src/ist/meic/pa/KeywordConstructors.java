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
            	//Nao da para ver annotation de um ctconstructor
            	if(c.isAnnotationPresent(KeywordArgs.class))
            	{
            		System.out.println("Annotation KeywordArgs is present in the following Cons:");
            		System.out.println("Constructor -> " + c.toString() + "\n" );	
            		

        			CtField[] classAttributes = ctClass.getDeclaredFields();
        			CtClass[] params = new CtClass[classAttributes.length];
        			int i=0;

        			for (CtField field : classAttributes) 
        			{
            			System.out.println("Attribute -> Name: " + field.getName());
            			params[i] = field.getType();
            			System.out.println("Attribute -> Type: " + params[i]);
               			i++;

    				}
    				for(CtClass p : params)
    				{
    					System.out.println(p);
    				}


					String body =   "{ " + 
								 	"width=10;" +
								 	"height=10;" +
								 	"margin=10;" +
        							"}"	;
					System.out.println("1");    
					ctClass.defrost();        							
					CtConstructor ctConstructor = CtNewConstructor.make(params, null, body, ctClass);
					System.out.println("2");
       				//ctConstructor.setExceptionTypes(ctExceptions);
					ctClass.addConstructor(ctConstructor);
					ctClass.writeFile();

        		
            		                     
        	
            	}
            }

            CtConstructor[] ctcons = ctClass.getConstructors();
            for(CtConstructor c : ctcons)
            {
            	System.out.println(c.getLongName());
            }

		}
	}
}
	