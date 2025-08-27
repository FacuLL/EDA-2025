package ar.edu.itba.eda;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.language.Soundex;

public class ApacheSoundex {
    private static final Soundex soundex = new Soundex();

    public static String encode(String str) {
        return soundex.soundex(str);
    }

    public static Integer comparate(String str1, String str2) {
        try {
            return soundex.difference(str1, str2);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}