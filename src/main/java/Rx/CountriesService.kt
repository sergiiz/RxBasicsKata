package Rx

import Rx.Country
import java.util.concurrent.FutureTask

import io.reactivex.Observable
import io.reactivex.Single

interface CountriesService {

    fun countryNameInCapitals(country: Country): Single<String>

    fun countCountries(countries: List<Country>): Single<Int>

    fun listPopulationOfEachCountry(countries: List<Country>): Observable<Long>

    fun listNameOfEachCountry(countries: List<Country>): Observable<String>
//
//    fun listOnly3rdAnd4thCountry(countries: List<Country>): Observable<Country>
//
//    fun isAllCountriesPopulationMoreThanOneMillion(countries: List<Country>): Single<Boolean>
//
//    fun listPopulationMoreThanOneMillion(countries: List<Country>): Observable<Country>

    /**
     * @param countriesFromNetwork an async task which is sometimes very very slow
     * @return the filtered values from the [FutureTask] or an [Observable.empty] if there are no values within 1 second
     */
//    fun listPopulationMoreThanOneMillionWithTimeoutFallbackToEmpty(countriesFromNetwork: FutureTask<List<Country>>): Observable<Country>
//
//    fun getCurrencyUsdIfNotFound(countryName: String, countries: List<Country>): Observable<String>
//
//    fun sumPopulationOfCountries(countries: List<Country>): Observable<Long>
//
//    fun sumPopulationOfCountries(countryObservable1: Observable<Country>,
//                                 countryObservable2: Observable<Country>): Observable<Long>
//
//    fun mapCountriesToNamePopulation(countries: List<Country>): Single<Map<String, Long>>
//
//    fun areEmittingSameSequences(countryObservable1: Observable<Country>,
//                                 countryObservable2: Observable<Country>): Single<Boolean>
}
