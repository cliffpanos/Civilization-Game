package view;

import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class StatusMenu extends AbstractMenu {

    /**
     * Concrete implementation of abstract menu, could be useful for
     * other unit information
     */
    public StatusMenu() {

        Text text = new Text("\nClick on a tile adjacent to a Settlement "
            + "to recruit a new unit or asset.");

        text.setWrappingWidth(130);
        text.setFont(new Font("Times", 16));
        text.setLineSpacing(5.5);
        text.setTextAlignment(TextAlignment.CENTER);

        addMenuItem(text);

    }
}
