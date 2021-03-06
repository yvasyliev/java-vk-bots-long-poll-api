package api.longpoll.bots.methods.groups;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.converters.CachedConverterFactory;
import api.longpoll.bots.converters.JsonToPojoConverter;
import api.longpoll.bots.methods.GetMethod;
import api.longpoll.bots.methods.VkApi;
import api.longpoll.bots.model.response.GenericResult;
import api.longpoll.bots.model.response.groups.GroupsAddCallbackServerResponse;
import org.jsoup.Connection;

import java.util.stream.Stream;

/**
 * Implements <b>groups.addCallbackServer</b> method.
 *
 * @see <a href="https://vk.com/dev/groups.addCallbackServer">https://vk.com/dev/groups.addCallbackServer</a>
 */
public class GroupsAddCallbackServer extends GetMethod<GenericResult<GroupsAddCallbackServerResponse>> {
    /**
     * Community ID.
     */
    private Integer groupId;

    /**
     * Server URL.
     */
    private String url;

    /**
     * Server title.
     */
    private String title;

    /**
     * Server secret key.
     */
    private String secretKey;

    public GroupsAddCallbackServer(LongPollBot bot) {
        super(bot);
    }

    @Override
    protected Stream<Connection.KeyVal> getKeyValStream() {
        return Stream.of(
                keyVal("group_id", groupId),
                keyVal("url", url),
                keyVal("title", title),
                keyVal("secret_key", secretKey)
        );
    }

    @Override
    protected String getApi() {
        return VkApi.getInstance().groupsAddCallbackServer();
    }

    @Override
    protected JsonToPojoConverter<GenericResult<GroupsAddCallbackServerResponse>> getConverter() {
        return CachedConverterFactory.getConverter(GenericResult.class, GroupsAddCallbackServerResponse.class);
    }

    public Integer getGroupId() {
        return groupId;
    }

    public GroupsAddCallbackServer setGroupId(Integer groupId) {
        this.groupId = groupId;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public GroupsAddCallbackServer setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public GroupsAddCallbackServer setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public GroupsAddCallbackServer setSecretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }
}
