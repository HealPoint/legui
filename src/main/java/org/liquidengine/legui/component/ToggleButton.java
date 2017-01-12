package org.liquidengine.legui.component;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joml.Vector2f;
import org.joml.Vector4f;
import org.liquidengine.legui.component.border.SimpleRectangleLineBorder;
import org.liquidengine.legui.util.ColorConstants;

/**
 * An implementation of "toggle" button.
 * Behavior the same as checkbox but have not any text
 * Created by Alexander on 12.01.2017.
 */
public class ToggleButton extends Component {
    protected ImageView backgroundImage;
    protected ImageView focusedBbackgroundImage;
    protected ImageView pressedBackgroundImage;
    protected ImageView hoveredBackgroundImage;
    protected ImageView togglededBackgroundImage;
    private boolean  toggled;
    private Vector4f toggledBackgroundColor;

    /**
     * Creates toggle button with default bg color and default toggled bg color.
     */
    public ToggleButton() {
        initialize();
    }

    /**
     * Creates a button with specified position and size and default bg color and default toggled bg color.
     */
    public ToggleButton(float x, float y, float width, float height) {
        super(x, y, width, height);
        initialize();
    }

    /**
     * Creates a button with specified position and size and default bg color and default toggled bg color.
     */
    public ToggleButton(Vector2f position, Vector2f size) {
        super(position, size);
        initialize();
    }

    /**
     * Initialize toggle button with default states
     */
    private void initialize() {
        backgroundColor = ColorConstants.red();
        toggledBackgroundColor = ColorConstants.green();
        border = new SimpleRectangleLineBorder(ColorConstants.black(), 1);
        cornerRadius = 0;
    }

    /**
     * Returns true if toggle button is toggled.
     *
     * @return true if toggle button is toggled.
     */
    public boolean isToggled() {
        return toggled;
    }

    /**
     * Used to change toggled state.
     *
     * @param toggled new value
     */
    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }

    /**
     * Returns background color which will be used as background color if button toggled
     *
     * @return toggled button color
     */
    public Vector4f getToggledBackgroundColor() {
        return toggledBackgroundColor;
    }

    /**
     * Used to change background color which will be used as background color if button toggled.
     *
     * @param toggledBackgroundColor new value
     */
    public void setToggledBackgroundColor(Vector4f toggledBackgroundColor) {
        this.toggledBackgroundColor = toggledBackgroundColor;
    }

    /**
     * Returns background image
     *
     * @return background image
     */
    public ImageView getBackgroundImage() {
        return backgroundImage;
    }

    /**
     * Used to change background image
     *
     * @param backgroundImage background image
     */
    public void setBackgroundImage(ImageView backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    /**
     * Returns focused background image
     *
     * @return focused background image
     */
    public ImageView getFocusedBbackgroundImage() {
        return focusedBbackgroundImage;
    }

    /**
     * Used to change focused background image
     *
     * @param focusedBbackgroundImage focused background image
     */
    public void setFocusedBbackgroundImage(ImageView focusedBbackgroundImage) {
        this.focusedBbackgroundImage = focusedBbackgroundImage;
    }

    /**
     * Returns pressed background image
     *
     * @return pressed background image
     */
    public ImageView getPressedBackgroundImage() {
        return pressedBackgroundImage;
    }

    /**
     * Used to change pressed background image
     *
     * @param pressedBackgroundImage pressed background image
     */
    public void setPressedBackgroundImage(ImageView pressedBackgroundImage) {
        this.pressedBackgroundImage = pressedBackgroundImage;
    }

    /**
     * Returns hovered background image
     *
     * @return hovered background image
     */
    public ImageView getHoveredBackgroundImage() {
        return hoveredBackgroundImage;
    }

    /**
     * Used to change hovered background image
     *
     * @param hoveredBackgroundImage hovered background image
     */
    public void setHoveredBackgroundImage(ImageView hoveredBackgroundImage) {
        this.hoveredBackgroundImage = hoveredBackgroundImage;
    }

    /**
     * Returns toggled background image
     *
     * @return toggled background image
     */
    public ImageView getTogglededBackgroundImage() {
        return togglededBackgroundImage;
    }

    /**
     * Used to change toggled background image
     *
     * @param togglededBackgroundImage toggled background image
     */
    public void setTogglededBackgroundImage(ImageView togglededBackgroundImage) {
        this.togglededBackgroundImage = togglededBackgroundImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ToggleButton button = (ToggleButton) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(toggledBackgroundColor, button.toggledBackgroundColor)
                .append(toggled, button.toggled)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(toggledBackgroundColor)
                .append(toggled)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("toggledBackgroundColor", toggledBackgroundColor)
                .append("toggled", toggled)
                .toString();
    }
}
