package lab03;
import java.lang.reflect.*;
import java.lang.annotation.*;
public class RunTests{
    public static void main(String[] args){
        try{
            Class c = Class.forName(args[0]);
            Method[] meth = c.getDeclaredMethods();
            for(Method m : meth){
                //System.out.println(m.toString());
                Annotation t = m.getAnnotation(Test.class);
                if(t != null){
                    try{
                    m.setAccessible(true);
                        m.invoke(null);
                        System.out.println(m + ": OK!");
                    } catch (Exception e){
                        //e.printStackTrace();
                        System.err.println(m + ": Failed!");
                    }
                }
            }   
        } catch(ClassNotFoundException cnfe){
            System.err.println("Error: Class not found: " + args[0]);
            System.exit(1);
        }
        
    }
}