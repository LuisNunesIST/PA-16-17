package ist.meic.pa;

import java.lang.annotation.*;
import javassist.*;
import java.io.*;
import java.lang.reflect.*;

public class KeywordConstructors {

    public static void main(String[] args) throws Exception, Throwable {
        if (args.length < 1)
        {
            System.out.println("FIXME");
        }
        else
        {
            Translator t = new MyTranslator();
            ClassPool pool = ClassPool.getDefault();
            Loader classLoader = new Loader(pool);
            classLoader.addTranslator(pool, t);
            String[] restArgs = new String[args.length-1];
            System.arraycopy(args, 1, restArgs, 0, restArgs.length);
            classLoader.run(args[0], restArgs);




        //CtClass ctClass = pool.get(args[0]);

                    
                    

                    
        } 
    }
}