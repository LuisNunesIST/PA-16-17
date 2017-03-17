package ist.meic.pa;

import java.lang.annotation.*;
import javassist.*;
import java.io.*;
import java.lang.reflect.*;

public class KeywordConstructors {

	public static void main(String[] args) throws Exception {
		if (args.length < 1)
		{
			System.out.println("FIXME - Case where we dont pass arguments to the constructor");
		}
		else
		{
			ClassPool pool = ClassPool.getDefault();
			CtClass ctClass = pool.get(args[0]);
			Class<?> rtClass = ctClass.toClass();
			ctClass.detach();

			//nao sei se faço as coisas com a rtClass ou com a ctclass, ainda tenho que ler melhor a teorica

			System.out.println("Class gotten:" + rtClass);

			Constructor[] cons = rtClass.getConstructors();
            for(Constructor c : cons){
            	if(c.isAnnotationPresent(KeywordArgs.class))
            	{
            		System.out.println("Annotation KeywordArgs present");
            		System.out.println("Constructor:" + c.toString());		//Esta a ir buscar os argumentos do construtor, é suposto ir buscar os atributos da classe e por isso no construtor?
            		try{

            			/*
						Nao seria feito nada disto, pensava que os construtores recebiam ja os parametros, mas pareceme que sao é os atributos da class (ints declarados la em cima)
						ou seja, 
						queria ir buscar os atributos da classe e de seguida redefinir o construtor com esses atributos e atribuirlhes os valores no campo value da annotation
            			*/
            			Class<?>[] parameters = c.getParameterTypes();
            			//Not sure about this "IF"
            			if(parameters.length > 0) {
					        System.out.println(parameters);
					        }                        
        			} catch (Throwable ex) {
               		  System.out.printf("Test %s failed: %s %n", c, ex.getCause());
            }
            	}
            }

		}
	}
}
	