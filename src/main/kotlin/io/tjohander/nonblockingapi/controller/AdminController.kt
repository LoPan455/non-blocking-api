package io.tjohander.nonblockingapi.controller

import io.tjohander.nonblockingapi.service.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("admin")
class AdminController(
    @Autowired private val postService: PostService
) {

    @PostMapping("/seed-data")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun seedDatabase() {
        postService.seedPosts()
    }
}