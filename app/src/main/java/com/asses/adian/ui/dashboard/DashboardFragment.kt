package com.asses.adian.ui.dashboard

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.asses.adian.databinding.FragmentDashboardBinding
import com.asses.adian.helpers.RecyclerAdapter
import com.asses.adian.model.Account
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*


class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null
    // onDestroyView.
    private val binding get() = _binding!!
    private var mRecyclerView: RecyclerView? = null
    val viewItems = ArrayList<Account>()

    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var layoutManager: LayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        binding.addCash.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Add cash button clicked",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.checkOut.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Cash out clicked",
                Toast.LENGTH_SHORT
            ).show()
        }
        mRecyclerView = binding.recycleview
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        // in content do not change the layout size of the RecyclerView
        mRecyclerView!!.setHasFixedSize(true)

        // use a linear layout manager
        layoutManager = LinearLayoutManager(requireActivity())
        mRecyclerView!!.layoutManager = layoutManager

        // specify an adapter (see also next example)

        // specify an adapter (see also next example)
        mAdapter = RecyclerAdapter(requireActivity(), viewItems)
        mRecyclerView!!.adapter = mAdapter

        addItemsFromJSON()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun addItemsFromJSON() {
        try {
            val jsonDataString = readJSONDataFromFile()
            val jsonArray = JSONArray(jsonDataString)
            for (i in 0 until jsonArray.length()) {
                val itemObj = jsonArray.getJSONObject(i)
                val name = itemObj.getString("name")
                val date = itemObj.getString("date")
                val amount = itemObj.getString("amount")
                val account = itemObj.getString("account")

                val maccount = Account(name, date, amount, account)
                viewItems.add(maccount)
            }
        } catch (e: JSONException) {
            Log.d(TAG, "addItemsFromJSON: ", e)
        } catch (e: IOException) {
            Log.d(TAG, "addItemsFromJSON: ", e)
        }
    }

    @Throws(IOException::class)
    private fun readJSONDataFromFile(): String {
        var inputStream: InputStream? = null
        val builder = StringBuilder()
        try {
            var jsonString: String? = null
            inputStream = resources.openRawResource(com.asses.adian.R.raw.json)
            val bufferedReader = BufferedReader(
                InputStreamReader(inputStream, "UTF-8")
            )
            while (bufferedReader.readLine().also { jsonString = it } != null) {
                builder.append(jsonString)
            }
        } finally {
            inputStream?.close()
        }
        return String(builder)
    }

}