package parse.response.message;

import api.longpoll.bots.constants.DocumentTypes;
import api.longpoll.bots.converters.response.events.GetEventsResultConverter;
import api.longpoll.bots.model.events.EventType;
import api.longpoll.bots.model.events.messages.MessageNewEvent;
import api.longpoll.bots.model.objects.additional.Image;
import api.longpoll.bots.model.objects.media.*;
import api.longpoll.bots.model.events.Event;
import api.longpoll.bots.model.events.EventObject;
import api.longpoll.bots.model.objects.additional.ClientInfo;
import api.longpoll.bots.model.objects.additional.Geo;
import api.longpoll.bots.model.objects.basic.Message;
import api.longpoll.bots.model.objects.additional.PhotoSize;
import api.longpoll.bots.model.response.events.GetEventsResult;

import api.longpoll.bots.model.objects.basic.WallPost;
import api.longpoll.bots.model.objects.basic.WallComment;
import com.google.gson.JsonObject;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import parse.response.AbstractParseTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MessageNewParseTest extends AbstractParseTest {
    @Test
    public void test1_messageNewText() throws IOException {
        JsonObject jsonObject = readJson("json/response/message_new/message_new_text_sample_5_110.json");
        GetEventsResult getEventsResult = new GetEventsResultConverter().convert(jsonObject);
        Assert.assertNotNull(getEventsResult);
        Assert.assertEquals(Integer.valueOf(2588), getEventsResult.getTs());

        List<Event> events = getEventsResult.getEvents();
        Assert.assertNotNull(events);
        Assert.assertEquals(1, events.size());

        Event event = events.get(0);
        Assert.assertNotNull(event);
        Assert.assertEquals(EventType.MESSAGE_NEW, event.getType());
        Assert.assertEquals(Integer.valueOf(333), event.getGroupId());
        Assert.assertEquals("aaa", event.getEventId());

        EventObject eventObject = event.getObject();
        Assert.assertNotNull(eventObject);

        Assert.assertTrue(eventObject instanceof MessageNewEvent);
        MessageNewEvent messageUpdate = (MessageNewEvent) eventObject;
        Assert.assertNotNull(messageUpdate);

        Message message = messageUpdate.getMessage();
        Assert.assertNotNull(message);
        Assert.assertEquals(Integer.valueOf(1593092311), message.getDate());
        Assert.assertEquals(Integer.valueOf(111), message.getFromId());
        Assert.assertEquals(Integer.valueOf(0), message.getId());
        Assert.assertEquals(Integer.valueOf(222), message.getPeerId());
        Assert.assertEquals("test", message.getText());
        Assert.assertEquals(Integer.valueOf(4392), message.getConversationMessageId());
        Assert.assertFalse(message.getImportant());
        Assert.assertEquals(Integer.valueOf(0), message.getRandomId());

        ClientInfo clientInfo = messageUpdate.getClientInfo();
        Assert.assertNotNull(clientInfo);
        Assert.assertTrue(clientInfo.isKeyboardAllowed());
        Assert.assertTrue(clientInfo.isInlineKeyboardAllowed());
        Assert.assertEquals(Integer.valueOf(0), clientInfo.getLangId());

        List<String> expected = Arrays.asList(
                "text",
                "vkpay",
                "open_app",
                "location",
                "open_link"
        );
        List<String> buttonActions = clientInfo.getButtonActions();
        Assert.assertEquals(expected, buttonActions);
    }

    @Test
    public void test2_messageNewReply() throws IOException {
        JsonObject jsonObject = readJson("json/response/message_new/message_new_reply_sample_5_110.json");
        GetEventsResult getEventsResult = new GetEventsResultConverter().convert(jsonObject);
        Message replyMessage = ((MessageNewEvent) getEventsResult.getEvents().get(0).getObject())
                .getMessage()
                .getReplyMessage();
        Assert.assertNotNull(replyMessage);
        Assert.assertEquals(Integer.valueOf(1593092313), replyMessage.getDate());
        Assert.assertEquals(Integer.valueOf(333), replyMessage.getFromId());
        Assert.assertEquals("test", replyMessage.getText());
        Assert.assertEquals(Integer.valueOf(4392), replyMessage.getConversationMessageId());
        Assert.assertEquals(Integer.valueOf(444), replyMessage.getPeerId());
        Assert.assertEquals(Integer.valueOf(555), replyMessage.getId());
    }

    @Test
    public void test3_messageNewFwd() throws IOException {
        JsonObject jsonObject = readJson("json/response/message_new/message_new_fwd_sample_5_110.json");
        List<Message> fwdMessages = ((MessageNewEvent) new GetEventsResultConverter().convert(jsonObject).getEvents().get(0).getObject()).getMessage().getFwdMessages();
        Assert.assertNotNull(fwdMessages);
        Assert.assertEquals(1, fwdMessages.size());

        Message message = fwdMessages.get(0);
        Assert.assertNotNull(message);
        Assert.assertEquals(Integer.valueOf(1590523631), message.getDate());
        Assert.assertEquals(Integer.valueOf(333), message.getFromId());
        Assert.assertEquals("testFwd", message.getText());
        Assert.assertEquals(Integer.valueOf(102248), message.getConversationMessageId());
        Assert.assertEquals(Integer.valueOf(444), message.getPeerId());
        Assert.assertEquals(Integer.valueOf(555), message.getId());
    }

    @Test
    public void test4_messageNewPhoto() throws IOException {
        JsonObject jsonObject = readJson("json/response/message_new/message_new_photo_sample_5_110.json");
        List<Attachment> attachments = ((MessageNewEvent) new GetEventsResultConverter().convert(jsonObject).getEvents().get(0).getObject()).getMessage().getAttachments();
        Assert.assertNotNull(attachments);
        Assert.assertEquals(1, attachments.size());

        Attachment attachment = attachments.get(0);
        Assert.assertNotNull(attachment);
        Assert.assertEquals(AttachmentType.PHOTO, attachment.getType());

        Attachable attachable = attachment.getAttachable();
        Assert.assertNotNull(attachable);
        Photo photo = (Photo) attachable;
        Assert.assertEquals(Integer.valueOf(-3), photo.getAlbumId());
        Assert.assertEquals(Integer.valueOf(1593095192), photo.getDate());
        Assert.assertEquals(Integer.valueOf(333), photo.getId());
        Assert.assertEquals(Integer.valueOf(444), photo.getOwnerId());

        List<PhotoSize> photoSizes = photo.getPhotoSizes();
        Assert.assertNotNull(photoSizes);
        Assert.assertFalse(photoSizes.isEmpty());

        PhotoSize photoSize = photoSizes.get(0);
        Assert.assertNotNull(photoSize);
        Assert.assertEquals(Integer.valueOf(130), photoSize.getHeight());
        Assert.assertEquals(Integer.valueOf(130), photoSize.getWidth());
        Assert.assertEquals("m", photoSize.getType());
        Assert.assertEquals("https://sun9-49.userapi.com/m0bXxRbjkI0X2SAClsqAZsRYVpiSgc6MEBAVtA/2VVGupYl8uM.jpg", photoSize.getUrl());
    }

    @Test
    public void test5_messageNewVideo() throws IOException {
        JsonObject jsonObject = readJson("json/response/message_new/message_new_video_sample_5_110.json");
        List<Attachment> attachments = ((MessageNewEvent) new GetEventsResultConverter().convert(jsonObject).getEvents().get(0).getObject()).getMessage().getAttachments();
        Assert.assertNotNull(attachments);
        Assert.assertEquals(1, attachments.size());

        Attachment attachment = attachments.get(0);
        Assert.assertNotNull(attachment);
        Assert.assertEquals(AttachmentType.VIDEO, attachment.getType());

        Video video = (Video) attachment.getAttachable();
        Assert.assertNotNull(video);
        Assert.assertEquals("aaa", video.getAccessKey());
        Assert.assertTrue(video.getCanAdd());
        Assert.assertEquals(Integer.valueOf(0), video.getCommentsAmount());
        Assert.assertEquals(Integer.valueOf(1593106800), video.getDate());
        Assert.assertFalse(video.getDescription().isEmpty());
        Assert.assertEquals(Integer.valueOf(360), video.getDuration());
        Assert.assertEquals(Integer.valueOf(444), video.getId());
        Assert.assertEquals(Integer.valueOf(555), video.getOwnerId());
        Assert.assertEquals("20 MOST POPULAR TUNES EVER", video.getTitle());
        Assert.assertFalse(video.getTrackCode().isEmpty());
        Assert.assertEquals("video", video.getType());
        Assert.assertEquals(Integer.valueOf(1), video.getViews());
        Assert.assertEquals(Integer.valueOf(0), video.getLocalViews());
        Assert.assertEquals("YouTube", video.getPlatform());

        List<Video.VideoImage> images = video.getImage();
        Assert.assertNotNull(images);
        Assert.assertFalse(images.isEmpty());

        Video.VideoImage image = images.get(0);
        Assert.assertNotNull(image);
        Assert.assertEquals(Integer.valueOf(96), image.getHeight());
        Assert.assertEquals(Integer.valueOf(130), image.getWidth());
        Assert.assertFalse(image.getUrl().isEmpty());
        Assert.assertTrue(image.getWithPadding());
    }

    @Test
    public void test6_messageNewAudio() throws IOException {
        JsonObject jsonObject = readJson("json/response/message_new/message_new_audio_sample_5_110.json");
        List<Attachment> attachments = ((MessageNewEvent) new GetEventsResultConverter().convert(jsonObject).getEvents().get(0).getObject()).getMessage().getAttachments();
        Assert.assertNotNull(attachments);
        Assert.assertEquals(1, attachments.size());

        Attachment attachment = attachments.get(0);
        Assert.assertNotNull(attachment);
        Assert.assertEquals(AttachmentType.AUDIO, attachment.getType());

        Audio audio = (Audio) attachment.getAttachable();
        Assert.assertEquals("Linkin Park", audio.getArtist());
        Assert.assertEquals(Integer.valueOf(333), audio.getId());
        Assert.assertEquals(Integer.valueOf(444), audio.getOwnerId());
        Assert.assertEquals("In the End", audio.getTitle());
        Assert.assertEquals(Integer.valueOf(219), audio.getDuration());
        Assert.assertEquals(Integer.valueOf(1490105766), audio.getDate());
    }

    @Test
    public void test7_messageNewDocPhoto() throws IOException {
        JsonObject jsonObject = readJson("json/response/message_new/message_new_doc_photo_sample_5_110.json");
        List<Attachment> attachments = ((MessageNewEvent) new GetEventsResultConverter().convert(jsonObject).getEvents().get(0).getObject()).getMessage().getAttachments();
        Assert.assertNotNull(attachments);
        Assert.assertEquals(1, attachments.size());

        Attachment attachment = attachments.get(0);
        Assert.assertNotNull(attachment);
        Assert.assertEquals(AttachmentType.DOCUMENT, attachment.getType());

        Doc doc = (Doc) attachment.getAttachable();
        Assert.assertEquals(Integer.valueOf(333), doc.getId());
        Assert.assertEquals(Integer.valueOf(444), doc.getOwnerId());
        Assert.assertEquals("x_fc4.png", doc.getTitle());
        Assert.assertEquals(Integer.valueOf(756010), doc.getSize());
        Assert.assertEquals("png", doc.getExt());
        Assert.assertEquals(Integer.valueOf(1593165675), doc.getDate());
        Assert.assertEquals(DocumentTypes.IMAGES, doc.getType());

        Map<DocType, Doc.Preview> preview = doc.getPreview();
        Assert.assertNotNull(preview);
        Assert.assertTrue(preview.containsKey(DocType.PHOTO));

        Doc.Preview photoPreview = preview.get(DocType.PHOTO);
        Assert.assertNotNull(photoPreview);
        Assert.assertTrue(photoPreview instanceof Doc.Photo);

        Doc.Photo photo = (Doc.Photo) photoPreview;

        List<Doc.Photo.Size> sizes = photo.getSizes();
        Assert.assertNotNull(sizes);
        Assert.assertFalse(sizes.isEmpty());

        Doc.Photo.Size size = sizes.get(0);
        Assert.assertNotNull(size);
        Assert.assertEquals(Integer.valueOf(130), size.getWidth());
        Assert.assertEquals(Integer.valueOf(100), size.getHeight());
        Assert.assertEquals("m", size.getType());
    }

    @Test
    public void test8_messageNewAudioMessage() throws IOException {
        JsonObject jsonObject = readJson("json/response/message_new/message_new_audio_message_sample_5_110.json");
        List<Attachment> attachments = ((MessageNewEvent) new GetEventsResultConverter().convert(jsonObject).getEvents().get(0).getObject()).getMessage().getAttachments();
        Assert.assertNotNull(attachments);
        Assert.assertEquals(1, attachments.size());

        Attachment attachment = attachments.get(0);
        Assert.assertNotNull(attachment);
        Assert.assertEquals(AttachmentType.AUDIO_MESSAGE, attachment.getType());

        AudioMessage audioMessage = (AudioMessage) attachment.getAttachable();
        Assert.assertNotNull(audioMessage);
        Assert.assertEquals(Integer.valueOf(333), audioMessage.getId());
        Assert.assertEquals(Integer.valueOf(444), audioMessage.getOwnerId());
        Assert.assertEquals(Integer.valueOf(2), audioMessage.getDuration());
        Assert.assertFalse(audioMessage.getLinkMp3().isEmpty());
        Assert.assertFalse(audioMessage.getLinkOgg().isEmpty());

        List<Integer> waveform = audioMessage.getWaveform();
        Assert.assertNotNull(waveform);
        Assert.assertFalse(waveform.isEmpty());
    }

    @Test
    public void test9_messageNewGraffiti() throws IOException {
        JsonObject jsonObject = readJson("json/response/message_new/message_new_graffiti_sample_5_110.json");
        List<Attachment> attachments = ((MessageNewEvent) new GetEventsResultConverter().convert(jsonObject).getEvents().get(0).getObject()).getMessage().getAttachments();
        Assert.assertNotNull(attachments);
        Assert.assertEquals(1, attachments.size());

        Attachment attachment = attachments.get(0);
        Assert.assertNotNull(attachment);
        Assert.assertEquals(AttachmentType.GRAFFITI, attachment.getType());

        Graffiti graffiti = (Graffiti) attachment.getAttachable();
        Assert.assertNotNull(graffiti);
        Assert.assertEquals(Integer.valueOf(333), graffiti.getId());
        Assert.assertEquals(Integer.valueOf(444), graffiti.getOwnerId());
        Assert.assertEquals(Integer.valueOf(720), graffiti.getWidth());
        Assert.assertEquals(Integer.valueOf(714), graffiti.getHeight());
    }

    @Test
    public void test10_messageNewGeo() throws IOException {
        JsonObject jsonObject = readJson("json/response/message_new/message_new_geo_sample_5_110.json");
        Geo geo = ((MessageNewEvent) new GetEventsResultConverter().convert(jsonObject).getEvents().get(0).getObject()).getMessage().getGeo();
        Assert.assertNotNull(geo);
        Assert.assertEquals("point", geo.getType());

        Geo.Coordinates coordinates = geo.getCoordinates();
        Assert.assertNotNull(coordinates);
        Assert.assertTrue(String.valueOf(coordinates.getLatitude()).startsWith("23.43"));
        Assert.assertTrue(String.valueOf(coordinates.getLongitude()).startsWith("7.03"));
    }

    @Test
    public void test11_messageNewSticker() throws IOException {
        JsonObject jsonObject = readJson("json/response/message_new/message_new_sticker_sample_5_110.json");
        List<Attachment> attachments = ((MessageNewEvent) new GetEventsResultConverter().convert(jsonObject).getEvents().get(0).getObject()).getMessage().getAttachments();
        Assert.assertNotNull(attachments);
        Assert.assertEquals(1, attachments.size());

        Attachment attachment = attachments.get(0);
        Assert.assertNotNull(attachment);
        Assert.assertEquals(AttachmentType.STICKER, attachment.getType());

        Sticker sticker = (Sticker) attachment.getAttachable();
        Assert.assertNotNull(sticker);
        Assert.assertEquals(Integer.valueOf(279), sticker.getProductId());
        Assert.assertEquals(Integer.valueOf(9012), sticker.getStickerId());

        List<Image> images = sticker.getImages();
        Assert.assertNotNull(images);
        Assert.assertFalse(images.isEmpty());

        Image image = images.get(0);
        Assert.assertNotNull(image);
        Assert.assertFalse(image.getUrl().isEmpty());
        Assert.assertEquals(Integer.valueOf(64), image.getHeight());
        Assert.assertEquals(Integer.valueOf(64), image.getWidth());

        images = sticker.getImagesWithBackground();
        Assert.assertNotNull(images);
        Assert.assertFalse(images.isEmpty());

        image = images.get(0);
        Assert.assertNotNull(image);
        Assert.assertFalse(image.getUrl().isEmpty());
        Assert.assertEquals(Integer.valueOf(64), image.getHeight());
        Assert.assertEquals(Integer.valueOf(64), image.getWidth());
    }

    @Test
    public void test12_messageNewWall() throws IOException {
        JsonObject jsonObject = readJson("json/response/message_new/message_new_wall_sample_5_110.json");
        List<Attachment> attachments = ((MessageNewEvent) new GetEventsResultConverter().convert(jsonObject).getEvents().get(0).getObject()).getMessage().getAttachments();
        Assert.assertNotNull(attachments);
        Assert.assertEquals(1, attachments.size());

        Attachment attachment = attachments.get(0);
        Assert.assertNotNull(attachment);
        Assert.assertEquals(AttachmentType.WALL_POST, attachment.getType());

        WallPost wallPost = (WallPost) attachment.getAttachable();
        Assert.assertNotNull(wallPost);
        Assert.assertEquals(Integer.valueOf(333), wallPost.getId());
        Assert.assertEquals(Integer.valueOf(-444), wallPost.getFromId());
        Assert.assertEquals(Integer.valueOf(1594228961), wallPost.getDate());
        Assert.assertEquals("post", wallPost.getPostType());
        Assert.assertFalse(wallPost.getText().isEmpty());
        Assert.assertFalse(wallPost.getMarkedAsAds());

        List<Attachment> wallAttachments = wallPost.getAttachments();
        Assert.assertNotNull(wallAttachments);
        Assert.assertFalse(wallAttachments.isEmpty());

        attachment = wallAttachments.get(0);
        Assert.assertNotNull(attachment);
        Assert.assertEquals(AttachmentType.PHOTO, attachment.getType());

        Attachable attachable = attachment.getAttachable();
        Assert.assertNotNull(attachable);
        Assert.assertTrue(attachable instanceof Photo);

        WallPost.Comments comments = wallPost.getComments();
        Assert.assertNotNull(comments);
        Assert.assertEquals(Integer.valueOf(7), comments.getCount());

        WallPost.Likes wallPostLikes = wallPost.getLikes();
        Assert.assertNotNull(wallPostLikes);
        Assert.assertEquals(Integer.valueOf(98), wallPostLikes.getCount());

        WallPost.Views views = wallPost.getViews();
        Assert.assertNotNull(views);
        Assert.assertEquals(Integer.valueOf(2597), views.getCount());

        WallPost.Reposts reposts = wallPost.getReposts();
        Assert.assertNotNull(reposts);
        Assert.assertEquals(Integer.valueOf(0), reposts.getCount());
    }

    @Test
    public void test13_messageNewWallReply() throws IOException {
        JsonObject jsonObject = readJson("json/response/message_new/message_new_wall_reply_sample_5_110.json");
        List<Attachment> attachments = ((MessageNewEvent) new GetEventsResultConverter().convert(jsonObject).getEvents().get(0).getObject()).getMessage().getAttachments();
        Assert.assertNotNull(attachments);
        Assert.assertEquals(1, attachments.size());

        Attachment attachment = attachments.get(0);
        Assert.assertNotNull(attachment);
        Assert.assertEquals(AttachmentType.WALL_REPLY, attachment.getType());

        WallComment wallReply = (WallComment) attachment.getAttachable();
        Assert.assertNotNull(wallReply);
        Assert.assertEquals(Integer.valueOf(333), wallReply.getId());
        Assert.assertEquals(Integer.valueOf(444), wallReply.getFromId());
        Assert.assertEquals(Integer.valueOf(555), wallReply.getPostId());
        Assert.assertEquals(Integer.valueOf(-666), wallReply.getOwnerId());
        Assert.assertEquals(Integer.valueOf(1594231450), wallReply.getDate());
        Assert.assertFalse(wallReply.getText().isEmpty());
        Assert.assertEquals(Integer.valueOf(888), wallReply.getReplyToUser());
        Assert.assertEquals(Integer.valueOf(999), wallReply.getReplyToComment());

        List<Integer> parentsStack = wallReply.getParentsStack();
        Assert.assertNotNull(parentsStack);
        Assert.assertEquals(1, parentsStack.size());

        Integer integer = parentsStack.get(0);
        Assert.assertNotNull(integer);
        Assert.assertEquals(Integer.valueOf(777), integer);

        WallComment.Likes likes = wallReply.getLikes();
        Assert.assertNotNull(likes);
        Assert.assertEquals(Integer.valueOf(1), likes.getCount());
        Assert.assertFalse(likes.getUserLikes());
        Assert.assertTrue(likes.getCanLike());
    }

    @Test
    public void test14_messageNewDocNoPreview() throws IOException {
        JsonObject jsonObject = readJson("json/response/message_new/message_new_doc_no_preview_sample_5_118.json");
        List<Attachment> attachments = ((MessageNewEvent) new GetEventsResultConverter().convert(jsonObject).getEvents().get(0).getObject()).getMessage().getAttachments();
        Assert.assertNotNull(attachments);
        Assert.assertEquals(1, attachments.size());

        Attachment attachment = attachments.get(0);
        Assert.assertNotNull(attachment);
        Assert.assertEquals(AttachmentType.DOCUMENT, attachment.getType());

        Doc doc = (Doc) attachment.getAttachable();
        Assert.assertNotNull(doc);
        Assert.assertEquals(Integer.valueOf(456), doc.getId());
        Assert.assertEquals(Integer.valueOf(789), doc.getOwnerId());
        Assert.assertEquals("canvas.rar", doc.getTitle());
        Assert.assertEquals(Integer.valueOf(2325), doc.getSize());
        Assert.assertEquals("rar", doc.getExt());
        Assert.assertEquals(Integer.valueOf(1559985418), doc.getDate());
        Assert.assertEquals(Integer.valueOf(2), doc.getType());
        Assert.assertEquals("https://vk.com/doc1234", doc.getUrl());
        Assert.assertEquals("5678", doc.getAccessKey());
        Assert.assertNull(doc.getPreview());
    }
}
