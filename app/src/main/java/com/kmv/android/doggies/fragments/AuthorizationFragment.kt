package com.kmv.android.doggies.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.kmv.android.doggies.R

class AuthorizationFragment : Fragment() {
    private lateinit var username : EditText
    private lateinit var password : EditText
    private lateinit var login : Button
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_authorization, null)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        username = view.findViewById(R.id.edit_user) as EditText
        password = view.findViewById(R.id.edit_password) as EditText
        login = view.findViewById(R.id.button_login) as Button
        login.setOnClickListener {
            if (username.text.isNotEmpty() && password.text.length in 1..16) {
                Toast.makeText(activity, R.string.success, Toast.LENGTH_SHORT).show()
                parentFragmentManager.beginTransaction()
                        .replace(R.id.container, LkFragment.newInstance(username.text.toString()))
                        .addToBackStack(null)
                        .commit()
            } else {
                Toast.makeText(activity, R.string.failed, Toast.LENGTH_SHORT ).show()
            }
        }
    }
}

