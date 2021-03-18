package com.example.converterapp2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.converterapp2.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentB.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentB : Fragment(){
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    var txt : String?= ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_b, container, false)

        txt = arguments?.getString("Text")

        val textView = view.findViewById<TextView>(R.id.convertionType)

        textView.text = "${txt} Convertion"

        val spinner1 = view.findViewById<Spinner>(R.id.convert1)
        val spinner2 = view.findViewById<Spinner>(R.id.convert2)
        if (txt == "Document") {
            val options = listOf(".doc", ".pdf")
            spinner1.adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, options)
            spinner2.adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, options)
        } else if (txt == "Image") {
            val options = listOf(".jpg", ".png", ".svg")
            spinner1.adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, options)
            spinner2.adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, options)
        } else if (txt == "Audio") {
            val options = listOf(".mp3", ".m3u", ".wav")
            spinner1.adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, options)
            spinner2.adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, options)
        } else if (txt == "Video") {
            val options = listOf(".mp4", ".mkv")
            spinner1.adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, options)
            spinner2.adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, options)
        }

        val button = view.findViewById<Button>(R.id.button2)
        val result = view.findViewById<TextView>(R.id.result)
        button.setOnClickListener {
            if (spinner1.selectedItem.toString() == spinner2.selectedItem.toString()) {
                Toast.makeText(requireContext(), "Same Type!", Toast.LENGTH_SHORT).show()
            } else {
                result.text = "Convert ${spinner1.selectedItem.toString()} to ${spinner2.selectedItem.toString()}"
            }
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentB.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentB().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}