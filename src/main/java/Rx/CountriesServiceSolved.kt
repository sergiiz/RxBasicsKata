package Rx
import Rx.CountriesService
import java.util.concurrent.FutureTask

import io.reactivex.Observable
import io.reactivex.Single

class CountriesServiceSolved : CountriesService {

    override fun countryNameInCapitals(country: Country): Single<String> {
        val capName = country.name.toUpperCase()
        return Single.just(capName)
    }

     override fun countCountries(countries: List<Country>): Single<Int> {
        val count = countries.count()
        return Single.just(count)
    }

    override fun listPopulationOfEachCountry(countries: List<Country>): Observable<Long> {
        val obsCountries = Observable.fromIterable(countries)
        return  obsCountries.map { it.population }
    }

    override fun listNameOfEachCountry(countries: List<Country>): Observable<String> {
        val obsCountries = Observable.fromIterable(countries)
        return obsCountries.map { it.name }
    }

    override fun listOnly3rdAnd4thCountry(countries: List<Country>): Observable<Country> {
        val thirdandforth = listOf<Country>(countries[2],countries[3])
        return Observable.fromIterable(thirdandforth)
    }

//    override fun isAllCountriesPopulationMoreThanOneMillion(countries: List<Country>): Single<Boolean>? {
//        return null // put your solution here
//    }
//
//    override fun listPopulationMoreThanOneMillion(countries: List<Country>): Observable<Country>? {
//        return null // put your solution here
//    }
//
//    override fun listPopulationMoreThanOneMillionWithTimeoutFallbackToEmpty(countriesFromNetwork: FutureTask<List<Country>>): Observable<Country> {
//        return null // put your solution here
//    }
//
//    override fun getCurrencyUsdIfNotFound(countryName: String, countries: List<Country>): Observable<String>? {
//        return null // put your solution here
//    }
//
//    override fun sumPopulationOfCountries(countries: List<Country>): Observable<Long>? {
//        return null // put your solution here
//    }
//
//    override fun mapCountriesToNamePopulation(countries: List<Country>): Single<Map<String, Long>>? {
//        return null // put your solution here
//    }
//
//    override fun sumPopulationOfCountries(countryObservable1: Observable<Country>,
//                                          countryObservable2: Observable<Country>): Observable<Long> {
//        return null // put your solution here
//    }
//
//    override fun areEmittingSameSequences(countryObservable1: Observable<Country>,
//                                          countryObservable2: Observable<Country>): Single<Boolean> {
//        return null // put your solution here
//    }
}
