/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.data.remote.responses


import com.google.gson.annotations.SerializedName

data class ResponsesDetail(
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
    var description: Any? = Any(),
    @SerializedName("downloads")
    var downloads: Int? = 0,
    @SerializedName("exif")
    var exif: Exif? = Exif(),
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
    @SerializedName("location")
    var location: Location? = Location(),
    @SerializedName("meta")
    var meta: Meta? = Meta(),
    @SerializedName("promoted_at")
    var promotedAt: Any? = Any(),
    @SerializedName("related_collections")
    var relatedCollections: RelatedCollections? = RelatedCollections(),
    @SerializedName("sponsorship")
    var sponsorship: Any? = Any(),
    @SerializedName("tags")
    var tags: List<Tag?>? = listOf(),
    @SerializedName("updated_at")
    var updatedAt: String? = "",
    @SerializedName("urls")
    var urls: Urls? = Urls(),
    @SerializedName("user")
    var user: User? = User(),
    @SerializedName("views")
    var views: Int? = 0,
    @SerializedName("width")
    var width: Int? = 0
) {
    data class Exif(
        @SerializedName("aperture")
        var aperture: Any? = Any(),
        @SerializedName("exposure_time")
        var exposureTime: Any? = Any(),
        @SerializedName("focal_length")
        var focalLength: Any? = Any(),
        @SerializedName("iso")
        var iso: Any? = Any(),
        @SerializedName("make")
        var make: Any? = Any(),
        @SerializedName("model")
        var model: Any? = Any()
    )

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

    data class Location(
        @SerializedName("city")
        var city: Any? = Any(),
        @SerializedName("country")
        var country: Any? = Any(),
        @SerializedName("name")
        var name: Any? = Any(),
        @SerializedName("position")
        var position: Position? = Position(),
        @SerializedName("title")
        var title: Any? = Any()
    ) {
        data class Position(
            @SerializedName("latitude")
            var latitude: Any? = Any(),
            @SerializedName("longitude")
            var longitude: Any? = Any()
        )
    }

    data class Meta(
        @SerializedName("index")
        var index: Boolean? = false
    )

    data class RelatedCollections(
        @SerializedName("results")
        var results: List<Result?>? = listOf(),
        @SerializedName("total")
        var total: Int? = 0,
        @SerializedName("type")
        var type: String? = ""
    ) {
        data class Result(
            @SerializedName("cover_photo")
            var coverPhoto: CoverPhoto? = CoverPhoto(),
            @SerializedName("curated")
            var curated: Boolean? = false,
            @SerializedName("description")
            var description: Any? = Any(),
            @SerializedName("featured")
            var featured: Boolean? = false,
            @SerializedName("id")
            var id: String? = "",
            @SerializedName("last_collected_at")
            var lastCollectedAt: String? = "",
            @SerializedName("links")
            var links: Links? = Links(),
            @SerializedName("preview_photos")
            var previewPhotos: List<PreviewPhoto?>? = listOf(),
            @SerializedName("private")
            var `private`: Boolean? = false,
            @SerializedName("published_at")
            var publishedAt: String? = "",
            @SerializedName("share_key")
            var shareKey: String? = "",
            @SerializedName("tags")
            var tags: List<Tag?>? = listOf(),
            @SerializedName("title")
            var title: String? = "",
            @SerializedName("total_photos")
            var totalPhotos: Int? = 0,
            @SerializedName("updated_at")
            var updatedAt: String? = "",
            @SerializedName("user")
            var user: User? = User()
        ) {
            data class CoverPhoto(
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
                var description: Any? = Any(),
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
                var promotedAt: Any? = Any(),
                @SerializedName("sponsorship")
                var sponsorship: Any? = Any(),
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
                    var lastName: Any? = Any(),
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

            data class Links(
                @SerializedName("html")
                var html: String? = "",
                @SerializedName("photos")
                var photos: String? = "",
                @SerializedName("related")
                var related: String? = "",
                @SerializedName("self")
                var self: String? = ""
            )

            data class PreviewPhoto(
                @SerializedName("blur_hash")
                var blurHash: String? = "",
                @SerializedName("created_at")
                var createdAt: String? = "",
                @SerializedName("id")
                var id: String? = "",
                @SerializedName("updated_at")
                var updatedAt: String? = "",
                @SerializedName("urls")
                var urls: Urls? = Urls()
            ) {
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
            }

            data class Tag(
                @SerializedName("source")
                var source: Source? = Source(),
                @SerializedName("title")
                var title: String? = "",
                @SerializedName("type")
                var type: String? = ""
            ) {
                data class Source(
                    @SerializedName("ancestry")
                    var ancestry: Ancestry? = Ancestry(),
                    @SerializedName("cover_photo")
                    var coverPhoto: CoverPhoto? = CoverPhoto(),
                    @SerializedName("description")
                    var description: String? = "",
                    @SerializedName("meta_description")
                    var metaDescription: String? = "",
                    @SerializedName("meta_title")
                    var metaTitle: String? = "",
                    @SerializedName("subtitle")
                    var subtitle: String? = "",
                    @SerializedName("title")
                    var title: String? = ""
                ) {
                    data class Ancestry(
                        @SerializedName("category")
                        var category: Category? = Category(),
                        @SerializedName("subcategory")
                        var subcategory: Subcategory? = Subcategory(),
                        @SerializedName("type")
                        var type: Type? = Type()
                    ) {
                        data class Category(
                            @SerializedName("pretty_slug")
                            var prettySlug: String? = "",
                            @SerializedName("slug")
                            var slug: String? = ""
                        )

                        data class Subcategory(
                            @SerializedName("pretty_slug")
                            var prettySlug: String? = "",
                            @SerializedName("slug")
                            var slug: String? = ""
                        )

                        data class Type(
                            @SerializedName("pretty_slug")
                            var prettySlug: String? = "",
                            @SerializedName("slug")
                            var slug: String? = ""
                        )
                    }

                    data class CoverPhoto(
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
                        var sponsorship: Any? = Any(),
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
                            var instagramUsername: Any? = Any(),
                            @SerializedName("last_name")
                            var lastName: String? = "",
                            @SerializedName("links")
                            var links: Links? = Links(),
                            @SerializedName("location")
                            var location: Any? = Any(),
                            @SerializedName("name")
                            var name: String? = "",
                            @SerializedName("portfolio_url")
                            var portfolioUrl: Any? = Any(),
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
                }
            }

            data class User(
                @SerializedName("accepted_tos")
                var acceptedTos: Boolean? = false,
                @SerializedName("bio")
                var bio: Any? = Any(),
                @SerializedName("first_name")
                var firstName: String? = "",
                @SerializedName("id")
                var id: String? = "",
                @SerializedName("instagram_username")
                var instagramUsername: Any? = Any(),
                @SerializedName("last_name")
                var lastName: String? = "",
                @SerializedName("links")
                var links: Links? = Links(),
                @SerializedName("location")
                var location: Any? = Any(),
                @SerializedName("name")
                var name: String? = "",
                @SerializedName("portfolio_url")
                var portfolioUrl: Any? = Any(),
                @SerializedName("profile_image")
                var profileImage: ProfileImage? = ProfileImage(),
                @SerializedName("total_collections")
                var totalCollections: Int? = 0,
                @SerializedName("total_likes")
                var totalLikes: Int? = 0,
                @SerializedName("total_photos")
                var totalPhotos: Int? = 0,
                @SerializedName("twitter_username")
                var twitterUsername: Any? = Any(),
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
    }

    data class Tag(
        @SerializedName("source")
        var source: Source? = Source(),
        @SerializedName("title")
        var title: String? = "",
        @SerializedName("type")
        var type: String? = ""
    ) {
        data class Source(
            @SerializedName("ancestry")
            var ancestry: Ancestry? = Ancestry(),
            @SerializedName("cover_photo")
            var coverPhoto: CoverPhoto? = CoverPhoto(),
            @SerializedName("description")
            var description: String? = "",
            @SerializedName("meta_description")
            var metaDescription: String? = "",
            @SerializedName("meta_title")
            var metaTitle: String? = "",
            @SerializedName("subtitle")
            var subtitle: String? = "",
            @SerializedName("title")
            var title: String? = ""
        ) {
            data class Ancestry(
                @SerializedName("category")
                var category: Category? = Category(),
                @SerializedName("subcategory")
                var subcategory: Subcategory? = Subcategory(),
                @SerializedName("type")
                var type: Type? = Type()
            ) {
                data class Category(
                    @SerializedName("pretty_slug")
                    var prettySlug: String? = "",
                    @SerializedName("slug")
                    var slug: String? = ""
                )

                data class Subcategory(
                    @SerializedName("pretty_slug")
                    var prettySlug: String? = "",
                    @SerializedName("slug")
                    var slug: String? = ""
                )

                data class Type(
                    @SerializedName("pretty_slug")
                    var prettySlug: String? = "",
                    @SerializedName("slug")
                    var slug: String? = ""
                )
            }

            data class CoverPhoto(
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
                var description: Any? = Any(),
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
                var sponsorship: Any? = Any(),
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