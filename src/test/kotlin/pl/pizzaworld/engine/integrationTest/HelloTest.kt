package pl.pizzaworld.engine.integrationTest

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus.OK

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTests(@Autowired val restTemplate: TestRestTemplate) {

    @Test
    fun `Assert that name is passed and status is 200`() {
        val entity = restTemplate.getForEntity<String>("/?name=hi")
        assertThat(entity.statusCode).isEqualTo(OK)
        assertThat(entity.body).contains("hi")
    }

}