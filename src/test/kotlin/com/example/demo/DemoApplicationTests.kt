package com.example.demo

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest
import java.time.Instant

@SpringBootTest
class DemoApplicationTests {

    val logger = LoggerFactory.getLogger(this::class.java)

    @Test
    fun testBlocking() {
        val start = Instant.now()
        (0..10).forEach {
            mockVariableResponseTime()
        }
        logger.info("blocking test took {}ms", Instant.now().toEpochMilli() - start.toEpochMilli())
    }
	
    @Test
    fun testParallel() {
        val start = Instant.now()
        runBlocking {
            (0..10).map {
                async { mockVariableResponseTime() }
            }.awaitAll()
        }
        logger.info("blocking test took {}ms", Instant.now().toEpochMilli() - start.toEpochMilli())
    }
	
    fun mockVariableResponseTime() {
        // anywhere from 500ms to 3000ms
        runBlocking {
            delay(200)
        }
        // todo make thread wait and then return
    }
}
