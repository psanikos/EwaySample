package com.simpletech.ewaysample.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simpletech.ewaysample.helpers.SortingCategories
import com.simpletech.ewaysample.models.TransactionElement
import com.simpletech.ewaysample.services.LocalData
import java.io.IOException

class MainViewModel : ViewModel() {

    private val _data = MutableLiveData<List<TransactionElement>>(listOf())
    val data = _data
    private val _filteredData = MutableLiveData<List<TransactionElement>>(listOf())
    val filteredData = _filteredData
    private val _currentTransaction = MutableLiveData<TransactionElement>()
    val currentTransaction = _currentTransaction
    private val _orderFilter = MutableLiveData<SortingCategories>(SortingCategories.RECENT)
    val orderFilter = _orderFilter
    private val _concession = MutableLiveData<String>()
    val concession = _concession
    private val _tollStations = MutableLiveData<List<String>>(listOf())
    val tollStations = _tollStations
    fun initData(context: Context) {

        try {
            _data.value = LocalData.getData(context)?.toList() ?: listOf()
            _filteredData.value = _data.value?.sortedBy {
                it.transactionDate
            }?.reversed()
        } catch (e: IOException) {
            Log.d("LOCALDATA", ("ERROR " + e.message))
        }
    }
    fun selectTransaction(id: Long, date: String) {
        _currentTransaction.value = _data.value?.firstOrNull { it.id == id && it.transactionDate == date }
    }
    fun sortBy(sort: SortingCategories) {
        _orderFilter.value = sort
        _filteredData.value = when (sort) {
            SortingCategories.RECENT -> _filteredData.value?.sortedBy {
                it.transactionDate
            }?.reversed()
            SortingCategories.LOW_HIGH -> _filteredData.value?.sortedBy {
                it.totalAmount
            }
            SortingCategories.HIGH_LOW -> _filteredData.value?.sortedBy {
                it.totalAmount
            }?.reversed()
        }
    }
    fun filter() {
        if (!_concession.value.isNullOrEmpty()) {
            _filteredData.value = _data.value?.filter {
                it.concession == _concession.value
            }
        }
        if (!_tollStations.value.isNullOrEmpty()) {
            _filteredData.value = _data.value?.filter {
                _tollStations.value!!.contains(it.tollStation)
            }
        }
        _filteredData.value = when (_orderFilter.value) {
            SortingCategories.RECENT -> _filteredData.value?.sortedBy {
                it.transactionDate
            }?.reversed()
            SortingCategories.LOW_HIGH -> _filteredData.value?.sortedBy {
                it.totalAmount
            }
            SortingCategories.HIGH_LOW -> _filteredData.value?.sortedBy {
                it.totalAmount
            }?.reversed()

            else -> _filteredData.value ?: listOf()
        }
    }

    fun selectConcession(concession: String) {
        _concession.value = concession
    }
    fun changeTollStations(station: String, remove: Boolean) {
        if (remove) {
            _tollStations.value = _tollStations.value?.minus(station)
        } else {
            _tollStations.value = _tollStations.value?.plus(station)
        }
    }

    fun clearFilters() {
        _concession.value = ""
        _tollStations.value = listOf()
        _filteredData.value = when (_orderFilter.value) {
            SortingCategories.RECENT -> _data.value?.sortedBy {
                it.transactionDate
            }?.reversed()
            SortingCategories.LOW_HIGH -> _data.value?.sortedBy {
                it.totalAmount
            }
            SortingCategories.HIGH_LOW -> _data.value?.sortedBy {
                it.totalAmount
            }?.reversed()

            else -> _data.value ?: listOf()
        }
    }
}
