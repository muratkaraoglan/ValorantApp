package com.reve.valorantapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.reve.valorantapp.databinding.AgentCardDesignBinding
import com.reve.valorantapp.domain.model.AgentUIModel

class AgentAdapter(
    var mContext: Context,
    var agents: List<AgentUIModel>,
    private val onItemClick: OnAgentClickListener
) : RecyclerView.Adapter<AgentAdapter.AgentCardDesignHolder>() {

    inner class AgentCardDesignHolder(var binding: AgentCardDesignBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentCardDesignHolder {
        val binding = AgentCardDesignBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return AgentCardDesignHolder(binding)
    }

    override fun getItemCount(): Int {
        return agents.size
    }

    override fun onBindViewHolder(holder: AgentCardDesignHolder, position: Int) {
        val agent = agents.get(position)
        val binding = holder.binding

        val imageUrl = agent.displayIcon
        Glide.with(mContext).load(imageUrl).into(binding.cardAgentImageView)

        binding.cardAgentNameTextView.text = agent.displayName
        binding.cardAgentRoleTextView.text = agent.role?.displayName

        binding.agentCardView.setOnClickListener {
          onItemClick.onAgentClick(agent)
        }
    }
}