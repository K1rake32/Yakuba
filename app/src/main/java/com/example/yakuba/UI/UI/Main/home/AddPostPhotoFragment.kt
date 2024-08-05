package com.example.yakuba.UI.UI.Main.home

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.icu.util.Calendar
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isInvisible
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yakuba.DataModel
import com.example.yakuba.MAIN
import com.example.yakuba.NavigationFragment
import com.example.yakuba.R
import com.example.yakuba.Recycle.App
import com.example.yakuba.Recycle.addPost.AddPost
import com.example.yakuba.Recycle.addPost.AddPostAdapter
import com.example.yakuba.Recycle.addPost.AddPostRecycle
import com.example.yakuba.Recycle.home.Post
import com.example.yakuba.Recycle.home.PostAdapter
import com.example.yakuba.databinding.FragmentAddPostPhotoBinding
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddPostPhotoFragment : Fragment() {

    private lateinit var binding: FragmentAddPostPhotoBinding
    private lateinit var adapter: AddPostAdapter
    private lateinit var adapterCreate: PostAdapter
    private lateinit var dataModel: DataModel


    private val addPostRecycle: AddPostRecycle
        get() = (requireActivity().application as App).addPostService

    private val selectImagesLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val selectedPosts = mutableListOf<AddPost>()

            data?.let {
                val clipData = it.clipData
                if (clipData != null) {
                    for (i in 0 until clipData.itemCount) {
                        val uri: Uri = clipData.getItemAt(i).uri
                        selectedPosts.add(AddPost(uri.toString()))
                    }
                } else {
                    val uri: Uri? = it.data
                    uri?.let {
                        selectedPosts.add(AddPost(it.toString()))
                    }
                }
            }
            adapter.addPosts(selectedPosts)
            updateText()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddPostPhotoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataModel = ViewModelProvider(requireActivity()).get(DataModel::class.java)

        rcAddPost()
        setCurrentTime()
        currentTime()
        initUi()
    }

    private fun rcAddPost() {
        val manager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        adapter = AddPostAdapter(selectImagesLauncher)
        adapter.data = addPostRecycle.getAddPost()
        adapterCreate = PostAdapter()

        with(binding) {
            rcAddPost.layoutManager = manager
            rcAddPost.adapter = adapter

            updateText()
        }
    }

    private fun setCurrentTime() {
        with(binding) {
            val calendar = Calendar.getInstance()

            val currentTime = calendar.time

            val timeFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())

            val formatedTime = timeFormat.format(currentTime)

            realTime.setText(formatedTime)
        }
    }

    private fun currentTime() {
        with(binding) {

            checkBoxTime.setOnCheckedChangeListener { _, isChecked ->
                if (checkBoxTime.isChecked) {
                    realTime.visibility = View.VISIBLE
                    textTime.visibility = View.VISIBLE
                } else {
                    realTime.visibility = View.INVISIBLE
                    textTime.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun initUi() {
        val textWatcher = object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                updateText()
            }
        }

        with(binding) {
            namePost.addTextChangedListener(textWatcher)
            descriptionPost.addTextChangedListener(textWatcher)
        }

    }

    private fun updateText() {
        with(binding) {

            val itemCount = adapter.itemCount
            if (namePost.text.isNotEmpty() && descriptionPost.text.isNotEmpty() && itemCount > 3) {
                createText.setTextColor(Color.RED)
                createText.isClickable = true
            } else {
                createText.setTextColor(Color.GRAY)
                createText.isClickable = false
            }

            navigateCreate()

        }

    }

    public fun navigateCreate() {
        with(binding) {

            val currentColor = createText.currentTextColor

            val imagesWithoutFirst = adapter.getImagesWithoutFirst()

            if (currentColor == Color.RED) {
                createText.setOnClickListener {

                    val textTitlePost = namePost.text.toString()
                    val textDescriptionPost = descriptionPost.text.toString()

                    val viewModel = ViewModelProvider(requireActivity()).get(DataModel::class.java)

                    val newPost = Post(
                        "Только что",
                        title = textTitlePost,
                        description = textDescriptionPost,
                        "0",
                        "0",
                        ImageId = imagesWithoutFirst
                    )

                    viewModel.addPost(newPost)


                    NavigationFragment.NavigationCreatPostBack(MAIN.navController)
                    toastCreate()
                    adapter.keepOnlyOneItem()
                }
            }

            backIcon.setOnClickListener() {
                NavigationFragment.NavigationCreatPostBack(MAIN.navController)
            }

        }
    }

    private fun toastCreate() {

        val toast = Toast.makeText(requireContext(), "", Toast.LENGTH_LONG)
        val inflater: LayoutInflater = requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout = inflater.inflate(R.layout.item_create_post_toast, null)

        val timeText = layout.findViewById<TextView>(R.id.textTime2)

        timeText.text = binding.realTime.text

        toast.apply {
            setGravity(Gravity.TOP, 0, 370)
            duration = Toast.LENGTH_LONG
            view = layout
            show()
        }
    }


}