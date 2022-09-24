package com.example.android.shoecorner

import android.app.Application
import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController

class ShoedetailViewModelFactory (val appContext: Context): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShoedetailFragmentViewModel::class.java)) {
            return ShoedetailFragmentViewModel(appContext) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}

class ShoedetailFragmentViewModel (appContext: Context): AndroidViewModel(appContext as Application), Observable {

    var context = appContext

    @Bindable
    var shoeName = MutableLiveData<String>("")

    @Bindable
    var shoeCompany = MutableLiveData<String>("")

    @Bindable
    var shoeSize= MutableLiveData<String>("")

    @Bindable
    var shoeDescription = MutableLiveData<String>("")

    fun saveData() {

        if (shoeName.value.toString() == "" || shoeCompany.value.toString() == "" ||
            shoeSize.value.toString() == "" || shoeDescription.value.toString() == "") {
            Toast.makeText(context, "Enter all the shoe details to save", Toast.LENGTH_SHORT).show()
        }
        else {
            var message = shoeName.value.toString()  + "\t\t" + shoeCompany.value.toString() +
                    "\t\t" + shoeSize.value.toString() + "\t" + shoeDescription.value.toString()
            viewModel.addShoe(message)
           shoeDetailBinding.cancelButton.findNavController().navigate(R.id.action_shoedetailFragment_to_shoelistingFragment)

        }
    }
    fun cancel() {
        shoeDetailBinding.cancelButton.findNavController().navigate(R.id.action_shoedetailFragment_to_shoelistingFragment)
    }
    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

}