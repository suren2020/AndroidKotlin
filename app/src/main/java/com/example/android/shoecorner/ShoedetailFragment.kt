package com.example.android.shoecorner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.findFragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ShoedetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoedetailFragment : Fragment() {
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shoedetail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Action for the cancel button
        val cancelButton = view.findViewById<Button>(R.id.cancelButton)
        cancelButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_shoedetailFragment_to_shoelistingFragment)
        )

        val saveButton = view.findViewById<Button>(R.id.saveButton)

        saveButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val shoedetailFrag = view?.findFragment<Fragment>()
                val shoedetailFragView: View? = shoedetailFrag?.view
                val shoeNameEditText = shoedetailFragView?.findViewById<EditText>(R.id.shoename)
                val shoeCompanyEditText = shoedetailFragView?.findViewById<EditText>(R.id.company)
                val shoeSizeEditText = shoedetailFragView?.findViewById<EditText>(R.id.shoesize)
                val shoeDescriptionEditText = shoedetailFragView?.findViewById<EditText>(R.id.shoedescription)
                if (shoeNameEditText?.text.toString() == "" || shoeCompanyEditText?.text.toString() == "" ||
                    shoeSizeEditText?.text.toString() == "" || shoeDescriptionEditText?.text.toString() == "") {
                    Toast.makeText(activity, "Enter all the shoe details to save", Toast.LENGTH_SHORT).show()
                }
                else {
                    var message = shoeNameEditText?.text.toString() + "\t\t" + shoeCompanyEditText?.text.toString() +
                            "\t\t" + shoeSizeEditText?.text.toString() + "\t" + shoeDescriptionEditText?.text.toString()
                    addShoeData(message)
                    view?.findNavController()?.navigate(R.id.action_shoedetailFragment_to_shoelistingFragment)
                }
            }
        })
    }

    fun addShoeData(message: String) {
        viewModel.addShoe(message)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ShoedetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ShoedetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}