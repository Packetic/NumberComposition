package com.example.numbercomposition.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.numbercomposition.R
import com.example.numbercomposition.databinding.FragmentGameFinishedBinding
import com.example.numbercomposition.domain.entity.GameResult
import com.example.numbercomposition.domain.entity.GameSettings
import com.example.numbercomposition.presentation.GameViewModel
import javax.security.auth.callback.Callback

class GameFinishedFragment : Fragment() {

    private val args by navArgs<GameFinishedFragmentArgs>()

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViews()
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
    }

    private fun observeViews() {
        binding.gameResult = args.gameResults
        with(binding) {
            emojiResult.setImageResource(
                if (args.gameResults.winner) R.drawable.ic_smile
                else R.drawable.ic_sad
            )
//            tvRequiredAnswers.text = String.format(
//                getString(R.string.required_score),
//                args.gameResults.gameSettings.minNumOfRightAnswers
//            )
//            tvScoreAnswers.text = String.format(
//                getString(R.string.score_answers),
//                args.gameResults.numOfRightAnswers
//            )
//            tvRequiredPercentage.text = String.format(
//                getString(R.string.required_percentage),
//                args.gameResults.gameSettings.minPercentOfRightAnswers
//            )
            tvScorePercentage.text = String.format(
                getString(R.string.score_percentage),
                getPercentOfRightAnswers()
            )
        }
    }

    private fun getPercentOfRightAnswers() = with(args.gameResults) {
        if (numOfRightAnswers == 0) 0
        else ((numOfRightAnswers / numOfQuestions.toDouble()) * 100).toInt()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun retryGame() {
        findNavController().popBackStack()
    }
}