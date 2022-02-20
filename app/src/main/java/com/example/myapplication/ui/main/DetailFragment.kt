package com.example.myapplication.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.example.myapplication.databinding.DetailFragmentBinding
import com.example.myapplication.util.load
import com.example.myapplication.util.setMarginTop

class DetailFragment : Fragment() {
    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        ViewCompat.setOnApplyWindowInsetsListener(binding.detailFragment) { _, insets ->
            binding.appbarLayout.setMarginTop(insets.getInsets(WindowInsetsCompat.Type.systemBars()).top)
            WindowInsetsCompat.CONSUMED
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            ivBackground.load(args.event.image)
            tvTime.text = args.event.getTimeStringByLocalTimeZone()
            tvTitle.text = args.event.title
            tvLocation.text = args.event.getFullLocation()
            tvDescription.text = args.event.description
            btBack.setOnClickListener { requireActivity().onBackPressed() }
            btShare.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_event_subject))
                    putExtra(Intent.EXTRA_TEXT, args.event.getMessageBody(requireContext()))
                    putExtra(Intent.EXTRA_STREAM, Uri.parse(args.event.image))
                    type = "image/*";
                }
                startActivity(Intent.createChooser(intent, getString(R.string.share_event_with)));
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}