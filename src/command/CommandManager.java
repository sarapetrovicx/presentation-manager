package command;

import gui.components.MainFrame;

import java.util.ArrayList;

public class CommandManager {
    private ArrayList<AbstractCommand> commands = new ArrayList<>();
    private int currentCommand = 0;

    public void addCommand(AbstractCommand command){
        while(currentCommand < commands.size())
            commands.remove(currentCommand);
        commands.add(command);
        doCommand();
    }

    public void doCommand(){
        if(currentCommand < commands.size()){
            commands.get(currentCommand++).redoCommand();
            MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(true);
        }
        if(currentCommand==commands.size()){
            MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(false);
        }
    }

    public void undoCommand(){
        if(currentCommand > 0){
            MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(true);
            commands.get(--currentCommand).undoCommand();
        }
        if(currentCommand==0){
            MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(false);
        }
    }

}
