package ru.techcoll.news.rss;

import ru.techcoll.news.serial.SerialNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The RSS &lt;channel&gt; element describes the RSS feed. The &lt;channel&gt; element usually
 * contains one or more &lt;item&gt; elements. Each &lt;item&gt; element defines an article or
 * "story" in the RSS feed.
 * @see <a href="https://validator.w3.org/feed/docs/rss2.html">The RSS specification</a>
 */
@SerialNode("/rss/channel")
public class RssChannel {

    private String title;

    private String link;

    private String description;

    private String imageUrl;

    private String lastBuildDate;

    private List<RssItem> items = new ArrayList<>();

    /**
     * The name of the channel. It's how people refer to the service.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @see RssChannel#getTitle()
     */
    @SerialNode("title")
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * The URL to the HTML website corresponding to the channel.
     */
    public String getLink() {
        return link;
    }

    /**
     * @see RssChannel#getLink()
     */
    @SerialNode("link")
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * Phrase or sentence describing the channel.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @see RssChannel#getDescription()
     */
    @SerialNode("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Specifies a GIF, JPEG or PNG image that can be displayed with the channel.
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * @see RssChannel#getImageUrl()
     */
    @SerialNode("image/url")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * The last time the content of the channel changed.
     */
    public String getLastBuildDate() {
        return lastBuildDate;
    }

    /**
     * @see RssChannel#getLastBuildDate()
     */
    @SerialNode("lastBuildDate")
    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    /**
     * Items in this channel.
     */
    public List<RssItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addToItems(RssItem rssItem) {
        items.add(rssItem);
    }

    @Override
    public String toString() {
        return String.format("[Channel] \"%s\"", title);
    }

}
