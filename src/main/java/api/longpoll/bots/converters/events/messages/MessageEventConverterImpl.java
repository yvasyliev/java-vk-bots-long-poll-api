package api.longpoll.bots.converters.events.messages;

import api.longpoll.bots.converters.JsonToPojoConverter;
import api.longpoll.bots.converters.basic.MessageConverterImpl;
import api.longpoll.bots.model.events.messages.MessageEvent;
import api.longpoll.bots.model.objects.basic.Message;
import com.google.gson.FieldAttributes;
import com.google.gson.JsonObject;

public class MessageEventConverterImpl extends JsonToPojoConverter<MessageEvent> {
	private static final String MESSAGE_FIELD = "message";

	@Override
	public MessageEvent convert(JsonObject jsonObject) {
		return gson.fromJson(jsonObject, MessageEvent.class)
				.setMessage(new MessageConverterImpl().convert(jsonObject.getAsJsonObject(MESSAGE_FIELD)));
	}

	@Override
	protected boolean shouldSkipField(FieldAttributes fieldAttributes) {
		return MessageEvent.class.equals(fieldAttributes.getDeclaringClass())
				&& Message.class.equals(fieldAttributes.getDeclaredClass())
				&& MESSAGE_FIELD.equals(fieldAttributes.getName());
	}
}