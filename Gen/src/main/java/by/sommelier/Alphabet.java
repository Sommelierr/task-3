package by.sommelier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Alphabet {
    List<String> alphabet = new ArrayList<>();
    String region;
    List<String> enAlphabet = Arrays.asList("A", "B", "C", "D",
            "E", "F", "G", "H", "I", "K", "L", "M", "N", "O", "P",
            "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");

    public Alphabet(String region){
        this.region =region;
    }

    public List<String> getAlphabet() throws ClassNotFoundException, IOException {

        switch (region){
            case("en_EN"): return enAlphabet;
            case("ru_RU"):{
                Class cls = Class.forName("by.sommelier.Alphabet");

                // returns the ClassLoader object associated with this Class
                ClassLoader cLoader = cls.getClassLoader();

                // input stream
                InputStream i = cLoader.getSystemResourceAsStream("rualphabet.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(i));
                String character;

                while( ( character = reader.readLine()) != null){
                    alphabet.add(character);
                }
            };
            case("be_BY"):{
                Class cls = Class.forName("by.sommelier.Alphabet");

                // returns the ClassLoader object associated with this Class
                ClassLoader cLoader = cls.getClassLoader();

                // input stream
                InputStream i = cLoader.getSystemResourceAsStream("byalphabet.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(i));
                String character;

                while( ( character = reader.readLine()) != null){
                    alphabet.add(character);
                }
            }
        }
        return alphabet;
    }

}
