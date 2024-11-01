import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCounter {
    public static int processText (StringBuffer text, String stopword, int count) throws InvalidStopwordException, TooSmallText {
        Pattern regex = Pattern.compile("[a-zA-Z0-9']+");
        Matcher regexMatcher = regex.matcher(text);

        if(stopword != null) {
            throw new InvalidStopwordException ();

        }

        if(stopword == null) {
        while (regexMatcher.find()) {
            count++;
        }
        if(!stopwordFound) {
            return -1;
        }
        if (count < 5) {
            throw new TooSmallText ()
        }
    } 

    }


}