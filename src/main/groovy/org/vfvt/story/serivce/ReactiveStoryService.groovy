package org.vfvt.story.serivce

import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service
import org.vfvt.story.data.model.Plot
import org.vfvt.story.data.model.Story
import org.vfvt.story.data.mongo.ReactiveStoryRepo
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Service
@Slf4j
class ReactiveStoryService {

    final ReactiveStoryRepo reactiveStoryRepo

    ReactiveStoryService(ReactiveStoryRepo reactiveStoryRepo) {
        this.reactiveStoryRepo = reactiveStoryRepo
    }

    Mono<Story> getStory(String id) {
        return this.reactiveStoryRepo.findById(id).get()
    }

    Flux<Story> getAll() {
        return this.reactiveStoryRepo.findAll()
    }

    Mono<Story> saveStory(Story story) {
        log.info("Saving new story ${story}")
        return this.reactiveStoryRepo.save(story)
    }

    Mono<Story> newStory() {
        def story = new Story()
        story.title = "New Story"
        story.author = "New Author"
        story.genre = "New Genre"
        story.maguffin = "New Maguffin"
        story.summary = "New Summary"
        println (story.toString())
        return this.reactiveStoryRepo.insert(story)
    }

    Mono<Story> updateStory(Story story) {
        return this.reactiveStoryRepo.save(story)
        }

    def deleteStory(String storyId) {
        this.reactiveStoryRepo.delete(this.reactiveStoryRepo.findById(storyId))
    }

    boolean addSubplot(Story story, Plot plot) {
        boolean stored = story.plots.add(plot)
        Story saved = reactiveStoryRepo.save(story)
        if (story) {
            return stored
        } else {
            return false
        }

    }

}
