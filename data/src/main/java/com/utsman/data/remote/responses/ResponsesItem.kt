/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.data.remote.responses


import com.google.gson.annotations.SerializedName

data class ResponsesItem(
    @SerializedName("alt_description")
    var altDescription: String? = "",
    @SerializedName("blur_hash")
    var blurHash: String? = "",
    @SerializedName("categories")
    var categories: List<Any?>? = listOf(),
    @SerializedName("color")
    var color: String? = "",
    @SerializedName("created_at")
    var createdAt: String? = "",
    @SerializedName("current_user_collections")
    var currentUserCollections: List<Any?>? = listOf(),
    @SerializedName("description")
    var description: String? = "",
    @SerializedName("height")
    var height: Int? = 0,
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("liked_by_user")
    var likedByUser: Boolean? = false,
    @SerializedName("likes")
    var likes: Int? = 0,
    @SerializedName("links")
    var links: Links? = Links(),
    @SerializedName("promoted_at")
    var promotedAt: String? = "",
    @SerializedName("sponsorship")
    var sponsorship: Sponsorship? = Sponsorship(),
    @SerializedName("updated_at")
    var updatedAt: String? = "",
    @SerializedName("urls")
    var urls: Urls? = Urls(),
    @SerializedName("user")
    var user: User? = User(),
    @SerializedName("width")
    var width: Int? = 0
) {
    data class Links(
        @SerializedName("download")
        var download: String? = "",
        @SerializedName("download_location")
        var downloadLocation: String? = "",
        @SerializedName("html")
        var html: String? = "",
        @SerializedName("self")
        var self: String? = ""
    )

    data class Sponsorship(
        @SerializedName("impression_urls")
        var impressionUrls: List<String?>? = listOf(),
        @SerializedName("sponsor")
        var sponsor: Sponsor? = Sponsor(),
        @SerializedName("tagline")
        var tagline: String? = "",
        @SerializedName("tagline_url")
        var taglineUrl: String? = ""
    ) {
        data class Sponsor(
            @SerializedName("accepted_tos")
            var acceptedTos: Boolean? = false,
            @SerializedName("bio")
            var bio: String? = "",
            @SerializedName("first_name")
            var firstName: String? = "",
            @SerializedName("id")
            var id: String? = "",
            @SerializedName("instagram_username")
            var instagramUsername: String? = "",
            @SerializedName("last_name")
            var lastName: Any? = Any(),
            @SerializedName("links")
            var links: Links? = Links(),
            @SerializedName("location")
            var location: Any? = Any(),
            @SerializedName("name")
            var name: String? = "",
            @SerializedName("portfolio_url")
            var portfolioUrl: String? = "",
            @SerializedName("profile_image")
            var profileImage: ProfileImage? = ProfileImage(),
            @SerializedName("total_collections")
            var totalCollections: Int? = 0,
            @SerializedName("total_likes")
            var totalLikes: Int? = 0,
            @SerializedName("total_photos")
            var totalPhotos: Int? = 0,
            @SerializedName("twitter_username")
            var twitterUsername: String? = "",
            @SerializedName("updated_at")
            var updatedAt: String? = "",
            @SerializedName("username")
            var username: String? = ""
        ) {
            data class Links(
                @SerializedName("followers")
                var followers: String? = "",
                @SerializedName("following")
                var following: String? = "",
                @SerializedName("html")
                var html: String? = "",
                @SerializedName("likes")
                var likes: String? = "",
                @SerializedName("photos")
                var photos: String? = "",
                @SerializedName("portfolio")
                var portfolio: String? = "",
                @SerializedName("self")
                var self: String? = ""
            )

            data class ProfileImage(
                @SerializedName("large")
                var large: String? = "",
                @SerializedName("medium")
                var medium: String? = "",
                @SerializedName("small")
                var small: String? = ""
            )
        }
    }

    data class Urls(
        @SerializedName("full")
        var full: String? = "",
        @SerializedName("raw")
        var raw: String? = "",
        @SerializedName("regular")
        var regular: String? = "",
        @SerializedName("small")
        var small: String? = "",
        @SerializedName("thumb")
        var thumb: String? = ""
    )

    data class User(
        @SerializedName("accepted_tos")
        var acceptedTos: Boolean? = false,
        @SerializedName("bio")
        var bio: String? = "",
        @SerializedName("first_name")
        var firstName: String? = "",
        @SerializedName("id")
        var id: String? = "",
        @SerializedName("instagram_username")
        var instagramUsername: String? = "",
        @SerializedName("last_name")
        var lastName: String? = "",
        @SerializedName("links")
        var links: Links? = Links(),
        @SerializedName("location")
        var location: String? = "",
        @SerializedName("name")
        var name: String? = "",
        @SerializedName("portfolio_url")
        var portfolioUrl: String? = "",
        @SerializedName("profile_image")
        var profileImage: ProfileImage? = ProfileImage(),
        @SerializedName("total_collections")
        var totalCollections: Int? = 0,
        @SerializedName("total_likes")
        var totalLikes: Int? = 0,
        @SerializedName("total_photos")
        var totalPhotos: Int? = 0,
        @SerializedName("twitter_username")
        var twitterUsername: String? = "",
        @SerializedName("updated_at")
        var updatedAt: String? = "",
        @SerializedName("username")
        var username: String? = ""
    ) {
        data class Links(
            @SerializedName("followers")
            var followers: String? = "",
            @SerializedName("following")
            var following: String? = "",
            @SerializedName("html")
            var html: String? = "",
            @SerializedName("likes")
            var likes: String? = "",
            @SerializedName("photos")
            var photos: String? = "",
            @SerializedName("portfolio")
            var portfolio: String? = "",
            @SerializedName("self")
            var self: String? = ""
        )

        data class ProfileImage(
            @SerializedName("large")
            var large: String? = "",
            @SerializedName("medium")
            var medium: String? = "",
            @SerializedName("small")
            var small: String? = ""
        )
    }
}