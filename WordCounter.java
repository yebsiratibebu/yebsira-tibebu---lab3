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
        if(stopword == null) {
            throw new InvalidStopwordException ("stopword is not found");
        }
        while (regexMatcher.find()) {
            String word = regexMatcher.group();
            count++;

            if (word.equals(stopword)) {
                stopwordcount++;
            }
        }
        if (count < 5) {
            throw new TooSmallText ("less the 5 words");
        }
        return count;
    } 
   
    public static StringBuffer processFile (String path) throws EmptyFileException {
        StringBuffer contents = new StringBuffer ();
        File file = new File (path);
        try {
            if (file.length() == 0) {
                throw new EmptyFileException ("file is empty");
            }
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                contents = new StringBuffer(contents.toString() + scan.next() + " ");
            }
            scan.close();
    }
    catch(FileNotFoundException e) {
        System.out.println("file not found re-enter the file name");
        Scanner input = new Scanner(System.in);
        path = input.nextLine();
        file = new File (path);
    }
    catch (Exception e) {
        throw new EmptyFileException("file not found");
    }
    return contents;
    }
    public static void main (String[] args)  {
        Scanner scan = new Scanner(System.in);
        String path = "";
        String stopword = null;
        int choice = 0;
        while (choice != 1 || choice != 2) {
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
                StringBuffer files = processFile(path);

                if(stopword == null || stopword.length()== 0) {
                System.out.println("enter a stop word or not");
                stopword = scan.nextLine();
                }
                
            
                try {
                int count = processText(files, stopword);
                System.out.println("word from file" + count);                
                }
    
                
                // try {
                //     StringBuffer empty = new StringBuffer();
                //     int count = processText(empty, stopword);
                //     System.out.println("word coutn from empty file" + count);
                // }
             catch (TooSmallText y) {
                    System.out.println("this test is too small");
                }
            
        
                catch (InvalidStopwordException t) {
                    System.out.println("error the stopword" + stopword + "was not found in the test");
                    System.out.println("try again");
                    stopword = scan.nextLine();
                }
            }
        }
        catch (EmptyFileException e) {
            System.out.println("error: the" + path + "is empty");
            }
                    // try {
                    //     StringBuffer newfile = processFile(path);
                    //     int count = processText(newfile, stopword);
                    //     if (count > 0) {
                    //     System.out.println("count with new stopword");
                    //     }
                    
                    //     else {
                    //     System.out.println("word was not found again");
                    //     }
                    // }
                    catch (Exception e1) {
                    System.out.println("error occured while processing text");
                    }
                    scan.close();
                }
            }

      
        


