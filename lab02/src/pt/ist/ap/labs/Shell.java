package pt.ist.ap.labs;

import java.util.Scanner;
import java.lang.reflect.*;
import java.util.*;
import java.io.*;
public class Shell{
    public static void main(String[] args){
        try{
            Scanner input = new Scanner(System.in);
            String command;
            String classname = "";
            String key = "";
            String method = "";
            int index = 0;
            Object currentObject = null;
            Object[] currentObjects;
            Object[] arrayResult;
            Map<String, Object> objects = new TreeMap<String, Object>();
            
            while(true){
                System.out.print("Command:>");
                command = input.next();
                if (classname instanceof String) {
                    switch(command){
                    case "Class":
                        classname = input.next();
                        currentObject = Class.forName(classname).newInstance();
                        System.out.println(currentObject.getClass());
                        break;
                    case "Set":
                        key = input.next();
                        objects.put(key, currentObject);
                        System.out.println("Saved name for object of type: " + currentObject.getClass());
                        break;
                    case "Get":
                        key = input.next();
                        currentObject = objects.get(key);
                        System.out.println(currentObject.getClass());
                        break;
                    case "Index":
                        /*index = input.nextInt();
                        if(currentObject instanceof Object[]){
                            currentObject = currentObject;
                            System.out.println(currentObject.getCLass());
                        }else {
                            System.out.println("The currently selected object is not an array.");
                        }*/
                        break;
                    case "help":
                        System.out.print("Usage: ");
                        System.out.println("Class <name>\nGet <name>\nSet <name>\nIndex <int>");
                        break;
                    case "exit":
                        System.exit(0);
                        break;
                    default:
                        method = input.next();
                        Method[] currentMethods = currentObject.getClass().getDeclaredMethods();
                        for(int i = 0; i < currentMethods.length; i++){
                            if(currentMethods[i].getName().equals(method)){
                                currentObject = currentMethods[i].invoke(currentObject);
                                break;
                            }
                        }
                        System.out.println("Current object does not contain a method with the name: " + method);
                        break;
                    }
                } else {
                System.out.println("Usage: ");
                System.out.println("Class <name>\nGet <name>\nSet <name>\nIndex <int>");
                System.exit(1);
                }
            }
        }catch(Exception e){
            System.out.println("ERROR");
        }
    }
}
