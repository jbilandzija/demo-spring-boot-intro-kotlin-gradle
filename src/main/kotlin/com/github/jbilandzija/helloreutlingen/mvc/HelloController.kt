package com.github.jbilandzija.helloreutlingen.mvc

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
class HelloController {

    private val responseModelMap: MutableMap<String, String> = HashMap()

    var logger = LoggerFactory.getLogger(HelloController::class.java)

    /**
     * Initialize and populate Map for Example
     */
    init {
        responseModelMap["1"] = "Response with Message 1"
        responseModelMap["2"] = "Response with Message 2"
        responseModelMap["3"] = "Response with Message 3"
        responseModelMap["4"] = "Response with Message 4"
        responseModelMap["5"] = "Response with Message 5"
        responseModelMap["6"] = "Response with Message 6"
        responseModelMap["7"] = "Response with Message 7"
        responseModelMap["8"] = "Response with Message 8"
        responseModelMap["9"] = "Response with Message 9"
    }

    /**
     * Default response
     */
    @RequestMapping("/")
    fun getGreeting(): String? {
        logger.info("Greeting endpoint was called.")
        return "Hello Reutlingen! This time from an kotlin backend!"
    }

    /**
     * If you had enough!
     */
    @RequestMapping("/pause")
    fun getPause(): String? {
        logger.info("Pause endpoint was called.")
        return "Dude.. Take a break!"
    }

    /**
     * Gets response from Map for a specific ID, which is passed as variable into this request
     */
    @GetMapping("/response/{id}")
    fun getResponseMapById(@PathVariable id: String): String? {
        logger.info("Response endpoint was called by id $id")
        return responseModelMap[id]
    }

    @GetMapping("/response")
    fun getResponseMap(): Map<String, String>? {
        logger.info("General response endpoint was called")
        return responseModelMap
    }

    @PostMapping("/response/{id}")
    @Throws(IllegalAccessException::class)
    fun createNewResponseById(@PathVariable id: String, @RequestBody body: String): String? {
        if (responseModelMap.containsKey(id)) {
            logger.error("Error during creation of new response id. Update not supported in POST!")
            throw IllegalAccessException("Update not supported in POST!")
        }
        logger.info("New response id has been created. Id: $id, Body: $body")
        return responseModelMap.put(id, body)
    }

    @DeleteMapping("/response/{id}")
    fun deleteResponseById(@PathVariable id: String): String? {
        logger.info("Response id has been deleted. Id: $id")
        return responseModelMap.remove(id)
    }

    @PutMapping("/response/{id}")
    @Throws(IllegalAccessException::class)
    fun updateResponseById(@PathVariable id: String, @RequestBody body: String): String? {
        return if (responseModelMap.containsKey(id)) {
            logger.info("Response id has been updated. Id: $id")
            responseModelMap.put(id, body)
        } else {
            logger.error("Failure during update. Create not supported in PUT! Id: $id, Body: $body")
            throw IllegalAccessException("Create not supported in PUT!")
        }
    }
}
