package com.example.henryjacobs.whatsbumping.data
// result generated from /json

data class Album(val album_type: String?, val artists: List<Artists1692225863>?, val external_urls: External_urls?, val href: String?, val id: String?, val images: List<Images1726777090>?, val name: String?, val release_date: String?, val release_date_precision: String?, val total_tracks: Number?, val type: String?, val uri: String?)

data class Artists1291900308(val external_urls: External_urls?, val href: String?, val id: String?, val name: String?, val type: String?, val uri: String?)

data class Artists1656398540(val external_urls: External_urls?, val href: String?, val id: String?, val name: String?, val type: String?, val uri: String?)

data class Artists1692225863(val external_urls: External_urls?, val href: String?, val id: String?, val name: String?, val type: String?, val uri: String?)

data class Artists439526594(val external_urls: External_urls?, val href: String?, val id: String?, val name: String?, val type: String?, val uri: String?)

data class Artists548272483(val external_urls: External_urls?, val href: String?, val id: String?, val name: String?, val type: String?, val uri: String?)

data class Artists96203421(val external_urls: External_urls?, val href: String?, val id: String?, val name: String?, val type: String?, val uri: String?)

data class Base(val tracks: Tracks?)

data class External_ids(val isrc: String?)

data class External_urls(val spotify: String?)

data class Images1105643849(val height: Number?, val url: String?, val width: Number?)

data class Images1377820397(val height: Number?, val url: String?, val width: Number?)

data class Images1726777090(val height: Number?, val url: String?, val width: Number?)

data class Items387968569(val album: Album?, val artists: List<Artists96203421>?, val disc_number: Number?, val duration_ms: Number?, val explicit: Boolean?, val external_ids: External_ids?, val external_urls: External_urls?, val href: String?, val id: String?, val is_local: Boolean?, val is_playable: Boolean?, val name: String?, val popularity: Number?, val preview_url: String?, val track_number: Number?, val type: String?, val uri: String?)

data class Tracks(val href: String?, val items: List<Any>?, val limit: Number?, val next: String?, val offset: Number?, val previous: Any?, val total: Number?)