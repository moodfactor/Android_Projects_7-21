package com.mood.try1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mood.try1.R
import com.mood.try1.databinding.FragmentMarketBinding
import com.mood.try1.viewModels.MarketViewModel
import timber.log.Timber


class MarketFragment : Fragment() {

    private var viewModelAdapter: MarketAdapter? = MarketAdapter()

    private val viewModel: MarketViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access viewModel after onActivityCreated"
        }
        ViewModelProvider(this, MarketViewModel.Factory(activity.application))
            .get(MarketViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentMarketBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_market,
            container,
            false
        )

        Timber.e(this::class.java.name, "Hello from onCreateView function")

        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel


        binding.root.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(context)
            Timber.d(this::class.java.name, "binding.root")
            adapter = viewModelAdapter
        }

        // Observer for the network error.
        viewModel.eventNetworkError.observe(
            viewLifecycleOwner,
            Observer<Boolean> { isNetworkError ->
                if (isNetworkError) onNetworkError()
            })

        return binding.root
    }

    /**
     * Method for displaying a Toast error message for network errors.
     */
    private fun onNetworkError() {
        if (!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Timber.d("Fragment Created")

        viewModel.marketList.observe(viewLifecycleOwner, Observer { markets ->
            markets?.let {
                viewModelAdapter?.markets = markets
            }
        })

    }

}

