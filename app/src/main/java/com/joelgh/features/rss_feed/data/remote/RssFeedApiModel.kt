package com.joelgh.features.rss_feed.data.remote

import org.simpleframework.xml.*
import java.util.*

@Root(name = "rss", strict = false)
data class RssFeedApiModel @JvmOverloads constructor(

    @field:Path("channel")
    @param:Path("channel")
    @field:ElementList(name = "item", inline = true, required = false)
    @param:ElementList(name = "item", inline = true, required = false)
    var items: List<ItemApiModel>? = null
)

@Root(name = "item", strict = false)
data class ItemApiModel @JvmOverloads constructor(
    @field:Path("title")
    @param:Path("title")
    @field:Text(required = false)
    @param:Text(required = false)
    var title: String? = null,

    @field:Path("description[1]")
    @param:Path("description[1]")
    @field:Text(required = false)
    @param:Text(required = false)
    var description: String? = null,

    @field:Path("link")
    @param:Path("link")
    @field:Text(required = false)
    @param:Text(required = false)
    var link: String? = null,

    @field:Path("pubDate")
    @param:Path("pubDate")
    @field:Text(required = false)
    @param:Text(required = false)
    var pubDate: String? = null,

    @field:Path("creator")
    @param:Path("creator")
    @field:Text(required = false)
    @param:Text(required = false)
    var creator: String? = null,

    @field:Path("content")
    @param:Path("content")
    @field:Attribute(name = "url", required = false)
    @param:Attribute(name = "url", required = false)
    var content: String? = null,

    @field:Path("thumbnail")
    @param:Path("thumbnail")
    @field:Attribute(name = "url", required = false)
    @param:Attribute(name = "url", required = false)
    var thumbnail: String? = null,

    @field:Path("title[2]")
    @param:Path("title[2]")
    @field:Text(required = false)
    @param:Text(required = false)
    var mediaTitle: String? = null,

    @field:Path("description[2]")
    @param:Path("description[2]")
    @field:Text(required = false)
    @param:Text(required = false)
    var mediaDescription: String? = null,

    @field:Path("guid")
    @param:Path("guid")
    @field:Text(required = false)
    @param:Text(required = false)
    var guid: String? = null
)