package org.mizar.application;

import org.mizar.classes.*;
import org.mizar.misc.*;

public class TIXApplication extends XMLApplication {

    public String fileName;

    @Override
    public XMLElement buildTree() {
        return new TextProper(document.getRootElement());
    }

    public static void main(String[] args) {
        try {
            TIXApplication app = new TIXApplication();
            app.fileName = args[0];
            Errors errors = new Errors(app.fileName);
            try {
                app.init(app.fileName + ".tix");
                app.xmlElement = app.buildTree();
                app.xmlElement.run();
            } catch (Exception exception) {
                Errors.logException(exception, app.fileName);
                exception.printStackTrace();
            } finally {
                errors.printErrors();
                errors.writeErrors();
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("Enter file name");
            exception.printStackTrace();
        }
    }
}
