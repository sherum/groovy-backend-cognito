package org.vfvt.story.controller

import groovy.util.logging.Slf4j
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.vfvt.story.data.model.Story
import org.vfvt.story.serivce.*

@RestController
@Slf4j
@RequestMapping(value="/story",consumes = 'application/json')
class StoryController {

    final StoryService storyService

    StoryController(StoryService storyService1){
        this.storyService = storyService1
    }

    @GetMapping()
    List<Story> getStories(){
        return this.storyService.getAll()
    }
    @GetMapping(value="/{id}")
    Story getStory(@PathVariable String id){
        return this.storyService.getStory(id)
    }

    @PostMapping(value="/create")
    Story createStory(@RequestBody Story story){
        return this.storyService.saveStory(story)
    }

    @PostMapping
    Story newStory(){
        def story = this.storyService.newStory()
        log.info("story new ${story}")
        return story
    }

    @PutMapping
    Story updateStory(@RequestBody Story story){
        log.info("updating...${story}")
        return this.storyService.updateStory(story)
    }
    @PutMapping(path="/saveall")
    Story updateAll(@RequestBody Story[] story){
        log.info("updating...${story}")
        return this.storyService.saveall(story)
    }

    @DeleteMapping(value="/{id}")
    def deleteStory(@PathVariable String id){
        this.storyService.deleteStory(id)
    }
}
