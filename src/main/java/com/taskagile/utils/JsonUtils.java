package com.taskagile.utils;

import java.io.IOException;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonUtils {

	private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);

	// constructor를 감춰서 class의 내용은 손 못대고 static method만 사용할 수 있게 하는 전략이다.
	private JsonUtils() {}

	public static String toJson(Object object) {
		ObjectMapper mapper = new ObjectMapper();

		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			System.out.println("Failed to convert object to JSON string");
			log.error("Failed to convert object to JSON string", e);
			return null;
		}
	}
	
	public static <T> T toObject(String json, Class<T> clazz) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, clazz);
		} catch (IOException e) {
			log.error("Faile to convert String `" + json + "` class `" + clazz.getName() + "`", e);
		}
		return null;
	}

	public static void write(PrintWriter writer, ApiResult message) throws IOException {
		new ObjectMapper().writeValue(writer, message);
	}
}
