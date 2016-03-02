package org.gephi.plugins.submenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "File",
        id = "org.gephi.desktop.filters.RemoveSelfLoopsAction")
@ActionRegistration(displayName = "#CTL_RemoveSelfLoopsAction")
@ActionReferences({
    @ActionReference(path = "Menu/Plugins", position = 5000)
})
@Messages("CTL_RemoveSelfLoopsAction=Remove self loops")
public final class SelfLoop implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        //Get the current graph model
        GraphController gc = Lookup.getDefault().lookup(GraphController.class);
        GraphModel graphModel = gc.getGraphModel();

        if (graphModel != null) {
            //Remove self loops
            int removed = 0;
            Graph graph = graphModel.getGraph();
            graph.writeLock();
            for (Edge edge : graph.getEdges().toArray()) {
                if (edge.isSelfLoop()) {
                    graph.removeEdge(edge);
                    removed++;
                }
            }
            graph.writeUnlock();

            //Notification message
            NotifyDescriptor d = new NotifyDescriptor.Message(removed + " self-loop have been removed", NotifyDescriptor.INFORMATION_MESSAGE);
            DialogDisplayer.getDefault().notify(d);
        } else {
            //Error message
            NotifyDescriptor d = new NotifyDescriptor.Message("No active workspace", NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d);
        }

    }
}
