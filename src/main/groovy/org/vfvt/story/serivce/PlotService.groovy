package org.vfvt.story.serivce

import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service
import org.vfvt.story.data.model.Plot
import org.vfvt.story.data.model.Story
import org.vfvt.story.data.mongo.PlotRepo

@Slf4j
@Service
class PlotService {

    final PlotRepo plotRepo
    final StoryService storyService
    Story currentStory;

    PlotService(PlotRepo plotRepo, StoryService storyService) {
       this.plotRepo = plotRepo
       this.storyService = storyService
    }

    def setCurrentStory(String storyId){
        log.info("Story id ${storyId}")
        this.currentStory = this.storyService.getStory(storyId)
        log.info("the current story is ${currentStory}")
    }


    Plot getPlot(String storyId,String plotId) {
        this.setCurrentStory(storyId)
        return this.currentStory.plots.find {plot ->plot.id == plotId}
    }

    List<Plot> getAll(String storyId) {
        this.setCurrentStory(storyId)
        return this.currentStory.plots
    }

    Plot newPlot(String storyId){
        this.setCurrentStory(storyId)
        def plot = new Plot();
        plot.name = "Plot Name"
        plot.type = "Plot type"
        plot.description="Plot description"
        plot.parentId = "none"
        log.info(plot.toString())
      //  def newplot = this.plotRepo.insert(plot)
        this.currentStory.plots.add(plot)
        this.storyService.updateStory(this.currentStory)
        log.info("Line 49 Plot service: ${this.currentStory} ")
        return plot
    }

    Plot savePlot(Plot Plot) {
        return this.plotRepo.save(Plot)
    }

    Plot updatePlot(Plot plot) {
        Plot plotOld = getPlot(plot.id)
        plotOld = Plot
        return this.plotRepo.save(plotOld)
    }

    List<Plot> savePlots(List<Plot> plots){
        return this.plotRepo.saveAll (plots)
    }

    Plot addPlotToStory(String storyId,Plot plot) {
        def story = storyService.getStory(storyId)
        story.plots.add(plot)
        storyService.saveStory(story)
        return plot
    }

    boolean addSubplot(Plot plot, Plot subplot){
        return  plot.subplots.add(subplot)
    }

    Plot addChildPlot(String parentId, String childId ){
        def parent = getPlot(parentId)
        def child = getPlot(childId)
        addSubplot(parent,child)
        return parent
    }
    def deletePlot(String storyId, String plotId){
        Story story = getCurrentStory(storyId)
        Plot plot = getPlot(storyId,plotId)
        story.plots.remove(plot)
        this.storyService.saveStory(story)
    }


}

