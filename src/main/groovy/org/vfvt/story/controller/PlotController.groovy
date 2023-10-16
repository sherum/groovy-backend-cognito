package org.vfvt.story.controller

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.util.logging.Slf4j
import org.springframework.web.bind.annotation.*
import org.vfvt.story.data.model.PlotView
import org.vfvt.story.serivce.UnifiedService

@Slf4j
@RestController
@RequestMapping(path = "/plot", consumes = 'application/json')
class PlotController {

    final UnifiedService unifiedService


    ObjectMapper mapper

    PlotController( UnifiedService unifiedService) {
        this.unifiedService = unifiedService
        this.mapper = new ObjectMapper()
    }

    @GetMapping()
    List<PlotView> getStories() {
        return this.unifiedService.getAllPlots()
    }

    @GetMapping("/{id}")
    PlotView getPlot(@PathVariable String id) {
        return this.unifiedService.getPlot(id)
    }

    @PostMapping
    PlotView newPlot() {
        def plot = this.unifiedService.newPlot()
        log.info("plot new $plot")
        return plot
    }

//    @PostMapping(path="/newref")
//    PlotView newPlot(@RequestBody HashMap<String,String> map) {
//        String storyId = map.get("body").substring(3)
//        log.info("New Ref: $storyId")
//        def plot = this.unifiedService.addPlotViewTSotory(storyId)
//        log.info("plot new $plot")
//        return plot
//    }

    @PutMapping("/new")
    PlotView topPlot() {
        PlotView plot = newPlot()
        plot.topPlot = true
        return updatePlot(plot)
    }

//    @PostMapping(path = "/insert")
//    PlotView insertPlot(@RequestBody HashMap<String, String> payload) {
//        String storyId = payload.get("storyId")
//        def plot = this.unifiedService.newPlotView(storyId)
//        log.info("Controller plot inserted $plot")
//        return plot
//    }

    @PutMapping
    PlotView updatePlot(@RequestBody PlotView payload) {
        log.info("updaing plot #59 $payload")
        return unifiedService.updatePlot(payload)
    }


    @PostMapping("/subplot")
    PlotView addSubplot(@RequestBody PlotView parent) {
        String id = parent.id
        log.info("controller add subplot")
        return unifiedService.addSubplot(id)

    }

    @DeleteMapping(path = "/{plotId}/{decendents}")
    def deletePlot(@PathVariable String plotId, @PathVariable boolean decendents) {
        this.unifiedService.removePlot(plotId, decendents);
    }

//    @PutMapping
//    boolean addPlot(@RequestBody Map<String,String> payload){
//        String storyId = payload.get("body")
//        String plotId = payload.get("plotId")
//        return this.unifiedService.addPlotToStory(storyId,plotId)
//    }

    @DeleteMapping(path = "/{plotId}")
    def deletePlot(@PathVariable String plotId) {
        this.unifiedService.deletePlot(plotId)
    }


}