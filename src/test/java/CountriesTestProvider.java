import java.util.ArrayList;
import java.util.List;

final class CountriesTestProvider {
    static final String CURRENCY_EUR = "EUR";
    static final String CURRENCY_PLN = "PLN";
    static final String CURRENCY_GBP = "GBP";
    static final String CURRENCY_UAH = "UAH";
    static final String CURRENCY_CHF = "CHF";

    private static ArrayList<Country> countries = new ArrayList<>();

    static {
        countries.add(new Country("Germany", CURRENCY_EUR, 80620000));
        countries.add(new Country("France", CURRENCY_EUR, 66030000));
        countries.add(new Country("United Kingdom", CURRENCY_GBP, 64100000));
        countries.add(new Country("Poland", CURRENCY_PLN, 38530000));
        countries.add(new Country("Ukraine", CURRENCY_UAH, 45490000));
        countries.add(new Country("Austria", CURRENCY_EUR, 8474000));
        countries.add(new Country("Switzerland", CURRENCY_CHF, 8081000));
        countries.add(new Country("Luxembourg", CURRENCY_EUR, 576249));
    }

    private CountriesTestProvider() {
        // hidden
    }

    static List<Country> countries() {
        return new ArrayList<>(countries);
    }

    static List<Country> countriesPopulationMoreThanOneMillion() {
        List<Country> result = new ArrayList<>();
        for (Country country : countries) {
            if (country.population > 1000000) {
                result.add(country);
            }
        }
        return result;
    }


    static List<Long> populationOfCountries() {
        List<Long> result = new ArrayList<>(countries.size());
        for (Country country : countries) {
            result.add(country.population);
        }
        return result;
    }

    static List<String> namesOfCountries() {
        List<String> result = new ArrayList<>(countries.size());
        for (Country country : countries) {
            result.add(country.name);
        }
        return result;
    }

    static Long sumPopulationOfAllCountries() {
        long result = 0L;
        for (Country country : countries) {
            result += country.population;
        }
        return result;
    }
}
