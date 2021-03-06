package org.liquidengine.legui.demo;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.nanovg.NanoVG.nvgBeginFrame;
import static org.lwjgl.nanovg.NanoVG.nvgBeginPath;
import static org.lwjgl.nanovg.NanoVG.nvgEndFrame;
import static org.lwjgl.nanovg.NanoVG.nvgFill;
import static org.lwjgl.nanovg.NanoVG.nvgFillColor;
import static org.lwjgl.nanovg.NanoVG.nvgRect;
import static org.lwjgl.nanovg.NanoVG.nvgResetScissor;
import static org.lwjgl.nanovg.NanoVG.nvgScissor;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_STENCIL_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.io.IOException;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NanoVGGL3;
import org.lwjgl.opengl.GL;

/**
 * Created by Alexander on 17.12.2016.
 */
public class MultipleWindowsNanoVG {

    public static final int WIDTH = 200;
    public static final int HEIGHT = 200;
    private static volatile boolean running = false;

    public static void main(String[] args) throws IOException {
        System.setProperty("joml.nounsafe", Boolean.TRUE.toString());
        System.setProperty("java.awt.headless", Boolean.TRUE.toString());

        if (!GLFW.glfwInit()) {
            throw new RuntimeException("Can't initialize GLFW");
        }

        int N = 3;
        long windows[] = new long[N];
        long nvgContexts[] = new long[N];

        for (int i = 0; i < N; i++) {
            windows[i] = glfwCreateWindow(WIDTH, HEIGHT, "Example " + i, NULL, NULL);
            glfwSetWindowPos(windows[i], 50 + WIDTH * i, 50);
            glfwShowWindow(windows[i]);
            glfwMakeContextCurrent(windows[i]);
            GL.createCapabilities();
            glfwSwapInterval(0);

            glfwSetKeyCallback(windows[i],
                               (window, key, scancode, action, mods) -> running = !(key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE));

            nvgContexts[i] = NanoVGGL3.nvgCreate(NanoVGGL3.NVG_ANTIALIAS | NanoVGGL3.NVG_STENCIL_STROKES | NanoVGGL3.NVG_DEBUG);
        }

        running = true;

        while (running) {
            for (int i = 0; i < N; i++) {
                long window = windows[i];
                long nvgContext = nvgContexts[i];

                glfwMakeContextCurrent(window);
                GL.createCapabilities();
                glfwSwapInterval(0);

                // Before rendering we need to update context with window size and window framebuffer size
                int[] windowWidth = {0}, windowHeight = {0};
                GLFW.glfwGetWindowSize(window, windowWidth, windowHeight);
                int[] frameBufferWidth = {0}, frameBufferHeight = {0};
                GLFW.glfwGetFramebufferSize(window, frameBufferWidth, frameBufferHeight);
                int[] xpos = {0}, ypos = {0};
                GLFW.glfwGetWindowPos(window, xpos, ypos);
                double[] mx = {0}, my = {0};
                GLFW.glfwGetCursorPos(window, mx, my);

                glClearColor(1, 1, 1, 1);
                // Set viewport size
                glViewport(0, 0, frameBufferWidth[0], frameBufferHeight[0]);
                // Clear screen
                glClear(GL_COLOR_BUFFER_BIT | GL_STENCIL_BUFFER_BIT);

                // render frame
                {
                    glEnable(GL_BLEND);
                    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
                    nvgBeginFrame(nvgContext, windowWidth[0], windowHeight[0], windowWidth[0] / frameBufferWidth[0]);

                    renderNVG(nvgContext);

                    nvgEndFrame(nvgContext);
                    glDisable(GL_BLEND);
                }

                // poll events to callbacks
                glfwPollEvents();
                glfwSwapBuffers(window);
            }
        }
        for (int i = 0; i < N; i++) {
            glfwDestroyWindow(windows[i]);
        }
        glfwTerminate();
    }

    private static void renderNVG(long nvgContext) {
        nvgScissor(nvgContext, 10, 10, WIDTH - 20, HEIGHT - 20);
        {
            NVGColor nvgColor = NVGColor.calloc();
            nvgColor.r(1);
            nvgColor.g(0);
            nvgColor.b(0);
            nvgColor.a(1);
            nvgBeginPath(nvgContext);
            nvgFillColor(nvgContext, nvgColor);
            nvgRect(nvgContext, 0, 0, WIDTH, HEIGHT);
            nvgFill(nvgContext);
            nvgColor.free();
        }
        nvgResetScissor(nvgContext);
    }
}