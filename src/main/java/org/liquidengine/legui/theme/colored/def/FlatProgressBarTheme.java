package org.liquidengine.legui.theme.colored.def;

import org.liquidengine.legui.component.ProgressBar;
import org.liquidengine.legui.style.border.SimpleLineBorder;
import org.liquidengine.legui.theme.colored.FlatColoredTheme.FlatColoredThemeSettings;

/**
 * Dark ProgressBar Theme for all progress bars. Used to make progress bar dark.
 *
 * @param <T> {@link ProgressBar} subclasses.
 */
public class FlatProgressBarTheme<T extends ProgressBar> extends FlatComponentTheme<T> {

    private FlatColoredThemeSettings settings;

    public FlatProgressBarTheme(FlatColoredThemeSettings settings) {
        super(settings);

        this.settings = settings;
    }

    /**
     * Used to apply theme only for component and not apply for child components.
     *
     * @param component component to apply theme.
     */
    @Override
    public void apply(T component) {
        super.apply(component);
        component.getStyle().setBorder(new SimpleLineBorder(settings.borderColor(), 1));
        component.setProgressColor(settings.allowColor());
    }
}
