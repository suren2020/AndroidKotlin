package com.example.android.shoecorner

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.io.IOException
import java.io.InputStream


class ShoelistViewModelFactory (val appContext: Context): ViewModelProvider.Factory {

    /*     override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return   modelClass.getConstructor(Application::class.java).newInstance(arg)
        }*/
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShoelistingFragmentViewModel::class.java)) {
            return ShoelistingFragmentViewModel(appContext) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}
class ShoelistingFragmentViewModel(appContext: Context): AndroidViewModel(appContext as Application) {

    var shoeList: MutableList<String>
    var string: String = ""

    private val _currentShoeList = MutableLiveData<MutableList<String>>()

    init {
        try {
            val inputStream: InputStream = appContext.assets.open("ShoeData.txt")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            string = String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        shoeList = string.split("\n") as MutableList<String>
        _currentShoeList.value = shoeList
    }

    // getter method for shoe list
    fun getShoeList(): MutableLiveData<MutableList<String>> {
        return _currentShoeList
    }

    fun addShoe(shoeInfo: String) {
        _currentShoeList.value!!.add(shoeInfo)
    }

    override fun onCleared() {
        super.onCleared()
        // Dispose All your Subscriptions to avoid memory leaks
    }
}