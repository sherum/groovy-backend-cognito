package org.vfvt.story.serivce

import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service
import org.vfvt.story.data.model.PlotView
import org.vfvt.story.data.mongo.PlotRepo

@Slf4j
@Service
class PlotService {

    final PlotRepo plotRepo
    final StoryService storyService

    PlotService(PlotRepo plotRepo, StoryService storyService) {
        this.plotRepo = plotRepo
        this.storyService = storyService
    }

    PlotView getPlot(String id){
        return plotRepo.getById(id);
    }


    List<PlotView> getAll() {
        def plots=  this.plotRepo.findAll()
        log.info("plots from repo ${plots.each {it.toString();println()}}")
        return plots
    }


    boolean addSubplot(PlotView plot, PlotView subplot) {
        return plot.subplots.add(subplot)
    }

    PlotView addPlotViewToStory(String storyId) {
        def story = storyService.getStory(storyId)
        PlotView plotView = newPlotView()
        story.plots.add(plotView)
        storyService.saveStory(story)
        return plotView
    }


    /*
    import/export functions?

    List<PlotView> savePlotViews(List<PlotView> plots) {
        return this.plotRepo.saveAll(plots)
    }
    */


    PlotView addChildPlotView(PlotView plotView) {
        log.info("Presubplot $plotView")
        def childPV = newPlotView();
        childPV.topPlot = false
//        childPV.parentId = plotView.id
//        childPV.storyId = plotView.storyId
        plotView.subplots.add(childPV)
        log.info "post subplot $plotView"
        return plotView
    }

    /* unused for now
    def deletePlotView(String storyId, String plotId) {
        PlotView plot = getPlotView(storyId, plotId)
        this.currentStory.plots.remove(plot)
        saveCurrentStory()
    }
    */

    PlotView newPlotView() {
        def plot = new PlotView()
        plot.name = "New Plot"
        plot.type = "New Type"
        plot.description = "New Description"
        log.info("passed a plot $plot")
        return plotRepo.insert(plot)
    }

    PlotView savePlotView(PlotView plotView) {
        log.info("passed a story $plotView ")
        return  plotRepo.save(plotView)
    }

    def updatePlot(PlotView plotView){
        log.info("Updating this $plotView")
        plotRepo.save(plotView)
    }

    def removePlot(String plotId, boolean deleteDecendents) {
        deleteDecendents ? println("recursive delete") : println("don't delete")
        this.plotRepo.deleteById(plotId)
    }

    def deletePlot(String plotId) {
        this.plotRepo.deleteById(plotId)
    }
}

