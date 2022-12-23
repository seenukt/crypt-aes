package com.example.android_sdk_starter.dialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import com.example.android_sdk_starter.R

class CryptFragment(
    val plainText: String? = null,
    val cryptText: String? = null,
    val decryptText: String? = null
) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_crypt, container, false)
        val plainTextView = view.findViewById<TextView>(R.id.plain_txt)
        val cryptTextview = view.findViewById<TextView>(R.id.crypt_txt)
        val decryptTextView = view.findViewById<TextView>(R.id.decrypt_txt)

        if (plainText != null) {
            decryptTextView.isVisible = false
            plainTextView.text = " Plain Text : \$plainText"
            cryptTextview.text = " encrypted text $cryptText"
        } else {
            plainTextView.isVisible = false
            cryptTextview.text = " encrypted text $cryptText"
            decryptTextView.text = "Decrypted text : $decryptText"
        }
        return view
    }

}