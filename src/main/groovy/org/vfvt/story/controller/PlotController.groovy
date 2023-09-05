package org.vfvt.story.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.web.bind.annotation.PatchMapping
import org.vfvt.story.serivce.PlotService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.vfvt.story.data.model.*


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

    @PostMapping
    Plot newPlot(@RequestBody Plot plot){
        return this.plotService.savePlot(plot)
    }

    @PostMapping("/plots")
    Plot newPlot(@RequestBody List<Plot> plots){
        List<Plot> json = mapper.readValue(plots,Plot.class)
        println "we got this far"
        return this.plotService.savePlots(json)
    }

    @PutMapping
    boolean addPlot(@RequestBody Map<String,String> payload){
        String storyId = payload.get("storyId")
        String plotId = payload.get("plotId")
        return this.plotService.addPlotToStory(storyId,plotId)
    }
    @PutMapping("/subplot")
    Plot addSubplot(@RequestBody Map<String,String>payload){

    }


}