package com.appetiser.meowvies.ui.movieInfo

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.appetiser.meowvies.R
import com.appetiser.meowvies.ui.main.MainViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import jp.wasabeef.blurry.Blurry
import kotlinx.android.synthetic.main.fragment_movie_info.*

/**
 * MovieInfoFragment class where the rendering of data from the ViewModel to UI happens
 */
class MovieInfoFragment : Fragment() {

    /**
     * Initializes the needed objects/variable
     */
    private var trackId: Int? = 0
    private var genre: String? = ""
    private var title: String? = ""
    private var price: String? = ""
    private var longDesc: String? = ""
    private var artwork: String? = ""

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    companion object {

        /**
         * This method puts all the bundle to an instance
         * @param id get the movie's id
         * @param title get the movie's title
         * @param genre get the movie's genre
         * @param price get the movie's price
         * @param longDesc get the movie's longDesc
         * @param artwork get the movie's artwork
         */
        fun newInstance(
            id: Int,
            genre: String?,
            title: String?,
            price: String?,
            longDesc: String?,
            artwork: String?
        ): MovieInfoFragment {
            val instance = MovieInfoFragment()
            val arguments = Bundle()
            arguments.putInt("id", id)
            arguments.putString("genre", genre)
            arguments.putString("title", title)
            arguments.putString("price", price)
            arguments.putString("longDesc", longDesc)
            arguments.putString("artwork", artwork)
            instance.arguments = arguments
            return instance
        }
    }

    private fun initViewModel() {
        mainViewModel =
            ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**
         * Reassigning bundles to a variable accordingly
         */
        if (arguments != null) {
            trackId = arguments?.getInt("id", 0)
            genre = arguments?.getString("genre", "")
            title = arguments?.getString("title", "")
            price = arguments?.getString("price", "")
            longDesc = arguments?.getString("longDesc", "")
            artwork = arguments?.getString("artwork", "")
        }
        setupUI()
    }

    @SuppressLint("SetTextI18n")
    private fun setupUI() {

        tvTrackGenre.text = genre
        tvLongDesc.text = longDesc
        tvTrackName.text = title
        tvTrackPrice.text = """$$price"""

        /**
         * Renders an image from URL to bitmap to be able to use it
         * in Blurry library.
         * This image will serve as a blurred background of the UI
         */
        Glide.with(requireContext())
            .asBitmap()
            .load(artwork).diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .placeholder(R.drawable.ic_placeholder)
            .into(object : CustomTarget<Bitmap>() {
                override fun onLoadCleared(placeholder: Drawable?) {}
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    Blurry.with(context).from(resource).into(ivBGThumbnail)
                    ivMainThumbnail.setImageBitmap(resource)
                }
            })

        /**
         * Renders an image from URL to the visible imageView in the UI
         */
        Glide.with(requireContext()).load(artwork).diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .placeholder(R.drawable.ic_placeholder).into(ivMainThumbnail)
    }

}