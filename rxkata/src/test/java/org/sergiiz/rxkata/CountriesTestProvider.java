package org.sergiiz.rxkata;


import java.util.*;

final class CountriesTestProvider {
    private CountriesTestProvider() {
    }

    static List<Country> countries() {
        List<Country> countries = new ArrayList<>();
        countries.add(new Country("Germany", Currencies.EUR, 80620000));
        countries.add(new Country("France", Currencies.EUR, 66030000));
        countries.add(new Country("United Kingdom", Currencies.GBP, 64100000));
        countries.add(new Country("Poland", Currencies.PLN, 38530000));
        countries.add(new Country("Ukraine", Currencies.UAH, 45490000));
        countries.add(new Country("Austria", Currencies.EUR, 8474000));
        countries.add(new Country("Switzerland", Currencies.CHF, 8081000));
        countries.add(new Country("Luxembourg", Currencies.EUR, 576249));

        return countries;
    }

    static class Currencies {
        static final String EUR = "EUR";
        static final String PLN = "PLN";
        static final String GBP = "GBP";
        static final String UAH = "UAH";
        static final String CHF = "CHF";

        static String[] all()  {
            return new String[]{CHF, EUR, GBP, PLN, UAH};
        }
    }

    static List<Country> countriesPopulationMoreThanOneMillion() {
        List<Country> result = new ArrayList<>();
        for (Country country : countries()) {
            if (country.population > 1000000) {
                result.add(country);
            }
        }
        return result;
    }


    static List<Long> populationOfCountries() {
        List<Long> result = new ArrayList<>(countries().size());
        for (Country country : countries()) {
            result.add(country.population);
        }
        return result;
    }

    static List<String> namesOfCountries() {
        List<String> result = new ArrayList<>(countries().size());
        for (Country country : countries()) {
            result.add(country.name);
        }
        return result;
    }

    static Long sumPopulationOfAllCountries() {
        Long result = 0L;
        for (Country country : countries()) {
            result += country.population;
        }
        return result;
    }

    static List<Tuple<String, Long>> averagePopulationByCurrency() {
        List<Tuple<String, Long>> result = new ArrayList<>();
        for (String currency: Currencies.all()) {
            Long sumOfPopulations = 0L;
            Integer numberOfCountries = 0;
            for (Country country : countries()) {
                if (!country.getCurrency().equals(currency)) continue;

                sumOfPopulations += country.getPopulation();
                numberOfCountries += 1;
            }

            if (numberOfCountries > 0) {
                Long averagePopulation = sumOfPopulations / numberOfCountries;
                result.add(Tuple.of(currency, averagePopulation));
            }
        }

        return result;
    }
}
