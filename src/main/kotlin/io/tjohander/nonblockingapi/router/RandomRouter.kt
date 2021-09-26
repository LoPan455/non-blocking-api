package io.tjohander.nonblockingapi.router

import io.tjohander.nonblockingapi.handler.RandomHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RequestPredicates.GET
import org.springframework.web.reactive.function.server.RequestPredicates.accept
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class RandomRouter {

    @Bean
    fun randomRoute(handler: RandomHandler): RouterFunction<ServerResponse> {
        return RouterFunctions
            .route(
                GET("/random/")
                    .and(accept(MediaType.APPLICATION_JSON)),
                handler::someLongRunningFunction
            )
    }
}