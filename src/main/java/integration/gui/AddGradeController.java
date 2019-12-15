package integration.gui;

import core.GMUClass;
import integration.IntegrationBase;

public class AddGradeController {

    private IntegrationBase brain;

    public AddGradeController(IntegrationBase brain) {
        this.brain = brain;
    }

    public boolean addClass(String classID, int credit) {
        GMUClass gmuClass = new GMUClass(credit);
        return brain.addClass(classID, gmuClass);
    }
}
