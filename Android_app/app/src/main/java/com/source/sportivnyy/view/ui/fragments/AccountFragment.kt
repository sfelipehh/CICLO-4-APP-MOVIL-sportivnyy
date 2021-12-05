package com.source.sportivnyy.view.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.firebase.ui.auth.AuthUI
import com.source.sportivnyy.R
import com.source.sportivnyy.databinding.AccountFragmentBinding
import com.source.sportivnyy.model.network.FirebaseServices
import com.squareup.picasso.Picasso

class AccountFragment:Fragment() {
    companion object {
        fun newInstance() = AccountFragment()
    }

    private  var _binding: AccountFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AccountFragmentBinding.inflate(inflater,container,false)
        val root:View = binding.root
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val firebaseAuth = FirebaseServices.FirebaseAuthService()
        val userName = firebaseAuth.firebaseAuth.currentUser?.displayName
        val userPhoto = firebaseAuth.firebaseAuth.currentUser?.photoUrl
        val userEmail = firebaseAuth.firebaseAuth.currentUser?.email
        val photoView = binding.photoUser
        val nameView = binding.nameUser
        val emailView = binding.mailUser
        val signOutButton = binding.singOutButtonInAccount
        signOutButton.setOnClickListener {
            AuthUI.getInstance()
                .signOut(requireContext())
            activity?.finish()
        }
        bind(photoView,nameView,emailView,userName,userPhoto,userEmail)

    }

    private fun bind(photoView:ImageView,nameView:TextView,emailView:TextView,
    userName:String?,userPhoto:Uri?,userEmail:String?){
        //R.dimen.image_in_detail_size = 300dp / 300px
        Picasso.get().load(userPhoto).into(photoView)
        nameView.text = userName
        emailView.text = userEmail
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val menuItems = setOf(R.id.to_shopping_cart, R.id.to_account)
        for (item in menuItems){
            menu.findItem(item).isVisible=false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}