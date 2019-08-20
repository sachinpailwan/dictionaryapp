package com.pailsom.dictionary;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import static org.hamcrest.CoreMatchers.containsString;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DictionaryApplicationTests {

	@Autowired
	private WebApplicationContext applicationContext;
	private MockMvc mockMvc;

	@Before
	public void setup(){
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.applicationContext).build();
	}
	@Test
	public void search() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/search/apple"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("SearchResult"));
	}

	@Test
	public void upload() throws Exception{
		byte [] fileContent = "come=arrive,approach".getBytes();
		MockMultipartFile filePart = new MockMultipartFile("file","orig",null,fileContent);

		this.mockMvc.perform(MockMvcRequestBuilders.multipart("/upload").file(filePart))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("UploadViewStatus"));

		this.mockMvc.perform(MockMvcRequestBuilders.get("/search/come"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("SearchResult"))
				.andExpect(MockMvcResultMatchers.content().string(containsString("arrive")))
				.andExpect(MockMvcResultMatchers.content().string(containsString("approach")));
	}

}
