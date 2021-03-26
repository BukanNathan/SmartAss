package com.example.converterapp2.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.converterapp2.*
import kotlinx.android.synthetic.main.fragment_b.*

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

    val convertReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            var persen = intent?.getIntExtra(EXTRA_PERSEN, 0)
            var finish = intent?.getBooleanExtra(EXTRA_FINISH, true)

            //inisialisasi komponen yang digunakan
            val spinner1 = view?.findViewById<Spinner>(R.id.convert1)
            val spinner2 = view?.findViewById<Spinner>(R.id.convert2)
            val result = view?.findViewById<TextView>(R.id.result)
            val type1 = spinner1?.selectedItem.toString()
            val type2 = spinner2?.selectedItem.toString()

            //operator Elvis, bernilai 0 jika false
            progressBar.progress = persen ?: 0
            if (finish!!) {
                //aktifkan kembali spinner yang telah di-disable
                spinner1!!.isEnabled = true
                spinner2!!.isEnabled = true

                Toast.makeText(requireContext(), "Finished!", Toast.LENGTH_SHORT).show()
                result?.text = "Convert ${type1} to ${type2}"
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    //Inisialisasi var txt yang akan digunakan untuk menampung interface data
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
        //dikarenakan fragment, perlu digunakan requireContext() dan requireActivity()
        val myService = Intent(requireContext(), ConvertService::class.java)
        button.setOnClickListener {
            if (spinner1.selectedItem.toString() == spinner2.selectedItem.toString()) {
                Toast.makeText(requireContext(), "Same Type!", Toast.LENGTH_SHORT).show()
            }
            else {
                //spinner di-disable saat button di click untuk mencegah perubahan data
                spinner1.isEnabled = false
                spinner2.isEnabled = false
                ConvertService.enqueueWork(requireContext(), myService)
            }
        }
        val filterConvert = IntentFilter(ACTION_CONVERT)
        requireActivity().registerReceiver(convertReceiver, filterConvert)

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        requireActivity().unregisterReceiver(convertReceiver)
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