package com.dongze.ecart.view.dashboard

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.dongze.ecart.R
import com.dongze.ecart.databinding.FragmentDashboardBinding
import com.dongze.ecart.view.Communicator
import com.dongze.ecart.view.CustomOutlineProvider
import com.dongze.ecart.viewModel.SearchViewModel
import java.util.Timer
import java.util.TimerTask

class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding
    private lateinit var viewModel: SearchViewModel
    private lateinit var communicator: Communicator

    private val images = listOf(
        R.drawable.slider_one,
        R.drawable.slider_two,
        R.drawable.slider_three,
        R.drawable.slider_four,
        R.drawable.slider_five
    )
    private lateinit var sliderHandler: Handler
    private lateinit var sliderRunnable: Runnable
    private lateinit var timer: Timer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        communicator = requireActivity() as Communicator

        viewModel = ViewModelProvider(this@DashboardFragment)[SearchViewModel::class.java]
        with(binding){
            btnSearch.setOnClickListener{
                val keyword = edtKeyword.text.toString()
                viewModel.searchProduct(keyword)
                viewModel.searchRes.observe(viewLifecycleOwner){
                    response->
                    Toast.makeText(requireContext(),response.message, Toast.LENGTH_SHORT).show()
                    communicator.sendPList(response.products)
                }
            }
            searchArea.elevation = 40f
            val radius = 50f
            searchArea.outlineProvider = CustomOutlineProvider(radius)
        }
        setUpImgSlider()
    }

    private fun setUpImgSlider() {
        binding.vp2ImageSlider.adapter = ImageSliderAdapter(images)

        // Add animation to ViewPager2
        binding.vp2ImageSlider.setPageTransformer(DepthPageTransformer())
        // Auto switch between images every 5 seconds
        sliderHandler = Handler(Looper.getMainLooper())
        sliderRunnable = Runnable {
            var currentItem = binding.vp2ImageSlider.currentItem
            currentItem = (currentItem + 1) % images.size
            binding.vp2ImageSlider.currentItem = currentItem
        }
    }

    override fun onResume() {
        super.onResume()
        startSlider()
    }

    override fun onPause() {
        super.onPause()
        stopSlider()
    }

    private fun startSlider() {
        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                sliderHandler.post(sliderRunnable)
            }
        }, 5000, 5000) // 5 seconds delay and interval
    }

    private fun stopSlider() {
        timer.cancel()  // Stop the timer
        sliderHandler.removeCallbacks(sliderRunnable)  // Remove any pending runnables
    }
}