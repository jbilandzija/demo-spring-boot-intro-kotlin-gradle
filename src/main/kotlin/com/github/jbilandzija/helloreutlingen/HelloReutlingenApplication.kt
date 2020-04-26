package com.github.jbilandzija.helloreutlingen

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HelloReutlingenApplication

fun main(args: Array<String>) {
	runApplication<HelloReutlingenApplication>(*args)
}
