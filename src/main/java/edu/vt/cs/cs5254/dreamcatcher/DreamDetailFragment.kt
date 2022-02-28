package edu.vt.cs.cs5254.dreamcatcher

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.vt.cs.cs5254.dreamcatcher.databinding.FragmentDreamDetailBinding
import java.util.*

private const val TAG = "DreamDetailFragment"
private const val ARG_DREAM_ID = "dream_id"

class DreamDetailFragment : Fragment() {

    private var _binding: FragmentDreamDetailBinding? = null
    // when fragment is not attached to the activity, it should be set to null
    private val binding: FragmentDreamDetailBinding
        get() = _binding!!
    // !! make sure this cant not be null => don't want this binding is null but _binding is null

    private val viewModel: DreamDetailViewModel by lazy {
        ViewModelProvider(this)[DreamDetailViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Log.d(TAG, "Dream detail fragment for dream with ID ${viewModel.dream.id}")
        val dreamId: UUID = arguments?.getSerializable(ARG_DREAM_ID) as UUID
        viewModel.loadDreamWithEntries(dreamId)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDreamDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.dreamTitle.setText(viewModel.dreamWithEntries.dream.title)

        binding.dreamDate.apply {
            text = viewModel.dreamWithEntries.dream.date.toString()
            isEnabled = false
        }

        binding.dreamFulfilledCheckbox.isChecked = viewModel.dreamWithEntries.dream.isFulfilled
        return view
    }

    // want listener only when user interactive
    // only want to trigger the listener, don't want to trigger when rotate

    override fun onStart() {
        super.onStart()
        val titleWatcher = object : TextWatcher {
            override fun beforeTextChanged(
                sequence: CharSequence?, start: Int, count: Int, after: Int) { }
            override fun onTextChanged(sequence: CharSequence?,
                                       start: Int, before: Int, count: Int) {
                viewModel.dreamWithEntries.dream.title = sequence.toString()
            }
            override fun afterTextChanged(sequence: Editable?) { }
        }
        binding.dreamTitle.addTextChangedListener(titleWatcher)
        binding.dreamFulfilledCheckbox.apply {
            setOnCheckedChangeListener { _, isChecked ->
                viewModel.dreamWithEntries.dream.isFulfilled = isChecked
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(dreamId: UUID): DreamDetailFragment {
            val args = Bundle().apply {
                putSerializable(ARG_DREAM_ID, dreamId)
            }
            return DreamDetailFragment().apply {
                arguments = args
            }
        }
    }

}