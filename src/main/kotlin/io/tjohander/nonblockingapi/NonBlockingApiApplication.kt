package io.tjohander.nonblockingapi

import io.tjohander.nonblockingapi.service.PostService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class NonBlockingApiApplication(
    @Autowired private val postService: PostService
) : CommandLineRunner {

    private val log = LoggerFactory.getLogger(NonBlockingApiApplication::class.java)

    override fun run(vararg args: String?) {
        log.info("Starting 'run'...")
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(NonBlockingApiApplication::class.java, *args)
}