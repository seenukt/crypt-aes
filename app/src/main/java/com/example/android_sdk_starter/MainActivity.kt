package com.example.android_sdk_starter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.android_sdk_starter.dialog.CryptFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

class MainActivity : AppCompatActivity(), KoinComponent {
    private val viewModel: MainViewModel by viewModel()
    private val dataToEncrypt: String = " M2P Data "
    private var chipperText: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1 = findViewById<Button>(R.id.btn_crypt)
        val button2 = findViewById<Button>(R.id.btn_decrypt)

        button1.setOnClickListener {
            viewModel.encryptData("1234567890123456", dataToEncrypt)
        }
        button2.setOnClickListener {
            if (chipperText != null) {
                viewModel.decryptData("1234567890123456", chipperText!!)
            }
        }


        viewModel.cryptData.observe(this) { data ->
            data?.let {
                chipperText = data as String
                CryptFragment(plainText = dataToEncrypt, cryptText = data).show(
                    supportFragmentManager,
                    ""
                )
            }
        }
        viewModel.decryptData.observe(this) { data ->
            data?.let {
                CryptFragment(cryptText = chipperText , decryptText = data).show(
                    supportFragmentManager,
                    ""
                )
            }
        }

    }
}