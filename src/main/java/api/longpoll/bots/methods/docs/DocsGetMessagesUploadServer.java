package api.longpoll.bots.methods.docs;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.converters.CachedConverterFactory;
import api.longpoll.bots.converters.JsonToPojoConverter;
import api.longpoll.bots.methods.GetMethod;
import api.longpoll.bots.methods.VkApi;
import api.longpoll.bots.model.response.GenericResult;
import api.longpoll.bots.model.response.docs.DocsGetUploadServerResponse;
import org.jsoup.Connection;

import java.util.stream.Stream;

/**
 * Implements <b>docs.getMessagesUploadServer</b> method.
 *
 * @see <a href="https://vk.com/dev/docs.getMessagesUploadServer">https://vk.com/dev/docs.getMessagesUploadServer</a>
 */
public class DocsGetMessagesUploadServer extends GetMethod<GenericResult<DocsGetUploadServerResponse>> {
    /**
     * Document type.
     */
    private String type;

    /**
     * Destination ID.
     */
    private Integer peerId;

    public DocsGetMessagesUploadServer(LongPollBot bot) {
        super(bot);
    }

    @Override
    protected String getApi() {
        return VkApi.getInstance().docsGetMessagesUploadServer();
    }


    @Override
    protected JsonToPojoConverter<GenericResult<DocsGetUploadServerResponse>> getConverter() {
        return CachedConverterFactory.getConverter(GenericResult.class, DocsGetUploadServerResponse.class);
    }

    @Override
    protected Stream<Connection.KeyVal> getKeyValStream() {
        return Stream.of(
                keyVal("type", type),
                keyVal("peer_id", peerId)
        );
    }

    public String getType() {
        return type;
    }

    public DocsGetMessagesUploadServer setType(String type) {
        this.type = type;
        return this;
    }

    public Integer getPeerId() {
        return peerId;
    }

    public DocsGetMessagesUploadServer setPeerId(Integer peerId) {
        this.peerId = peerId;
        return this;
    }
}
