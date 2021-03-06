package parse.response.wall;

import api.longpoll.bots.converters.response.events.GetEventsResultConverter;
import api.longpoll.bots.model.events.Event;
import api.longpoll.bots.model.events.EventObject;
import api.longpoll.bots.model.events.EventType;
import api.longpoll.bots.model.objects.basic.WallComment;
import api.longpoll.bots.model.response.events.GetEventsResult;
import api.longpoll.bots.model.events.wall.comments.WallReplyEvent;
import com.google.gson.JsonObject;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import parse.response.AbstractParseTest;

import java.io.IOException;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WallCommentRestoreParseTest extends AbstractParseTest {
    @Test
    public void test1_wallReplyRestore() throws IOException {
        JsonObject jsonObject = readJson("json/response/wall_reply_restore/wall_reply_restore_sample_5_110.json");
        GetEventsResult getEventsResult = new GetEventsResultConverter().convert(jsonObject);
        Assert.assertNotNull(getEventsResult);
        Assert.assertEquals(Integer.valueOf(2634), getEventsResult.getTs());

        List<Event> events = getEventsResult.getEvents();
        Assert.assertNotNull(events);
        Assert.assertFalse(events.isEmpty());

        Event event = events.get(0);
        Assert.assertNotNull(event);
        Assert.assertEquals(EventType.WALL_REPLY_RESTORE, event.getType());
        Assert.assertEquals(Integer.valueOf(444), event.getGroupId());
        Assert.assertEquals("aaa", event.getEventId());

        EventObject eventObject = event.getObject();
        Assert.assertTrue(eventObject instanceof WallReplyEvent);

        WallReplyEvent wallReplyUpdate = (WallReplyEvent) eventObject;
        Assert.assertEquals(Integer.valueOf(4), wallReplyUpdate.getId());
        Assert.assertEquals(Integer.valueOf(111), wallReplyUpdate.getFromId());
        Assert.assertEquals(Integer.valueOf(3), wallReplyUpdate.getPostId());
        Assert.assertEquals(Integer.valueOf(-222), wallReplyUpdate.getOwnerId());
        Assert.assertEquals(Integer.valueOf(1594972082), wallReplyUpdate.getDate());
        Assert.assertEquals(Integer.valueOf(-333), wallReplyUpdate.getPostOwnerId());
        Assert.assertEquals("test1", wallReplyUpdate.getText());

        WallComment.Thread thread = wallReplyUpdate.getThread();
        Assert.assertNotNull(thread);
        Assert.assertEquals(Integer.valueOf(0), thread.getCount());
    }
}
