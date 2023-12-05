package com.example.newsweatherapp.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NewsListResponseModel {

    @SerializedName("count")
    @Expose
    private var count: Int? = null

    @SerializedName("next")
    @Expose
    private var next: String? = null

    @SerializedName("previous")
    @Expose
    private var previous: String? = null

    @SerializedName("results")
    @Expose
    private var results: List<NewsList>? = null


    fun getCount(): Int? {
        return count
    }

    fun setCount(count: Int) {
        this.count = count
    }

     fun getResult(): List<NewsList?>? {
        return results
     }

     fun setResult(results: List<NewsList>) {
        this.results = results
     }

    fun getNext(): String? {
        return next
    }

    fun setNext(next: String) {
        this.next = next
    }

    fun getPrevious(): String? {
        return previous
    }

    fun setPrevious(previous: String) {
        this.previous = previous
    }


    class NewsList {

        @SerializedName("id")
        @Expose
        private var id: Int? = null

        @SerializedName("title")
        @Expose
        private var title: String? = null


        @SerializedName("image_url")
        @Expose
        private var image_url: String? = null

        @SerializedName("summary")
        @Expose
        private var summary: String? = null

        @SerializedName("url")
        @Expose
        private var url: String? = null



        fun getId(): Int? {
            return id
        }

        fun setId(id: Int) {
            this.id = id
        }

        fun getTitle(): String? {
            return title
        }

        fun setTitle(title: String) {
            this.title = title
        }


        fun getImageUrl(): String? {
            return image_url
        }

        fun setImageUrl(image_url: String) {
            this.image_url = image_url
        }


        fun getSummary(): String? {
            return summary
        }

        fun getSummary(summary: String) {
            this.summary = summary
        }

        fun getURL(): String? {
            return url
        }

        fun getURL(url: String) {
            this.url = url
        }


    }

}