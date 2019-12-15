package integration.gui;

import core.GMUClass;
import integration.IntegrationBase;

public class RemoveGradeController {

    private IntegrationBase brain;

    public RemoveGradeController(IntegrationBase brain) {
        this.brain = brain;
    }

    public GMUClass removeClass(String classID) {
        return brain.removeClass(classID);
    }
}
