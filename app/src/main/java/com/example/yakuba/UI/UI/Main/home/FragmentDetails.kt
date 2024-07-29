package com.example.yakuba.UI.UI.Main.home

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yakuba.R
import com.example.yakuba.Recycle.App
import com.example.yakuba.Recycle.coment.Person
import com.example.yakuba.Recycle.coment.PersonAdapter
import com.example.yakuba.Recycle.coment.PersonRecycle
import com.example.yakuba.Recycle.home.Post
import com.example.yakuba.Recycle.home.PostAdapter
import com.example.yakuba.Recycle.home.PostPagerAdapter
import com.example.yakuba.Recycle.home.PostRecycle
import com.example.yakuba.databinding.FragmentDetailsBinding

class FragmentDetails : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var adapter: PersonAdapter
    private lateinit var postRecycle: PostRecycle
    private lateinit var postAdapter: PostPagerAdapter

    private val personRecycle: PersonRecycle
        get() = (requireActivity().application as App).personService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        rcPerson()
        sentComent()
    }

    private fun sentComent() {
        with(binding) {
            sentComent.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    if (s != null && s.isNotEmpty()) {
                        cardViewForSent.setCardBackgroundColor(Color.RED)
                    } else {
                        cardViewForSent.setCardBackgroundColor(Color.GRAY)
                    }
                }
            })
        }
    }

    private fun rcPerson() {
        val manager = LinearLayoutManager(requireContext())
        adapter = PersonAdapter()
        adapter.data = personRecycle.getPersons()

        with(binding) {
            rcView.layoutManager = manager
            rcView.adapter = adapter

            cardViewForSent.setOnClickListener {
                val coment = sentComent.text.toString()
                if (cardViewForSent.cardBackgroundColor.defaultColor == Color.RED) {
                    val newPerson = Person("Андрей Крутой", description = coment, "только что", R.drawable.rewiwse)
                    adapter.addPerson(newPerson)
                    rcView.scrollToPosition(0)
                    sentComent.text.clear()
                }
            }
        }
    }
}