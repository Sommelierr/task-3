package by.sommelier;

import com.github.javafaker.Faker;
import java.io.IOException;
import java.util.Locale;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        double countOfErrors;
        String region = args[0];
        String street;
        String  city;
        Faker faker;
        String countryWithError;
        Generator generator;
        int countOfPeople;

        if(args.length <=1 ) {
            System.err.println("Set more arguments");
            return;
        }

        //Definition locale
        if(region.equals("ru_RU")) {
            faker = new Faker(new Locale("ru"));
            street = ", ул.";
            city = ", г.";
        }
        else if(region.equals("en_EN")){
            faker = new Faker(new Locale("en"));
            street = ", st.";
            city = ", c.";
        }
        else if(region.equals("be_BY")){
            faker = new Faker(new Locale("by"));
            street = ", вул.";
            city = ", г.";
        }
        else {
            System.err.println("Locale not found");
            return;
        }

        generator = new Generator(region);

        if(generator.getCountOfPeople(args[1]) == 0) {
            System.err.println("Enter valid number of people");
            return;
        }

        //Definition of 3 param
        if(args.length == 2) {
            countOfErrors = 0.;
        }
        else {
            countOfErrors  = generator.getCountOfErrors(args[2]);
            if(countOfErrors > 3.) countOfErrors=3.;
        }



        String country = generator.getCountry();
        countOfPeople = generator.getCountOfPeople(args[1]);
        if(countOfErrors < 1 && countOfErrors > 0){
            Random random = new Random();
            for(int i =0; i<countOfPeople; i++) {
                if(random.nextInt(10)/10. > countOfErrors) {
                    System.out.println(faker.name().fullName() + "; "
                            + faker.address().zipCode() + ", "
                            + generator.getCountryWithError(country)
                            + city + faker.address().city() + ", "
                            + street + faker.address().streetAddress()
                            + "; " + generator.getNumber());
                }
                else {
                    System.out.println(faker.name().fullName() + "; "
                            + faker.address().zipCode() + ", " + country
                            + city + faker.address().city()
                            + street + faker.address().streetAddress()
                            + "; " + generator.getNumber());
                }

            }
        }
        else{
            for (int i = 0;i<countOfPeople;i++){
                countryWithError = country;
                for (int j = 0;j < countOfErrors;j++){
                    countryWithError = generator.getCountryWithError(countryWithError);
                }
                System.out.println(faker.name().fullName() + "; "
                        + faker.address().zipCode() + ", " + countryWithError
                        + city + faker.address().city()
                        + street + faker.address().streetAddress()
                        + "; " + generator.getNumber());
            }
        }
    }
}