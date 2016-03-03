package org.gephi.plugins.submenu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "File",
id = "org.gephi.desktop.filters.TestAction")
@ActionRegistration(displayName = "#CTL_TestAction")
@ActionReferences({
    @ActionReference(path = "Menu/Plugins", position = 3333)
})
@Messages("CTL_TestAction=CorePeriphery")
public final class CorePeriphery implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame jframe = new JFrame();
        MyPanelTopComponent myPanel = new MyPanelTopComponent();jframe.setLayout(new BorderLayout());
        jframe.add(myPanel);
        jframe.setSize(900, 600);
        jframe.setVisible(true);
    }
}
