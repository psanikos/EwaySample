package com.simpletech.ewaysample.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simpletech.ewaysample.models.TransactionElement
import com.simpletech.ewaysample.services.LocalData
import java.io.IOException

class MainViewModel:ViewModel() {

    private val _data = MutableLiveData<List<TransactionElement>>(listOf())
    val data = _data
    private val _currentTransaction = MutableLiveData<TransactionElement>()
    val currentTransaction = _currentTransaction

fun initData(context: Context){

        try {
            _data.value = LocalData.getData(context)?.toList() ?: listOf()

        }catch (e:IOException){
            Log.d("LOCALDATA", ("ERROR " + e.message) ?: "")
        }

}
    fun selectTransaction(id:Long,date:String){
        _currentTransaction.value = _data.value?.firstOrNull { it.id == id && it.transactionDate == date}
    }
}