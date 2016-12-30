package com.example

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

//@RunWith(SpringRunner)
@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests extends Specification {

  @Autowired
  WebApplicationContext context



  def 'hellos the world'() {
    setup:
    Map map = [test: "test"]
    when:
    map << [test2: "test2"]
    then:
    map.test2 == "test2"
    context != null
  }

}
