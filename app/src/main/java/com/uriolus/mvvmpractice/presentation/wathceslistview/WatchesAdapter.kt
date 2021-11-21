package com.uriolus.mvvmpractice.presentation.wathceslistview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.RecyclerView
import com.uriolus.mvvmpractice.databinding.WatchItemBinding
import com.uriolus.mvvmpractice.domain.model.Watch
import com.uriolus.mvvmpractice.domain.timeutils.Formatter.Companion.formatToSeconds
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class WatchesAdapter(private val context: Context, private val scope: LifecycleCoroutineScope) :
    RecyclerView.Adapter<WatchViewHolder>() {
    private val watches: MutableList<Watch> = mutableListOf()
    fun updateWatches(watches: List<Watch>) {
        this.watches.clear()
        this.watches.addAll(watches)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchViewHolder {
        val binding = WatchItemBinding.inflate(LayoutInflater.from(context))
        return WatchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WatchViewHolder, position: Int) {
        watches[position].let { watch: Watch ->
            scope.launch {
                watch.getTimeFlow()
                    .flowOn(Dispatchers.Main)
                    .collect {
                        holder.binding.watchTime.text = it.formatToSeconds()
                    }
            }
        }
    }

    override fun getItemCount(): Int {
        return watches.size
    }
}