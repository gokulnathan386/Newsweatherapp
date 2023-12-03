package com.example.newsweatherapp.adapter

import android.content.Context
import android.content.Intent
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsweatherapp.Activity.NewWebViewActivity
import com.example.newsweatherapp.Activity.NewsScreenActivity
import com.example.newsweatherapp.Model.NewsListResponseModel
import com.example.newsweatherapp.R
import com.squareup.picasso.Picasso


internal class NewsListAdapter(
    private val context: Context,
    private var newsList: List<NewsListResponseModel.NewsList?>
) :
    RecyclerView.Adapter<NewsListAdapter.MyViewHolder>() {



    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {



        var img: ImageView = view.findViewById(R.id.img)
        var titleTextView: TextView = view.findViewById(R.id.titleTextView)
        var descriptionTextView: TextView = view.findViewById(R.id.descriptionTextView)
        var layoutTxt: LinearLayout = view.findViewById(R.id.layoutTxt)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.newsdesign, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        Log.d("gokulnathan"," " + newsList.size);

        holder.titleTextView.text = newsList.get(position)!!.getTitle()
        holder.descriptionTextView.text =  newsList.get(position)!!.getSummary()
        Picasso.get()
            .load(newsList.get(position)!!.getImageUrl())
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.img)

        holder.layoutTxt.setOnClickListener{
            val i = Intent(context, NewWebViewActivity::class.java)
            i.putExtra("URL", newsList[position]?.getURL())
            context.startActivity(i)
        }




        val vto = holder.descriptionTextView.viewTreeObserver
        vto.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {

            override fun onGlobalLayout() {
                val obs = holder.descriptionTextView.viewTreeObserver
                obs.removeOnGlobalLayoutListener(this)
                if (holder.descriptionTextView.lineCount > 3) {
                    val lineEndIndex = holder.descriptionTextView.layout.getLineEnd(2)
                    val text = "<font color=#00000>${holder.descriptionTextView.text.subSequence(0, lineEndIndex - 15)}</font> <font color=#7EC8E3>View more</font>"
                    holder.descriptionTextView.text = Html.fromHtml(text)
                }
            }
        })


       /* if(position == getItemCount() - 1 ){
             Log.d("lastpositionItem","askcnasncnasckjasncjnc")
            if (context is NewsScreenActivity) {
               // (context as NewsScreenActivity).newsListView(50)
            }
        }*/


    }
    override fun getItemCount(): Int {

        return newsList.size
    }
}

