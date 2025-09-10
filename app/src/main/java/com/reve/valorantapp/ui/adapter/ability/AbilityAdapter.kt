package com.reve.valorantapp.ui.adapter.ability

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.reve.valorantapp.data.model.Ability
import com.reve.valorantapp.databinding.AgentAbilityCardDesignBinding

class AbilityAdapter(
    var mContext: Context,
    var abilities: List<Ability>
) : RecyclerView.Adapter<AbilityAdapter.AbilityCardDesignHolder>() {
    inner class AbilityCardDesignHolder(var binding: AgentAbilityCardDesignBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilityCardDesignHolder {
        val binding =
            AgentAbilityCardDesignBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return AbilityCardDesignHolder(binding)
    }

    override fun getItemCount(): Int {
        return abilities.size
    }

    override fun onBindViewHolder(holder: AbilityCardDesignHolder, position: Int) {
        val ability = abilities.get(position)
        val binding = holder.binding

        Glide.with(mContext).load(ability.displayIcon).into(binding.agentAbilityImage)
        binding.abilityNameText.text = ability.displayName
        binding.agentAbilityDescriptionText.text = ability.description
    }
}