package msc.hska.client.util;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import msc.hska.client.exception.GsonException;

public class GsonUtils {
	private static GsonUtils gsonUtils;
	private volatile Gson gson;

	private GsonUtils() {
		gson = new GsonBuilder().setExclusionStrategies(new GsonExclusionStrategy()).serializeNulls()
				.setPrettyPrinting().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
	}

	public static GsonUtils getInstance() {
		if (gsonUtils == null)
			gsonUtils = new GsonUtils();
		return gsonUtils;
	}

	public Gson getGson() {
		return gson;
	}

	public String toJson(Object obj) {
		if (obj != null)
			return gson.toJson(obj);
		return "";
	}

	public <T> T fromJson(String json, Class<T> destClass) throws GsonException {
		try {
			if (json != null && !json.isEmpty())
				return gson.fromJson(json, destClass);
		} catch (JsonSyntaxException ex) {
			throw new GsonException(ex);
		}
		return null;
	}

	public <T> T fromJson(String json, Type type) throws GsonException {
		try {
			if (json != null && !json.isEmpty())
				return gson.fromJson(json, type);
		} catch (JsonSyntaxException ex) {
			throw new GsonException(ex);
		}
		return null;
	}

	public class GsonExclusionStrategy implements ExclusionStrategy {
		private List<Class<?>> classList;

		public GsonExclusionStrategy(Class<?>... typesToSkip) {
			if (typesToSkip != null) {
				classList = new ArrayList<>();
				for (Class<?> classType : typesToSkip)
					classList.add(classType);
			}
		}

		@Override
		public boolean shouldSkipClass(Class<?> clazz) {
			if (classList == null || classList.isEmpty())
				return false;
			return classList.stream().anyMatch(classType -> classType == clazz);
		}

		@Override
		public boolean shouldSkipField(FieldAttributes field) {
			return (field.getAnnotation(JsonIgnore.class) != null);
		}
	}
}