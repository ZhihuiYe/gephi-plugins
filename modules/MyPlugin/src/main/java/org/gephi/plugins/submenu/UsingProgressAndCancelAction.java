package org.gephi.plugins.submenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.gephi.utils.longtask.api.LongTaskExecutor;
import org.gephi.utils.longtask.spi.LongTask;
import org.gephi.utils.progress.Progress;
import org.gephi.utils.progress.ProgressTicket;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "File",
id = "org.gephi.desktop.filters.UsingProgressAndCancelAction")
@ActionRegistration(displayName = "#CTL_UsingProgressAndCancelAction")
@ActionReferences({
    @ActionReference(path = "Menu/Plugins", position = 7000)
})
@Messages("CTL_UsingProgressAndCancelAction=Test progress and cancel")
public final class UsingProgressAndCancelAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        LongTaskExecutor executor = new LongTaskExecutor(true);
        LongTaskExample longTaskExample = new LongTaskExample();
        executor.execute(longTaskExample, longTaskExample, "Task...", null);
    }

    private static class LongTaskExample implements LongTask, Runnable {

        private ProgressTicket progressTicket;
        private boolean cancelled;

        @Override
        public void run() {
            int waitSeconds = 5;
            Progress.start(progressTicket, waitSeconds);
            for (int i = 0; i < waitSeconds && !cancelled; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Exceptions.printStackTrace(ex);
                }
                Progress.progress(progressTicket);
            }
            Progress.finish(progressTicket);
        }

        @Override
        public boolean cancel() {
            cancelled = true;
            return true;
        }

        @Override
        public void setProgressTicket(ProgressTicket pt) {
            this.progressTicket = pt;
        }
    }
}