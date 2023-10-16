package org.vfvt.story.controller

import groovy.util.logging.Slf4j
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.vfvt.story.data.model.Story
import org.vfvt.story.serivce.ReactiveStoryService
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@RestController
@RequestMapping(path="/story/rx")
@Slf4j
class ReactiveStoryController {

    final ReactiveStoryService reactiveStoryService

    ReactiveStoryController(ReactiveStoryService storyService1){
        this.reactiveStoryService = storyService1
    }

    @GetMapping()
    Flux<Story> getStories(){
        return this.reactiveStoryService.getAll()
    }
    @GetMapping(value="/{id}")
    Mono<Story> getStory(@PathVariable String id){
        return this.reactiveStoryService.getStory(id)
    }

    @PostMapping(value="/create")
    Mono<Story> createStory(@RequestBody Story story){
        return this.reactiveStoryService.saveStory(story)
    }

    @PostMapping
    Mono<Story> newStory(){
        def story = this.reactiveStoryService.newStory()
        log.info("story new ${story}")
        return story
    }

    @PutMapping
    ResponseEntity<Mono<Story>> updateStory(@RequestBody Story story){
        log.info("updating...${story}")
        Mono<Story> updated =  this.reactiveStoryService.updateStory(story)
        return  ResponseEntity.ok(updated)
    }
}
