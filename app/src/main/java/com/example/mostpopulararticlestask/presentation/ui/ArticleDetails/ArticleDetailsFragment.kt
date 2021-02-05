package com.example.mostpopulararticlestask.presentation.ui.ArticleDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.mostpopulararticlestask.R
import com.example.mostpopulararticlestask.databinding.FragmentArticleDetailsBinding
import com.example.mostpopulararticlestask.databinding.FragmentHomeFragmentBinding
import com.example.mostpopulararticlestask.presentation.ui.MainActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ArticleDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArticleDetailsFragment : Fragment() {


    private val args: ArticleDetailsFragmentArgs by navArgs()
    private lateinit var fragmentArticleDetailsBinding: FragmentArticleDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (requireActivity()).title = getString(R.string.article_details)
        fragmentArticleDetailsBinding = FragmentArticleDetailsBinding.inflate(inflater, container, false).apply {
            article = args.article
        }

        return fragmentArticleDetailsBinding.root
    }

}