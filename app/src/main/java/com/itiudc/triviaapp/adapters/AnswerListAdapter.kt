package com.itiudc.triviaapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itiudc.triviaapp.databinding.AnswersListItemBinding
import com.itiudc.triviaapp.models.Answer

class AnswerListAdapter(private val answerList: List<Answer>) : RecyclerView.Adapter<AnswerListAdapter.ViewHolder>() {

    lateinit var onAnswerClick: (Answer) -> Unit

    inner class ViewHolder(private val binding: AnswersListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(answer: Answer){
            binding.textAnswer.text = answer.answerText

            binding.root.setOnClickListener(){
                if(::onAnswerClick.isInitialized){
                    onAnswerClick(answer)
                }else{
                    Log.i("edg", "The onAnswerClick is not initialized")
                }
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = AnswersListItemBinding.inflate(LayoutInflater.from(parent.context))

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(answerList[position])
    }

    override fun getItemCount(): Int {
        return answerList.count()
    }
}