package org.vfvt.story.data.model

import groovy.transform.Canonical
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Canonical
@Document(collection = "plots")
class PlotView {

    String name
    @Id
    String id
    String parentId
    String type
    String description
    List<PlotView> subplots = []

    PlotView(){
        this.id = UUID.randomUUID().toString()
    }
}
