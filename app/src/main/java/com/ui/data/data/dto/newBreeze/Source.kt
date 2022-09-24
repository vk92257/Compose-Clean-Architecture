package com.ui.data.data.dto.newBreeze

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize


/*(
    @ForeignKey(
        enttity = Article::class,
        parentColumns = ["id"],
        childColumns = ["sourceId"],
        onDelete = ForeignKey.CASCADE
    )
    )*/

//@Entity
/*(
    foreignKeys = [ForeignKey(
        entity = Article::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("sourceId"),
        onDelete = CASCADE
    )]
)*/
data class Source(
    var sourceId: String = "the-washington-post",
    var name: String = "The Washington Post"
) : Parcelable


/*
data class ArticleAndSource(


    val article: Article,
    val source: Source
)*/
