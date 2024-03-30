package com.team.krontesttask.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.team.krontesttask.app.App
import com.team.krontesttask.databinding.FragmentUserDetailBinding
import javax.inject.Inject


class UserDetailFragment : Fragment() {

    @Inject
    lateinit var vmFactory: UserDetailViewModelFactory

    private var _binding: FragmentUserDetailBinding? = null
    private val binding: FragmentUserDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentUserDetailBinding is null")

    private var userLoginArgs: String? = null

    private lateinit var viewModel: UserDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().applicationContext as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this, vmFactory)[UserDetailViewModel::class.java]
        parsParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isNotVisibleInfo()
        viewModel.getUserDetail(userLoginArgs ?: "")
        observeViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun parsParams() {
        val args = requireArguments()
        if (!args.containsKey(EXTRA_LOGIN)) {
            throw RuntimeException("args login is not")
        }
        userLoginArgs = args.getString(EXTRA_LOGIN)
    }

    private fun observeViewModel() {
        viewModel.user.observe(viewLifecycleOwner) {
            isVisibleInfo()
            with(binding) {
                userName.text = String.format("Name: %s", it.name)
                userEmail.text = String.format("Email: %s", it.email ?: "No email")
                userOrganizations.text = String.format("Org: %s", it.organizationsUrl)
                userFollowing.text = String.format("Following: %s", it.following.toString())
                userFollowers.text = String.format("Followers: %s", it.followers.toString())
                userCreatedAt.text = String.format("CreatedAt: %s", it.createdAt)
            }
            Glide.with(this)
                .load(it.avatarUrl)
                .into(binding.userImage)
        }
    }

    private fun isVisibleInfo() {
        with(binding) {
            userName.visibility = View.VISIBLE
            userEmail.visibility = View.VISIBLE
            userOrganizations.visibility = View.VISIBLE
            userFollowing.visibility = View.VISIBLE
            userFollowers.visibility = View.VISIBLE
            userCreatedAt.visibility = View.VISIBLE
            userImage.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }
    }

    private fun isNotVisibleInfo() {
        with(binding) {
            userName.visibility = View.GONE
            userEmail.visibility = View.GONE
            userOrganizations.visibility = View.GONE
            userFollowing.visibility = View.GONE
            userFollowers.visibility = View.GONE
            userCreatedAt.visibility = View.GONE
            userImage.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        }
    }

    companion object {

        const val TAG_FRAGMENT = "user_detail_fragment"
        private const val EXTRA_LOGIN = "login"

        fun newInstance(login: String): UserDetailFragment {
            return UserDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_LOGIN, login)
                }
            }
        }
    }
}