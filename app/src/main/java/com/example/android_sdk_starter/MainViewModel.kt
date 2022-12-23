package com.example.android_sdk_starter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sdk_starter.M2pInit

class MainViewModel : ViewModel() {

    private val _cryptData = MutableLiveData<String?>()
    val cryptData: LiveData<String?> get() = _cryptData

    private val _decryptData = MutableLiveData<String?>()
    val decryptData: LiveData<String?> get() = _decryptData


    fun encryptData(key: String, dataToEncrypt: String) {
        _cryptData.postValue(M2pInit.encrypt(key = key, txtToEncrypt = dataToEncrypt))
    }

    fun decryptData(key: String, dataToDecrypt: String) {
        _decryptData.postValue(M2pInit.decrypt(key = key, cipherText = dataToDecrypt))
    }

}