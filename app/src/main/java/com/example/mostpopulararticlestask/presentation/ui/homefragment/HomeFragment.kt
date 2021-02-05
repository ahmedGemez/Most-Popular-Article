package com.example.mostpopulararticlestask.presentation.ui.homefragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mostpopulararticlestask.R
import com.example.mostpopulararticlestask.databinding.FragmentHomeFragmentBinding
import com.example.mostpopulararticlestask.domain.models.Article
import com.example.mostpopulararticlestask.presentation.MostPobularApp
import com.example.mostpopulararticlestask.presentation.di.AppComponent
import com.example.mostpopulararticlestask.presentation.di.ViewModelFactory
import javax.inject.Inject


class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MostPopularViewModel
    private lateinit var listAdapter: ArticlesAdapter
    private lateinit var fragmentHomeFragmentBinding: FragmentHomeFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var appComponent: AppComponent = (requireActivity().application as MostPobularApp).getAppComponent()
        appComponent.inject(this)

        (requireActivity()).title = getString(R.string.article)
        viewModel = ViewModelProvider(
                this,
                viewModelFactory
        ).get<MostPopularViewModel>(MostPopularViewModel::class.java)

        fragmentHomeFragmentBinding = FragmentHomeFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }

        viewModel.getMostPopular("7")
        observeOpenArticle()
        observeProgress()
        return fragmentHomeFragmentBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentHomeFragmentBinding.lifecycleOwner = this.viewLifecycleOwner
        setupListAdapter()
    }

    private fun setupListAdapter() {
        if (viewModel != null) {
            listAdapter = ArticlesAdapter(viewModel)
            fragmentHomeFragmentBinding.articleList.adapter = listAdapter
        } else {
            Log.d("FragmentBudgetsBinding", "ViewModel not initialized when attempting to set up adapter.")
        }
    }


    private fun observeOpenArticle() {
        viewModel.openArticleEvent.observe(viewLifecycleOwner, Observer {
            it?.let {
                openArticleDetails(it)
            }
        })
    }

    private fun observeProgress() {
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    fragmentHomeFragmentBinding.progressContainer.visibility = View.VISIBLE
                } else {
                    fragmentHomeFragmentBinding.progressContainer.visibility = View.GONE

                }
            }
        })
    }

    private fun openArticleDetails(article: Article) {

        //comment condetion for ui testing
     //   val currentFragment = NavHostFragment.findNavController(this).currentDestination?.id
      //  if (currentFragment == R.id.homeFragment) {
            val action = HomeFragmentDirections.actionHomeFragmentToArticleDetailsFragment(article);
            findNavController().navigate(action)
      //  }
    }
}