package com.gmail.at.sergey.christensen.helloWorldWithSpringSecurity;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;

import com.gmail.at.sergey.christensen.helloWorldWithSpringSecurity.controllers.MainController;
import com.gmail.at.sergey.christensen.helloWorldWithSpringSecurity.service.interfaces.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
public class MainControllerTest {
	@InjectMocks
	MainController mainController;
	@Mock
	UserService userService;
	@Mock
	View mockView;
	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(mainController).setSingleView(mockView).build();
	}
	
    @Test
    public void testIndex() throws Exception {
         mockMvc.perform(get("/"))
            .andExpect(status().isOk())
         	.andExpect(view().name("index"));
    }
    
    @Test
    public void testLogin() throws Exception {
         mockMvc.perform(get("/login"))
            .andExpect(status().isOk())
         	.andExpect(view().name("login"));
    }
    
    @Test
    public void testRegistration() throws Exception {
         mockMvc.perform(get("/registration"))
            .andExpect(status().isOk())
         	.andExpect(view().name("registration"));
    }
    
    @Test
    public void testAccessDenied() throws Exception {
         mockMvc.perform(get("/accessDenied"))
            .andExpect(status().isOk())
         	.andExpect(view().name("accessDenied"));
    }

}
