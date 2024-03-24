package com.test.view;

import com.test.controller.OpenDialogController;

import java.io.File;
import java.io.IOException;

public class OpenDialogView {
    public static File disPlay() throws IOException {
        return OpenDialogController.showFileChooseDialog();
    }
}
