package api.longpoll.bots.methods.groups;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.converters.JsonToPojoConverter;
import api.longpoll.bots.converters.response.groups.GroupsIsMemberResultConverter;
import api.longpoll.bots.methods.GetMethod;
import api.longpoll.bots.methods.VkApi;
import api.longpoll.bots.model.response.GenericResult;
import org.jsoup.Connection;

import java.util.List;
import java.util.stream.Stream;

/**
 * Implements <b>groups.isMember</b> method.
 *
 * @see <a href="https://vk.com/dev/groups.isMember">https://vk.com/dev/groups.isMember</a>
 */
public class GroupsIsMember extends GetMethod<GenericResult<Object>> {
    private static final JsonToPojoConverter<GenericResult<Object>> GROUPS_IS_MEMBER_RESULT_CONVERTER = new GroupsIsMemberResultConverter();

    /**
     * ID or screen name of the community.
     */
    private String groupId;

    /**
     * User ID.
     */
    private Integer userId;

    /**
     * User IDs.
     */
    private List<Integer> userIds;

    /**
     * <b>true</b> to return an extended response with additional fields.
     */
    private Boolean extended;

    public GroupsIsMember(LongPollBot bot) {
        super(bot);
    }

    @Override
    protected String getApi() {
        return VkApi.getInstance().groupsIsMember();
    }

    @Override
    protected JsonToPojoConverter<GenericResult<Object>> getConverter() {
        return GROUPS_IS_MEMBER_RESULT_CONVERTER;
    }

    @Override
    protected Stream<Connection.KeyVal> getKeyValStream() {
        return Stream.of(
                keyVal("group_id", groupId),
                keyVal("user_id", userId),
                keyVal("user_ids", userIds),
                keyVal("extended", extended, true)
        );
    }

    public String getGroupId() {
        return groupId;
    }

    public GroupsIsMember setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public GroupsIsMember setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public List<Integer> getUserIds() {
        return userIds;
    }

    public GroupsIsMember setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
        return this;
    }

    public Boolean getExtended() {
        return extended;
    }

    public GroupsIsMember setExtended(Boolean extended) {
        this.extended = extended;
        return this;
    }
}
