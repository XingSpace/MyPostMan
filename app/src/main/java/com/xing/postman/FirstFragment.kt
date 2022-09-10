package com.xing.postman

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.xing.ui.adapter.OnItemClickListener
import com.xing.postman.databinding.FragmentFirstBinding
import com.xing.postman.param.adapter.ParamsAdapter
import com.xing.postman.param.adapter.ParamsVH
import com.xing.postman.param.data.Param
import com.xing.postman.worker.TestConnectWorker

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.selectedSpinner.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            resources.getStringArray(R.array.connect_method)
        )

        binding.paramList.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = ParamsAdapter(mutableListOf(
                Param("", ""),
                Param("q", "w"),
                Param("a", "d"),
                Param("z", "c")
            )).apply {
                onItemClickListener = object : OnItemClickListener<ParamsVH> {
                    override fun onItemClick(position: Int, v: ParamsVH) {
                        if (v.viewType == ADD_TYPE) {
                            list.add(Param("", ""))
                            notifyItemInserted(position)
                            notifyItemRangeChanged(position, list.size)
                            return
                        }
                        list.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position, list.size)
                    }
                }
            }
        }

        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

            val workRequest = TestConnectWorker.start("http://www.baidu.com")

            WorkManager.getInstance(requireContext()).getWorkInfoByIdLiveData(workRequest.id)
                .observe(requireActivity()) {
                    if (it.state == WorkInfo.State.SUCCEEDED) {
                        binding.textviewFirst.setText(it.outputData.getString(TestConnectWorker.RESULT_KEY))
                    } else if (it.state == WorkInfo.State.FAILED) {
                        binding.textviewFirst.setText("出错了")
                    }
                }
            WorkManager.getInstance(requireContext()).enqueue(workRequest)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}