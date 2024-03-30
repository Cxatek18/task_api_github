package com.team.krontesttask.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.team.krontesttask.R
import com.team.krontesttask.app.App
import com.team.krontesttask.databinding.FragmentMainBinding
import com.team.krontesttask.presentation.adapter.UserAdapter
import javax.inject.Inject


class MainFragment : Fragment() {
    @Inject
    lateinit var vmFactory: MainViewModelFactory


    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding is null")

    private lateinit var adapter: UserAdapter

    private var counterUsersApi = 20
    private var sinceStart = 0

    private lateinit var layoutManager: LinearLayoutManager

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().applicationContext as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this, vmFactory)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getListUser(counterUsersApi, sinceStart)
        adapter = UserAdapter()
        binding.rvMain.adapter = adapter
        layoutManager = (binding.rvMain.layoutManager as? LinearLayoutManager)
            ?: throw RuntimeException("layoutManager not is RV")
        onClickUserItem()
        observeViewModel()
        startSwiperRefresh()
        paginatorStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun onClickUserItem() {
        adapter.onClickUser = {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, UserDetailFragment.newInstance(it))
                .addToBackStack(UserDetailFragment.TAG_FRAGMENT)
                .commit()
        }
    }

    private fun observeViewModel() {
        viewModel.listUser.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun startSwiperRefresh() {
        binding.swiperRefresh.setOnRefreshListener {
            adapter.notifyDataSetChanged()
            binding.swiperRefresh.isRefreshing = false
        }
    }

    private fun paginatorStart() {
        binding.rvMain.addOnScrollListener(object :
            OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (layoutManager.findLastVisibleItemPosition() == adapter.itemCount - 1) {
                    sinceStart += 20
                    viewModel.getListUser(counterUsersApi, sinceStart)
                }
            }
        }
        )
    }

    companion object {

        const val TAG_FRAGMENT = "main_fragment"

        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
}