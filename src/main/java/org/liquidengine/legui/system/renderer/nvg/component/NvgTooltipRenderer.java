package org.liquidengine.legui.system.renderer.nvg.component;

import static org.liquidengine.legui.system.renderer.nvg.util.NvgColorUtil.rgba;
import static org.liquidengine.legui.system.renderer.nvg.util.NvgRenderUtils.alignTextInBox;
import static org.liquidengine.legui.system.renderer.nvg.util.NvgRenderUtils.createBounds;
import static org.liquidengine.legui.system.renderer.nvg.util.NvgRenderUtils.createScissor;
import static org.liquidengine.legui.system.renderer.nvg.util.NvgRenderUtils.intersectScissor;
import static org.liquidengine.legui.system.renderer.nvg.util.NvgRenderUtils.resetScissor;
import static org.lwjgl.nanovg.NanoVG.nnvgText;
import static org.lwjgl.nanovg.NanoVG.nnvgTextBreakLines;
import static org.lwjgl.nanovg.NanoVG.nvgBeginPath;
import static org.lwjgl.nanovg.NanoVG.nvgFillColor;
import static org.lwjgl.nanovg.NanoVG.nvgFontFace;
import static org.lwjgl.nanovg.NanoVG.nvgFontSize;
import static org.lwjgl.system.MemoryUtil.memAddress;
import static org.lwjgl.system.MemoryUtil.memFree;
import static org.lwjgl.system.MemoryUtil.memUTF8;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import org.joml.Vector2f;
import org.joml.Vector4f;
import org.liquidengine.legui.component.Tooltip;
import org.liquidengine.legui.component.optional.TextState;
import org.liquidengine.legui.component.optional.align.HorizontalAlign;
import org.liquidengine.legui.component.optional.align.VerticalAlign;
import org.liquidengine.legui.system.context.Context;
import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NVGTextRow;

/**
 * Created by ShchAlexander on 13.02.2017.
 */
public class NvgTooltipRenderer extends NvgDefaultComponentRenderer<Tooltip> {


    @Override
    public void renderSelf(Tooltip component, Context context, long nanovg) {
        createScissor(nanovg, component);
        {
            TextState textState = component.getTextState();
            Vector2f pos = component.getAbsolutePosition();
            Vector2f size = component.getSize();
            float fontSize = textState.getFontSize();
            String font = textState.getFont();
            String text = textState.getText();
            HorizontalAlign horizontalAlign = textState.getHorizontalAlign();
            VerticalAlign verticalAlign = textState.getVerticalAlign();
            Vector4f textColor = textState.getTextColor();
            Vector4f padding = new Vector4f(component.getStyle().getPadding());

            renderBackground(component, context, nanovg);

            nvgFontSize(nanovg, fontSize);
            nvgFontFace(nanovg, font);

            ByteBuffer byteText = null;
            try {

                byteText = memUTF8(text, false);
                long start = memAddress(byteText);
                long end = start + byteText.remaining();

                float x = pos.x + padding.x;
                float y = pos.y + padding.y;
                float w = size.x - padding.x - padding.z;
                float h = size.y - padding.y - padding.w;

                intersectScissor(nanovg, new Vector4f(x, y, w, h));

                List<float[]> boundList = new ArrayList<>();
                List<long[]> indicesList = new ArrayList<>();

                NVGColor colorA = NVGColor.calloc();
                alignTextInBox(nanovg, HorizontalAlign.LEFT, VerticalAlign.MIDDLE);
                nvgFontSize(nanovg, fontSize);
                nvgFontFace(nanovg, font);
                nvgFillColor(nanovg, rgba(textColor, colorA));

                // calculate text bounds for every line and start/end indices
                NVGTextRow.Buffer buffer = NVGTextRow.calloc(1);
                int rows = 0;
                while (nnvgTextBreakLines(nanovg, start, end, size.x, memAddress(buffer), 1) != 0) {
                    NVGTextRow row = buffer.get(0);
                    float[] bounds = createBounds(x, y + rows * fontSize, w, h, horizontalAlign, verticalAlign, row.width(), fontSize);
                    boundList.add(bounds);
                    indicesList.add(new long[]{row.start(), row.end()});
                    start = row.next();
                    rows++;
                }

                // calculate offset for all lines
                float offsetY = 0.5f * fontSize * ((rows - 1) * verticalAlign.index - 1);

                // render text lines
                nvgFillColor(nanovg, rgba(textColor, colorA));
                for (int i = 0; i < rows; i++) {
                    float[] bounds = boundList.get(i);
                    long[] indices = indicesList.get(i);

                    nvgBeginPath(nanovg);
                    nnvgText(nanovg, bounds[4], bounds[5] - offsetY, indices[0], indices[1]);
                }
                buffer.free();
                colorA.free();
            } finally {
                memFree(byteText);
            }
        }
        resetScissor(nanovg);
    }
}
