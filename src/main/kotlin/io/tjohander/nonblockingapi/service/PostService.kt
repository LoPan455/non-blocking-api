package io.tjohander.nonblockingapi.service

import io.tjohander.nonblockingapi.enum.DataSource
import io.tjohander.nonblockingapi.model.Post
import io.tjohander.nonblockingapi.repository.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux

@Service
class PostService(
    @Autowired private val apiClient: WebClient,
    @Autowired private val postRepository: PostRepository
) {
    fun getPosts(source: DataSource): Flux<Post> {
        return when (source) {
            DataSource.API -> getPostsFromApi()
            DataSource.DB -> getPostsFromDb()
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