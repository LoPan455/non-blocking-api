package io.tjohander.nonblockingapi.controller

import io.tjohander.nonblockingapi.model.Post
import io.tjohander.nonblockingapi.service.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("rest/posts")
class PostController(
    @Autowired private val postService: PostService
) {

    @RequestMapping("/")
    fun getPosts(@RequestParam source: PostSource): Flux<Post> = postService.getPosts(source)
}

enum class PostSource {
    DB,
    API
}