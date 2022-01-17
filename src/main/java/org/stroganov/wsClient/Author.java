package org.stroganov.wsClient;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for author complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="author">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="authorID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="authorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "author", propOrder = {
        "authorID",
        "authorName"
})
public class Author {

    protected int authorID;
    protected String authorName;

    /**
     * Gets the value of the authorID property.
     */
    public int getAuthorID() {
        return authorID;
    }

    /**
     * Sets the value of the authorID property.
     */
    public void setAuthorID(int value) {
        this.authorID = value;
    }

    /**
     * Gets the value of the authorName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * Sets the value of the authorName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAuthorName(String value) {
        this.authorName = value;
    }

}
