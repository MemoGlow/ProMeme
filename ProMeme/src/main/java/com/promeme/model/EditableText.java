package com.promeme.model;

import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class EditableText extends Text implements Serializable{
    public void changeTextSize(double scale){
        this.setFont(new Font(this.getFont().getFamily(), this.getFont().getSize() * scale));
    }
    private void readObject(ObjectInputStream ois) throws IOException {
        this.setText(ois.readUTF());
        Font font = new Font(ois.readUTF(), ois.readDouble());
        this.setFont(font);
        this.setFill(Paint.valueOf(ois.readUTF()));
        this.setX(ois.readDouble());
        this.setY(ois.readDouble());
    }
    private void writeObject(ObjectOutputStream ous) throws IOException {
        ous.writeUTF(this.getText());
        ous.writeUTF(this.getFont().getFamily());
        ous.writeDouble(this.getFont().getSize());
        ous.writeUTF(String.valueOf(this.getFill()));
        ous.writeDouble(this.getX());
        ous.writeDouble(this.getY());
    }
}
    