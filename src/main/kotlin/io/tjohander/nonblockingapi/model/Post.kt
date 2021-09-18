package io.tjohander.nonblockingapi.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Post(
    @Id
    val id: Int = 0,
    val userId: Int = 0,
    val title: String? = null,
    val body: String? = null,
)

data class PostList(
    val posts: List<Post>
)