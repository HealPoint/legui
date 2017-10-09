package org.liquidengine.legui.theme.white.def;

import static org.liquidengine.legui.font.FontRegistry.MATERIAL_ICONS_REGULAR;

import org.joml.Vector2f;
import org.liquidengine.legui.color.ColorConstants;
import org.liquidengine.legui.component.RadioButton;
import org.liquidengine.legui.component.optional.align.HorizontalAlign;
import org.liquidengine.legui.icon.CharIcon;

/**
 * White RadioButton Theme for all radio buttons. Used to make radio button white.
 *
 * @param <T> {@link RadioButton} subclasses.
 */
public class WhiteRadioButtonTheme<T extends RadioButton> extends WhiteComponentTheme<T> {

    @Override
    public void apply(T component) {
        super.apply(component);
        component.setBorder(null);
        component.setBackgroundColor(ColorConstants.transparent());
        component.getTextState().setTextColor(ColorConstants.black());
        component.getTextState().setHorizontalAlign(HorizontalAlign.LEFT);
        component.setIconUnchecked(new CharIcon(new Vector2f(16), MATERIAL_ICONS_REGULAR, (char) 0xE836, ColorConstants.black()));
        component.setIconChecked(new CharIcon(new Vector2f(16), MATERIAL_ICONS_REGULAR, (char) 0xE837, ColorConstants.black()));
        component.getIconUnchecked().setHorizontalAlign(HorizontalAlign.LEFT);
        component.getIconChecked().setHorizontalAlign(HorizontalAlign.LEFT);
    }
}
