package com.example.numbercomposition.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.numbercomposition.R
import com.example.numbercomposition.databinding.FragmentGameFinishedBinding
import com.example.numbercomposition.domain.entity.GameResult
import com.example.numbercomposition.domain.entity.GameSettings
import com.example.numbercomposition.presentation.GameViewModel
import javax.security.auth.callback.Callback

class GameFinishedFragment : Fragment() {

    private lateinit var gameResults: GameResult

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding = null")

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[GameViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

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
        observeViewModel()
        setupClickListeners()
    }

    private fun setupClickListeners() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    retryGame()
                }
            })
        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
    }

    private fun observeViewModel() {
        with(binding) {
            emojiResult.setImageResource(
                if (gameResults.winner) R.drawable.ic_smile
                else R.drawable.ic_sad
            )
            tvRequiredAnswers.text = String.format(
                getString(R.string.required_score),
                gameResults.gameSettings.minNumOfRightAnswers
            )
            tvScoreAnswers.text = String.format(
                getString(R.string.score_answers),
                gameResults.numOfRightAnswers
            )
            tvRequiredPercentage.text = String.format(
                getString(R.string.required_percentage),
                gameResults.gameSettings.minPercentOfRightAnswers
            )
            tvScorePercentage.text = String.format(
                getString(R.string.score_percentage),
                getPercentOfRightAnswers()
            )
        }
    }

    private fun getPercentOfRightAnswers() = with(gameResults) {
        if (numOfRightAnswers == 0) 0
        else ((numOfRightAnswers / numOfQuestions.toDouble()) * 100).toInt()
    }

    private fun parseArgs() {
        requireArguments().getParcelable<GameResult>(KEY_GAME_SETTINGS)?.let {
            gameResults = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun retryGame() {
        requireActivity().supportFragmentManager.popBackStack(
            GameFragment.NAME,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    companion object {
        private const val KEY_GAME_SETTINGS = "game_settings"
        fun newInstance(gameResult: GameResult): GameFinishedFragment {
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_GAME_SETTINGS, gameResult)
                }
            }
        }
    }
}