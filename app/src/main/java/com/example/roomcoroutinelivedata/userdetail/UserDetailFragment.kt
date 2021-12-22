package com.example.roomcoroutinelivedata.userdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.roomcoroutinelivedata.R
import com.example.roomcoroutinelivedata.UserDBViewModel
import com.example.roomcoroutinelivedata.UserDBViewModelFactory
import com.example.roomcoroutinelivedata.databinding.FragmentUserDetailBinding

class UserDetailFragment : Fragment(){
    val args: UserDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentUserDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_user_detail, container, false)
        val application = requireNotNull(this.activity).application



        val userDetailViewModel: UserDetailViewModel by viewModels(){ UserDetailViewModelFactory(args.userid, application)}

        binding.uservm = userDetailViewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}