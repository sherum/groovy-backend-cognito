package org.vfvt.story.controller

import groovy.util.logging.Slf4j
import org.springframework.web.bind.annotation.*
import org.vfvt.story.data.model.Story
import org.vfvt.story.serivce.UnifiedService

@RestController
@Slf4j
@RequestMapping(value = "/story", consumes = 'application/json')
class StoryController {

    final UnifiedService unifiedService

    StoryController(UnifiedService unifiedService1) {
        unifiedService = unifiedService1
    }

    @GetMapping()
    List<Story> getStories() {
        return this.unifiedService.getAllStories()
    }

    @GetMapping(value = "/{id}")
    Story getStory(@PathVariable String id) {
        log.info("get story with id: $id")
        return this.unifiedService.getStory(id)
    }

//    @PostMapping(value="/create")
//    Story createStory(@RequestBody Story story){
//        return this.storyService.saveStory(story)
//    }

    @PostMapping
    Story newStory() {
        def story = this.unifiedService.createNewStory()
        log.info("story new ${story}")
        return story
    }

    @PutMapping
    Story updateStory(@RequestBody Story story) {
        log.info("updating...${story}")
        return this.unifiedService.updateStory(story)
    }

    @PutMapping(path = "/saveall")
    Story updateAll(@RequestBody Story[] story) {
        log.info("updating...${story}")
        return this.unifiedService.updateAllStories(story)
    }

    @DeleteMapping(value = "/{id}")
    def deleteStory(@PathVariable String id) {
        this.unifiedService.deleteStory(id)
    }
}
