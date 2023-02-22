package controller;

import controller.actions.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ActionManager {

    private InfoAction infoAction;
    private NewProjectAction newProjectAction;
    private AuthorAction authorAction;
    private ImageAction imageAction;
    private NewPresentationAction presentationAction;
    private NewSlideAction newSlideAction;
    private DeleteAction deleteAction;
    private SlideShowAction slideShowAction;
    private AddSlotAction addSlotAction;
    private DeleteSlotAction deleteSlotAction;
    private MoveSlotAction moveSlotAction;
    private SelectSlotAction selectSlotAction;
    private SelectColorAction selectColorAction;
    private StrokeAction strokeAction;
    private SaveProjectAction saveProjectAction;
    private RedoAction redoAction;
    private UndoAction undoAction;
    private SharingPresentationAction sharingPresentationAction;
    private EditSlotAction editSlotAction;
    private TypeOfSlotAction typeOfSlotAction;
    private OpenProjectAction openProjectAction;
    private ExportAction exportAction;
    private ItalicAction italicAction;
    private BoldAction boldAction;
    private UnderlineAction underlineAction;

    public ActionManager() {
        init();
    }

    public void init() {
        infoAction = new InfoAction();
        newProjectAction = new NewProjectAction();
        authorAction = new AuthorAction();
        imageAction = new ImageAction();
        presentationAction = new NewPresentationAction();
        newSlideAction = new NewSlideAction();
        deleteAction = new DeleteAction();
        slideShowAction = new SlideShowAction();
        addSlotAction = new AddSlotAction();
        deleteSlotAction = new DeleteSlotAction();
        moveSlotAction = new MoveSlotAction();
        selectSlotAction = new SelectSlotAction();
        selectColorAction = new SelectColorAction();
        strokeAction = new StrokeAction();
        saveProjectAction = new SaveProjectAction();
        redoAction = new RedoAction();
        undoAction = new UndoAction();
        sharingPresentationAction = new SharingPresentationAction();
        editSlotAction = new EditSlotAction();
        typeOfSlotAction = new TypeOfSlotAction();
        openProjectAction = new OpenProjectAction();
        exportAction = new ExportAction();
        boldAction = new BoldAction();
        underlineAction = new UnderlineAction();
        italicAction = new ItalicAction();
    }

}
