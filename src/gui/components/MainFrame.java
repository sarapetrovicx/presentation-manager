package gui.components;

import command.CommandManager;
import controller.ActionManager;
import controller.MyWindowListener;
import controller.actions.error.ErrorFactory;
import gui.slots.model.Slot;
import gui.tree.model.MyTreeNode;
import gui.tree.view.MyJTree;
import gui.tree.view.ProjekatView;
import gui.tree.observer.ISubscriber;
import gui.tree.observer.Message;
import lombok.Getter;
import lombok.Setter;
import model.workspace.Projekat;
import model.workspace.Workspace;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

@Getter
@Setter

public class MainFrame extends JFrame implements ISubscriber {

    private static MainFrame instance = null;
    private ActionManager actionManager;
    private JMenuBar menu;
    private JToolBar toolBar;
    private JPanel panel2;
    private JSplitPane splitPane;
    private JScrollPane scrollPane;
    private ProjekatView projekatView;
    private JPanel desniPanel;

    private MyJTree jTree;
    private Workspace workspace;
    private CommandManager commandManager;
    private Slot selectedSlot;

    private MainFrame() {
    }

    public void init(){

        actionManager = new ActionManager();
        commandManager = new CommandManager();
        selectedSlot = null;
        initialiseWorkspaceTree();
        initGUI();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        addWindowListener(new MyWindowListener());
    }

    private void initialiseWorkspaceTree() {

        workspace = new Workspace("Workspace", null);
        MyTreeNode myTreeNode = new MyTreeNode(workspace);
        DefaultTreeModel model = new DefaultTreeModel(myTreeNode);
        jTree = new MyJTree(model);
    }

    private void initGUI(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("RuDok");

        menu = new Menu();
        setJMenuBar(menu);

        toolBar = new ToolBar();
        add(toolBar, BorderLayout.NORTH);

        projekatView = new ProjekatView(new Projekat("", workspace));

        desniPanel = new JPanel();
        desniPanel.setLayout(new BorderLayout());
        desniPanel.add(projekatView, BorderLayout.CENTER);
        desniPanel.setMinimumSize(new Dimension(400,400));

        scrollPane = new JScrollPane(jTree, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        splitPane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, desniPanel);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(200);

        ErrorFactory.getInstance().addSubscriber(this);

        add(splitPane);

    }

    public static MainFrame getInstance() {
        if(instance == null){
            instance = new MainFrame();
            instance.init();
        }
        return instance;
    }

    @Override
    public void update(Object notification, Message message) {
        JOptionPane.showMessageDialog(this, notification);
    }

}
