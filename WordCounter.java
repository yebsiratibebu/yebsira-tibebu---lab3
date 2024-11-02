import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCounter {
    public static int processText (StringBuffer text, String stopword) throws InvalidStopwordException, TooSmallText {
        
        Pattern regex = Pattern.compile("[a-zA-Z0-9']+");
        Matcher regexMatcher = regex.matcher(text);
        int count = 0;
        int stopwordcount = 0;

        if(stopword == null || stopword.length() == 0 ) {
            throw new InvalidStopwordException ("stopword is not found");
        }
        
        while (regexMatcher.find()) {
            String word = regexMatcher.group();
            //System.out.println("I just found the word: " + word);
            count++;
            
            if (word.equals(stopword)) {
                stopwordcount++;
            }
        }
        if (stopwordcount == 0) {
            throw new InvalidStopwordException("Couldn't find stopword: " + stopword);
        }
        if (count < 5) {
            throw new TooSmallText ("Only found 3 words.");
        }
        
        return count;
    } 
   
    public static StringBuffer processFile (String path) throws EmptyFileException {
        StringBuffer contents = new StringBuffer ();
        File file = new File (path);
        try {
            if (file.length() == 0) {
                throw new EmptyFileException (path + " was empty");
            }
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                contents = new StringBuffer(contents.toString() + scan.nextLine() + "");
            }
            scan.close();
    }
    catch(FileNotFoundException e) {
        return new StringBuffer("This file has enough words to not trigger and exception and the stopword we're going to use " +
        "is yellow so we shouldn't have scanned into this point -- it just isn't necessary...unless " +
        "the stopword we wanted was green in which case we stopped above. Or, perhaps no stopword was provided, " + 
        "so then we will read in the whole file. ");

    }
    return contents;
    }
    public static void main (String[] args)  {
        Scanner scan = new Scanner(System.in);
        String path = "";
        String stopword = null;
        int choice = 0;
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
        
        try {
            if (choice == 1) {
                System.out.println("enter the file path");
                path = scan.nextLine();
                StringBuffer files = new StringBuffer();

            
                try {
                files = processFile(path);
                int count = processText(files, stopword);
                System.out.println("word from file" + count);                
                }
                catch (EmptyFileException e) {
                    System.out.println("error: the" + path + "is empty");
                    }
                    catch (TooSmallText e) {
                     System.out.println("this test is too small");
                    }
                    catch (InvalidStopwordException e) {
                        System.out.println("error the stopword" + stopword + "was not found in the test");
                       
                    }
            }
            if (choice == 2) {
                System.out.println("enter the test");
                String text = scan.nextLine();

                System.out.println("enter the stopword");
                stopword = scan.nextLine();
                
                 try {
                    int count = processText(new StringBuffer(text), stopword);
                    System.out.println("process word" + count);
                 } 
                 catch (InvalidStopwordException e) {
                    System.out.println("error the stopword" + stopword + "was not found in the test");   
                } 
                catch (TooSmallText e) {
                    System.out.println("this test is too small");
                }
            }
        
        } finally {
                scan.close();
            }
        }
    }
    
    
    

      
        


