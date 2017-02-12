package org.sergiiz.rxkata;

import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.FutureTask;

class CountriesServiceSolved implements CountriesService {

    @Override
    public Single<String> countryNameInCapitals(Country country) {
        return Single.just(country.name.toUpperCase(Locale.US)); // solution
    }

    public Single<Integer> countCountries(List<Country> countries) {
        return Single.just(countries.size()); // solution
    }

    public Observable<Long> listPopulationOfEachCountry(List<Country> countries) {
        return Observable.fromIterable(countries) // solution
                .map(country -> country.population);
    }

    @Override
    public Observable<String> listNameOfEachCountry(List<Country> countries) {
        return Observable.fromIterable(countries) // solution
                .map(country -> country.name);
    }

    @Override
    public Observable<Country> listOnly3rdAnd4thCountry(List<Country> countries) {
        return Observable.fromIterable(countries) // solution
                .skip(2)
                .take(2);
    }

    @Override
    public Single<Boolean> isAllCountriesPopulationMoreThanOneMillion(List<Country> countries) {
        return Observable.fromIterable(countries)  // solution
                .all(country -> country.population > 1000000);
    }


    @Override
    public Observable<Country> listPopulationMoreThanOneMillion(List<Country> countries) {
        return Observable.fromIterable(countries)  // solution
                .filter(country -> country.population > 1000000);
    }

    @Override
    public Observable<Country> listPopulationMoreThanOneMillion(FutureTask<List<Country>> countriesFromNetwork) {
        return Observable.fromFuture(countriesFromNetwork)  // solution
                .flatMap(countriesList -> Observable.fromIterable(countriesList))
                .filter(country -> country.population > 1000000);
    }

    @Override
    public Observable<String> getCurrencyUsdIfNotFound(String countryName, List<Country> countries) {
        return Observable.fromIterable(countries) // solution
                .filter(country -> country.name.equals(countryName))
                .map(country -> country.currency)
                .defaultIfEmpty("USD");
    }

    @Override
    public Observable<Long> sumPopulationOfCountries(List<Country> countries) {
        return Observable.fromIterable(countries)  // solution
                .map(country -> country.population)
                .reduce((i1, i2) -> i1 + i2)
                .toObservable();
    }

    @Override
    public Single<Map<String, Long>> mapCountriesToNamePopulation(List<Country> countries) {
        return Observable.fromIterable(countries)  // solution
                .toMap(
                        country -> country.name,
                        country -> country.population);
    }

    @Override
    public Observable<Long> sumPopulationOfCountries(Observable<Country> countryObservable1,
                                                               Observable<Country> countryObservable2) {
        return Observable.merge(countryObservable1, countryObservable2)
                .map(country -> country.population)
                .reduce((i1, i2) -> i1 + i2)
                .toObservable();
    }

    @Override
    public Single<Boolean> areEmittingSameItems(Observable<Country> countryObservable1, Observable<Country> countryObservable2) {
        return Observable.sequenceEqual(countryObservable1,countryObservable2);
    }

    @Override
    public Single<Boolean> ifObservableEmitsGivenItem(List<Country> countries, Country country) {
        return Observable.fromIterable(countries)
                .contains(country);
    }
}
