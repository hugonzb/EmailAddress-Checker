/**
 * EmailAddresses.java
 * Etude 1
 * Semester 1 2020
 * 
 * Checks all emails addresses given in stdin are valid.
 * 
 * @author: Hugo Baird
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher; 
import java.util.regex.Pattern; 
import java.util.regex.*;

public class EmailAddresses{
  private static Pattern pattern;
  private Matcher matcher;
  
  public static void main(String[]args){
    ArrayList<String>emails = new ArrayList<String>();
    Scanner scan = new Scanner(System.in);
    String email;
    System.out.println("Enter the email addresses");
    while (scan.hasNextLine()){
      email = scan.nextLine();
      if(email.length() > 1){
        emails.add(email);
      }
    }
    for (String emailAddress : emails){
      checkEmailAddresses(emailAddress);
    }
  } 
    
  public static void checkEmailAddresses(String emailAddress){
    String[] domainExtensions = { "co.nz", "com.au", "co.ca", "com", "co.us", "co.uk" };
    String originalEmail = emailAddress;
    emailAddress = emailAddress.replaceAll("_at_", "@");
    emailAddress = emailAddress.replaceAll("_dot_", ".");
    emailAddress = emailAddress.toLowerCase();
    int atIndex = emailAddress.indexOf('@');
    
    if (atIndex < 0){
      System.out.println(emailAddress + " <- Missing @ symbol");
    }else{
      String[] parts = emailAddress.split("@|co.nz|com.au|co.ca|com|co.us|co.uk" );
      String mailboxName = parts[0];
      String domainName = parts[1];
      if(mailboxName.matches("[a-zA-Z0-9._-]+")){
        if((domainName.contains("[")) & (domainName.contains("]"))){
          if((domainName.indexOf('[') == 0) & (domainName.indexOf(']') == (domainName.length()-1))){
            if(domainName.matches("[0-9.\\[\\]]+")){
              System.out.println(emailAddress);
            }else{
              System.out.println(originalEmail + " <- Invalid numerical domain format");
            }
          }else{
            System.out.println(originalEmail + " <- Numerical domain not surrounded by brackets");
          }
        }else{
          if(domainName.matches("[a-zA-Z0-9._-]+")){
            boolean matchedExtension = false;
            for (String domainExtension : domainExtensions){    
              if (emailAddress.contains(domainExtension)){
                matchedExtension = true;
                System.out.println(emailAddress);
              }
            }
            if(matchedExtension == false){
              System.out.println(originalEmail + " <- Invalid extension");
            }
          }else{
            System.out.println(originalEmail + " <- Domain name is not alphanumeric");
          }
        }
      }else{
        System.out.println(originalEmail + " <- Mailbox name is not alphanumeric");
      }
    }
  }  
}