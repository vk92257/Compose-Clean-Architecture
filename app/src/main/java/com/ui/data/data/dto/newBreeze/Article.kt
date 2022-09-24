package com.ui.data.data.dto.newBreeze

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    var author: String? = "Selena Ross, Scott Dance",
    var content: String? = "One of the strongest storms ever to hit Canada slammed into Nova Scotias coastline early Saturday, leaving hundreds of thousands of people without power.\r\nFormer Hurricane Fiona made landfall early o… [+2844 chars]",
    var description: String? = "Former Hurricane Fiona made landfall in Canada early on Saturday morning, with more than 40% of Nova Scotia's population affected by outages.",
    var publishedAt: String? = "2022-09-24T10:40:23Z",
    @Embedded
    var source: Source = Source(),
    var title: String? = "Fiona wallops Canada's Novia Scotia with storm surges expected - The Washington Post",
    var url: String? = "https://www.washingtonpost.com/world/2022/09/24/hurricane-fiona-nova-scotia-canada/",
    var urlToImage: String? = "https://www.washingtonpost.com/wp-apps/imrs.php?src=https://arc-anglerfish-washpost-prod-washpost.s3.amazonaws.com/public/IPSOXEB3O4I63OFPBICOLXB5WY.jpg&w=1440",
    var isSaved: Boolean = false
) : Parcelable