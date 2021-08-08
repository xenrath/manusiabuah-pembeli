package com.xenrath.manusiabuahpembeli.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.xenrath.manusiabuahpembeli.R
import com.xenrath.manusiabuahpembeli.data.Constant

class GlideHelper {

    companion object {

        fun setImage(context: Context, urlImage: String, imageView: ImageView) {
            Glide.with(context)
                .load(Constant.IP_IMAGE + urlImage)
                .centerCrop()
                .placeholder(R.drawable.user)
                .error(R.drawable.user)
                .into(imageView)
        }

    }

}