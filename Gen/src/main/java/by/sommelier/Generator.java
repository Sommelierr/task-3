package by.sommelier;

import java.io.*;
import java.util.*;

public class Generator {
    private String region;
    private Integer setCharNumber;
    private char saveChar;
    final int PHONE_NUMBER_LENGTH = 7;
    String s = "123456789";
    Alphabet alphabet;
    List<String> arrAlphabet;

    public Generator(String region){
        this.region = region;
    }

    public String getNumber(){
            StringBuffer phoneNumber = new StringBuffer();

            for (int i = 0; i < PHONE_NUMBER_LENGTH; i++) {
                phoneNumber.append(s.charAt(new Random().nextInt(s.length())));
            }

            switch (region){
                case("en_EN"):return "1 (202) " + phoneNumber.toString();
                case("ru_RU"):return "8 (926) " + phoneNumber.toString();
                default:return "+375 (29) " + phoneNumber.toString();
            }

    }


    public int getCountOfPeople(String strNum) {
        int counter;
        try {
            counter = Integer.parseInt(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return 0;
        }
        return counter;
    }

    public double getCountOfErrors(String strNum) {
        double counter;
        try {
            counter = Double.parseDouble(strNum);
            if(counter >= 3.) counter = 3.;
        } catch (NumberFormatException | NullPointerException nfe) {
            return 3.;
        }
        return counter;
    }

    public String getCountry() throws IOException, ClassNotFoundException {
        List<String> countries = new ArrayList<>();

        Class cls = Class.forName("by.sommelier.Generator");

        // returns the ClassLoader object associated with this Class
        ClassLoader cLoader = cls.getClassLoader();

        // input stream
        InputStream i = cLoader.getSystemResourceAsStream("countries.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(i));
        String country;

        while( (country = reader.readLine()) != null){
            countries.add(country);
        }

        switch (region){
            case("ru_RU"): return countries.get(1);
            case("en_EN"): return countries.get(2);
            default : return countries.get(0);
        }
    }

    public String getCountryWithError(String country) throws IOException, ClassNotFoundException {
        Random random = new Random();
        StringBuilder error = new StringBuilder(country);
        int rand = random.nextInt(country.length());
        alphabet = new Alphabet(region);
        arrAlphabet = alphabet.getAlphabet();

        switch (random.nextInt(4)){
            case(0): return error.delete(rand,rand+1).toString();
            case(1):{
                setCharNumber = random.nextInt(country.length()-1);
                saveChar = error.charAt(setCharNumber + 1);
                error.delete(setCharNumber +1 ,setCharNumber + 2);
                return error.insert(setCharNumber, Character.toString(saveChar)).toString();
            }
            case(2): return error.append(random.nextInt(10)).toString();
            default: return error.append(arrAlphabet.get(random.nextInt(25))).toString();
        }
    }

}
