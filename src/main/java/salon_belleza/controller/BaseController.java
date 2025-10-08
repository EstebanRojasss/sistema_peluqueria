package salon_belleza.controller;

import javafx.fxml.FXML;
import net.synedra.validatorfx.Validator;

public abstract class BaseController {

    protected final Validator validator = new Validator();

    @FXML
    public void initialize(){
        setUpValidations(validator);
    }

    protected abstract void setUpValidations(Validator validator);

}
