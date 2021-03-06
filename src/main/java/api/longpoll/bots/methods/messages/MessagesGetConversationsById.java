package api.longpoll.bots.methods.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.converters.CachedConverterFactory;
import api.longpoll.bots.converters.JsonToPojoConverter;
import api.longpoll.bots.methods.GetMethod;
import api.longpoll.bots.methods.VkApi;
import api.longpoll.bots.model.objects.basic.Conversation;
import api.longpoll.bots.model.response.ExtendedVkList;
import api.longpoll.bots.model.response.GenericResult;
import org.jsoup.Connection;

import java.util.List;
import java.util.stream.Stream;

/**
 * Implements <b>messages.getConversationsById</b> method.
 *
 * @see <a href="https://vk.com/dev/messages.getConversationsById">https://vk.com/dev/messages.getConversationsById</a>
 */
public class MessagesGetConversationsById extends GetMethod<GenericResult<ExtendedVkList<Conversation>>> {
    /**
     * List of destination IDs.
     */
    private List<Integer> peerIds;

    /**
     * <b>true</b> – return additional information about users and communities in users and communities fields.
     */
    private Boolean extended;

    /**
     * List of additional fields for users and communities.
     */
    private List<String> fields;

    /**
     * Group ID.
     */
    private Integer groupId;

    public MessagesGetConversationsById(LongPollBot bot) {
        super(bot);
    }

    @Override
    protected String getApi() {
        return VkApi.getInstance().messagesGetConversationsById();
    }

    @Override
    protected JsonToPojoConverter<GenericResult<ExtendedVkList<Conversation>>> getConverter() {
        return CachedConverterFactory.getConverter(GenericResult.class, ExtendedVkList.class, Conversation.class);
    }

    @Override
    protected Stream<Connection.KeyVal> getKeyValStream() {
        return Stream.of(
                keyVal("peer_ids", peerIds),
                keyVal("extended", extended, true),
                keyVal("fields", fields),
                keyVal("group_id", groupId)
        );
    }

    public List<Integer> getPeerIds() {
        return peerIds;
    }

    public MessagesGetConversationsById setPeerIds(List<Integer> peerIds) {
        this.peerIds = peerIds;
        return this;
    }

    public Boolean getExtended() {
        return extended;
    }

    public MessagesGetConversationsById setExtended(Boolean extended) {
        this.extended = extended;
        return this;
    }

    public List<String> getFields() {
        return fields;
    }

    public MessagesGetConversationsById setFields(List<String> fields) {
        this.fields = fields;
        return this;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public MessagesGetConversationsById setGroupId(Integer groupId) {
        this.groupId = groupId;
        return this;
    }
}
