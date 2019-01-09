import com.intellij.ide.projectView.ProjectView;
import com.intellij.ide.projectView.impl.AbstractProjectViewPane;
import com.intellij.ide.projectView.impl.ProjectViewImpl;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.openapi.wm.ex.ToolWindowEx;

import javax.swing.*;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

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
    
        System.out.println("tree.getRowCount() = " + tree.getRowCount());
    
        System.out.println("-----------------");
        System.out.println("Paths with for parents");
        System.out.println("-----------------");
    
        IntStream.range(0, tree.getRowCount())
                 .forEach(value -> System.out.println(value + " name= " + tree.getPathForRow(value)
                                                                              .getParentPath()
                                                                              .toString()));
    
        System.out.println("-----------------");
        System.out.println("Path for childs");
        System.out.println("-----------------");
    
        IntStream.range(0, tree.getRowCount())
                 .forEach(value -> System.out.println(value + " name= " + tree.getPathForRow(value)
                                                                              .toString()));
    
        System.out.println("-----------------");
    
        int desiredWidth = tree.getPathBounds(tree.getPathForRow(9)).width + tree.getPathBounds(tree.getPathForRow(9)).x;
        System.out.println("desiredWidth = " + desiredWidth);
        
        int width = tw.getComponent()
                      .getWidth();
        tw.stretchWidth(desiredWidth - width);
    
        longestPathValueOfTree(tree);
    }
    
    int longestPathValueOfTree(JTree tree) {
        List<Integer> list = IntStream.range(0, tree.getRowCount())
                                      .map(rowIndex -> tree.getPathForRow(rowIndex)
                                                           .toString()
                                                           .length())
                                      .boxed()
                                      .collect(toList());
        
        System.out.println("List length:");
        list.forEach(System.out::println);
        
        Integer max = list.get(0);
        for (final Integer integer : list) {
            if (integer > max) {
                max = integer;
            }
        }
        
        return list.indexOf(max);
    }
    
}
