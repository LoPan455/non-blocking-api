package io.tjohander.nonblockingapi.repository

import io.tjohander.nonblockingapi.model.Post
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : ReactiveMongoRepository<Post, Int>