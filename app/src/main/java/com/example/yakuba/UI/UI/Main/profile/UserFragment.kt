package com.example.yakuba.UI.UI.Main.profile

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.yakuba.DataModel
import com.example.yakuba.MAIN
import com.example.yakuba.NavigationFragment
import com.example.yakuba.R
import com.example.yakuba.databinding.FragmentUserBinding

class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private val dataModel: DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editProfile()
        leaveInProfile()
        nameEdit()
    }

    private fun editProfile() {
        with(binding) {
            editProfile.setOnClickListener {
                NavigationFragment.NavigationEdit(MAIN.navController)
            }
        }
    }

    private fun leaveInProfile() {
        with(binding) {
            closeProfile.setOnClickListener() {
                val dialogBinding = layoutInflater.inflate(R.layout.leave_dialog_item, null)
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
                    NavigationFragment.NavigationUserDelete(MAIN.navController)
                    myDialog.dismiss()
                }

            }
        }

    }

    private fun nameEdit() {
        dataModel.name.observe(activity as LifecycleOwner, {
            binding.nameEdit.text = it
        })
    }

}