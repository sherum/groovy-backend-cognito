package org.vfvt.story.data.model

import groovy.transform.Canonical
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "stories")
@Canonical
class Story {
    @Id
    String id

    String author

    String title

    String genre

    String maguffin

    String summary


    List<String> plots = []


    List<String> scenes = []


    List<String> locations = []


    List<String> things = []


    List<String> events = []


    List<String> people = []

    Story() {
        this.id = UUID.randomUUID().toString()
    }
}

@Canonical
@Document(collection = "people")
class Person {
    @Id
    String id

    String name

    String type

    String description

    Person() {
        this.id = UUID.randomUUID().toString()
    }
}

@Canonical
@Document(collection = "events")
class Event {
    @Id
    String id

    String name

    String description

    String type //incident, event, season

    Location location

    Event() {
        this.id = UUID.randomUUID().toString()
    }
}

@Canonical
@Document(collection = "locations")
class Location {
    @Id
    String id

    String name

    String description

    Location() {
        this.id = UUID.randomUUID().toString()
    }
}

@Canonical
@Document(collection = "things")
class Thing {
    @Id
    String id

    String name

    String description

    Thing() {
        this.id = UUID.randomUUID().toString()
    }
}

@Canonical
@Document(collection = "plots")
class Plot {
    @Id
    String id
    String name
    String type
    String description
    String parentId

    Plot() {
        this.id = UUID.randomUUID().toString()
    }

    Plot findParent(List<Plot> plots){
        Plot parent = plots.find {p -> p.id == parentId}
    }

}

@Canonical
@Document(collection = "scenes")
class Scene {
    @Id
    String id

    String dtg

    String name

    String setting

    String plotPoints

    String summary


    Location location

    @DBRef(lazy = true)
    List<Plot> scenePlots = []

    @DBRef(lazy = true)
    List<Thing> sceneThings = []

    @DBRef(lazy = true)
    List<Event> sceneEvents = []

    @DBRef(lazy = true)
    List<Person> scenePeople = []

    Scene() {
        this.id = UUID.randomUUID().toString()
    }

}


