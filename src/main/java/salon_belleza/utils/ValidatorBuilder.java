package salon_belleza.utils;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import net.synedra.validatorfx.TooltipWrapper;
import net.synedra.validatorfx.Validator;

public class ValidatorBuilder {
    private final Validator validator;
    private String key;
    private StringProperty stringProperty;
    private Node node;

    private ValidatorBuilder(Validator validator){
        this.validator = validator;
    }

    public static ValidatorBuilder create(Validator validator){
        return new ValidatorBuilder(validator);
    }

    public ValidatorBuilder field(String key, StringProperty stringProperty){
        this.key = key;
        this.stringProperty = stringProperty;
        return this;
    }

    public ValidatorBuilder decorates(Node node){
        this.node = node;
        return this;
    }

    public ValidatorBuilder validateNoEmpty(String warn){
        validator.createCheck()
                .dependsOn(key, stringProperty)
                .withMethod(c -> {
                    String field = stringProperty.get();
                    if(field.isEmpty()){
                        c.warn(warn);
                    }
                })
                .decorates(node);
        return this;
    }

    public void build(){

    }






}
