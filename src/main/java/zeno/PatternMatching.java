/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zeno;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author rajanikant
 */
public class PatternMatching {
    public static void main(String[] args){    
        Scanner sc=new Scanner(System.in);  
         
            System.out.println("Enter regex pattern:");  
            Pattern pattern = Pattern.compile("[([^w]{3})]+([a-zA-Z]*)+[.]");    
            System.out.println("Enter text:");  
            Matcher matcher = pattern.matcher(sc.nextLine()); 
            matcher.find();
         try {
            System.out.println(matcher.group().replaceAll("\\s+","").substring(1, matcher.group().length()-1));
         }catch (IllegalStateException e) {
        	 
			System.out.println("not a URL kindly enter a URL");
		}catch (StringIndexOutOfBoundsException e) {
			System.out.println("Invalid, Please remove spaces from the URL then enter");
		}
          
    }    
}
