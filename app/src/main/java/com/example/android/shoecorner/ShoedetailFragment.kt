package com.example.android.shoecorner

// import android.R
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
// import com.example.android.shoecorner.BR.shoeDetails
import com.example.android.shoecorner.databinding.FragmentShoedetailBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

lateinit var shoeDetailBinding: FragmentShoedetailBinding
private lateinit var shoedetailViewModel: ShoedetailFragmentViewModel

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

        var app : Context = this.requireContext().applicationContext

        val viewModelFactory = ShoedetailViewModelFactory(app)
        shoedetailViewModel = ViewModelProvider(requireActivity(),viewModelFactory).get(ShoedetailFragmentViewModel::class.java)

        shoeDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoedetail, container, false)

        shoeDetailBinding.cancelButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_shoedetailFragment_to_shoelistingFragment)
        }

        shoeDetailBinding.saveButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_shoedetailFragment_to_shoelistingFragment)
        }

        shoeDetailBinding.viewModel = shoedetailViewModel

        shoeDetailBinding.lifecycleOwner = this

        return shoeDetailBinding.root

        // return inflater.inflate(R.layout.fragment_shoedetail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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