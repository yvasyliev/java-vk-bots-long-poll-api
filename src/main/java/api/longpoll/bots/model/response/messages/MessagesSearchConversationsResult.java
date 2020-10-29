package api.longpoll.bots.model.response.messages;

import api.longpoll.bots.model.objects.additional.VkList;
import api.longpoll.bots.model.objects.basic.Community;
import api.longpoll.bots.model.objects.basic.Conversation;
import api.longpoll.bots.model.objects.basic.User;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Response to <b>messages.searchConversations</b> request.
 */
public class MessagesSearchConversationsResult {
    /**
     * Response object.
     */
    @SerializedName("response")
    private Response response;

    /**
     * Describes response.
     */
    public static class Response extends VkList<Conversation> {
        /**
         * List of users.
         */
        @SerializedName("profiles")
        private List<User> profiles;

        /**
         * List of communities.
         */
        @SerializedName("groups")
        private List<Community> groups;

        public List<User> getProfiles() {
            return profiles;
        }

        public void setProfiles(List<User> profiles) {
            this.profiles = profiles;
        }

        public List<Community> getGroups() {
            return groups;
        }

        public void setGroups(List<Community> groups) {
            this.groups = groups;
        }
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}