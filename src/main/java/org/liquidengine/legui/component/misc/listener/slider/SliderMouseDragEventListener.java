package org.liquidengine.legui.component.misc.listener.slider;

import org.joml.Vector2f;
import org.liquidengine.legui.component.Slider;
import org.liquidengine.legui.component.event.slider.SliderChangeValueEvent;
import org.liquidengine.legui.component.optional.Orientation;
import org.liquidengine.legui.event.MouseDragEvent;
import org.liquidengine.legui.input.Mouse;
import org.liquidengine.legui.listener.MouseDragEventListener;
import org.liquidengine.legui.listener.processor.EventProcessor;

/**
 * Slider mouse drag event listener. Used to change slider value. Generates slider value change event.
 */
public class SliderMouseDragEventListener implements MouseDragEventListener {

    @Override
    public void process(MouseDragEvent event) {
        Slider slider = (Slider) event.getTargetComponent();
        if (!Mouse.MouseButton.MOUSE_BUTTON_LEFT.isPressed()) {
            return;
        }

        Vector2f pos = slider.getAbsolutePosition();

        Vector2f cursorPosition = Mouse.getCursorPosition();
        float value;
        float sliderSize = slider.getSliderSize();
        if (Orientation.VERTICAL.equals(slider.getOrientation())) {
            value = 100f * ((pos.y + slider.getSize().y) - cursorPosition.y - sliderSize / 2f) / (slider.getSize().y - sliderSize);
        } else {
            value = 100f * (cursorPosition.x - pos.x - sliderSize / 2f) / (slider.getSize().x - sliderSize);
        }

        if (value > Slider.MAX_VALUE) {
            value = Slider.MAX_VALUE;
        }
        if (value < Slider.MIN_VALUE) {
            value = Slider.MIN_VALUE;
        }

        EventProcessor.getInstance().pushEvent(new SliderChangeValueEvent(slider, event.getContext(), event.getFrame(), slider.getValue(), value));
        slider.setValue(value);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj != null) && ((obj == this) || ((obj != this) && (obj.getClass() == this.getClass())));
    }
}
