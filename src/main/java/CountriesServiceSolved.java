import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Predicate;

import java.util.List;
import java.util.Map;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

class CountriesServiceSolved implements CountriesService {

    public static final int A_MILLION = 1_000_000;
    public static final Predicate<Country> countryWithPopulationMoreThanAMillion = country -> country.population > A_MILLION;

    @Override
    public Single<String> countryNameInCapitals(Country country) {
        return Single.just(country)
          .map(Country::getName)
          .map(String::toUpperCase);
    }

    public Single<Integer> countCountries(List<Country> countries) {
        return Single.just(countries)
          .map(List::size);
    }

    public Observable<Long> listPopulationOfEachCountry(List<Country> countries) {
        return Observable.fromIterable(countries)
          .map(Country::getPopulation);
    }

    @Override
    public Observable<String> listNameOfEachCountry(List<Country> countries) {
        return Observable.fromIterable(countries)
          .map(Country::getName);
    }

    @Override
    public Observable<Country> listOnly3rdAnd4thCountry(List<Country> countries) {
        return Observable.fromIterable(countries)
          .skip(2)
          .take(2);
    }

    @Override
    public Single<Boolean> isAllCountriesPopulationMoreThanOneMillion(List<Country> countries) {
        return Flowable.fromIterable(countries)
          .all(countryWithPopulationMoreThanAMillion); // put your solution here
    }

    @Override
    public Observable<Country> listPopulationMoreThanOneMillion(List<Country> countries) {
        return Observable.fromIterable(countries)
          .filter(countryWithPopulationMoreThanAMillion); // put your solution here
    }

    @Override
    public Observable<Country> listPopulationMoreThanOneMillionWithTimeoutFallbackToEmpty(final FutureTask<List<Country>> countriesFromNetwork) {
        return Observable.fromFuture(countriesFromNetwork)
          .flatMap(Observable::fromIterable)
          .filter(countryWithPopulationMoreThanAMillion)
          .timeout(10, TimeUnit.SECONDS, Observable.empty());
    }

    @Override
    public Observable<String> getCurrencyUsdIfNotFound(String countryName, List<Country> countries) {
        return Observable.fromIterable(countries)
          .filter(country -> country.getName().equals(countryName))
          .map(Country::getCurrency)
          .defaultIfEmpty("USD");
    }

    @Override
    public Observable<Long> sumPopulationOfCountries(List<Country> countries) {
        return Observable.fromIterable(countries)
          .map(Country::getPopulation)
          .reduce(0L, Long::sum)
          .toObservable();
    }

    @Override
    public Single<Map<String, Long>> mapCountriesToNamePopulation(List<Country> countries) {
        return Observable.fromIterable(countries)
          .toMap(
            Country::getName,
            Country::getPopulation
          );
    }

    @Override
    public Observable<Long> sumPopulationOfCountries(Observable<Country> countryObservable1,
                                                     Observable<Country> countryObservable2) {
        return countryObservable1.concatWith(countryObservable2)
          .collect(Collectors.summingLong(Country::getPopulation))
          .toObservable();
    }

    @Override
    public Single<Boolean> areEmittingSameSequences(Observable<Country> countryObservable1,
                                                    Observable<Country> countryObservable2) {
        return Observable.sequenceEqual(countryObservable1, countryObservable2);
    }
}
