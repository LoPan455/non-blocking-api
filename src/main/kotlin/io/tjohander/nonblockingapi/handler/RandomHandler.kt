package io.tjohander.nonblockingapi.handler

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserter
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono
import kotlin.random.Random

@Component
class RandomHandler {

    fun someLongRunningFunction(request: ServerRequest): Mono<ServerResponse> {
        return Mono.just(Random.nextLong(300, 500))
            .map { randomLong ->
               Thread.sleep(randomLong)
               randomLong
            }
            .flatMap { randomLong ->
                ok().contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue("This method paused for $randomLong ms"))
            }
    }
}