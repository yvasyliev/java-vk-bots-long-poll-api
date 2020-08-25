package api.longpoll.bots.methods.groups;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.converters.GenericConverterFactory;
import api.longpoll.bots.converters.JsonToPojoConverter;
import api.longpoll.bots.methods.GetMethod;
import api.longpoll.bots.methods.VkApi;
import api.longpoll.bots.model.response.groups.GroupsGetCallbackServersResult;
import org.jsoup.Connection;

import java.util.List;
import java.util.stream.Stream;

public class GroupsGetCallbackServers extends GetMethod<GroupsGetCallbackServersResult> {
    private Integer groupId;
    private List<Integer> serverIds;

    public GroupsGetCallbackServers(LongPollBot bot) {
        super(bot);
    }

    @Override
    protected String getApi() {
        return VkApi.getInstance().groupsGetCallbackServers();
    }

    @Override
    protected JsonToPojoConverter<GroupsGetCallbackServersResult> getConverter() {
        return GenericConverterFactory.get(GroupsGetCallbackServersResult.class);
    }

    @Override
    protected Stream<Connection.KeyVal> getKeyValStream() {
        return Stream.of(
                keyVal("group_id", groupId),
                keyVal("server_ids", serverIds)
        );
    }

    public Integer getGroupId() {
        return groupId;
    }

    public GroupsGetCallbackServers setGroupId(Integer groupId) {
        this.groupId = groupId;
        return this;
    }

    public List<Integer> getServerIds() {
        return serverIds;
    }

    public GroupsGetCallbackServers setServerIds(List<Integer> serverIds) {
        this.serverIds = serverIds;
        return this;
    }
}