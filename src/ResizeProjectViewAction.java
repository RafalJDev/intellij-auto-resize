import com.intellij.ide.projectView.ProjectView;
import com.intellij.ide.projectView.impl.AbstractProjectViewPane;
import com.intellij.ide.projectView.impl.ProjectViewImpl;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.openapi.wm.ex.ToolWindowEx;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class ResizeProjectViewAction extends AnAction {
    
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        Project project = anActionEvent.getProject();
        
        ToolWindowManager instance = ToolWindowManager.getInstance(project);
        
        String toolWindowID = "Project";
        ToolWindowEx tw = (ToolWindowEx) instance.getToolWindow(toolWindowID);
        
        ProjectViewImpl projectView = (ProjectViewImpl) ProjectView.getInstance(project);
        
        AbstractProjectViewPane currentProjectViewPane = projectView.getCurrentProjectViewPane();
        
        JTree tree = currentProjectViewPane.getTree();
        
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel()
                                                                   .getRoot();
        
        System.out.println("root.getClass() = " + root.getClass());
        
        Container parent = currentProjectViewPane.getTree()
                                                 .getParent();
        
        Component component = parent.getComponent(0);
        
        //TreeNode child = root.getChild(parent, 1);
        
        System.out.println("projectView.getCurrentProjectViewPane()\n                   .getTree()\n                 "
                               + "  " + ".getAccessibleContext()\n                   .getAccessibleTable() = " + projectView.getCurrentProjectViewPane()
                                                                                                                                                                                                                    .getTree()
                                                                                                                                                                                                                    .getAccessibleContext()
                                                                                                                                                                                                                    .getAccessibleTable());
        
        int desiredWidth = 200;
        
        int width = tw.getComponent()
                      .getWidth();
        tw.stretchWidth(desiredWidth - width);
    }
    
}
