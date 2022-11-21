package com.example.numbercomposition.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.view.View.OnClickListener
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.numbercomposition.R
import com.example.numbercomposition.domain.entity.GameResult

interface OnOptionClickListener {
    fun onOptionClick(option: Int)
}

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_score),
        count
    )
}

@BindingAdapter("scoreAnswers")
fun bindScoreAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        count
    )
}

@BindingAdapter("requiredPercents")
fun bindRequiredPercents(textView: TextView, percent: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        percent
    )
}

@BindingAdapter("scorePercents")
fun bindScorePercents(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.score_percentage),
        getPercentOfRightAnswers(gameResult)
    )
}

@BindingAdapter("resultImage")
fun bindResultImage(imageView: ImageView, isWinner: Boolean) {
    imageView.setImageResource(
        if (isWinner) R.drawable.ic_smile
        else R.drawable.ic_sad
    )
}

@BindingAdapter("enoughCount")
fun bindEnoughCount(textView: TextView, enough: Boolean) {
    textView.setTextColor(getColorByState(textView.context, enough))
}

@BindingAdapter("enoughPercent")
fun bindEnoughPercent(progressBar: ProgressBar, enough: Boolean) {
    val color = getColorByState(progressBar.context ,enough)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

@BindingAdapter("numberAsText")
fun bindNumberAsText(textView: TextView, number: Int) {
    textView.text = number.toString()
}

@BindingAdapter("onOptionClickListener")
fun bindOptionClickListener(textView: TextView, clickListener: OnOptionClickListener) {
    textView.setOnClickListener{ clickListener.onOptionClick(textView.text.toString().toInt()) }
}

private fun getColorByState(context: Context, goodState: Boolean): Int {
    val colorResId =
        if (goodState) android.R.color.holo_green_light
        else android.R.color.holo_red_light
    return ContextCompat.getColor(context, colorResId)
}

private fun getPercentOfRightAnswers(gameResult: GameResult) = with(gameResult) {
    if (numOfRightAnswers == 0) 0
    else ((numOfRightAnswers / numOfQuestions.toDouble()) * 100).toInt()
}