package com.example.android.shoecorner

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.textview.MaterialTextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

lateinit var viewModel: ShoelistingFragmentViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [ShoelistingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoelistingFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        setHasOptionsMenu(true)
        // toolbar = Toolbar(context)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.shoelistmenu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.loginFragment) {
            val navController = requireView().findNavController()
            navController.navigate(R.id.loginFragment)
            return true
        } else {
            return NavigationUI.onNavDestinationSelected(
                item,
                requireView().findNavController()
            ) || super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shoelisting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // var app : Context = this.context!!.applicationContext
        var app : Context = this.requireContext().applicationContext

        val viewModelFactory = ShoelistViewModelFactory(app)
        viewModel = ViewModelProvider(requireActivity(),viewModelFactory).get(ShoelistingFragmentViewModel::class.java)

        var mListView = view.findViewById<ListView>(R.id.shoeListView)


        // observing the shoe list of the view model class
        viewModel.getShoeList().observe(this, Observer {
            val arrayAdapter: ArrayAdapter<*>
            arrayAdapter = ArrayAdapter(requireContext(),
                android.R.layout.simple_expandable_list_item_1, it)
            mListView.adapter = arrayAdapter
        })

        // get the layout for list view header view
        val header: MaterialTextView = layoutInflater.inflate(
            android.R.layout.simple_dropdown_item_1line,
            mListView,
            false
        ) as MaterialTextView

        // set text for header view
        header.text = "\t\tShoe Name\t\t\t\tCompany\t\t\t\t\tSize\t\tShoe Description"

        // make header text bold
        header.setTypeface(header.typeface, Typeface.BOLD)

        // give a background color to header
        header.setBackgroundColor(Color.parseColor("#C19A6B"))

        // set header text color
        header.setTextColor(Color.parseColor("#1B1811"))

        // finally, add the header view to list view
        mListView.addHeaderView(
            header,
            null,
            false // make it not selectable/clickable
        )



        // Action for the floating button
        val fabButton: View = view.findViewById<Button>(R.id.fab)
        fabButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_shoelistingFragment_to_shoedetailFragment)
        )
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ShoelistingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ShoelistingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}