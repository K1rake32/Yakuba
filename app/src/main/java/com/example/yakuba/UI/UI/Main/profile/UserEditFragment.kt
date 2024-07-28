package com.example.yakuba.UI.UI.Main.profile

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.text.Editable
import android.text.SpannableString
import android.text.TextWatcher
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navArgs
import com.example.yakuba.DataModel
import com.example.yakuba.MAIN
import com.example.yakuba.NavigationFragment
import com.example.yakuba.R
import com.example.yakuba.databinding.FragmentUserBinding
import com.example.yakuba.databinding.FragmentUserEditBinding
import com.example.yakuba.databinding.FragmentUserInformationBinding
import org.w3c.dom.Text

class UserEditFragment : Fragment() {

    private lateinit var binding: FragmentUserEditBinding
    private val dataModel: DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserEditBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backUser()
        saveData()
        dialogDelete()
    }

    private fun backUser() {
        with(binding) {
            backIcon.setOnClickListener {
                NavigationFragment.NavigationDataSave(MAIN.navController)
            }
        }
    }

    private fun saveData() {
        with(binding) {
            dataSave.setOnClickListener {
                dataEdit()
                toastSave()
            }
        }
    }

    private fun toastSave() {
        val nameUser = binding.nameEdit.text.toString()

        if (nameUser != "" || nameUser.isNotEmpty()) {
            val inflater: LayoutInflater = requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val layout = inflater.inflate(R.layout.custom_toast_save, null)

            val toast = Toast(requireContext()).apply {
                setGravity(Gravity.BOTTOM, 0, 40)
                duration = Toast.LENGTH_SHORT
                view = layout
                show()
            }
        } else {
            isEmpty()
        }
    }

    private fun dialogDelete() {
        with(binding) {
            deleteProfile.setOnClickListener() {
                val dialogBinding = layoutInflater.inflate(R.layout.delete_dialog_item, null)
                val myDialog = Dialog(requireContext())
                myDialog.setContentView(dialogBinding)
                myDialog.setCancelable(true)
                myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    myDialog.show()

                    val dialogCancel = dialogBinding.findViewById<TextView>(R.id.dialogCancel)
                    val dialogDelete = dialogBinding.findViewById<TextView>(R.id.dialogDelete)

                    dialogCancel.setOnClickListener() {
                        myDialog.dismiss()
                    }

                    dialogDelete.setOnClickListener() {
                        NavigationFragment.NavigationUserDelete2(MAIN.navController)
                        myDialog.dismiss()
                }
            }
        }
    }

    private fun dataEdit() {
        with(binding) {
                val nameUser = nameEdit.text.toString()

                if (nameUser != "" && nameUser.isNotEmpty()) {
                    val spannableString = SpannableString(nameUser)
                    dataModel.name.value = spannableString
                    nameEdit.clearFocus()
                    hideKeyboard()
                } else {
                    nameEdit.clearFocus()
                    hideKeyboard()
                }
        }
    }


    private fun hideKeyboard() {
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    private fun isEmpty() {
        val inflater: LayoutInflater = requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout = inflater.inflate(R.layout.toast_is_empty, null)

        val toast = Toast(requireContext()).apply {
            setGravity(Gravity.BOTTOM, 0, 40)
            duration = Toast.LENGTH_SHORT
            view = layout
            show()
        }
    }

}