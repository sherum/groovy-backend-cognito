package org.vfvt.story.serivce

import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.vfvt.story.data.model.Plot
import org.vfvt.story.data.mongo.PlotRepo

@Service
class PlotService {

    final PlotRepo plotRepo
    final StoryService storyService

    PlotService(PlotRepo plotRepo, StoryService storyService) {
        this.plotRepo = plotRepo
        this.storyService = storyService
    }

    Plot getPlot(String id) {
        return this.plotRepo.findById(id)
    }

    List<Plot> getAll() {
        return this.plotRepo.findAll()
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

    boolean addPlotToStory(String storyId, String plotId) {
        boolean savedPlot=false
        def story = storyService.getStory(storyId)
        def plot = getPlot(plotId)
        story.plots.add(plot)
        for (Plot p : story.plots){
            if(p.id == plotId) savedPlot = true
        }
        return savedPlot
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


}

