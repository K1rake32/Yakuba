package com.example.yakuba.UI.UI.Main.profile

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.provider.MediaStore
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
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navArgs
import com.example.yakuba.DataModel
import com.example.yakuba.MAIN
import com.example.yakuba.NavigationFragment
import com.example.yakuba.R
import com.example.yakuba.UI.UI.Auth.UserInformationFragment
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
        broadcastUI()
        dialogUI()
    }

    private fun backUser() {
        with(binding) {
            backIcon.setOnClickListener {
                NavigationFragment.NavigationDataSave(MAIN.navController)
                dataEdit()
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
                val sername = userNameEdit.text.toString()

                if ((nameUser != "" && nameUser.isNotEmpty()) || (sername != "" && nameUser.isNotEmpty())) {
                    val spannableString = SpannableString(nameUser)
                    val spannableString2 = SpannableString(sername)
                    dataModel.name.value = spannableString
                    dataModel.sername.value = spannableString2
                    nameEdit.clearFocus()
                    userNameEdit.clearFocus()
                    hideKeyboard()
                } else {
                    nameEdit.clearFocus()
                    hideKeyboard()
                    userNameEdit.clearFocus()
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

    private fun dialogUI() {
        binding.textView27.setOnClickListener() {
            val dialogBinding = layoutInflater.inflate(R.layout.dilog_choose_inf, null)
            val myDialog = Dialog(requireContext())
            myDialog.setContentView(dialogBinding)
            myDialog.setCancelable(true)
            myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            myDialog.show()

            val gallery = dialogBinding.findViewById<TextView>(R.id.gallery)

            gallery.setOnClickListener() {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                getResult.launch(intent)
            }
        }
    }

    private val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            result.data?.data?.let { uri ->
                binding.mainAvatarImg.setImageURI(uri)
                dataModel.userAvatar.value = uri
            }
        }
    }


    private fun broadcastUI() {
        with(binding) {

            dataModel.name.observe(activity as LifecycleOwner) { spannable ->
                nameEdit.setText(spannable?.toString() ?: "")
            }

            dataModel.sername.observe(activity as LifecycleOwner) { spannable ->
                userNameEdit.setText(spannable?.toString() ?: "")
            }

            dataModel.number.observe(activity as LifecycleOwner) { spannable ->
                inputNumber.setHint(spannable?.toString() ?: "")
            }

            dataModel.email.observe(activity as LifecycleOwner) { spannable ->
                mailEdit.setHint(spannable?.toString() ?: "")
            }

            dataModel.userAvatar.observe(activity as LifecycleOwner, { uri ->
                if (mainAvatarImg != null) {
                    uri?.let {
                        mainAvatarImg.setImageURI(it)
                    } ?: run {
                        mainAvatarImg.setImageResource(R.drawable.user)
                    }
                }
            })

        }
    }
}