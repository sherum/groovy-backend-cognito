package org.vfvt.story.serivce

import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service
import org.vfvt.story.data.model.Plot
import org.vfvt.story.data.model.Story
import org.vfvt.story.data.mongo.StoryRepo

@Service
@Slf4j
class StoryService {

    final StoryRepo storyRepo


    StoryService(StoryRepo mongoRepo){
        this.storyRepo = mongoRepo

    }

    Story getStory(String id) {
        return this.storyRepo.findById(id).get()
    }

    List<Story> getAll() {
        return this.storyRepo.findAll()
    }

    Story saveStory(Story story) {
        log.info("Saving new story ${story}")
        return this.storyRepo.save(story)
    }

    Story newStory() {
        def story = new Story()
        story.title = "New Story"
        story.author = "New Author"
        story.genre = "New Genre"
        story.maguffin = "New Maguffin"
        story.summary = "New Summary"
        println(story.toString())
        return this.storyRepo.insert(story)
    }

    def updateStory(Story story) {
        log.info("Updating existing story ${story}")
        this.storyRepo.save(story)
    }

    def updateAll(Story[] story) {
        log.info("Updating existing story ${story}")
        this.storyRepo.saveAll(story)
    }


    def deleteStory(String storyId) {
        log.info("Story ID: ${storyId} deleted")
        this.storyRepo.deleteById(storyId)
    }

    boolean addSubplot(Story story, Plot plot) {
        boolean stored = story.plots.add(plot)
        Story saved = storyRepo.save(story)
        if (story) {
            return stored
        } else {
            return false
        }

    }



}
