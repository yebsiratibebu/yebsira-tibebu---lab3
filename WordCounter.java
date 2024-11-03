
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCounter {
    public static int processText (StringBuffer text, String stopword) throws InvalidStopwordException, TooSmallText {
        
        Pattern regex = Pattern.compile("[a-zA-Z0-9']+");
        Matcher regexMatcher = regex.matcher(text);
        int count = 0;
        int stopwordcount = 0;

        // if(stopword.length() == 0 ) {
        //     throw new InvalidStopwordException ("stopword is not found");
        // }
        //System.out.println("Stopword processed: " + stopwordcount);  
        if (stopword == null) {
            while (regexMatcher.find()) {
                //System.out.println("I just found the word: " + regexMatcher.group());
                count++;
            }
            if (count < 5) {
                throw new TooSmallText (count);
            }
        }  else {
        while (regexMatcher.find()) {
            String word = regexMatcher.group();
            //System.out.println("I just found the word: " + regexMatcher.group());
            count++;
            
            if (word.equals(stopword)) {
                stopwordcount++;
                break;
            }
        }
      
        if (count < 5) {
            Pattern regex1 = Pattern.compile("[a-zA-Z0-9']+");
            Matcher regexMatcher1 = regex1.matcher(text);
            int countall = 0;

            while (regexMatcher1.find()) {
                //System.out.println("I just found the word: " + regexMatcher.group());
                countall++;
            }
            throw new TooSmallText (countall);
        }
        if (stopwordcount == 0) {
            throw new InvalidStopwordException(stopword);
        }     
    }
        return count;
    } 

   
    public static StringBuffer processFile (String path) throws EmptyFileException {
        StringBuffer contents = new StringBuffer ();
       

        try {
            LineNumberReader reader = new LineNumberReader(new InputStreamReader(new FileInputStream(path)));
            String file = reader.readLine();
        
           
            while (file != null) {
              contents.append(file);
              //file = reader.readLine()
              break;
            }
            reader.close();
    }
    catch (IOException e) {
       System.out.println("error while reading file");
       contents = null;
    }
    if (contents.length() == 0) {
        throw new EmptyFileException(path);
    }
   
    return contents;
    }
    public static void main (String[] args)  {
        Scanner scan = new Scanner(System.in);
        String path = args[0];
        String stopword = null;
        int choice = scan.nextInt();
        
        while (choice != 1 && choice != 2) {
            System.out.println("choose the either 1 or 2 option");
            if (scan.hasNextInt()) {
                choice = scan.nextInt();
                scan.nextLine(); //clean the buffer
            }
            //System.out.println("choose the correct option");
            else {
            scan.next(); //clear invalid input
        }
    }
        if (args.length > 1) {
            stopword = args[1];
        }
       
            if (choice == 1) {
                StringBuffer files = new StringBuffer();
                try {
                files = processFile(path);
               
                int result = processText(files, stopword);
                System.out.println("Found " + result + " words.");                
                }
                catch (EmptyFileException e) {
                    //System.out.println(e.toString());
                    files = new StringBuffer();
                    try {
                    int result = processText(files, null);
                    System.out.println("Found " + result + " words.");    
                    }
                    catch (TooSmallText f) {
                        System.out.println(f.toString());
                    }
                    catch (InvalidStopwordException f) {
                        System.out.println(files.toString());
                    }
                }
                catch (TooSmallText e) {
                    System.out.println(e.toString());
                }
                catch (InvalidStopwordException e) {
                    System.out.println(e.toString());
                }
            }
            if (choice == 2) {
                System.out.println("enter the test");
                String text = scan.nextLine();

                System.out.println("enter the stopword");
                stopword = scan.nextLine();
                
                try {
                    int result = processText(new StringBuffer(text), stopword);
                    System.out.println("Found " + result + " words.");
                } 
                catch (TooSmallText e) {
                    System.out.println(e.toString());
                }
                catch (InvalidStopwordException e) {
                    System.out.println(e.toString());   
                } 
            }
            scan.close();
        } 
               
    }
        
    
    
    
    

      
        


