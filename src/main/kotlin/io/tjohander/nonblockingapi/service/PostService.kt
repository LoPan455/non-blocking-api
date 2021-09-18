package io.tjohander.nonblockingapi.service

import io.tjohander.nonblockingapi.controller.PostSource
import io.tjohander.nonblockingapi.model.Post
import io.tjohander.nonblockingapi.model.PostList
import io.tjohander.nonblockingapi.repository.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class PostService(
    @Autowired private val apiClient: WebClient,
    @Autowired private val postRepository: PostRepository
) {
    fun getPosts(source: PostSource): Flux<Post> {
        return when (source) {
            PostSource.API -> getPostsFromApi()
            PostSource.DB -> getPostsFromDb()
        }
    }

    fun getPostsFromApi(): Flux<Post> =
        apiClient
            .get()
            .uri { builder -> builder.path("/posts").build() }
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToFlux(typeReference<Post>())

    fun getPostsFromDb(): Flux<Post> =
        postRepository.findAll()


    fun seedPosts() =
        getPostsFromApi()
            .map {
                postRepository.save(it)
            }

    private inline fun <reified T> typeReference() = object : ParameterizedTypeReference<T>() {}
}