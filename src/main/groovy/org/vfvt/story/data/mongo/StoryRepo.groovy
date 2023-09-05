package org.vfvt.story.data.mongo

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import org.vfvt.story.data.model.Event
import org.vfvt.story.data.model.Location
import org.vfvt.story.data.model.Person
import org.vfvt.story.data.model.Plot
import org.vfvt.story.data.model.Scene
import org.vfvt.story.data.model.Story
import org.vfvt.story.data.model.Thing

@Repository
interface StoryRepo extends  MongoRepository<Story,String> {
}

@Repository
interface PlotRepo extends  MongoRepository<Plot,String>{
}
@Repository
interface SceneRepo extends  MongoRepository<Scene,String>{
}
@Repository
interface PersonRepo extends  MongoRepository<Person,String>{
}
@Repository
interface LocationRepo extends  MongoRepository<Location,String>{
}
@Repository
interface EventRepo extends  MongoRepository<Event,String>{
}
@Repository
interface ThingRepo extends  MongoRepository<Thing,String>{
}

