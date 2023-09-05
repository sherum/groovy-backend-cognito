package org.vfvt.story.serivce

import groovy.util.logging.Slf4j
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import groovy.transform.Canonical
import org.springframework.stereotype.Service
import org.vfvt.story.data.model.*
import org.vfvt.story.data.mongo.StoryRepo


@Service
@Slf4j
class StoryService {

    final StoryRepo mongoRepo

    StoryService(StoryRepo mongoRepo) {
        this.mongoRepo = mongoRepo
    }

    Story getStory(String id) {
        return this.mongoRepo.findById(id).get()
    }

    List<Story> getAll() {
        return this.mongoRepo.findAll()
    }

    Story saveStory(Story story) {
        log.info("Saving new story ${story}")
        return this.mongoRepo.save(story)
    }

    Story newStory() {
        def story = new Story()
        story.title = "New Story"
        story.author = "New Author"
        story.genre = "New Genre"
        story.maguffin = "New Maguffin"
        story.summary = "New Summary"
        println (story.toString())
        return this.mongoRepo.save(story)
    }

    def updateStory(Story story) {
        this.mongoRepo.save(story)
        }


    def deleteStory(String storyId) {
        this.mongoRepo.delete(this.mongoRepo.findById(storyId))
    }

    boolean addSubplot(Story story, Plot plot) {
        boolean stored = story.plots.add(plot)
        Story saved = mongoRepo.save(story)
        if (story) {
            return stored
        } else {
            return false
        }

    }

}
