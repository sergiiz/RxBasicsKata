package org.sergiiz.rxkata;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

public class CountriesServiceSolvedTest {

    private CountriesService countriesService;
    private List<Country> allCountries;

    @Rule
    public Timeout globalTimeout = Timeout.seconds(2);

    @Before
    public void setUp() {
        countriesService = new CountriesServiceSolved();
        allCountries = CountriesTestProvider.countries();
    }

    @Test
    public void rx_CountryNameInCapitals() {
        Country testCountry = CountriesTestProvider.countries().get(0);
        String expected = testCountry.name.toUpperCase(Locale.US);
        TestObserver<String> testObserver = countriesService
                .countryNameInCapitals(testCountry)
                .test();
        testObserver.assertNoErrors();
        testObserver.assertValue(expected);
    }

    @Test
    public void rx_CountAmountOfCountries() {
        Integer expected = CountriesTestProvider.countries().size();
        TestObserver<Integer> testObserver = countriesService
                .countCountries(allCountries)
                .test();
        testObserver.assertNoErrors();
        testObserver.assertValue(expected);
    }

    @Test
    public void rx_ListPopulationOfEachCountry() {
        List<Long> expectedResult = CountriesTestProvider.populationOfCountries();
        TestObserver<Long> testObserver = countriesService
                .listPopulationOfEachCountry(allCountries)
                .test();
        testObserver.assertValueSet(expectedResult);
        testObserver.assertNoErrors();
    }

    @Test
    public void rx_ListNameOfEachCountry() {
        List<String> expectedResult = CountriesTestProvider.namesOfCountries();
        TestObserver<String> testObserver = countriesService
                .listNameOfEachCountry(allCountries)
                .test();
        testObserver.assertValueSet(expectedResult);
        testObserver.assertNoErrors();
    }

    @Test
    public void rx_ListOnly3rdAnd4thCountry() {
        List<Country> expectedResult = new ArrayList<>();
        expectedResult.add(allCountries.get(2));
        expectedResult.add(allCountries.get(3));

        TestObserver<Country> testObserver = countriesService
                .listOnly3rdAnd4thCountry(allCountries)
                .test();
        testObserver.assertValueSet(expectedResult);
        testObserver.assertNoErrors();
    }

    @Test
    public void rx_IsAllCountriesPopulationMoreThanOneMillion_Positive() {
        TestObserver<Boolean> testObserver = countriesService
                .isAllCountriesPopulationMoreThanOneMillion(CountriesTestProvider.countriesPopulationMoreThanOneMillion())
                .test();
        testObserver.assertResult(true);
        testObserver.assertNoErrors();
    }

    @Test
    public void rx_IsAllCountriesPopulationMoreThanOneMillion_Negative() {
        TestObserver<Boolean> testObserver = countriesService
                .isAllCountriesPopulationMoreThanOneMillion(allCountries)
                .test();
        testObserver.assertResult(false);
        testObserver.assertNoErrors();
    }

    @Test
    public void rx_ListPopulationMoreThanOneMillion() {
        List<Country> expectedResult = CountriesTestProvider.countriesPopulationMoreThanOneMillion();
        TestObserver<Country> testObserver = countriesService
                .listPopulationMoreThanOneMillion(allCountries)
                .test();
        testObserver.assertValueSet(expectedResult);
        testObserver.assertNoErrors();
    }

    @Test
    public void rx_ListPopulationMoreThanOneMillionWithTimeoutFallbackToEmpty_When_NoTimeout() {
        FutureTask<List<Country>> futureTask = new FutureTask<>(() -> {
            TimeUnit.MILLISECONDS.sleep(100);
            return allCountries;
        });
        new Thread(futureTask).start();
        TestObserver<Country> testObserver = countriesService
                .listPopulationMoreThanOneMillionWithTimeoutFallbackToEmpty(futureTask)
                .test();
        List<Country> expectedResult = CountriesTestProvider.countriesPopulationMoreThanOneMillion();
        testObserver.awaitTerminalEvent();
        testObserver.assertComplete();
        testObserver.assertValueSet(expectedResult);
        testObserver.assertNoErrors();
    }

    @Test
    public void rx_ListPopulationMoreThanOneMillionWithTimeoutFallbackToEmpty_When_Timeout() {
        FutureTask<List<Country>> futureTask = new FutureTask<>(() -> {
            TimeUnit.HOURS.sleep(1);
            return allCountries;
        });
        new Thread(futureTask).start();
        TestObserver<Country> testObserver = countriesService
                .listPopulationMoreThanOneMillionWithTimeoutFallbackToEmpty(futureTask)
                .test();
        testObserver.awaitTerminalEvent();
        testObserver.assertComplete();
        testObserver.assertNoValues();
        testObserver.assertNoErrors();
    }

    @Test
    public void rx_GetCurrencyUsdIfNotFound_When_CountryFound() {
        String countryRequested = "Austria";
        String expectedCurrencyValue = "EUR";
        TestObserver<String> testObserver = countriesService
                .getCurrencyUsdIfNotFound(countryRequested, allCountries)
                .test();
        testObserver.assertResult(expectedCurrencyValue);
        testObserver.assertNoErrors();
    }

    @Test
    public void rx_GetCurrencyUsdIfNotFound_When_CountryNotFound() {
        String countryRequested = "Senegal";
        String expectedCurrencyValue = "USD";
        TestObserver<String> testObserver = countriesService
                .getCurrencyUsdIfNotFound(countryRequested, allCountries)
                .test();
        testObserver.assertResult(expectedCurrencyValue);
        testObserver.assertNoErrors();
    }

    @Test
    public void rx_SumPopulationOfCountries() {
        // hint: use "reduce" operator
        TestObserver<Long> testObserver = countriesService
                .sumPopulationOfCountries(allCountries)
                .test();
        testObserver.assertResult(CountriesTestProvider.sumPopulationOfAllCountries());
        testObserver.assertNoErrors();
    }


    @Test
    public void rx_MapCountriesToNamePopulation() {
        TestObserver<Map<String, Long>> values = countriesService.mapCountriesToNamePopulation(allCountries).test();
        Map<String, Long> expected = new HashMap<>();
        for (Country country : allCountries) {
            expected.put(country.name, country.population);
        }
        values.assertResult(expected);
        values.assertNoErrors();
    }

    @Test
    public void rx_sumPopulationOfCountries() {
        // hint: use "map" operator
        TestObserver<Long> testObserver = countriesService
                .sumPopulationOfCountries(Observable.fromIterable(allCountries), Observable.fromIterable(allCountries))
                .test();
        testObserver.assertResult(CountriesTestProvider.sumPopulationOfAllCountries()
                + CountriesTestProvider.sumPopulationOfAllCountries());
        testObserver.assertNoErrors();
    }

    @Test
    public void rx_areEmittingSameSequences_Positive() {
        // hint: use "sequenceEqual" operator
        TestObserver<Boolean> testObserver = countriesService
                .areEmittingSameSequences(Observable.fromIterable(allCountries), Observable.fromIterable(allCountries))
                .test();
        testObserver.assertResult(true);
        testObserver.assertNoErrors();
    }

    @Test
    public void rx_areEmittingSameSequences_Negative() {
        List<Country> allCountriesDifferentSequence = new ArrayList<>(allCountries);
        Collections.swap(allCountriesDifferentSequence, 0, 1);
        TestObserver<Boolean> testObserver = countriesService
                .areEmittingSameSequences(
                        Observable.fromIterable(allCountries),
                        Observable.fromIterable(allCountriesDifferentSequence))
                .test();
        testObserver.assertResult(false);
        testObserver.assertNoErrors();
    }

    @Test
    public void rx_getAveragePopulationByCurrency() {
        TestObserver<Tuple<String, Long>> testObserver = countriesService
                .getAveragePopulationByCurrency(allCountries)
                .test();
        testObserver.assertValueSet(CountriesTestProvider.averagePopulationByCurrency());
        testObserver.assertNoErrors();
    }
}