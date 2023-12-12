package configuration.swagger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SwaggerTest {

    @Test
    public void testSwaggerAnnotations() {
        // Specify the base package where your controllers are located
        String basePackage = "com.example.demo.controller";

        // Use Reflections library to scan classes in the specified package
        Reflections reflections = new Reflections(basePackage);

        // Get all classes with @RestController annotation in the specified package
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(org.springframework.web.bind.annotation.RestController.class);

        for (Class<?> controllerClass : classes) {
            // Check if the class has @Api annotation
            assertTrue(controllerClass.isAnnotationPresent(Api.class), "Missing @Api annotation: " + controllerClass.getName());

            // Check if the class has @RequestMapping annotation
            assertTrue(controllerClass.isAnnotationPresent(RequestMapping.class), "Missing @RequestMapping annotation: " + controllerClass.getName());

            // Check annotations for each method
            for (Method method : controllerClass.getMethods()) {
                // Check if the method has @ApiOperation annotation
                assertTrue(method.isAnnotationPresent(ApiOperation.class), "Missing @ApiOperation annotation for method " + method.getName() + " in class " + controllerClass.getName());

                // Check if the method has @GetMapping, @PostMapping, etc., annotations
                assertTrue(method.isAnnotationPresent(GetMapping.class)
                                || method.isAnnotationPresent(org.springframework.web.bind.annotation.PostMapping.class)
                                || method.isAnnotationPresent(org.springframework.web.bind.annotation.PutMapping.class)
                                || method.isAnnotationPresent(org.springframework.web.bind.annotation.DeleteMapping.class)
                                || method.isAnnotationPresent(org.springframework.web.bind.annotation.PatchMapping.class),
                        "Missing request mapping annotation (e.g., @GetMapping, @PostMapping, etc.) for method " + method.getName() + " in class " + controllerClass.getName());
            }
        }
    }
}
