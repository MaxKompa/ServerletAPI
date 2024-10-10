package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class OrderServletTest {

    @InjectMocks
    private OrderServlet orderServlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private PrintWriter writer;

    @BeforeEach
    public void setup() {

        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testCreateOrder() throws Exception {

        StringWriter stringWriter = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));


        when(request.getParameter("cost")).thenReturn("100.0");


        orderServlet.doPost(request, response);


        verify(response).setStatus(HttpServletResponse.SC_CREATED);
        assertEquals("Order created", stringWriter.toString().trim());
    }


    @Test
    public void testGetOrder() throws Exception {

        StringWriter stringWriter = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));


        when(request.getParameter("id")).thenReturn("1");


        orderServlet.doGet(request, response);


        verify(response).setStatus(HttpServletResponse.SC_OK);
        assertTrue(stringWriter.toString().contains("Order details for ID: 1"));
    }


    @Test
    public void testUpdateOrder() throws Exception {

        StringWriter stringWriter = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));


        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("cost")).thenReturn("200.0");


        orderServlet.doPut(request, response);


        verify(response).setStatus(HttpServletResponse.SC_OK);
        assertEquals("Order updated", stringWriter.toString().trim());
    }


    @Test
    public void testDeleteOrder() throws Exception {

        StringWriter stringWriter = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));


        when(request.getParameter("id")).thenReturn("1");


        orderServlet.doDelete(request, response);


        verify(response).setStatus(HttpServletResponse.SC_OK);
        assertEquals("Order deleted", stringWriter.toString().trim());
    }
}
