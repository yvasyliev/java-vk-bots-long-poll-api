package api.longpoll.bots.methods.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.converters.CachedConverterFactory;
import api.longpoll.bots.converters.JsonToPojoConverter;
import api.longpoll.bots.methods.GetMethod;
import api.longpoll.bots.methods.VkApi;
import api.longpoll.bots.model.objects.additional.PinnedMessage;
import api.longpoll.bots.model.response.GenericResult;
import org.jsoup.Connection;

import java.util.stream.Stream;

/**
 * Implements <b>messages.pin</b> method.
 *
 * @see <a href="https://vk.com/dev/messages.pin">https://vk.com/dev/messages.pin</a>
 */
public class MessagesPin extends GetMethod<GenericResult<PinnedMessage>> {
    /**
     * Peer ID.
     */
    private Integer peerId;

    /**
     * Message Id.
     */
    private Integer messageId;

    public MessagesPin(LongPollBot bot) {
        super(bot);
    }

    @Override
    protected String getApi() {
        return VkApi.getInstance().messagesPin();
    }

    @Override
    protected JsonToPojoConverter<GenericResult<PinnedMessage>> getConverter() {
        return CachedConverterFactory.getConverter(GenericResult.class, PinnedMessage.class);
    }

    @Override
    protected Stream<Connection.KeyVal> getKeyValStream() {
        return Stream.of(
                keyVal("peer_id", peerId),
                keyVal("message_id", messageId)
        );
    }

    public Integer getPeerId() {
        return peerId;
    }

    public MessagesPin setPeerId(Integer peerId) {
        this.peerId = peerId;
        return this;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public MessagesPin setMessageId(Integer messageId) {
        this.messageId = messageId;
        return this;
    }
}
