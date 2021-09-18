package io.tjohander.nonblockingapi.config

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories


@EnableMongoRepositories
class MongoConfiguration(
    @Value("\${spring.data.mongodb.database}") private val dbName: String
) : AbstractReactiveMongoConfiguration() {

    @Bean
    fun mongoClient(): MongoClient? {
        return MongoClients.create()
    }


    override fun getDatabaseName(): String {
        return dbName
    }
}