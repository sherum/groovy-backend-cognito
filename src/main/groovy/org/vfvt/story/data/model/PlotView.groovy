package org.vfvt.story.data.model

import groovy.transform.Canonical
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Canonical
@Document(collection = "plots")
class PlotView {

    String name
    @Id
    String id

    boolean topPlot
    String type
    String description
    List<String> subplots = []

    PlotView(){
        id = UUID.randomUUID().toString()
        topPlot = false
    }

}
