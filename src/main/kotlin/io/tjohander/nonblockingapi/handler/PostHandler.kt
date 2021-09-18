package io.tjohander.nonblockingapi.handler

import io.tjohander.nonblockingapi.model.Post
import io.tjohander.nonblockingapi.service.PostService
import kotlinx.coroutines.flow.Flow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyAndAwait
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class PostHandler(
    @Autowired private val apiClient: WebClient,
    @Autowired private val postService: PostService
) {

    fun getPosts(request: ServerRequest): Mono<ServerResponse> {
        return postService
            .getPostsFromDb()
            .collectList()
            .flatMap { list ->
                ok().contentType(MediaType.APPLICATION_JSON).bodyValue(list)
            }
    }


    // This will fetch Posts from the public Fake API 'POSTS' endpoint
    private fun getPostsFromDownstream(): Mono<List<Post>> =
        apiClient
            .get()
            .uri { builder -> builder.path("/posts").build() }
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(typeReference<List<Post>>())

    private inline fun <reified T> typeReference() = object : ParameterizedTypeReference<T>() {}
}