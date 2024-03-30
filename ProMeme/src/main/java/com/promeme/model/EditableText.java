package com.promeme.model;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class EditableText extends Text {
    public void changeTextSize(double scale){
        this.setFont(new Font(this.getFont().getFamily(), this.getFont().getSize() * scale));
    }

}
