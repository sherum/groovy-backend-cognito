package org.vfvt.story.serivce

import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service
import org.vfvt.story.data.model.PlotView
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


    PlotView getPlotView(String storyId, String plotId) {
        this.setCurrentStory(storyId)
        return this.currentStory.plots.find {plot ->plot.id == plotId}
    }

    List<PlotView> getAll(String storyId) {
        this.setCurrentStory(storyId)
        return this.currentStory.plots
    }

    PlotView newPlotView(String storyId){
        this.setCurrentStory(storyId)
        def plot = new PlotView();
        plot.name = "PlotView Name"
        plot.type = "PlotView type"
        plot.description="PlotView description"
        plot.parentId = "none"
        log.info(plot.toString())
      //  def newplot = this.plotRepo.insert(plot)
        this.currentStory.plots.add(plot)
        this.storyService.updateStory(this.currentStory)
        log.info("Line 49 PlotView service: ${this.currentStory} ")
        return plot
    }

    PlotView savePlotView(PlotView PlotView) {
        return this.plotRepo.save(PlotView)
    }

    PlotView updatePlotView(PlotView plot) {
        PlotView plotOld = getPlotView(plot.id)
        plotOld = PlotView
        return this.plotRepo.save(plotOld)
    }

    List<PlotView> savePlotViews(List<PlotView> plots){
        return this.plotRepo.saveAll (plots)
    }

    PlotView addPlotViewToStory(String storyId,PlotView plot) {
        def story = storyService.getStory(storyId)
        story.plots.add(plot)
        storyService.saveStory(story)
        return plot
    }

    boolean addSubplot(PlotView plot, PlotView subplot){
        return  plot.subplots.add(subplot)
    }

    PlotView addChildPlotView(String parentId, String childId ){
        def parent = getPlotView(parentId)
        def child = getPlotView(childId)
        addSubplot(parent,child)
        return parent
    }
    def deletePlotView(String storyId, String plotId){
        PlotView plot = getPlotView(storyId,plotId)
        this.currentStory.plots.remove(plot)
        saveCurrentStory()
    }

    def saveCurrentStory(){
        this.storyService.saveStory(this.currentStory)
    }



}

