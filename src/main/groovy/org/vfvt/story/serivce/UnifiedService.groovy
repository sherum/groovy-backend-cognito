package org.vfvt.story.serivce

import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service
import org.vfvt.story.data.model.PlotView
import org.vfvt.story.data.model.Story

@Service
@Slf4j
class UnifiedService {


    final StoryService storyService
    final PlotService plotService

    UnifiedService(StoryService storyService1,PlotService plotService1){
        storyService = storyService1
        plotService = plotService1
    }

    Story createNewStory() {
        Story story = storyService.newStory()
        PlotView plotView = plotService.newPlotView()
        story.plots.add(plotView)
        storyService.updateStory(story)
        return story
    }

    List<Story> getAllStories(){
        storyService.getAll()
    }

    Story getStory(id){
        return storyService.getStory(id)
    }

    Story updateStory(Story story){
        storyService.updateStory(story);
    }

    List<Story> updateAllStories(Story[] stories){
        this.storyService.updateAll(stories)
    }

    def deleteStory(String id){
        this.storyService.deleteStory(id)
    }


    //PLOT SERVICE

    List<PlotView> getAllPlots(){
        return plotService.getAll()
    }

    PlotView getPlot(String id){
        return plotService.getPlot(id)
    }

    PlotView newPlot(){
        return plotService.newPlotView()
    }

    PlotView updatePlot(PlotView plotView){
        plotService.updatePlot(plotView)
    }

    def removePlot(String id, boolean children){
        this.plotService.removePlot(id,children)
    }

    def deletePlot(String id){
        this.plotService.deletePlot(id)
    }


}
