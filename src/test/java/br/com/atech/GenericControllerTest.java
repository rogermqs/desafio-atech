package br.com.atech;

import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class GenericControllerTest<T> {
	
	
	protected MockMvc mockMvc;
	protected String endPoint;

	protected ObjectMapper jsonMapper;

	private final Class<T> classParameter;

	@SuppressWarnings( "unchecked" )
	public GenericControllerTest() {
		this.classParameter = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}
	
	
	protected void setup( final AbstractController genericController ) {
		this.mockMvc = MockMvcBuilders.standaloneSetup(genericController).build();
		this.endPoint = genericController.getBaseRequestMapping();
		this.jsonMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}


	public String findById( final Long anyId ) {
		MvcResult mvcResult;
		String responseContent = null;
		try {
			mvcResult = mockMvc.perform(
					MockMvcRequestBuilders.get(endPoint + "/{id}", anyId).header("Content-Type", "application/json")
					.header("gri_user", "ercarval").accept(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk()).andReturn();
			responseContent = mvcResult.getResponse().getContentAsString();
			Assert.assertNotNull(jsonMapper.readValue(responseContent, this.classParameter));
		}
		catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}

		return responseContent;
	}
	
	public String findByData( final String anyData ) {
		MvcResult mvcResult;
		String responseContent = null;
		try {
			mvcResult = mockMvc.perform(
					MockMvcRequestBuilders.get(endPoint + "/{id}", anyData).header("Content-Type", "application/json")
					.header("gri_user", "ercarval").accept(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk()).andReturn();
			responseContent = mvcResult.getResponse().getContentAsString();
			Assert.assertTrue(jsonMapper.readValue(responseContent, List.class).size() > 0);
		}
		catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}

		return responseContent;
	}

	@SuppressWarnings( "unchecked" )
	protected List<T> findAllOk(final Collection<T> expectedReturn) {

		List<T> list = null;

		try {

			final Writer responseJson = new StringWriter();
			jsonMapper.writeValue(responseJson, expectedReturn);

			final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
					.get(endPoint)
					.header("Content-Type", "application/json")
					.accept(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk())
					.andReturn();

			list = jsonMapper.readValue(mvcResult.getResponse().getContentAsString(), LinkedList.class);

			final String responseContent = mvcResult.getResponse().getContentAsString();
			assertEquals(responseJson.toString(), responseContent);


		} catch (final Exception exception) {
			exception.printStackTrace();
			Assert.fail();
		}

		return list;
	}

	protected T findOneOk(final T expectedReturn, final Long id) {

		T entity = null;

		try {
			final Writer responseJson = new StringWriter();
			jsonMapper.writeValue(responseJson, expectedReturn);

			final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
					.get(endPoint + "/{id}", id)
					.header("Content-Type", "application/json")
					.accept(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk())
					.andReturn();

			entity = jsonMapper.readValue(mvcResult.getResponse().getContentAsString(), classParameter);

			final String responseContent = mvcResult.getResponse().getContentAsString();
			assertEquals(responseJson.toString(), responseContent);

		} catch (final Exception exception) {
			exception.printStackTrace();
			Assert.fail();
		}

		return entity;

	}

	protected void createReturnCreate(final T expectedReturn) {

		try {
			final Writer responseJson = new StringWriter();
			jsonMapper.writeValue(responseJson, expectedReturn);

			final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
					.post(endPoint)
					.header("Content-Type", "application/json")
					.content(responseJson.toString().getBytes())
					.accept(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isCreated())
					.andReturn();

			final HttpStatus status = HttpStatus.valueOf(mvcResult.getResponse().getStatus());
			assertEquals(HttpStatus.CREATED, status);

		} catch (final Exception exception) {
			exception.printStackTrace();
			Assert.fail();
		}
	}
	
	protected void createReturnCreateBadRequest(final T expectedReturn) {

		try {
			final Writer responseJson = new StringWriter();
			jsonMapper.writeValue(responseJson, expectedReturn);

			final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
					.post(endPoint)
					.header("Content-Type", "application/json")
					.content(responseJson.toString().getBytes())
					.accept(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isBadRequest())
					.andReturn();

			final HttpStatus status = HttpStatus.valueOf(mvcResult.getResponse().getStatus());
			assertEquals(HttpStatus.BAD_REQUEST, status);

		} catch (final Exception exception) {
			exception.printStackTrace();
			Assert.fail();
		}
	}

	protected void updateReturn(final T expected) {
		try {

			final Writer responseJson = new StringWriter();
			jsonMapper.writeValue(responseJson, expected);

			final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
					.put(endPoint)
					.header("Content-Type", "application/json")
					.content(responseJson.toString().getBytes())
					.accept(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk())
					.andReturn();

			final HttpStatus status = HttpStatus.valueOf(mvcResult.getResponse().getStatus());
			assertEquals(HttpStatus.OK, status);

		} catch (final Exception exception) {
			exception.printStackTrace();
			Assert.fail();
		}
	}

	protected void deleteOk(final Long id) {
		try {

			mockMvc.perform(MockMvcRequestBuilders
					.delete(endPoint + "/{id}", id)
					.header("Content-Type", "application/json")
					.accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk());

		} catch (final Exception exception) {
			exception.printStackTrace();
			Assert.fail();
		}
	}

}
