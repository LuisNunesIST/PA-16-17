package pt.ist.ap.labs;

import java.util.Scanner;
import java.lang.reflect.*;
import java.io.*;

public class AskString {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		System.out.println("Name of class?");
		String className = input.next();
		if (className instanceof String) {
		instantiateClass(className);
		} else {
		System.out.print("Usage: ");
		System.out.println("<class>");
		System.exit(1);
		}

	}

	static Object instantiateClass(String className) {

	try {
		 Message instance = (Message) Class.forName(className).newInstance();
		 instance.say();
		 return instance;
	} 
	catch (ClassNotFoundException cnfe) {
		System.err.println("Class '" + className + "' not found");
		System.exit(1);
		return null;
		}
        catch(Exception e){
            return null;
        }
	}
}