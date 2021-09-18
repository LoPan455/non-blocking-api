package io.tjohander.nonblockingapi.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.util.MimeTypeUtils
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class ApiConfiguration(
    @Value("\${api.base-url}") private val apiBaseUrl: String
) {
    /*
    This level of configuration is probably not necessary, but was implemented
    to troubleshoot a downstream issue.  I'm leaving it in for now because of the
    flexibility it introduces
     */
    @Bean
    fun apiClient(): WebClient =
        WebClient.builder()
            .baseUrl(apiBaseUrl)
            .exchangeStrategies(ExchangeStrategies.builder()
                .codecs { clientCodecConfigurer ->
                    val objectMapper = ObjectMapper()
                    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    clientCodecConfigurer.customCodecs().decoder(
                          Jackson2JsonDecoder(objectMapper, MimeTypeUtils.parseMimeType(MediaType.TEXT_PLAIN_VALUE)
                        )
                    )
                }.build()
            ).build()
}