import com.intellij.ide.projectView.ProjectView;
import com.intellij.ide.projectView.impl.AbstractProjectViewPane;
import com.intellij.ide.projectView.impl.ProjectViewImpl;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.openapi.wm.ex.ToolWindowEx;

import javax.swing.*;

public class ResizeProjectViewAction extends AnAction {
    
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        Project project = anActionEvent.getProject();
        
        ToolWindowManager instance = ToolWindowManager.getInstance(project);
        
        String toolWindowID = "Project";
        ToolWindowEx tw = (ToolWindowEx) instance.getToolWindow(toolWindowID);
        
        ProjectViewImpl projectView = (ProjectViewImpl) ProjectView.getInstance(project);
        
        AbstractProjectViewPane currentProjectViewPane = projectView.getCurrentProjectViewPane();
    
        JTree jTree = currentProjectViewPane.getTree();
    
        int longestPathIndex = ResizerUtil.longestPathValueOfTree(jTree);
    
        int desiredWidth = ResizerUtil.calculateDesiredLength(jTree, longestPathIndex);
        System.out.println("desiredWidth = " + desiredWidth);
        
        int width = tw.getComponent()
                      .getWidth();
        tw.stretchWidth(desiredWidth - width);
    
        ResizerUtil.longestPathValueOfTree(jTree);
    
        // TODO: 10.01.19  
        //org.jetbrains.idea.maven.project.MavenProjct;
    
        //MavenProjectsManager projectsManager = MavenProjectsManager.getInstance(project);
    }
    
}
