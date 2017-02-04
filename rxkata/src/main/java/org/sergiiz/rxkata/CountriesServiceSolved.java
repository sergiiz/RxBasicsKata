package org.sergiiz.rxkata;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.FutureTask;

import io.reactivex.Observable;
import io.reactivex.Single;

class CountriesServiceSolved implements CountriesService {

    @Override
    public Single<String> countryNameInCapitals(Country country) {
        return Observable.just(country) // solution
                .map(item -> item.name.toUpperCase(Locale.US))
                .onErrorReturn(error -> "[NOT AVAILABLE]")
                .singleOrError();
    }

    public Single<Integer> countCountries(List<Country> countries) {
        return Observable.just(countries) // solution
                .map(items -> items.size())
                .single(0);
    }

    public Observable<Long> listPopulationOfEachCountry(List<Country> countries) {
        return Observable.just(countries) // solution
                .flatMapIterable(countriesList -> countriesList)
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
                .skip(3)
                .take(2);
    }

    @Override
    public Single<Boolean> isPopulationMoreThan1Million(List<Country> countries) {
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
                .defaultIfEmpty("USD (default)");
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
}
