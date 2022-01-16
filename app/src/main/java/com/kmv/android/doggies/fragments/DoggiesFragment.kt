package com.kmv.android.doggies.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.kmv.android.doggies.R
import com.kmv.android.doggies.adapter.DoggiesAdapter
import com.kmv.android.doggies.databinding.FragmentDoggiesBinding
import com.kmv.android.doggies.retrofit.Common
import dmax.dialog.SpotsDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DoggiesFragment : Fragment() {

    private lateinit var binding: FragmentDoggiesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_doggies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDoggiesBinding.bind(view)
        binding.userText.text = arguments?.getString(USER_NAME) ?: ""
        binding.recycleView.layoutManager = LinearLayoutManager(activity)
        loadDoggies()
    }

    private fun loadDoggies() {
        val dialog = SpotsDialog.Builder().setCancelable(true).setContext(activity).build()
        dialog.show()
        Common.retrofitService
                .getShibesList(15)
                .enqueue(object : Callback<List<String>> {
            override fun onResponse(
                    call: Call<List<String>>,
                    response: Response<List<String>>
            ) {
                binding.recycleView.adapter = DoggiesAdapter(response.body() as List<String>)
                dialog.dismiss()
            }
            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                dialog.dismiss()
                Toast.makeText(activity, R.string.failed_load, Toast.LENGTH_SHORT ).show()
            }
        })
    }

    companion object {
        private const val USER_NAME = "USER_NAME"
        fun newInstance(userName: String) = DoggiesFragment().apply {
            arguments = bundleOf(USER_NAME to userName)
        }
    }
}