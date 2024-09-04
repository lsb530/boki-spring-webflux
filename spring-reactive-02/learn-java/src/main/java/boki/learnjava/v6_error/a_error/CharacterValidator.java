package boki.learnjava.v6_error.a_error;

import java.util.zip.DataFormatException;

public class CharacterValidator {
    public static void isAlphabeticCharacter(char ch) throws DataFormatException {
        if (!Character.isAlphabetic(ch)) {
            throw new DataFormatException("Not Alphabetic");
        }
    }
}
