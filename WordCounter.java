import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCounter {
    public static int processText (StringBuffer text, String stopword, int count) throws InvalidStopwordException, TooSmallText {
        Pattern regex = Pattern.compile("[a-zA-Z0-9']+");
        Matcher regexMatcher = regex.matcher(text);

        if(stopword == null) {
            throw new InvalidStopwordException ("stopword is not found");
        }
        while (regexMatcher.find()) {
            count++;
        }
        if (count < 5) {
            throw new TooSmallText ("less the 5 words");
        }
        return count;
    } 
   
    public static StringBuffer processFile (String path) throws EmptyFileException {
        StringBuffer contents = new StringBuffer();
        File file = new File (path);
        try {
            if (file.length() == 0) {
                throw new EmptyFileException ("file is empty");
            }
            while (file != null) {
                Scanner scan = new Scanner(file);
                
            }

    }
}
}