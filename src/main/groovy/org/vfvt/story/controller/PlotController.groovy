package org.vfvt.story.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.sun.net.httpserver.Headers
import groovy.util.logging.Slf4j
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.vfvt.story.serivce.PlotService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.vfvt.story.data.model.*

@Slf4j
@RestController
@RequestMapping(path="/plot",consumes = 'application/json')
class PlotController {

    final PlotService plotService

    ObjectMapper mapper

    PlotController(PlotService plotService1){
        this.plotService = plotService1
        this.mapper = new ObjectMapper()
    }

    @GetMapping()
    List<Plot> getStories(){
        return this.plotService.all()
    }
    @GetMapping("/{id}")
    Plot getPlot(@PathVariable String id){
        return this.plotService.getPlot(id)
    }

    @PostMapping(path="/new")
    Plot newPlot(@RequestBody String payload){
        log.info("new plot ",payload)
        String storyId = payload.substring(3)
        log.info("adjusted story ${storyId}")
        def plot = this.plotService.newPlot(storyId)
        log.info("plot new ${plot}")
        return plot
    }


    @PutMapping
    boolean addPlot(@RequestBody Map<String,String> payload){
        String storyId = payload.get("body")
        String plotId = payload.get("plotId")
        return this.plotService.addPlotToStory(storyId,plotId)
    }
    @PutMapping("/subplot")
    Plot addSubplot(@RequestBody Map<String,String>payload){

    }

    @DeleteMapping(path="/{storyId}/{plotId}")
    def deletePlot(@PathVariable String storyId, @PathVariable String plotId){
        this.plotService.deletePlot(storyId,plotId)
    }


}