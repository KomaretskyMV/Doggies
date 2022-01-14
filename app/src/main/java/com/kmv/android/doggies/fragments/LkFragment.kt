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
import com.kmv.android.doggies.adapter.MyRecyclerAdapter
import com.kmv.android.doggies.databinding.FragmentLkBinding
import com.kmv.android.doggies.retrofit.Common
import dmax.dialog.SpotsDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LkFragment : Fragment() {
    private lateinit var lkBinding: FragmentLkBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lk, null)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lkBinding = FragmentLkBinding.bind(view)
        lkBinding.userText.text = arguments?.getString(AF) ?: ""
        lkBinding.recycleView.layoutManager = LinearLayoutManager(activity)
        loadDoggies()
    }
    private fun loadDoggies() {
        val dialog = SpotsDialog.Builder().setCancelable(true).setContext(activity).build()
        dialog.show()
        Common.retrofitService.getShibesList(15).enqueue(object : Callback<List<String>> {
            override fun onResponse(
                    call: Call<List<String>>,
                    response: Response<List<String>>
            ) {
                lkBinding.recycleView.adapter = MyRecyclerAdapter(response.body() as List<String>)
                dialog.dismiss()
            }
            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                dialog.dismiss()
                Toast.makeText(activity, R.string.failed_load, Toast.LENGTH_SHORT ).show()
            }

        })
    }
    companion object {
        private const val AF = "af"
        fun newInstance(userName: String) = LkFragment().apply {
            arguments = bundleOf(AF to userName)
        }
    }

}