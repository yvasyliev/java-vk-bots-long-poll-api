package api.longpoll.bots.methods.groups;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.converters.CachedConverterFactory;
import api.longpoll.bots.converters.JsonToPojoConverter;
import api.longpoll.bots.methods.GetMethod;
import api.longpoll.bots.methods.VkApi;
import api.longpoll.bots.model.objects.additional.VkList;
import api.longpoll.bots.model.response.GenericResult;
import api.longpoll.bots.model.response.groups.GroupsGetBannedResponseItem;
import org.jsoup.Connection;

import java.util.List;
import java.util.stream.Stream;

/**
 * Implements <b>groups.getBanned</b> method.
 *
 * @see <a href="https://vk.com/dev/groups.getBanned">https://vk.com/dev/groups.getBanned</a>
 */
public class GroupsGetBanned extends GetMethod<GenericResult<VkList<GroupsGetBannedResponseItem>>> {
    /**
     * Community ID.
     */
    private Integer groupId;

    /**
     * Offset needed to return a specific subset of users.
     */
    private Integer offset;

    /**
     * Number of users to return.
     */
    private Integer count;

    /**
     * List of fields of groups and profiles to return.
     */
    private List<String> fields;

    /**
     * User or community ID from the blacklist to return information about.
     */
    private Integer ownerId;

    public GroupsGetBanned(LongPollBot bot) {
        super(bot);
    }

    @Override
    protected String getApi() {
        return VkApi.getInstance().groupsGetBanned();
    }

    @Override
    protected JsonToPojoConverter<GenericResult<VkList<GroupsGetBannedResponseItem>>> getConverter() {
        return CachedConverterFactory.getConverter(GenericResult.class, VkList.class, GroupsGetBannedResponseItem.class);
    }

    @Override
    protected Stream<Connection.KeyVal> getKeyValStream() {
        return Stream.of(
                keyVal("group_id", groupId),
                keyVal("offset", offset),
                keyVal("count", count),
                keyVal("fields", fields),
                keyVal("owner_id", ownerId)
        );
    }

    public Integer getGroupId() {
        return groupId;
    }

    public GroupsGetBanned setGroupId(Integer groupId) {
        this.groupId = groupId;
        return this;
    }

    public Integer getOffset() {
        return offset;
    }

    public GroupsGetBanned setOffset(Integer offset) {
        this.offset = offset;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public GroupsGetBanned setCount(Integer count) {
        this.count = count;
        return this;
    }

    public List<String> getFields() {
        return fields;
    }

    public GroupsGetBanned setFields(List<String> fields) {
        this.fields = fields;
        return this;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public GroupsGetBanned setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
        return this;
    }
}
