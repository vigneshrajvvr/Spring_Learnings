package com.example.demo.controller;

import com.example.demo.controllers.HelloController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HelloControllerUnitTest {

    @Test
    public void testSayHello() {
        HelloController controller = new HelloController();
        Model model = new BindingAwareModelMap();
        String result = controller.sayHello("Dolly", model);
        assertAll(
                () -> assertEquals("hello", result),
                () -> assertEquals("Dolly", model.getAttribute("user"))
        );

        // If one assertion fails, other assertion will not be executed
        // assertEquals("hello", result);
        // assertEquals("Dolly", model.getAttribute("user"));

    }

    @Test    // true unit test as it does not on anything
    public void sayHelloWithStub() {
        HelloController controller = new HelloController();
        Model mockModel = mock(Model.class);

        // Setting expectation on mock
        when(mockModel.addAttribute(anyString(), any())).thenReturn(mockModel);
        String result = controller.sayHello("Dolly", mockModel);
        assertEquals("hello", result);
    }

}
