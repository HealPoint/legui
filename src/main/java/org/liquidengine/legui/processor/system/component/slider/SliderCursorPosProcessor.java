package org.liquidengine.legui.processor.system.component.slider;

import org.joml.Vector2f;
import org.liquidengine.legui.component.Slider;
import org.liquidengine.legui.component.optional.Orientation;
import org.liquidengine.legui.context.LeguiContext;
import org.liquidengine.legui.event.system.SystemCursorPosEvent;
import org.liquidengine.legui.processor.system.component.LeguiComponentEventProcessor;
import org.liquidengine.legui.util.Util;
import org.lwjgl.glfw.GLFW;

import static org.liquidengine.legui.processor.system.component.slider.SliderUpdateUtil.updateSliderValue;


/**
 * Created by Alexander on 28.08.2016.
 */
public class SliderCursorPosProcessor implements LeguiComponentEventProcessor<Slider, SystemCursorPosEvent> {
    @Override
    public void process(Slider slider, SystemCursorPosEvent event, LeguiContext leguiContext) {
        if (!slider.isVisible()) return;
        if (!slider.isEnabled()) return;
        if (leguiContext.getFocusedGui() != slider) return;
        if (!leguiContext.getMouseButtonStates()[GLFW.GLFW_MOUSE_BUTTON_LEFT]) return;

        Vector2f pos = Util.calculatePosition(slider);

        Vector2f cursorPosition = leguiContext.getCursorPosition();
        if (Orientation.VERTICAL.equals(slider.getOrientation())) {
            float value = 100f * ((pos.y + slider.getSize().y) - cursorPosition.y) / slider.getSize().y;
            updateSliderValue(slider, value, leguiContext);
        } else {
            float value = 100f * (cursorPosition.x - pos.x) / slider.getSize().x;
            updateSliderValue(slider, value, leguiContext);
        }
    }
}
