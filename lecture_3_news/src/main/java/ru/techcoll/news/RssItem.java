package ru.techcoll.news;

/**
 * A channel may contain any number of &lt;item&gt;s. An item may represent a "story" - much like
 * a story in a newspaper or magazine; if so its description is a synopsis of the story,
 * and the link points to the full story. An item may also be complete in itself, if so,
 * the description contains the text (entity-encoded HTML is allowed), and the link and title
 * may be omitted. All elements of an item are optional, however at least one of title or
 * description must be present.
 */
public class RssItem {

    private String title;

    private String link;

    private String description;

    private String pubDate;

    private String guid;

    /**
     * The title of the item.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @see RssItem#getTitle()
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * The URL of the item.
     */
    public String getLink() {
        return link;
    }

    /**
     * @see RssItem#getLink()
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * The item synopsis.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @see RssItem#getDescription()
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Indicates when the item was published.
     */
    public String getPubDate() {
        return pubDate;
    }

    /**
     * @see RssItem#getPubDate()
     */
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    /**
     * A string that uniquely identifies the item.
     */
    public String getGuid() {
        return guid;
    }

    /**
     * @see RssItem#getGuid()
     */
    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Override
    public String toString() {
        return String.format("[Item] \"%s\"", title);
    }

}
