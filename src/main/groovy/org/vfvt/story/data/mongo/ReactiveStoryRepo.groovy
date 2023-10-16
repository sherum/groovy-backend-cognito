package org.vfvt.story.data.mongo

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import org.vfvt.story.data.model.Story

@Repository
interface ReactiveStoryRepo extends ReactiveMongoRepository<Story,String> {

}