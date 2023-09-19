package org.vfvt.story.data.model

import groovy.transform.Canonical

@Canonical
class Dao {

    String id
    String name
    String type
    String description
    List<Dao> childPlot
    String parentId
    boolean topPlot


    static Dao create(Plot plot){
        Dao dao = new Dao()
        dao.id = plot.id
        dao.name = plot.name
        dao.type = plot.type
        dao.description = plot.description
        dao.topPlot = dao.id == plot.parentId?true:false
    }

    static List<Dao> createDaoList(List<Plot> plots){
        Map<Plot,Plot> childParent = new HashMap<>()
        List<Dao> daoList = []

        for (Plot c in plots){
            Plot parent = c.findParent(plots)
            childParent.put(c,parent)
            def cDao = Dao.create(c)
            def pDao = Dao.create(parent)
            if(daoList.contains(cDao) && daoList.contains(parent)){
                def child = daoList.find(){cDao}
                def par = daoList.find(){pDao}
                par.childPlot.add(child)
            }else if(!daoList.contains(cDao) && daoList.contains(parent)){
                def par = daoList.find(){pDao}
                par.childPlot.add(cDao)
                daoList.add(cDao)
            }else if(daoList.contains(cDao) && !daoList.contains(parent)){
                def child = daoList.find(){cDao}
                pDao.childPlot.add(child)
                daoList.add(pDao)
            }else{
                pDao.childPlot.add(cDao)
                daoList.addAll([pDao,cDao])
            }
        }
     return daoList
    }
}
