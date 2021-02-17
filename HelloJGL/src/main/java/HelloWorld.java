import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.system.*;

import java.nio.*;
import java.util.Objects;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL.createCapabilities;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class HelloWorld {

    long id;

    public void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        init();
        loop();

        // Free the window callbacks and destroy the window
//        glfwFreeCallbacks(id);
//        glfwDestroyWindow(id);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        Objects.requireNonNull(glfwSetErrorCallback(null)).free();

    }

    private void init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        //=====================================

        String title = "MyTitle";
        boolean resizable = true;
        int m_width = 1000;
        int m_height = 1000;

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
        glfwWindowHint(GLFW_RESIZABLE, resizable ? GLFW_TRUE : GLFW_FALSE);

        id = glfwCreateWindow(m_width, m_height, title, NULL, NULL);




        //=====================================


        // Get the thread stack and push a new frame
        try ( MemoryStack stack = stackPush() ) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(id, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        }

        glfwMakeContextCurrent(id);
        createCapabilities();
        glfwSwapInterval(1);

        glViewport(0, 0, 1000, 1000);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 400, 0, 400, 0, 1);

    }

    private void loop() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
//        createCapabilities();

        // Set the clear color
//        glClearColor(1.0f, 0.0f, 0.0f, 0.0f);


        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( !glfwWindowShouldClose(id) ) {
            glClearColor(1f, 1, 1f, 1);
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            // clear the framebuffer


            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();

            glPointSize(20);
            glLineWidth(2.5f);
            glColor3f(1.0f, 0.0f, 0.0f);
            glBegin(GL_3D);
            glVertex3f(0,0,0);
            glVertex3f(100,100,100);
            glEnd();

            glfwSwapBuffers(id);

            glfwPollEvents();
        // swap the color buffers
        }
    }

    public static void main(String[] args) {
        new HelloWorld().run();
    }

}
