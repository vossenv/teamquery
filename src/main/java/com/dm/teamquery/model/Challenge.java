package com.dm.teamquery.model;


import com.dm.teamquery.config.LocalDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.hibernate.annotations.Type;
import lombok.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "challenge")
@EqualsAndHashCode
@Getter @Setter
public class Challenge {

    @Id
    @Type(type="uuid-char")
    @Column(name = "challengeid")
    private UUID challengeId;

    @Column(name = "question", columnDefinition = "TEXT")
    private String question;

    @Column(name = "answer", columnDefinition = "TEXT")
    private String answer;

    @Column(name = "author")
    private String author;

    @Column(name = "lastauthor")
    private String lastAuthor;

    @Column(name = "enabled")
    private Boolean enabled;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "datecreated")
    private LocalDateTime dateCreated;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "datelastmodified")
    private LocalDateTime dateLastModified;
}
